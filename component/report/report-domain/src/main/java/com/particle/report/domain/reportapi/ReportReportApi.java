package com.particle.report.domain.reportapi;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 报告接口 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Data
@Entity
public class ReportReportApi extends AggreateRoot {

    private ReportReportApiId id;

    /**
    * 编码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 是否为组，1=组，0=接口
    */
    private Boolean isGroup;

    /**
    * 接口权限码
    */
    private String permissions;

    /**
    * 接口地址，以/开头，用于匹配
    */
    private String url;

    /**
    * 报告片段模板id
    */
    private Long reportSegmentTemplateId;

    /**
    * 入参示例，纯文本展示
    */
    private String inParamExampleConfigJson;

	/**
	 * 后置处理脚本，可以用来对渲染的结果进一步处理，如转为pdf，目前仅支持groovy脚本
	 */
	private String postScript;

    /**
    * 描述
    */
    private String remark;

    /**
    * 父级
    */
    private Long parentId;



    /**
     * 创建报告接口领域模型对象
     * @return 报告接口领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static ReportReportApi create(){
        return DomainFactory.create(ReportReportApi.class);
    }
}