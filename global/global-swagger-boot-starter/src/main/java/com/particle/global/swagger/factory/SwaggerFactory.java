package com.particle.global.swagger.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.particle.global.swagger.SwaggerInfo;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;

import java.util.List;
import java.util.Optional;

/**
 * swagger 文档工厂类
 *
 * 该类为 [Swagger] 工厂类, 主要用于根据 {@link SwaggerInfo} 创建 {@link GroupedOpenApi} 对象
 * 该类指定了部分默认参数, 如需自定义在参数 {@link SwaggerInfo} 中进行赋值相关属性即可
 *
 * @author yangwei
 * @see SwaggerInfo
 * @since 2022-05-19 9:27
 */
public class SwaggerFactory {

    private static String PROJECT_NAME = "Particle";

    /**
     * 默认分组名
     */
    public static final String DEFAULT_GROUP_NAME = "default";

    /**
     * 默认标题
     */
    public static String DEFAULT_TITLE = PROJECT_NAME + " Swagger Apis";

    /**
     * 默认描述
     */
    public static String DEFAULT_DESCRIPTION = PROJECT_NAME + " Swagger Apis Description";

    /**
     * 默认服务条款URL
     */
    public static final String DEFAULT_TERMS_OF_SERVICE_URL = "https://ahbdz.com";

    /**
     * 默认作者信息
     */
    public static final Contact DEFAULT_CONTACT = new Contact().name("yangwei").url("https://ahbdz.com").email("feihua666@sina.com");

    /**
     * 默认版本号
     */
    public static final String DEFAULT_VERSION = "1.0.0";

    /**
     * 初始化API文档
     *
     * @param infoDTO 自定义Swagger信息
     * @return API文档
     */
    public static GroupedOpenApi createRestApi(SwaggerInfo infoDTO) {
        String groupName = Optional.ofNullable(infoDTO.getGroupName())
                .orElse(DEFAULT_GROUP_NAME);
        GroupedOpenApi build = GroupedOpenApi.builder()
                .group(groupName)
                .packagesToScan(infoDTO.getBasePackage())
                .addOperationCustomizer((operation, handlerMethod) -> {
                    if (CollectionUtil.isNotEmpty(infoDTO.getParameters())) {
                        for (Parameter parameter : infoDTO.getParameters()) {
                            operation.addParametersItem(parameter);
                        }
                    }
                    return operation;
                }).addOpenApiCustomizer(openApi -> {
                    openApi.info(apiInfo(infoDTO));
                    SecurityRequirement securityRequirement = securityRequirement(infoDTO.getSecuritySchemes());
                    if (securityRequirement != null) {
                        openApi.addSecurityItem(securityRequirement);
                    }
                })
                .build();
        return build;
    }

    /**
     * 设置API信息
     * @param infoDTO 自定义Swagger信息
     * @return API信息
     */
    public static Info apiInfo(SwaggerInfo infoDTO) {
        return new Info()
                .title(Optional.ofNullable(infoDTO.getTitle()).orElse(DEFAULT_TITLE))
                .description(Optional.ofNullable(infoDTO.getDescription()).orElse(DEFAULT_DESCRIPTION))
                .termsOfService(Optional.ofNullable(infoDTO.getTermsOfServiceUrl()).orElse(DEFAULT_TERMS_OF_SERVICE_URL))
                .contact(Optional.ofNullable(infoDTO.getContact()).orElse(DEFAULT_CONTACT))
                .version(Optional.ofNullable(infoDTO.getVersion()).orElse(DEFAULT_VERSION))
                ;
    }

    /**
     * 创建安全上下文
     * @param securitySchemes 授权参数
     * @return 返回权限上下文
     */
    private static SecurityRequirement securityRequirement(List<SecurityScheme> securitySchemes) {
        if (CollectionUtil.isEmpty(securitySchemes)) {
            return null;
        }
        String[] authorizationScopes = new String[] {"web", "access_token"};
        SecurityRequirement securityRequirement = new SecurityRequirement();
        for (SecurityScheme securityScheme : securitySchemes) {
            securityRequirement.addList(securityScheme.getName(), Lists.newArrayList(authorizationScopes));
        }

        return securityRequirement;
    }
}
