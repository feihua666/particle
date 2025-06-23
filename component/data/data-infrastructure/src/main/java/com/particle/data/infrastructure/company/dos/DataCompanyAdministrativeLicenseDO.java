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
 * 企业行政许可表
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_administrative_license")
public class DataCompanyAdministrativeLicenseDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 许可编号 ,许可文件编号，（信用中国、工商公示）
    */
    private String licenceNo;

    /**
    * 许可证书名称,许可文件名称，（信用中国、工商公示）
    */
    private String licenceName;

    /**
    * 行政许可决定文书号，（信用中国、工商公示）
    */
    private String decisionDocumentNo;

    /**
    * 许可证决定文书名称，（信用中国、工商公示）
    */
    private String decisionDocumentName;

    /**
    * 许可决定日期，（信用中国、工商公示）
    */
    private LocalDate decisionDate;
    
    /**
    * 许可开始日期，（信用中国、工商公示）
    */
    private LocalDate fromDate;
    
    /**
    * 许可截止日期，（信用中国、工商公示）
    */
    private LocalDate endDate;
    
    /**
    * 许可内容，（信用中国、工商公示）
    */
    private String licenceContent;

    /**
    * 许可机关，（信用中国、工商公示）
    */
    private String institute;

    /**
    * 许可机关统一社会信用代码 ，（信用中国）
    */
    private String instituteUscc;

    /**
    * 许可机关公司id
    */
    private Long instituteCompanyId;

    /**
    * 数据来源单位 ，（信用中国）
    */
    private String dataFrom;

    /**
    * 数据来源单位统一社会信用代码，（信用中国）
    */
    private String dataFromUscc;

    /**
    * 数据来源单位公司id
    */
    private Long dataFromCompanyId;

    /**
    * 许可类型，审核类型，（工商公示、信用中国）
    */
    private String licenceType;

    /**
    * 许可备注，（工商公示）
    */
    private String licenceRemark;

    /**
    * 是否数据标识为工商公示，1=工商公示，0=非工商公示
    */
    private Boolean isDataFlagGs;

    /**
    * 是否数据标识为信用中国，1=信用中国，0=非信用中国
    */
    private Boolean isDataFlagXyzg;

    /**
    * 数据md5,decision_document_no + decision_date + licence_content + licence_type + is_data_flag_gs + is_data_flag_xyzg
    */
    private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
