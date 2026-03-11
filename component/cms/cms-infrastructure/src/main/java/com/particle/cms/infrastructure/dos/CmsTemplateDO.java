package com.particle.cms.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 模板表
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_template")
public class CmsTemplateDO extends BaseTreeDO {

    /**
    * 唯一键
    */
    private String templateKey;

    /**
    * 文件名，目录名
    */
    private String name;

    /**
    * 是否为目录，1=目录，0=文件
    */
    private Boolean isDirectory;

    /**
    * 模板内容
    */
    private String content;

    /**
    * 排序,默认按该字段升序排序
    */
    private Integer seq;


}
