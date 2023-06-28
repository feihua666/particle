package com.particle.global.wxjava.cp.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>
 * 配置
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 14:42:15
 */
@Data
@ConfigurationProperties(prefix = "particle.wxcp")
public class WxCpProperties {

  private List<AppConfig> appConfigs;

  @Getter
  @Setter
  public static class AppConfig {

    /**
     * 设置企业微信的corpId
     */
    private String corpId;

    /**
     * 设置企业微信应用的AgentId
     */
    private Integer agentId;

    /**
     * 设置企业微信应用的Secret
     */
    private String secret;

    /**
     * 设置企业微信应用的token
     */
    private String token;

    /**
     * 设置企业微信应用的EncodingAESKey
     */
    private String aesKey;

  }

}
