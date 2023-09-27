package com.particle.report.infrastructure.template.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 报告片段模板表
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Data
@TableName("component_report_segment_template")
public class ReportSegmentTemplateDO extends BaseTreeDO {

    /**
    * 编码，唯一
    */
    private String code;

    /**
    * 模板名称，仅做展示
    */
    private String name;

    /**
     * 数据获取脚本，目前仅支持groovy脚本
     */
    private String dataResolveScript;

    /**
     * 渲染条件脚本，目前仅支持groovy脚本
     */
    private String renderConditionScript;

    /**
    * 计算模板
    */
    private String computeTemplate;

    /**
    * 名称模板
    */
    private String nameTemplate;

    /**
    * 名称输出变量名
    */
    private String nameOutputVariable;

    /**
    * 内容模板
    */
    private String contentTemplate;

    /**
    * 引用模板id
    */
    private Long referenceSegmentTemplateId;

    /**
    * 输出类型字典id，file=文件，dir=目录，segment=片段
    */
    private Long outputTypeDictId;

    /**
    * 内容输出变量名
    */
    private String outputVariable;

    /**
    * 共享变量名，多个以逗号分隔，变量类型为Set<String>
    */
    private String shareVariables;

	/**
	 * 排序,默认按该字段升序排序
	 */
	private Integer seq;

    /**
    * 描述
    */
    private String remark;


}