package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.data.common.tool.SomeMd5Tool;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业融资 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:43
 */
@Data
@Entity
public class DataCompanyVcFinancing extends AggreateRoot {

    private DataCompanyVcFinancingId id;

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

    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
        initDataMd5();
    }

    public void initDataMd5() {
        this.dataMd5 = SomeMd5Tool.dataCompanyVcFinancingDataMd5(productName,roundName,amount,valuation,financingDate,publishTitle);
    }

    /**
     * 创建企业融资领域模型对象
     * @return 企业融资领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyVcFinancing create(){
        return DomainFactory.create(DataCompanyVcFinancing.class);
    }
}
