package com.particle.openplatform.infrastructure.providerrecord.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数表
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Data
@TableName("component_openplatform_provider_record_param")
public class OpenplatformProviderRecordParamDO extends BaseDO {

    /**
    * 供应商调用记录id
    */
    private Long openplatformProviderRecordId;

    /**
    * 请求参数
    */
    private String requestParam;

    /**
    * 响应结果
    */
    private String responseResult;


}
