package com.particle.data.infrastructure.temp.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 企业md5ids表
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_md5_ids")
public class DataCompanyMd5IdsDO extends BaseDO {

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


}
