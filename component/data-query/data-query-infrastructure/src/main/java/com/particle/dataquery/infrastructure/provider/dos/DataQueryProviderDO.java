package com.particle.dataquery.infrastructure.provider.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 数据查询供应商表
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Data
@TableName("component_data_query_provider")
public class DataQueryProviderDO extends BaseDO {

    /**
    * 供应商名称
    */
    private String name;

    /**
    * 是否禁用
    */
    private Boolean isDisabled;
    /**
     * 禁用原因
     */
    private String disabledReason;
    /**
    * 联系人姓名
    */
    private String contactUserName;

    /**
    * 联系人邮箱
    */
    private String contactUserEmail;

    /**
    * 联系人电话
    */
    private String contactUserPhone;

    /**
    * 描述
    */
    private String remark;


}
