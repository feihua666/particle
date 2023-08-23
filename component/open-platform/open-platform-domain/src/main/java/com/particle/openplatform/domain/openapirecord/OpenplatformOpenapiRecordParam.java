package com.particle.openplatform.domain.openapirecord;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口调用记录参数 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Data
@Entity
public class OpenplatformOpenapiRecordParam extends AggreateRoot {

    private OpenplatformOpenapiRecordParamId id;

    /**
    * 调用记录id
    */
    private Long openplatformOpenapiRecordId;

    /**
    * 请求参数
    */
    private String requestParam;

    /**
    * 响应结果
    */
    private String responseResult;



    /**
     * 创建开放平台开放接口调用记录参数领域模型对象
     * @return 开放平台开放接口调用记录参数领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static OpenplatformOpenapiRecordParam create(){
        return DomainFactory.create(OpenplatformOpenapiRecordParam.class);
    }
}
