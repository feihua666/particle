package com.particle.data.domain.temp;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 企业md5ids 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Data
@Entity
public class DataCompanyMd5Ids extends AggreateRoot {

    private DataCompanyMd5IdsId id;

    /**
    * 企业id
    */
    private Long companyId;

    /**
    * uuid0
    */
    private String parentIdUuid0;

    /**
    * uuid1
    */
    private String parentIdUuid1;

    /**
    * uuid2
    */
    private String parentIdUuid2;

    /**
    * uuid3
    */
    private String parentIdUuid3;

    /**
    * uuid4
    */
    private String parentIdUuid4;

    /**
    * uuid5
    */
    private String parentIdUuid5;

    /**
    * uuid6
    */
    private String parentIdUuid6;

    /**
    * uuid7
    */
    private String parentIdUuid7;

    /**
    * uuid8
    */
    private String parentIdUuid8;

    /**
    * uuid9
    */
    private String parentIdUuid9;

    /**
    * uuid10
    */
    private String parentIdUuid10;

    /**
    * uuid11
    */
    private String parentIdUuid11;



    /**
     * 创建企业md5ids领域模型对象
     * @return 企业md5ids领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyMd5Ids create(){
        return DomainFactory.create(DataCompanyMd5Ids.class);
    }
}
