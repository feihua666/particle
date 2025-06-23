package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权软件著作 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
@Data
@Entity
public class DataCompanyIprSoftwareCopyright extends AggreateRoot {

    private DataCompanyIprSoftwareCopyrightId id;

    /**
    * 注册号
    */
    private String regNo;

    /**
    * 分类号
    */
    private String categoryNo;

    /**
    * 软件全称
    */
    private String name;

    /**
    * 软件简称
    */
    private String nameSimple;

    /**
    * 版本号
    */
    private String versionNo;

    /**
    * 著作权人
    */
    private String copyrightOwner;

    /**
    * 是否著作权人为自然人，1=自然人，0=非自然人
    */
    private Boolean isCopyrightOwnerNaturalPerson;

    /**
    * 著作权人公司id，is_copyright_owner_natural_person = 0 时有值
    */
    private Long copyrightOwnerCompanyId;

    /**
    * 著作权人个人id，is_copyright_owner_natural_person = 1 时有值
    */
    private Long copyrightOwnerCompanyPersonId;

    /**
    * 首次发表日期
    */
    private LocalDate firstPublicDate;

    /**
    * 登记日期
    */
    private LocalDate regDate;

    /**
    * 国家，如：中国
    */
    private String country;

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
     * 创建企业知识产权软件著作领域模型对象
     * @return 企业知识产权软件著作领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprSoftwareCopyright create(){
        return DomainFactory.create(DataCompanyIprSoftwareCopyright.class);
    }
}
