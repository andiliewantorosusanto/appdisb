GO
DROP TABLE [AD_USER]
DROP TABLE [AD_ROLE]
GO
CREATE TABLE [dbo].[AD_USER](
	[id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[name] [varchar](255) NOT NULL,
	[username] [varchar](20) NOT NULL,
	[level] [int] NOT NULL,
	[role_id] [bigint] NULL,
	[limit_id] [bigint] NULL,
	[branch_id] [varchar](20) NULL

)
GO
CREATE TABLE [dbo].[AD_ROLE](
	[id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[name] [varchar](255) NOT NULL,
)
GO
CREATE TABLE [dbo].[AD_LIMIT] (
	[id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[name] [varchar](255) NOT NULL,
	[limit] [money] NOT NULL
)
GO
CREATE TABLE [dbo].[AD_GROUP] (
	[id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[name] [varchar](255) NOT NULL
)
GO
CREATE TABLE [dbo].[AD_MENU] (
	[id] [bigint] IDENTITY(1,1) PRIMARY KEY,
	[name] [varchar](255) NOT NULL
)
GO
CREATE TABLE [dbo].[AD_GROUP_MENU] (
	[group_id] [bigint] FOREIGN KEY REFERENCES AD_GROUP(id),
	[menu_id] [bigint] FOREIGN KEY REFERENCES AD_MENU(id),
	PRIMARY KEY(group_id,menu_id)
)
