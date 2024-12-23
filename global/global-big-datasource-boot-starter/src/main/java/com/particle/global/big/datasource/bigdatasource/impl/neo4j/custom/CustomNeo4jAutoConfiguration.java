package com.particle.global.big.datasource.bigdatasource.impl.neo4j.custom;

import org.neo4j.driver.AuthToken;
import org.neo4j.driver.AuthTokenManager;
import org.neo4j.driver.AuthTokens;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.neo4j.Neo4jConnectionDetails;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.URI;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/12/9 21:33
 */
public class CustomNeo4jAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(Neo4jConnectionDetails.class)
    public PropertiesNeo4jConnectionDetails neo4jConnectionDetails(Neo4jProperties properties,
                                                                                   ObjectProvider<AuthTokenManager> authTokenManager) {
        return new PropertiesNeo4jConnectionDetails(properties, authTokenManager.getIfUnique());
    }
    //
    // @Bean
    // @ConditionalOnMissingBean
    // public Driver neo4jDriver(Neo4jProperties properties, Environment environment,
    //                           Neo4jConnectionDetails connectionDetails,
    //                           ObjectProvider<ConfigBuilderCustomizer> configBuilderCustomizers) {
    //
    //     Config config = mapDriverConfig(properties, connectionDetails,
    //             configBuilderCustomizers.orderedStream().toList());
    //     AuthTokenManager authTokenManager = connectionDetails.getAuthTokenManager();
    //     if (authTokenManager != null) {
    //         return GraphDatabase.driver(connectionDetails.getUri(), authTokenManager, config);
    //     }
    //     AuthToken authToken = connectionDetails.getAuthToken();
    //     return GraphDatabase.driver(connectionDetails.getUri(), authToken, config);
    // }
    //
    // Config mapDriverConfig(Neo4jProperties properties, Neo4jConnectionDetails connectionDetails,
    //                        List<ConfigBuilderCustomizer> customizers) {
    //     Config.ConfigBuilder builder = Config.builder();
    //     configurePoolSettings(builder, properties.getPool());
    //     URI uri = connectionDetails.getUri();
    //     String scheme = (uri != null) ? uri.getScheme() : "bolt";
    //     configureDriverSettings(builder, properties, isSimpleScheme(scheme));
    //     builder.withLogging(new Neo4jSpringJclLogging());
    //     customizers.forEach((customizer) -> customizer.customize(builder));
    //     return builder.build();
    // }
    //
    // private boolean isSimpleScheme(String scheme) {
    //     String lowerCaseScheme = scheme.toLowerCase(Locale.ENGLISH);
    //     try {
    //         Scheme.validateScheme(lowerCaseScheme);
    //     }
    //     catch (IllegalArgumentException ex) {
    //         throw new IllegalArgumentException(String.format("'%s' is not a supported scheme.", scheme));
    //     }
    //     return lowerCaseScheme.equals("bolt") || lowerCaseScheme.equals("neo4j");
    // }
    //
    // private void configurePoolSettings(Config.ConfigBuilder builder, Neo4jProperties.Pool pool) {
    //     if (pool.isLogLeakedSessions()) {
    //         builder.withLeakedSessionsLogging();
    //     }
    //     builder.withMaxConnectionPoolSize(pool.getMaxConnectionPoolSize());
    //     Duration idleTimeBeforeConnectionTest = pool.getIdleTimeBeforeConnectionTest();
    //     if (idleTimeBeforeConnectionTest != null) {
    //         builder.withConnectionLivenessCheckTimeout(idleTimeBeforeConnectionTest.toMillis(), TimeUnit.MILLISECONDS);
    //     }
    //     builder.withMaxConnectionLifetime(pool.getMaxConnectionLifetime().toMillis(), TimeUnit.MILLISECONDS);
    //     builder.withConnectionAcquisitionTimeout(pool.getConnectionAcquisitionTimeout().toMillis(),
    //             TimeUnit.MILLISECONDS);
    //     if (pool.isMetricsEnabled()) {
    //         builder.withDriverMetrics();
    //     }
    //     else {
    //         builder.withoutDriverMetrics();
    //     }
    // }
    //
    // private void configureDriverSettings(Config.ConfigBuilder builder, Neo4jProperties properties,
    //                                      boolean withEncryptionAndTrustSettings) {
    //     if (withEncryptionAndTrustSettings) {
    //         applyEncryptionAndTrustSettings(builder, properties.getSecurity());
    //     }
    //     builder.withConnectionTimeout(properties.getConnectionTimeout().toMillis(), TimeUnit.MILLISECONDS);
    //     builder.withMaxTransactionRetryTime(properties.getMaxTransactionRetryTime().toMillis(), TimeUnit.MILLISECONDS);
    // }
    //
    // private void applyEncryptionAndTrustSettings(Config.ConfigBuilder builder,
    //                                              Neo4jProperties.Security securityProperties) {
    //     if (securityProperties.isEncrypted()) {
    //         builder.withEncryption();
    //     }
    //     else {
    //         builder.withoutEncryption();
    //     }
    //     builder.withTrustStrategy(mapTrustStrategy(securityProperties));
    // }
    //
    // private Config.TrustStrategy mapTrustStrategy(Neo4jProperties.Security securityProperties) {
    //     String propertyName = "spring.neo4j.security.trust-strategy";
    //     Neo4jProperties.Security.TrustStrategy strategy = securityProperties.getTrustStrategy();
    //     Config.TrustStrategy trustStrategy = createTrustStrategy(securityProperties, propertyName, strategy);
    //     if (securityProperties.isHostnameVerificationEnabled()) {
    //         trustStrategy.withHostnameVerification();
    //     }
    //     else {
    //         trustStrategy.withoutHostnameVerification();
    //     }
    //     return trustStrategy;
    // }
    //
    // private Config.TrustStrategy createTrustStrategy(Neo4jProperties.Security securityProperties, String propertyName,
    //                                                  Neo4jProperties.Security.TrustStrategy strategy) {
    //     return switch (strategy) {
    //         case TRUST_ALL_CERTIFICATES -> Config.TrustStrategy.trustAllCertificates();
    //         case TRUST_SYSTEM_CA_SIGNED_CERTIFICATES -> Config.TrustStrategy.trustSystemCertificates();
    //         case TRUST_CUSTOM_CA_SIGNED_CERTIFICATES -> {
    //             File certFile = securityProperties.getCertFile();
    //             if (certFile == null || !certFile.isFile()) {
    //                 throw new InvalidConfigurationPropertyValueException(propertyName, strategy.name(),
    //                         "Configured trust strategy requires a certificate file.");
    //             }
    //             yield Config.TrustStrategy.trustCustomCertificateSignedBy(certFile);
    //         }
    //         default -> throw new InvalidConfigurationPropertyValueException(propertyName, strategy.name(),
    //                 "Unknown strategy.");
    //     };
    // }

    /**
     * Adapts {@link Neo4jProperties} to {@link Neo4jConnectionDetails}.
     */
    public static class PropertiesNeo4jConnectionDetails implements Neo4jConnectionDetails {

        private final Neo4jProperties properties;

        private final AuthTokenManager authTokenManager;

        PropertiesNeo4jConnectionDetails(Neo4jProperties properties, AuthTokenManager authTokenManager) {
            this.properties = properties;
            this.authTokenManager = authTokenManager;
        }

        @Override
        public URI getUri() {
            URI uri = this.properties.getUri();
            return (uri != null) ? uri : Neo4jConnectionDetails.super.getUri();
        }

        @Override
        public AuthToken getAuthToken() {
            Neo4jProperties.Authentication authentication = this.properties.getAuthentication();
            String username = authentication.getUsername();
            String kerberosTicket = authentication.getKerberosTicket();
            boolean hasUsername = StringUtils.hasText(username);
            boolean hasKerberosTicket = StringUtils.hasText(kerberosTicket);
            Assert.state(!(hasUsername && hasKerberosTicket),
                    () -> "Cannot specify both username ('%s') and kerberos ticket ('%s')".formatted(username,
                            kerberosTicket));
            String password = authentication.getPassword();
            if (hasUsername && StringUtils.hasText(password)) {
                return AuthTokens.basic(username, password, authentication.getRealm());
            }
            if (hasKerberosTicket) {
                return AuthTokens.kerberos(kerberosTicket);
            }
            return AuthTokens.none();
        }

        @Override
        public AuthTokenManager getAuthTokenManager() {
            return this.authTokenManager;
        }

    }

}
