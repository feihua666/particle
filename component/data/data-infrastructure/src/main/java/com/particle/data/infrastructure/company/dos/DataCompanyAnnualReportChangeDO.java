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
 * 企业年报变更表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_annual_report_change")
public class DataCompanyAnnualReportChangeDO extends BaseDO {

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
    * 变更事项,字典id
    */
    private Long changeItemDictId;

    /**
    * 变更事项
    */
    private String changeItemName;

    /**
    * 变更前内容
    */
    private String contentBefore;

    /**
    * 变更后内容
    */
    private String contentAfter;

    /**
    * 变更日期
    */
    private LocalDate changeDate;

	/**
	 * 数据md5,change_item_name + content_before + content_after + change_date
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
