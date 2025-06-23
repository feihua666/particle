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
 * 企业知识产权商标许可信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:10
 */
@Data
@Entity
public class DataCompanyIprTrademarkLicense extends AggreateRoot {

    private DataCompanyIprTrademarkLicenseId id;

    /**
    * 企业知识产权商标表id
    */
    private Long companyIprTrademarkId;

    /**
    * 许可期号
    */
    private String licenseIssueNo;

    /**
    * 许可页码
    */
    private String licensePageNo;

    /**
    * 许可备案号
    */
    private String licenseFilingNo;

    /**
    * 许可备案公告日期
    */
    private LocalDate licenseFilingPublicDate;

    /**
    * 许可期限
    */
    private String licenseTerm;

    /**
    * 许可使用的商品服务
    */
    private String licenseGoodService;

    /**
    * 数据md5,license_issue_no
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkLicenseDataMd5(licenseIssueNo);
    }


    /**
     * 创建企业知识产权商标许可信息领域模型对象
     * @return 企业知识产权商标许可信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprTrademarkLicense create(){
        return DomainFactory.create(DataCompanyIprTrademarkLicense.class);
    }
}
