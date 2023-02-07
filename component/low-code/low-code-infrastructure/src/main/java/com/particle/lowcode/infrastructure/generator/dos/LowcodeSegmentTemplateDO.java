package com.particle.lowcode.infrastructure.generator.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 低代码片段模板表
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Data
@TableName("component_lowcode_segment_template")
public class LowcodeSegmentTemplateDO extends BaseTreeDO {
    /**
    * 编码，唯一
    */
    private String code;
    /**
    * 模板名称，仅做展示
    */
    private String name;
    /**
    * 名称模板
    */
    private String nameTemplate;
    /**
    * 内容模板
    */
    private String contentTemplate;
    /**
    * 引用模板id
    */
    private Long referenceSegmentTemplateId;
    /**
    * 输出类型，file=文件，dir=目录，segment=片段
    */
    private String outputType;
    /**
    * 输出变量名
    */
    private String outputVariable;
    /**
     * 共享变量名，多个以逗号分隔，变量类型为List<String>
     */
    private String shareVariables;
    /**
    * 描述
    */
    private String remark;

}
