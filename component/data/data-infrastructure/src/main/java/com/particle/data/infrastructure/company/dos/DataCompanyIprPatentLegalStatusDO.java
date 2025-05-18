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
 * 企业知识产权专利法律状态表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:03
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_legal_status")
public class DataCompanyIprPatentLegalStatusDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 法律状态，字典id
    */
    private Long legalStatusDictId;

	/**
	 * 法律状态代码
	 */
	private String legalStatusCode;

	/**
	 * 原始法律状态名称
	 */
	private String legalStatusName;

	/**
	 * 英文法律状态名称
	 */
	private String legalStatusNameEn;

	/**
	 * 中文法律状态名称
	 */
	private String legalStatusNameCn;

    /**
    * 原始法律状态详情
    */
    private String legalStatusDetail;

	/**
	 * 英文法律状态详情
	 */
	private String legalStatusDetailEn;

	/**
	 * 中文法律状态详情
	 */
	private String legalStatusDetailCn;

    /**
    * 法律状态日期
    */
    private LocalDate legalStatusDate;

	/**
	 * 数据md5,legal_status_name + legal_status_detail + legal_status_date
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}