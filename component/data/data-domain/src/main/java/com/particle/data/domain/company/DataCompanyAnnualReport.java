package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业年报 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:58
 */
@Data
@Entity
public class DataCompanyAnnualReport extends AggreateRoot {

    private DataCompanyAnnualReportId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 年报年度
    */
    private Integer year;

    /**
    * 企业名称
    */
    private String companyName;

    /**
    * 统一社会信用代码
    */
    private String uscc;

    /**
    * 注册号
    */
    private String regNo;

    /**
    * 资金数额（万元）
    */
    private BigDecimal capital;

    /**
    * 资金数额币种，字典id，如：人民币
    */
    private Long capitalCurrencyDictId;

    /**
    * 经营者id，对应的人id
    */
    private Long operatorCompanyPersonId;

    /**
    * 经营者名称，人名，一般为法人，冗余 operator_company_person_id
    */
    private String operatorName;

    /**
    * 企业通信地址
    */
    private String postalAddress;

    /**
    * 邮政编码
    */
    private String postCode;

    /**
    * 企业联系电话
    */
    private String contactPhone;

    /**
    * 企业电子邮箱
    */
    private String email;

    /**
    * 从业人数
    */
    private Integer employeeNum;

    /**
    * 其中女性从业人数
    */
    private Integer femaleEmployeeNum;

    /**
    * 企业经营状态,字典id，如：开业
    */
    private Long statusDictId;

    /**
    * 企业控股情况
    */
    private String holdingControlInfo;

    /**
    * 是否有投资信息或购买其他公司股权
    */
    private Boolean isHasInvestOrBugEquity;

    /**
    * 是否有网站或网店
    */
    private Boolean isHasWebsite;

    /**
    * 是否有对外提供担保信息
    */
    private Boolean isHasForeignGuarantee;

    /**
    * 有限责任公司本年度是否发生股东股权转让
    */
    private Boolean isHasEquityTransfer;

    /**
    * 经营范围（一般项目）
    */
    private String normalBusinessScope;

    /**
    * 经营范围（许可项目）
    */
    private String approvedBusinessScope;

    /**
    * 是否对外提供担保信息公示
    */
    private Boolean isIsHasForeignGuaranteePublic;

    /**
    * 是否其中女性从业人数公示
    */
    private Boolean isFemaleEmployeeNumPublic;

    /**
    * 是否企业控股情况公示
    */
    private Boolean isHoldingControlInfoPublic;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }

    /**
     * 创建企业年报领域模型对象
     * @return 企业年报领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAnnualReport create(){
        return DomainFactory.create(DataCompanyAnnualReport.class);
    }
}
