package com.bcafinance.appdisb.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bcafinance.appdisb.http.Response;
import com.bcafinance.appdisb.http.auth.AuthResponse;
import com.bcafinance.appdisb.http.auth.LoginRequest;
import com.bcafinance.appdisb.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public boolean login(Response<AuthResponse> baseResponseAPI, LoginRequest body) {
      if (body.getUserid().isEmpty() || body.getPassword().isEmpty()) {
        baseResponseAPI.setErrorCode(ErrorCodeAPI.BODY_NOT_VALID);
        baseResponseAPI.setErrorMessage(ErrorMessageAPI.BODY_NOT_VALID);
        return false;
      }
      try {
        Map<String, Object> requestLdap = new HashMap<>();
        Map<String, Object> credentialsBody = new HashMap<>();
        credentialsBody.put("UserId", body.getUserid());
        credentialsBody.put("UserName", null);
        credentialsBody.put("Password", body.getPassword());
        requestLdap.put("TrxId", UUID.randomUUID().toString());
        requestLdap.put("Credentials", credentialsBody);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(ldapProperties.getUsernameAuth(), ldapProperties.getPasswordAuth());
        
        HttpEntity<String> httpEntity = new HttpEntity<>(helper.getObjectMapper().writeValueAsString(requestLdap), headers);
        ResponseEntity<String> responseMobileAID = helper.getRestTemplate().exchange(ldapProperties.getAuthenticateUser(), HttpMethod.POST, httpEntity, String.class);
        System.out.println(responseMobileAID.getBody());
        if (!responseMobileAID.hasBody()) {
          return false;
        }
        Map<String,Object> result =
          helper.getObjectMapper().readValue(responseMobileAID.getBody(), HashMap.class);
        
        Optional<User> userOptional = userRepository.findById(body.getUserid());
        if (!userOptional.isPresent()) {
          return false;
        }
        User user = userOptional.get();
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(body.getUserid(), user.getNomorHandphone()));
    
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
  
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
  
        List<GroupResponse> groups = new ArrayList<>();
        for (GroupUser groupUser : user.getGroupUsers()) {
          GroupResponse group = modelMapper.map(groupUser.getGroup(), GroupResponse.class);
          List<MenuResponse> menus = groupUser.getGroup().getGroupMenus().stream().map(a -> modelMapper.map(a, MenuResponse.class)).collect(Collectors.toList());
          group.setMenus(menus);
          groups.add(group);
        }
        AuthResponse authResponse = new AuthResponse();
        authResponse.setExpires(expireTime);
        authResponse.setToken(jwt);
        authResponse.setUserid(userDetails.getUsername());
        authResponse.setMenus(groups);
        baseResponseAPI.setData(authResponse);
      } catch (HttpStatusCodeException e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        System.out.println(e.getResponseBodyAsString());
        if (e.getResponseBodyAsString().contains(body.getUserid())) {
          baseResponseAPI.setErrorCode(ErrorCodeAPI.FAILED_LOGIN);
          baseResponseAPI.setErrorMessage(ErrorMessageAPI.FAILED_LOGIN);
          return false;
        }
        baseResponseAPI.setErrorCode(ErrorCodeAPI.CANNOT_ACCESS_SERVICE);
        baseResponseAPI.setErrorMessage(ErrorMessageAPI.CANNOT_ACCESS_SERVICE);
        return false;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
      }
      return true;
    }
}
