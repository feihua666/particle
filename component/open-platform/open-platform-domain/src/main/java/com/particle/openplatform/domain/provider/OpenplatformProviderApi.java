package com.particle.openplatform.domain.provider;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台供应商接口 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Data
@Entity
public class OpenplatformProviderApi extends AggreateRoot {

    private OpenplatformProviderApiId id;

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



    /**
     * 创建开放平台供应商接口领域模型对象
     * @return 开放平台供应商接口领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformProviderApi create(){
        return DomainFactory.create(OpenplatformProviderApi.class);
    }
}
