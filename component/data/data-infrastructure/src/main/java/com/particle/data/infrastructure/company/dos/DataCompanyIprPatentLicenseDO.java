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
 * 企业知识产权专利许可信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_license")
public class DataCompanyIprPatentLicenseDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 专利权许可类型,如：1、2
    */
    private String licenseType;

    /**
    * 专利备案合同号编码
    */
    private String filingContractNo;

    /**
    * 合同备案日期
    */
    private LocalDate filingContractDate;
    
    /**
    * 让与人，转让方，如：xxxx公司
    */
    private String assignor;

    /**
    * 合同变更日期
    */
    private LocalDate contractChangeDate;
    
    /**
    * 受让人，接收方，如：xxxx公司
    */
    private String assignee;

    /**
    * 合同解除日期
    */
    private LocalDate contractRescissionDate;

	/**
	 * 数据md5,license_type + filing_contract_no + filing_contract_date + assignor + assignee
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}