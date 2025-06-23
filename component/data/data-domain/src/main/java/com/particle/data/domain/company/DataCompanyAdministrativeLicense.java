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
 * 企业行政许可 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:17:53
 */
@Data
@Entity
public class DataCompanyAdministrativeLicense extends AggreateRoot {

    private DataCompanyAdministrativeLicenseId id;

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
        this.dataMd5 = SomeMd5Tool.dataCompanyAdministrativeLicenseDataMd5(decisionDocumentNo, decisionDate, licenceContent, licenceType, isDataFlagGs, isDataFlagXyzg);
    }
    /**
     * 创建企业行政许可领域模型对象
     * @return 企业行政许可领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyAdministrativeLicense create(){
        return DomainFactory.create(DataCompanyAdministrativeLicense.class);
    }
}
