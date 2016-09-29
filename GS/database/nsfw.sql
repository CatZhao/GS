/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/9/27 11:10:17                           */
/*==============================================================*/


drop table if exists info;

drop table if exists privilege;

drop table if exists role;

drop table if exists user;

drop table if exists user_role;

/*==============================================================*/
/* Table: info                                                  */
/*==============================================================*/
create table info
(
   infoId               varchar(32) not null,
   type                 varchar(20) not null,
   source               varchar(50),
   title                varchar(100) not null,
   content              text,
   memo                 varchar(200),
   creater              varchar(10) not null,
   create_time          datetime not null,
   state                varchar(1) not null,
   primary key (infoId)
);

/*==============================================================*/
/* Table: privilege                                             */
/*==============================================================*/
create table privilege
(
   roleId               varchar(32) not null,
   code                 varchar(10) not null,
   primary key (roleId, code)
);

alter table privilege comment 'nsfw 纳税服务
wdkj 我的空间
zxxx 在线学习
hqfw 后勤服务
                              -';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   roleId               varchar(32) not null,
   name                 varchar(10) not null,
   state                varchar(1) not null,
   primary key (roleId)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   userId               varchar(32) not null,
   dept                 varchar(10),
   name                 varchar(20),
   account              varchar(20) not null,
   password             varchar(20) not null,
   img                  varchar(100),
   gender               varchar(1),
   state                varchar(1) not null,
   tel                  varchar(11),
   email                varchar(50),
   birthday             date,
   memo                 varchar(200),
   primary key (userId)
);

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   userId               varchar(32) not null,
   roleId               varchar(32) not null,
   primary key (userId, roleId)
);

alter table privilege add constraint FK_role_privilege foreign key (roleId)
      references role (roleId) on delete restrict on update restrict;

alter table user_role add constraint FK_拥有 foreign key (userId)
      references user (userId) on delete restrict on update restrict;

alter table user_role add constraint FK_拥有2 foreign key (roleId)
      references role (roleId) on delete restrict on update restrict;

