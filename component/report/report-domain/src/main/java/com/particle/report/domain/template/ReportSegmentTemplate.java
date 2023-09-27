package com.particle.report.domain.template;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 报告片段模板 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Data
@Entity
public class ReportSegmentTemplate extends AggreateRoot {

    private ReportSegmentTemplateId id;

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

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建报告片段模板领域模型对象
     * @return 报告片段模板领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static ReportSegmentTemplate create(){
        return DomainFactory.create(ReportSegmentTemplate.class);
    }
}