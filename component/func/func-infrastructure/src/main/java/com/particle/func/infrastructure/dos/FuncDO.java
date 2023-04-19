package com.particle.func.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;

/**
 * <p>
 * 菜单功能表
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@TableName("component_func")
public class FuncDO extends BaseTreeDO {
    /**
    * 编码，模糊查询
    */
    private String code;
    /**
    * 名称，模糊查询
    */
    private String name;
    /**
    * 功能分组id
    */
    private Long funcGroupId;
    /**
    * 图标
    */
    private String icon;
    /**
    * 是否禁用
    */
    private Boolean isDisabled;
    /**
    * 禁用原因
    */
    private String disabledReason;
    /**
    * 地址
    */
    private String url;
    /**
    * shiro权限串，多个以逗号分隔
    */
    private String permissions;
    /**
    * 类型,字典id
    */
    private Long typeDictId;
    /**
     * 是否展示
     */
    private Boolean isShow;
    /**
     * 归属组件，用来标识功能菜单是为哪个组件添加的
     * 应该填写的内容参见 {@link com.particle.global.projectinfo.component.ComponentProperties#getEnable()} 中的key，两者应该保持一致，不包括 ALLKEY 对应的值
     */
    private String componentOf;
    /**
    * 描述
    */
    private String remark;
    
    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

}
