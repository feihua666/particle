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
 * 企业知识产权专利当事人表
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent_party")
public class DataCompanyIprPatentPartyDO extends BaseDO {

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 当事人名称原始名称，is_party_natural_person 等于0时为人名，等于1时为公司名
    */
    private String partyName;

    /**
    * 当事人名称英文名称，is_party_natural_person 等于0时为人名，等于1时为公司名
    */
    private String partyNameEn;

    /**
    * 当事人名称中文名称，is_party_natural_person 等于0时为人名，等于1时为公司名
    */
    private String partyNameCn;

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
    * 是否申请人，1=申请人，0=非申请人
    */
    private Boolean isApplicant;

    /**
    * 是否发明人，1=发明人，0=非发明人
    */
    private Boolean isInvent;

    /**
    * 是否代理人，1=代理人，0=非代理人
    */
    private Boolean isAgent;

    /**
    * 是否代理机构，1=代理机构，0=非代理机构
    */
    private Boolean isAgency;

    /**
    * 是否审查员，1=审查员，0=非审查员
    */
    private Boolean isExaminer;

    /**
    * 是否权利人，1=权利人，0=非权利人
    */
    private Boolean isRight;

    /**
    * 是否当前权利人，1=当前权利人，0=非当前权利人
    */
    private Boolean isCurrentRight;

    /**
    * 地址
    */
    private String address;

    /**
    * 区域编码
    */
    private String areaCode;

    /**
    * 类型
    */
    private String typeName;

	/**
	 * 代码，主要是代理机构代码
	 */
	private String code;

	/**
	 * 排序,默认按该字段升序排序
	 */
	private Integer seq;

    /**
    * 数据md5,party_name + is_applicant + is_invent + is_agent + is_agency + is_examiner + is_right + is_current_right
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}