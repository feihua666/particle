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
 * 企业知识产权地理标识表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_geogra")
public class DataCompanyIprGeograDO extends BaseDO {

    /**
    * 公告号
    */
    private String publicNo;

    /**
    * 公告日期
    */
    private LocalDate publicDate;
    
    /**
    * 产品名称
    */
    private String name;

    /**
    * 国民经济行业分类
    */
    private String nationalEconomicClassification;

    /**
    * 公告类型，如：批准公告
    */
    private String publicTypeName;

    /**
    * 公告单位，如：国家质量监督检验检疫总局
    */
    private String publicDeptName;

    /**
    * 所在地域 ，如：北京市昌平区
    */
    private String areaAddress;

    /**
    * 保护范围
    */
    private String protectScope;

    /**
    * 保护范围界定文件
    */
    private String protectFile;

    /**
    * 质量技术要求
    */
    private String qualityTechnicalRequirement;

    /**
    * 专用标志
    */
    private String specialSign;

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
    * 申请人地址
    */
    private String applicantAddress;

    /**
    * 初审机构
    */
    private String primaryReviewInstitute;

    /**
    * 初审日期
    */
    private LocalDate primaryReviewDate;
    
    /**
    * 代理机构
    */
    private String agencyName;

    /**
    * 使用商品
    */
    private String useProduct;

    /**
    * 商品组别
    */
    private String productGroup;

    /**
    * 文件存放路径
    */
    private String filePath;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
