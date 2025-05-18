package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业融资历史投资机构关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Data
@Entity
public class DataCompanyVcFinancingInvestInstitutionRel extends AggreateRoot {

    private DataCompanyVcFinancingInvestInstitutionRelId id;

    /**
    * 企业融资表ID
    */
    private Long companyVcFinancingId;

    /**
    * 企业投资机构表id
    */
    private Long companyVcInvestInstitutionId;

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
     * 创建企业融资历史投资机构关系领域模型对象
     * @return 企业融资历史投资机构关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyVcFinancingInvestInstitutionRel create(){
        return DomainFactory.create(DataCompanyVcFinancingInvestInstitutionRel.class);
    }
}
