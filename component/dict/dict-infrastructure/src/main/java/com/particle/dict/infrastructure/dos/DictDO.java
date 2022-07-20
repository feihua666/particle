package com.particle.dict.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@TableName("component_dict")
public class DictDO extends BaseTreeDO {
    /**
    * 字典编码,模糊查询，字典组时必填
    */
    private String code;
    /**
    * 字典名称,模糊查询
    */
    private String name;
    /**
    * 字典值,模糊查询
    */
    private String value;
    /**
    * 是否为系统字典，一般系统字典代码中会做判断，不能修改或删除
    */
    private Boolean isSystem;
    /**
    * 是否为公共字典，如果为公共字典不限制使用，否则按相应数据权限查询
    */
    private Boolean isPublic;
    /**
    * 是否为字典组，不是字典组就是字典项目，没有其它的
    */
    private Boolean isGroup;
    /**
    * 是否禁用
    */
    private Boolean isDisabled;
    /**
    * 禁用原因
    */
    private String disabledReason;
    /**
    * 私有标识,模糊查询
    */
    private String privateFlag;
    /**
    * 私有标识备忘
    */
    private String privateFlagMemo;
    /**
    * 分组标识
    */
    private String groupFlag;
    /**
    * 分组标识备忘
    */
    private String groupFlagMemo;
    /**
    * 标签，多个以逗号分隔，用来区分字典项
    */
    private String tags;
    /**
    * 描述
    */
    private String remark;
    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

}
