package com.particle.lowcode.domain.generator;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 低代码片段模板 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Data
@Entity
public class LowcodeSegmentTemplate extends AggreateRoot {

	private LowcodeSegmentTemplateId id;
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

	/**
	 * 父级id
	 */
	private Long parentId;


	/**
	 * 创建低代码片段模板领域模型对象
	 * @return 低代码片段模板领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static LowcodeSegmentTemplate create(){
		return DomainFactory.create(LowcodeSegmentTemplate.class);
	}
}
