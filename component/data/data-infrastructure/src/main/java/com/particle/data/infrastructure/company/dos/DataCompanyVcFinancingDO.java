package com.particle.data.infrastructure.company.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业融资表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_vc_financing")
public class DataCompanyVcFinancingDO extends BaseDO {

    /**
    * 企业表ID
    */
    private Long companyId;

    /**
    * 公司产品id
    */
    private Long companyVcProductId;

	/**
	 * 产品名称，冗余产品名称
	 */
	private String productName;

    /**
    * 融资轮次,字典id
    */
    private Long roundDictId;

	/**
	 * 融资轮次名称
	 */
	private String roundName;

    /**
    * 融资金额（万元）
    */
    private BigDecimal amount;

    /**
    * 融资金额币种，字典id，如：人民币
    */
    private Long amountCurrencyDictId;

    /**
    * 估值
    */
    private String valuation;

    /**
    * 融资日期
    */
    private LocalDate financingDate;

    /**
    * 报道时间
    */
    private LocalDateTime publishAt;

    /**
    * 报道标题
    */
    private String publishTitle;

    /**
    * 报道链接地址，外部链接
    */
    private String publishUrl;

    /**
    * 报道快照链接地址，内部链接
    */
    private String publishSnapshotUrl;

	/**
	 * 数据md5,product_name + round_name + amount + valuation + financing_date + publish_title
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}