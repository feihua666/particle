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
 * 企业融资投资机构关系表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_vc_financing_invest_institution_rel")
public class DataCompanyVcFinancingInvestInstitutionRelDO extends BaseDO {

    /**
    * 企业融资表ID
    */
    private Long companyVcFinancingId;

    /**
    * 企业投资机构表id
    */
    private Long companyVcInvestInstitutionId;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
