-- 注意：如果要修改表名，请同步修改类 com.particle.oauth2authorization.infrastructure.client.service.impl.SecurityAuthorizationRegisteredClientRepositoryOauth2RegisteredClientServiceListener.table_name
DROP TABLE IF EXISTS oauth2_registered_client;
CREATE TABLE oauth2_registered_client (
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(200) DEFAULT NULL,
    client_secret_expires_at timestamp DEFAULT NULL,
    client_name varchar(200) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(1000) NOT NULL,
    redirect_uris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);
-- 表名不能添加 global_ 前缀，因为在程序中没有自定义修改表名的入口，且声名了 final 字符串，也不能通过反射修改表名
-- 改一下表名 位置在：org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql
