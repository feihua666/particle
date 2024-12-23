package com.particle.global.swagger;

import com.particle.global.swagger.factory.SwaggerFactory;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Swagger信息
 *
 * <p>该类为 {@code Swagger} 相关信息封装,
 * 配合 {@link SwaggerFactory} 工厂类生成
 * {@link GroupedOpenApi} 对象, 创建相关接口文档数据
 *
 * @see SwaggerFactory
 * @author yangwei
 * @since 2022-05-19 09:29:36
 */
public class SwaggerInfo {

    /**
     * 自定义请求头 token
     */
    public static String token = "c-token-id";

    /**
     * 分组名称
     * 默认值 = {@link SwaggerFactory#DEFAULT_GROUP_NAME}
     */
    private final String groupName;

    /**
     * 标题
     * 默认值 = {@link SwaggerFactory#DEFAULT_TITLE}
     */
    private final String title;

    /**
     * 描述
     * 默认值 = {@link SwaggerFactory#DEFAULT_DESCRIPTION}
     */
    private final String description;

    /**
     * 服务条款URL
     * 默认值 = {@link SwaggerFactory#DEFAULT_TERMS_OF_SERVICE_URL}
     */
    private final String termsOfServiceUrl;

    /**
     * 作者信息
     * 默认值 = {@link SwaggerFactory#DEFAULT_CONTACT}
     */
    private final Contact contact;

    /**
     * 版本号
     * 默认值 = {@link SwaggerFactory#DEFAULT_VERSION}
     */
    private final String version;

    /**
     * 扫描的包路径
     */
    private final String basePackage;

    /**
     * 授权参数
     * <ul>
     *     <li>KeyValue: new ApiKey("参数说明(Token)", "参数名(Authorization)", "参数位置(header|query)")</li>
     *     <li>TODO BasicAuth Not Finish</li>
     *     <li>TODO OAuth</li>
     * </ul>
     */
    private final List<SecurityScheme> securitySchemes;

    private final List<Parameter> parameter;

    public SwaggerInfo(String groupName, String title, String description, String termsOfServiceUrl,
                       Contact contact, String version, String basePackage,List<SecurityScheme> securitySchemes,List<Parameter> parameter) {
        this.groupName = groupName;
        this.title = title;
        this.description = description;
        this.termsOfServiceUrl = termsOfServiceUrl;
        this.contact = contact;
        this.version = version;
        this.basePackage = basePackage;
        this.securitySchemes = securitySchemes;
        this.parameter = parameter;
    }

    public static SwaggerInfoDtoBuilder builder() {
        return new SwaggerInfoDtoBuilder();
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public Contact getContact() {
        return contact;
    }

    public String getVersion() {
        return version;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public List<SecurityScheme> getSecuritySchemes() {
        return securitySchemes;
    }

    public List<Parameter> getParameters() {
        return parameter;
    }

    @Override
    public String toString() {
        return "SwaggerInfoDTO{" +
                "groupName='" + groupName + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                ", contact=" + contact +
                ", version='" + version + '\'' +
                ", basePackage='" + basePackage + '\'' +
                ", securitySchemes=" + securitySchemes +
                ", requestParameter=" + parameter +
                '}';
    }

    public static class SwaggerInfoDtoBuilder {

        private String groupName;
        private String title;
        private String description;
        private String termsOfServiceUrl;
        private Contact contact;
        private String version;
        private String basePackage;
        private List<SecurityScheme> securitySchemes;
        // 全局请求参数
        private List<Parameter> requestParameter;
        public SwaggerInfo build() {
            if (requestParameter == null) {
                requestParameter = new ArrayList<>();
            }
            if (!requestParameter.stream().filter(item -> token.equals(item.getName())).findFirst().isPresent()) {
                Parameter tokenParameter = new Parameter();
                tokenParameter.setName(token);
                tokenParameter.setSchema(new StringSchema());
                tokenParameter.setRequired(false);
                tokenParameter.setIn(ParameterIn.HEADER.toString());
                requestParameter.add(tokenParameter);
            }

            return new SwaggerInfo(this.groupName, this.title, this.description, this.termsOfServiceUrl, this.contact, this.version, this.basePackage, this.securitySchemes,this.requestParameter);
        }

        public SwaggerInfoDtoBuilder groupName(final String groupName) {
            this.groupName = groupName;
            return this;
        }

        public SwaggerInfoDtoBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public SwaggerInfoDtoBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public SwaggerInfoDtoBuilder termsOfServiceUrl(final String termsOfServiceUrl) {
            this.termsOfServiceUrl = termsOfServiceUrl;
            return this;
        }

        public SwaggerInfoDtoBuilder contact(final Contact contact) {
            this.contact = contact;
            return this;
        }

        public SwaggerInfoDtoBuilder version(final String version) {
            this.version = version;
            return this;
        }

        public SwaggerInfoDtoBuilder basePackage(final String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public SwaggerInfoDtoBuilder securitySchemes(final List<SecurityScheme> securitySchemes) {
            this.securitySchemes = securitySchemes;
            return this;
        }
        public SwaggerInfoDtoBuilder requestParameter(final List<Parameter> requestParameter) {
            this.requestParameter = requestParameter;
            return this;
        }
        @Override
        public String toString() {
            return "SwaggerInfoDtoBuilder{" +
                    "groupName='" + groupName + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", termsOfServiceUrl='" + termsOfServiceUrl + '\'' +
                    ", contact=" + contact +
                    ", version='" + version + '\'' +
                    ", basePackage='" + basePackage + '\'' +
                    ", securitySchemes=" + securitySchemes +
                    ", requestParameter=" + requestParameter +
                    '}';
        }
    }
}
