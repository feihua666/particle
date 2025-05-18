package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利质押信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Data
@Entity
public class DataCompanyIprPatentPledge extends AggreateRoot {

    private DataCompanyIprPatentPledgeId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 质押登记号
    */
    private String pledgeNo;

    /**
    * 质押保全类型，如：1;1;1、1
    */
    private String pledgePreserveType;

    /**
    * 质押保全权力类型，如：2;2;2;2;2;2、2
    */
    private String pledgePreserveRightType;

    /**
    * 生效日期
    */
    private LocalDate effectiveDate;

    /**
    * 变更日期
    */
    private LocalDate changeDate;

    /**
    * 出质人，如：xxxx公司
    */
    private String pledgor;

    /**
    * 质权人，如：xxxx公司
    */
    private String pledgee;

    /**
    * 解除日期
    */
    private LocalDate rescissionDate;

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
     * 创建企业知识产权专利质押信息领域模型对象
     * @return 企业知识产权专利质押信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentPledge create(){
        return DomainFactory.create(DataCompanyIprPatentPledge.class);
    }
}
