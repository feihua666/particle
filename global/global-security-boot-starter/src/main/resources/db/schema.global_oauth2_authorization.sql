DROP TABLE IF EXISTS oauth2_authorization;
/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
*/
CREATE TABLE oauth2_authorization (
    id varchar(100) NOT NULL,
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorization_grant_type varchar(100) NOT NULL,
    authorized_scopes varchar(1000) DEFAULT NULL,
    attributes blob DEFAULT NULL,
    state varchar(500) DEFAULT NULL,
    authorization_code_value blob DEFAULT NULL,
    authorization_code_issued_at datetime DEFAULT NULL,
    authorization_code_expires_at datetime DEFAULT NULL,
    authorization_code_metadata blob DEFAULT NULL,
    access_token_value blob DEFAULT NULL,
    access_token_issued_at datetime DEFAULT NULL,
    access_token_expires_at datetime DEFAULT NULL,
    access_token_metadata blob DEFAULT NULL,
    access_token_type varchar(100) DEFAULT NULL,
    access_token_scopes varchar(1000) DEFAULT NULL,
    oidc_id_token_value blob DEFAULT NULL,
    oidc_id_token_issued_at datetime DEFAULT NULL,
    oidc_id_token_expires_at datetime DEFAULT NULL,
    oidc_id_token_metadata blob DEFAULT NULL,
    refresh_token_value blob DEFAULT NULL,
    refresh_token_issued_at datetime DEFAULT NULL,
    refresh_token_expires_at datetime DEFAULT NULL,
    refresh_token_metadata blob DEFAULT NULL,
    PRIMARY KEY (id)
);
-- 表名不能添加 global_ 前缀，因为在程序中没有自定义修改表名的入口，且声名了 final 字符串，也不能通过反射修改表名
-- 改一下表名 位置在：org/springframework/security/oauth2/server/authorization/JdbcOAuth2AuthorizationService/oauth2-authorization-schema.sql