package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权商标许可人表
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:22
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_trademark_license_person")
public class DataCompanyIprTrademarkLicensePersonDO extends BaseDO {

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
    

}
