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
 * 企业融资产品竞品关系表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_vc_product_competitive_product_rel")
public class DataCompanyVcProductCompetitiveProductRelDO extends BaseDO {

    /**
    * 企业融资产品表ID
    */
    private Long companyVcProductId;

    /**
    * 企业竞品id，企业融资产品表ID
    */
    private Long companyVcCompetitiveProductId;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}
