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
 * 企业知识产权植物新品种表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:40
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_plant_variety")
public class DataCompanyIprPlantVarietyDO extends BaseDO {

    /**
    * 申请号
    */
    private String applyNo;

    /**
    * 申请日期
    */
    private LocalDate applyDate;
    
    /**
    * 公告号
    */
    private String publicNo;

    /**
    * 公告日期
    */
    private LocalDate publicDate;
    
    /**
    * 品种名称
    */
    private String name;

    /**
    * 申请公告号
    */
    private String applyPublicNo;

    /**
    * 申请公告日期
    */
    private LocalDate applyPublicDate;
    
    /**
    * 公告类型，如：申请公告
    */
    private String publicTypeName;

    /**
    * 植物种类，如：葡萄属 Vitis L．、玉米 Zea mays L.
    */
    private String plantKindName;

    /**
    * 品种权事务分类
    */
    private String varietyRightType;

    /**
    * 申请人名称
    */
    private String applicantName;

    /**
    * 是否申请人为自然人，1=自然人，0=非自然人
    */
    private Boolean isApplicantNameNaturalPerson;

    /**
    * 申请人公司id，is_applicant_name_natural_person = 0 时有值
    */
    private Long applicantNameCompanyId;

    /**
    * 申请人个人id，is_applicant_name_natural_person = 1 时有值
    */
    private Long applicantNameCompanyPersonId;

    /**
    * 申请人地址,品种权人地址 
    */
    private String applicantAddress;

    /**
    * 培育人，一般为人名
    */
    private String cultivateName;

    /**
    * 共同品种权人
    */
    private String coVarietyRightName;

    /**
    * 代理机构
    */
    private String agency;

    /**
    * 代理机构地址
    */
    private String agencyAddress;

    /**
    * 代理人
    */
    private String agent;

    /**
    * 优先权号
    */
    private String priorityNo;

    /**
    * 品种来源
    */
    private String varietySourceFrom;

    /**
    * 批次号
    */
    private String batchNo;

    /**
    * 说明
    */
    private String description;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
