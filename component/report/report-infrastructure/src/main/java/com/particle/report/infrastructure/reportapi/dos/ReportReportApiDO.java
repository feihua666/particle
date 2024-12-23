package com.particle.report.infrastructure.reportapi.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 报告接口表
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Data
@TableName("component_report_report_api")
public class ReportReportApiDO extends BaseTreeDO {

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


}
