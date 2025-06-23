package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权出质 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Data
@Entity
public class DataCompanyIprPledge extends AggreateRoot {

    private DataCompanyIprPledgeId id;

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 知识产权登记证号
    */
    private String regNo;

    /**
    * 知识产权名称
    */
    private String name;

    /**
    * 知识产权种类
    */
    private Long typeName;

    /**
    * 出质人名称
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
    * 质权人名称
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
    * 质权登记期限自
    */
    private LocalDate pledgeFromDate;

    /**
    * 质权登记期限至
    */
    private LocalDate pledgeToDate;

    /**
    * 状态,如：有效
    */
    private String statusName;

    /**
    * 公示日期
    */
    private LocalDate publishDate;

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
     * 创建企业知识产权出质领域模型对象
     * @return 企业知识产权出质领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPledge create(){
        return DomainFactory.create(DataCompanyIprPledge.class);
    }
}
