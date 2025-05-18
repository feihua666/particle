package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业年报网站网店表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:16
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_website")
public class DataCompanyAnnualReportWebsiteDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 企业年报表ID
    */
    private Long companyAnnualReportId;

    /**
    * 年报年度
    */
    private Integer year;

    /**
    * 类型，字典id，如：网站、网店
    */
    private Long typeDictId;

	/**
	 * 类型名称，如：网站、网店
	 */
	private String typeName;

    /**
    * 名称
    */
    private String name;

    /**
    * 网址
    */
    private String url;

	/**
	 * 数据md5,type_name + name + url
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}