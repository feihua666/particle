DROP TABLE IF EXISTS oauth2_authorization_consent;
CREATE TABLE oauth2_authorization_consent (
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorities varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);
-- 表名不能添加 global_ 前缀，因为在程序中没有自定义修改表名的入口，且声名了 final 字符串，也不能通过反射修改表名
-- 改一下表名 位置在：org/springframework/security/oauth2/server/authorization/JdbcOAuth2AuthorizationService/oauth2-authorization-consent-schema.sql