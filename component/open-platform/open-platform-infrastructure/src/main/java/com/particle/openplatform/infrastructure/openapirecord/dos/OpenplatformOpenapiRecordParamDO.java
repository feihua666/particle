package com.particle.openplatform.infrastructure.openapirecord.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口调用记录参数表
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Data
@TableName("component_openplatform_openapi_record_param")
public class OpenplatformOpenapiRecordParamDO extends BaseDO {

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


}
