package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业融资产品表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_vc_product")
public class DataCompanyVcProductDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 产品名称
    */
    private String productName;

    /**
    * 产品logo地址
    */
    private String productLogoUrl;

    /**
    * 产品介绍
    */
    private String productDescription;

    /**
    * 是否是该公司代表性的产品
    */
    private Boolean isPrimary;

    /**
    * 融资次数
    */
    private Integer roundNum;

    /**
    * 竞品数量
    */
    private Integer competitiveProductNum;

    /**
    * 当前融资轮次
    */
    private Long currentRoundDictId;

    /**
    * 融资金额（万元）
    */
    private BigDecimal amount;

	/**
	 * 数据md5,product_name
	 */
	private String dataMd5;
    
    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}