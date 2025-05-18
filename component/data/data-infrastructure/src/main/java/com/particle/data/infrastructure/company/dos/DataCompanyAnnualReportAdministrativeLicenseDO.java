package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业年报行政许可表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_administrative_license")
public class DataCompanyAnnualReportAdministrativeLicenseDO extends BaseDO {

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
    * 序号
    */
    private Integer serialNumber;

    /**
    * 许可文件名称
    */
    private String fileName;

    /**
    * 许可文件到期日期
    */
    private LocalDate validToDate;

	/**
	 * 数据md5,file_name
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}