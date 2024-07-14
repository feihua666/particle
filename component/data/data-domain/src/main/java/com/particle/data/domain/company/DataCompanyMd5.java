package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 企业md5 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Data
@Entity
public class DataCompanyMd5 extends AggreateRoot {

    private DataCompanyMd5Id id;

    /**
    * 企业id
    */
    private Long companyId;

    /**
    * 企业名称md5
    */
    private String nameMd5;

    /**
    * 统一社会信用代码md5
    */
    private String usccMd5;

    /**
    * 注册号md5
    */
    private String regNoMd5;

    /**
    * 组织机构代码md5
    */
    private String orgCodeMd5;

    /**
    * 英文名称md5
    */
    private String enNameMd5;



    /**
     * 创建企业md5领域模型对象
     * @return 企业md5领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyMd5 create(){
        return DomainFactory.create(DataCompanyMd5.class);
    }
}
