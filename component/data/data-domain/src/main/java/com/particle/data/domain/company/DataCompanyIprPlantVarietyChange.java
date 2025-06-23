package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权植物新品种变更信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Data
@Entity
public class DataCompanyIprPlantVarietyChange extends AggreateRoot {

    private DataCompanyIprPlantVarietyChangeId id;

    /**
    * 企业知识产权植物新品种表id
    */
    private Long companyIprPlantVarietyId;

    /**
    * 变更日期
    */
    private LocalDate changeDate;

    /**
    * 变更前
    */
    private String changeBefore;

    /**
    * 变更后
    */
    private String changeAfter;

    /**
    * 原因
    */
    private String changeReason;

    /**
    * 是否名称变更
    */
    private Boolean isName;

    /**
    * 是否转让变更
    */
    private Boolean isTransfer;

    /**
    * 是否培育人变更
    */
    private Boolean isCultivate;

    /**
    * 是否申请人变更
    */
    private Boolean isApplicant;

    /**
    * 是否代理人变更
    */
    private Boolean isAgent;

    /**
    * 是否代理机构变更
    */
    private Boolean isAgency;

    /**
    * 数据md5,change_date + change_before + change_after + change_reason + is_name + is_transfer + is_cultivate + is_applicant + is_agent + is_agency
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPlantVarietyChangeDataMd5(changeDate,changeBefore,changeAfter,changeReason,
                isName, isTransfer, isCultivate, isApplicant, isAgent, isAgency);
    }

    /**
     * 创建企业知识产权植物新品种变更信息领域模型对象
     * @return 企业知识产权植物新品种变更信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPlantVarietyChange create(){
        return DomainFactory.create(DataCompanyIprPlantVarietyChange.class);
    }
}
