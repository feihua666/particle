
-- 注意，必须以 _attributes 结尾并且是大写，参见：org.springframework.session.jdbc.jdbcindexedsessionrepository 类注释，大根在90行左右
-- 还有一办法就是处理一下sql参见：com.particle.global.session.sessionrepositoryconfiguration.jdbcsessionrepositycustomizer.customize
drop table if exists global_spring_session_attributes;
drop table if exists global_spring_session;

create table global_spring_session (
	primary_id char(36) not null,
	session_id char(36) not null,
	creation_time bigint not null,
	last_access_time bigint not null,
	max_inactive_interval int not null,
	expiry_time bigint not null,
	principal_name varchar(100),
	constraint spring_session_pk primary key (primary_id)
) engine=innodb row_format=dynamic;

create unique index spring_session_ix1 on global_spring_session (session_id);
create index spring_session_ix2 on global_spring_session (expiry_time);
create index spring_session_ix3 on global_spring_session (principal_name);


create table global_spring_session_attributes (
	session_primary_id char(36) not null,
	attribute_name varchar(200) not null,
	attribute_bytes longblob not null,
	constraint spring_session_attributes_pk primary key (session_primary_id, attribute_name),
	constraint spring_session_attributes_fk foreign key (session_primary_id) references global_spring_session(primary_id) on delete cascade
) engine=innodb row_format=dynamic;