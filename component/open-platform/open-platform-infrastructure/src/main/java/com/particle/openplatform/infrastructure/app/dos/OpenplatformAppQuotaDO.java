package com.particle.openplatform.infrastructure.app.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 开放平台应用额度表
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Accessors(chain = true)
@Data
@TableName("component_openplatform_app_quota")
public class OpenplatformAppQuotaDO extends BaseDO {

    /**
    * 开放平台应用id
    */
    private Long openplatformAppId;

    /**
    * 限制方式，字典id，如：按条限制，按金额费用限制，不限制有等
    */
    private Long limitTypeDictId;

    /**
    * 限制条数
    */
    private Integer limitCount;

    /**
    * 限制金额费用，单位分
    */
    private Integer limitFee;

    /**
    * 描述
    */
    private String remark;


}
