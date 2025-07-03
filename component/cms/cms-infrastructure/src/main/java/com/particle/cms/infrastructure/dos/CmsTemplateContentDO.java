package com.particle.cms.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 模板内容表
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Accessors(chain = true)
@Data
@TableName("component_cms_template_content")
public class CmsTemplateContentDO extends BaseDO {

    /**
    * 数据id，不同模板类型，分类对象不同id
    */
    private Long dataId;

    /**
    * 模板类型，site=端点首页模板、channel=栏目模板、content=内容模板
    */
    private String typeName;

    /**
    * 模板内容
    */
    private String content;


}
