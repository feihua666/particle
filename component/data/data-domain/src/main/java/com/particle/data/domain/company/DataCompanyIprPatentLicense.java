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
 * 企业知识产权专利许可信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:59
 */
@Data
@Entity
public class DataCompanyIprPatentLicense extends AggreateRoot {

    private DataCompanyIprPatentLicenseId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 专利权许可类型,如：1、2
    */
    private String licenseType;

    /**
    * 专利备案合同号编码
    */
    private String filingContractNo;

    /**
    * 合同备案日期
    */
    private LocalDate filingContractDate;

    /**
    * 让与人，转让方，如：xxxx公司
    */
    private String assignor;

    /**
    * 合同变更日期
    */
    private LocalDate contractChangeDate;

    /**
    * 受让人，接收方，如：xxxx公司
    */
    private String assignee;

    /**
    * 合同解除日期
    */
    private LocalDate contractRescissionDate;

	/**
	 * 数据md5,license_type + filing_contract_no + filing_contract_date + assignor + assignee
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprPatentLicenseDataMd5(licenseType,filingContractNo,filingContractDate,assignor,assignee);
    }

    /**
     * 创建企业知识产权专利许可信息领域模型对象
     * @return 企业知识产权专利许可信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentLicense create(){
        return DomainFactory.create(DataCompanyIprPatentLicense.class);
    }
}
