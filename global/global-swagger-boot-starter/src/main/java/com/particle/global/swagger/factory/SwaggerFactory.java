package com.particle.global.swagger.factory;

import com.particle.global.swagger.SwaggerInfo;
import io.swagger.annotations.Api;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * swagger 文档工厂类
 *
 * 该类为 [Swagger] 工厂类, 主要用于根据 {@link SwaggerInfo} 创建 {@link Docket} 对象
 * 该类指定了部分默认参数, 如需自定义在参数 {@link SwaggerInfo} 中进行赋值相关属性即可
 * 注意: 请求地址中带有 [common] 字段时 {@link SecurityContext} 上下文将不生效
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
    public static final Contact DEFAULT_CONTACT = new Contact("yangwei", "https://ahbdz.com", "feihua666@sina.com");

    /**
     * 默认版本号
     */
    public static final String DEFAULT_VERSION = "1.0.0";

    /**
     * 忽略权限的正则表达式
     * 主要用于接口文档中默认对匹配的路径不加上请求头, 方便操作
     */
    public static final Pattern IGNORE_PATTERN = Pattern.compile("^((?!common).)*$");

    /**
     * 初始化API文档
     *
     * @param infoDTO 自定义Swagger信息
     * @return API文档
     */
    public static Docket createRestApi(SwaggerInfo infoDTO) {
        Docket docket = new Docket(DocumentationType.OAS_30);
        Optional.ofNullable(infoDTO.getParameters())
                .ifPresent(p -> docket.securitySchemes(p).securityContexts(Arrays.asList(securityContext(p))));

        String groupName = Optional.ofNullable(infoDTO.getGroupName())
                .orElse(DEFAULT_GROUP_NAME);

        Predicate<RequestHandler> predicate = Optional.ofNullable(infoDTO.getBasePackage())
                .map(p -> RequestHandlerSelectors.basePackage(p))
                .orElse(RequestHandlerSelectors.withClassAnnotation(Api.class));

        return docket.apiInfo(apiInfo(infoDTO))
                .groupName(groupName)
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build()
                .extensions(infoDTO.getOpenApiExtensionResolver().buildExtensions(groupName));
    }

    /**
     * 设置API信息
     * @param infoDTO 自定义Swagger信息
     * @return API信息
     */
    private static ApiInfo apiInfo(SwaggerInfo infoDTO) {
        return new ApiInfoBuilder()
                .title(Optional.ofNullable(infoDTO.getTitle()).orElse(DEFAULT_TITLE))
                .description(Optional.ofNullable(infoDTO.getDescription()).orElse(DEFAULT_DESCRIPTION))
                .termsOfServiceUrl(Optional.ofNullable(infoDTO.getTermsOfServiceUrl()).orElse(DEFAULT_TERMS_OF_SERVICE_URL))
                .contact(Optional.ofNullable(infoDTO.getContact()).orElse(DEFAULT_CONTACT))
                .version(Optional.ofNullable(infoDTO.getVersion()).orElse(DEFAULT_VERSION))
                .build();
    }

    /**
     * 创建安全上下文
     * @param parameters 授权参数
     * @return 返回权限上下文
     */
    private static SecurityContext securityContext(List<SecurityScheme> parameters) {
        AuthorizationScope[] authorizationScopes = {new AuthorizationScope("web", "access_token")};
        return SecurityContext.builder()
                .securityReferences(parameters.stream()
                        .map(parameter -> new SecurityReference(parameter.getName(), authorizationScopes)).collect(Collectors.toList()))
                .operationSelector(path -> IGNORE_PATTERN.matcher(path.requestMappingPattern()).matches())
                .build();
    }
}
