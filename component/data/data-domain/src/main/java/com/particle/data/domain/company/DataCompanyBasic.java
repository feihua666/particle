package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业基本信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Entity
public class DataCompanyBasic extends AggreateRoot {

    private DataCompanyBasicId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 纳税人识别号，Taxpayer Identification Number,简称为 tin
    */
    private String tin;

    /**
    * 登记状态，字典id，如：在营
    */
    private Long statusDictId;

    /**
    * 性质，字典id，如：大陆企业、社会组织、中国香港公司、事业单位、中国台湾公司、基金会、医院、海外公司、律师事务所、学校、机关单位、其他未识别性质
    */
    private Long natureDictId;

    /**
    * 法人名称，is_legal_person_natural_person 等于0时为人名，等于1时为公司名
    */
    private String legalPersonName;

    /**
    * 是否法人为自然人，1=自然人，0=非自然人
    */
    private Boolean isLegalPersonNaturalPerson;

    /**
    * 法人公司id，is_legal_person_natural_person = 0 时有值
    */
    private Long legalPersonCompanyId;

    /**
    * 法人个人id，is_legal_person_natural_person = 1 时有值
    */
    private Long legalPersonCompanyPersonId;

    /**
    * 企业类型，字典id，如有限责任公司，股份有限公司，个人独资企业
    */
    private Long typeDictId;

    /**
    * 注册地址
    */
    private String regAddress;

    /**
    * 注册地址，邮政编码
    */
    private String regAddressPostalCode;

    /**
    * 经营地址，实际经营地址
    */
    private String businessAddress;

    /**
    * 经营地址，邮政编码
    */
    private String businessAddressPostalCode;

    /**
    * 成立日期
    */
    private LocalDate establishDate;
    
    /**
    * 营业期限开始日期
    */
    private LocalDate businessFromDate;
    
    /**
    * 营业期限终止日期
    */
    private LocalDate businessToDate;
    
    /**
    * 核准日期
    */
    private LocalDate approveDate;
    
    /**
    * 注销日期
    */
    private LocalDate cancelDate;
    
    /**
    * 注销原因
    */
    private String cancelReason;

    /**
    * 吊销日期
    */
    private LocalDate revokeDate;
    
    /**
    * 吊销原因
    */
    private String revokeReason;

    /**
    * 经营范围
    */
    private String businessScope;

    /**
    * 注册机关公司id
    */
    private Long regInstituteCompanyId;

    /**
    * 注册机关名称，冗余公司名称
    */
    private String regInstituteName;

    /**
    * 注册资本（万元）
    */
    private BigDecimal regCapital;
    
    /**
    * 注册资金币种，字典id，如：人民币
    */
    private Long regCapitalCurrencyDictId;

    /**
    * 认缴出资金额（万元）
    */
    private BigDecimal subCapital;
    
    /**
    * 认缴出资金额币种，字典id，如：人民币
    */
    private Long subCapitalCurrencyDictId;

    /**
    * 认缴出资方式
    */
    private Long subCapitalTypeDictId;

    /**
    * 认缴出资日期
    */
    private LocalDate subCapitalDate;
    
    /**
    * 实缴出资金额（万元）
    */
    private BigDecimal actualCapital;
    
    /**
    * 实缴出资金额币种，字典id，如：人民币
    */
    private Long actualCapitalCurrencyDictId;

    /**
    * 实缴出资方式
    */
    private Long capitalTypeDictId;

    /**
    * 实缴出资日期
    */
    private LocalDate actualCapitalDate;
    
    /**
    * 手机号码
    */
    private String mobile;

    /**
    * 电话号码
    */
    private String telephone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 从业人数
    */
    private Integer employeeNum;

    /**
    * 社保人数
    */
    private Integer socialSecurityNum;

    /**
    * 最新年报年份
    */
    private Integer latestYearReportYear;

    /**
    * 规模类型，字典id，如：大型、中型、小型、微型
    */
    private Long scaleTypeDictId;

    /**
    * 经度
    */
    private String longitude;

    /**
    * 纬度
    */
    private String latitude;

    /**
    * 所属行业，字典id，根据国家统计局将国民经济行业划分为门类、大类、中类和小类四级，本字段存储门类
    */
    private Long industryMainDictId;

    /**
    * 所属行业，字典id，根据国家统计局将国民经济行业划分为门类、大类、中类和小类四级，本字段存储大类类
    */
    private Long industryBigDictId;

    /**
    * 所属行业，字典id，根据国家统计局将国民经济行业划分为门类、大类、中类和小类四级，本字段存储中类
    */
    private Long industryMiddleDictId;

    /**
    * 所属行业，字典id，根据国家统计局将国民经济行业划分为门类、大类、中类和小类四级，本字段存储小类
    */
    private Long industrySmallDictId;

    /**
    * 所在省份，区域id
    */
    private Long provinceAreaId;

    /**
    * 所在城市，区域id
    */
    private Long cityAreaId;

    /**
    * 所在区县，区域id
    */
    private Long countyAreaId;

    /**
    * 是否上市
    */
    private Boolean isListed;

    /**
    * 股票代码，若A股、港股同时上市，这里存储A股
    */
    private String stockCode;

    /**
    * 上市类型，如：A股、港股、美股、新三板、新四板
    */
    private Long listedTypeDictId;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    


    /**
     * 创建企业基本信息领域模型对象
     * @return 企业基本信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyBasic create(){
        return DomainFactory.create(DataCompanyBasic.class);
    }
}
