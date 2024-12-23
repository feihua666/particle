package com.particle.usagecount.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 使用次数定义表
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Data
@TableName("component_usage_count_define")
public class UsageCountDefineDO extends BaseTreeDO {

    /**
    * 编码，唯一,模糊查询
    */
    private String code;

    /**
    * 名称,模糊查询
    */
    private String name;

    /**
    * 匹配的url地址，填写且能匹配后端限制
    */
    private String urlPattern;

    /**
    * 是否为分组，1=分组，0=配置
    */
    private Boolean isGroup;

    /**
    * 备注
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
