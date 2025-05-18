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
 * 企业立案信息当事人表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:50
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_case_filing_party")
public class DataCompanyCaseFilingPartyDO extends BaseDO {

    /**
    * 立案信息表id
    */
    private Long companyCaseFilingId;

    /**
    * 当事人名称
    */
    private String partyName;

    /**
    * 是否当事人为自然人，1=自然人，0=非自然人
    */
    private Boolean isPartyNaturalPerson;

    /**
    * 当事人公司id，is_party_natural_person = 0 时有值
    */
    private Long partyCompanyId;

    /**
    * 当事人个人id，is_party_natural_person = 1 时有值
    */
    private Long partyCompanyPersonId;

    /**
    * 当事人角色,字典id，如：原告、被告
    */
    private Long partyRoleDictId;

    /**
    * 当事人描述信息
    */
    private String partyDescription;

	/**
	 * 数据md5,party_name
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
