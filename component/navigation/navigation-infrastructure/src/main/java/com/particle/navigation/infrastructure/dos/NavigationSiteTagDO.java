package com.particle.navigation.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 导航网站标签表
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Accessors(chain = true)
@Data
@TableName("component_navigation_site_tag")
public class NavigationSiteTagDO extends BaseDO {

    /**
    * 标签编码
    */
    private String code;

    /**
    * 标签名称
    */
    private String name;

    /**
    * 分组，字典id
    */
    private Long groupDictId;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;

    /**
    * 备注
    */
    private String remark;


}
