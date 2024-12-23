package com.particle.navigation.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 导航分类表
 * </p>
 *
 * @author yw
 * @since 2024-10-22 15:34:42
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_category")
public class NavigationCategoryDO extends BaseTreeDO {

    /**
    * 编码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 图标
    */
    private String icon;

    /**
    * 描述
    */
    private String remark;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
