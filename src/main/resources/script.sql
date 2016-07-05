
/* senha crypto = 123456 */


create table TB_USUARIO  (id bigint generated by default as identity, email varchar(50) not null, name varchar(100) not null, password varchar(32) not null, primary key (id));
create table TB_USUARIO_ROLES  (id bigint generated by default as identity, name varchar(255), USER_ID bigint, primary key (id));
alter table TB_USUARIO_ROLES  add constraint fk_user foreign key (USER_ID) references TB_USUARIO; 

insert into TB_USUARIO (ID, EMAIL, NAME, PASSWORD) values (1 , 'admin@sistema.com', 'Administrador do sistema', 'e10adc3949ba59abbe56e057f20f883e');
insert into TB_USUARIO_ROLES (ID, NAME, USER_ID) values (1, 'ROLE_ADMIN_MANTER_USUARIOS', 1 );