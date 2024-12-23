package com.particle.openplatform.infrastructure.provider.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 开放平台供应商接口表
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_provider_api")
public class OpenplatformProviderApiDO extends BaseDO {

    /**
    * 编码，唯一
    */
    private String code;

    /**
    * 供应商名称
    */
    private String name;

    /**
    * 数据查询数据源接口id，兼容一下数据查询数据进行统一
    */
    private Long dataQueryDatasourceApiId;

    /**
    * 计费id，不配置不计费
    */
    private Long openplatformOpenapiFeeId;

    /**
    * 请求地址，一般为http开关的绝对地址
    */
    private String requestUrl;

    /**
    * 脚本类型，字典值，如：groovy_script
    */
    private String scriptTypeDictValue;

    /**
    * 脚本内容
    */
    private String scriptContent;

    /**
    * 读取等待时间，单位ms，超过该时间将会放弃
    */
    private Integer readTimeout;

    /**
    * 连接等待时间，单位ms，超过该时间将会放弃
    */
    private Integer connectTimeout;

    /**
    * 描述
    */
    private String remark;


}
