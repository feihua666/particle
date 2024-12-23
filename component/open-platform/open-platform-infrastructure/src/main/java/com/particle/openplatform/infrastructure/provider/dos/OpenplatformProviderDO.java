package com.particle.openplatform.infrastructure.provider.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口供应商表
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Data
@TableName("component_openplatform_provider")
public class OpenplatformProviderDO extends BaseDO {

    /**
    * 编码，唯一
    */
    private String code;

    /**
    * 供应商名称
    */
    private String name;

    /**
    * 数据查询供应商id，兼容一下数据查询数据进行统一供应商
    */
    private Long dataQueryProviderId;

    /**
    * 描述
    */
    private String remark;


}
