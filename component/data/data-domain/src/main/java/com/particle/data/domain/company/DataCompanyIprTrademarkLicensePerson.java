package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标许可人 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Data
@Entity
public class DataCompanyIprTrademarkLicensePerson extends AggreateRoot {

    private DataCompanyIprTrademarkLicensePersonId id;

    /**
    * 企业知识产权商标许可表id
    */
    private Long companyIprTrademarkLicenseId;

    /**
    * 原始许可人名称
    */
    private String licenseName;

    /**
    * 中文许可人名称
    */
    private String licenseNameCn;

    /**
    * 英文许可人名称
    */
    private String licenseNameEn;

    /**
    * 是否为被许可人，1=被许可人，0=许可人
    */
    private Boolean isLicensed;

    /**
    * 数据md5,license_name + is_licensed
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
        this.dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkLicensePersonDataMd5(licenseName,isLicensed);
    }


    /**
     * 创建企业知识产权商标许可人领域模型对象
     * @return 企业知识产权商标许可人领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprTrademarkLicensePerson create(){
        return DomainFactory.create(DataCompanyIprTrademarkLicensePerson.class);
    }
}
