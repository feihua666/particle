package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业融资产品 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Data
@Entity
public class DataCompanyVcProduct extends AggreateRoot {

    private DataCompanyVcProductId id;

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

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        // initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyVcProductDataMd5(productName);
    }

    /**
     * 创建企业融资产品领域模型对象
     * @return 企业融资产品领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyVcProduct create(){
        return DomainFactory.create(DataCompanyVcProduct.class);
    }
}
