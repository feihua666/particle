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
 * 企业股权出质 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Data
@Entity
public class DataCompanyEquityPledge extends AggreateRoot {

    private DataCompanyEquityPledgeId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 登记编号
    */
    private String regNo;

    /**
    * 出质人
    */
    private String pledgor;

    /**
    * 是否出质人为自然人，1=自然人，0=非自然人
    */
    private Boolean isPledgorNaturalPerson;

    /**
    * 出质人公司id，is_pledgor_natural_person = 0 时有值
    */
    private Long pledgorCompanyId;

    /**
    * 出质人个人id，is_pledgor_natural_person = 1 时有值
    */
    private Long pledgorCompanyPersonId;

    /**
    * 出质人证照/证件号码
    */
    private String pledgorLicenseNo;

    /**
    * 出质股权数额（万股）
    */
    private BigDecimal equityAmount;

    /**
    * 质权人
    */
    private String pledgee;

    /**
    * 是否质权人为自然人，1=自然人，0=非自然人
    */
    private Boolean isPledgeeNaturalPerson;

    /**
    * 质权人公司id，is_pledgee_natural_person = 0 时有值
    */
    private Long pledgeeCompanyId;

    /**
    * 质权人个人id，is_pledgee_natural_person = 1 时有值
    */
    private Long pledgeeCompanyPersonId;

    /**
    * 质权人证照/证件号码
    */
    private String pledgeeLicenseNo;

    /**
    * 股权出质设立登记日期
    */
    private LocalDate regDate;

    /**
    * 状态,如：有效
    */
    private String statusName;

    /**
    * 公示日期
    */
    private LocalDate publishDate;

    /**
    * 变化情况
    */
    private String changeSituation;

    /**
    * 注销日期
    */
    private LocalDate cancelDate;

    /**
    * 注销原因
    */
    private String cancelReason;

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
     * 创建企业股权出质领域模型对象
     * @return 企业股权出质领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyEquityPledge create(){
        return DomainFactory.create(DataCompanyEquityPledge.class);
    }
}
