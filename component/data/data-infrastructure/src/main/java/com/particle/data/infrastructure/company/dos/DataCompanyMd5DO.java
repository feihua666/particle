package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 企业md5表
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_md5")
public class DataCompanyMd5DO extends BaseDO {

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


}
