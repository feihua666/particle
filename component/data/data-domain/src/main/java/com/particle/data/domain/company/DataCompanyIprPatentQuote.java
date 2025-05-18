package com.particle.data.domain.company;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
/**
 * <p>
 * 企业知识产权专利引证信息 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Data
@Entity
public class DataCompanyIprPatentQuote extends AggreateRoot {

    private DataCompanyIprPatentQuoteId id;

    /**
    * 企业知识产权专利表id
    */
    private Long companyIprPatentId;

    /**
    * 原始申请号
    */
    private String applyNo;

	/**
	 * 标准申请号，如：CN101995000006852
	 */
	private String standardApplyNo;

    /**
    * 申请日期
    */
    private LocalDate applyDate;

    /**
    * 原始公布号
    */
    private String publicNo;

	/**
	 * 标准公布号
	 */
	private String standardPublicNo;

    /**
    * 公布日
    */
    private LocalDate publicDate;

    /**
    * 原始标题，如：一种环境检测仪用支撑设备
    */
    private String title;

	/**
	 * 中文标题
	 */
	private String titleCn;

	/**
	 * 英文标题
	 */
	private String titleEn;

    /**
    * 引证来源,如：APP、EXA
    */
    private String quoteFrom;

    /**
    * 引证来源类型,如：审查员引证、申请人引证
    */
    private String quoteFromType;

    /**
    * 申请人引证标准号
    */
    private String applicantQuoteNo;

    /**
    * 审查员引证标准号
    */
    private String examinerQuoteNo;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


    public void initForAdd() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }
    public void initForUpdate() {
        LocalDateTime now = LocalDateTime.now();
        this.latestHandleAt = now;
    }

    /**
     * 创建企业知识产权专利引证信息领域模型对象
     * @return 企业知识产权专利引证信息领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DataCompanyIprPatentQuote create(){
        return DomainFactory.create(DataCompanyIprPatentQuote.class);
    }
}