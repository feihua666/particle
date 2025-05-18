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
 * 企业知识产权专利引证信息表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_quote")
public class DataCompanyIprPatentQuoteDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 原始申请号
    */
    private String applyNo;

	/**
	 * 标准申请号，如：CN101995000006852
	 */
	private String standardApplyNo;

    /**
    * 申请日期
    */
    private LocalDate applyDate;
    
    /**
    * 原始公布号
    */
    private String publicNo;

	/**
	 * 标准公布号
	 */
	private String standardPublicNo;

    /**
    * 公布日
    */
    private LocalDate publicDate;
    
    /**
    * 原始标题，如：一种环境检测仪用支撑设备
    */
    private String title;

	/**
	 * 中文标题
	 */
	private String titleCn;

	/**
	 * 英文标题
	 */
	private String titleEn;

    /**
    * 引证来源,如：APP、EXA
    */
    private String quoteFrom;

    /**
    * 引证来源类型,如：审查员引证、申请人引证
    */
    private String quoteFromType;

    /**
    * 申请人引证标准号
    */
    private String applicantQuoteNo;

    /**
    * 审查员引证标准号
    */
    private String examinerQuoteNo;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}