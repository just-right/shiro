-- 数据库 mybatis
-- 初始用户 luffy1/password  luffy2/password

-- 用户:luffy1 拥有角色1->role:user:manage
-- 用户:luffy2 拥有角色2->role:article:manage
-- 角色1 拥有权限1,2 permission:user:manage:menu/permission:user:manage:del
-- 角色2 拥有权限3   permission:article:manage:menu

-- 用户表
create table user
(
    uid      int auto_increment
        primary key,
    name     varchar(20)  null,
    password varchar(100) null,
    salt     varchar(100) null,
    constraint user_name_uindex
        unique (name)
);
-- 初始数据
INSERT INTO mybatis.user (uid, name, password, salt) VALUES (1, 'luffy1', 'e2af9e2f4451f7da6321752152316c87', '706fc22e-84f1-4d24-ae49-a7ea2b748bf2');
INSERT INTO mybatis.user (uid, name, password, salt) VALUES (2, 'luffy2', '75c59f5fed25abd1269ed3e6fcd54aa8', 'd727f6ec-2570-4c53-a755-376dd8daf2bb');


-- 角色表
create table role
(
    rid    int auto_increment
        primary key,
    rname  varchar(20) null,
    rdesc  varchar(30) null,
    rvalue varchar(30) null,
    constraint role_rname_uindex
        unique (rname)
);

-- 初始数据
INSERT INTO mybatis.role (rid, rname, rdesc, rvalue) VALUES (1, '用户管理', '管理用户角色', 'role:user:manage');
INSERT INTO mybatis.role (rid, rname, rdesc, rvalue) VALUES (2, '文章管理', '管理文章角色', 'role:article:manage');


-- 权限表
create table permission
(
    pid    int auto_increment
        primary key,
    pname  varchar(20) null,
    ptype  int         null,
    pvalue varchar(50) null,
    constraint permission_pname_uindex
        unique (pname)
);

-- 初始数据 ptype == 1 菜单类型 ptype == 2 按钮类型
INSERT INTO mybatis.permission (pid, pname, ptype, pvalue) VALUES (1, '用户管理职责-菜单', 1, 'permission:user:manage:menu');
INSERT INTO mybatis.permission (pid, pname, ptype, pvalue) VALUES (2, '用户管理职责-删除', 2, 'permission:user:manage:del');
INSERT INTO mybatis.permission (pid, pname, ptype, pvalue) VALUES (3, '文章管理职责-菜单', 1, 'permission:article:manage:menu');
INSERT INTO mybatis.permission (pid, pname, ptype, pvalue) VALUES (4, '文章管理职责-查询', 2, 'permission:article:manage:query');


-- 用户-角色关联表
create table user_role
(
    id  int auto_increment
        primary key,
    uid int null,
    rid int null
);

-- 初始数据
INSERT INTO mybatis.user_role (id, uid, rid) VALUES (1, 1, 1);
INSERT INTO mybatis.user_role (id, uid, rid) VALUES (2, 2, 2);

-- 角色-权限表
create table role_permission
(
    id  int auto_increment
        primary key,
    rid int null,
    pid int null
);

-- 初始数据
INSERT INTO mybatis.role_permission (id, rid, pid) VALUES (1, 1, 1);
INSERT INTO mybatis.role_permission (id, rid, pid) VALUES (2, 1, 2);
INSERT INTO mybatis.role_permission (id, rid, pid) VALUES (3, 2, 3);