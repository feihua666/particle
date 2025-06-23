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
 * 企业知识产权植物新品种变更信息表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_plant_variety_change")
public class DataCompanyIprPlantVarietyChangeDO extends BaseDO {

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
    

}
