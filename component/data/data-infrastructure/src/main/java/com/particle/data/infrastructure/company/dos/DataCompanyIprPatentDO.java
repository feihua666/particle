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
 * 企业知识产权专利表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_ipr_patent")
public class DataCompanyIprPatentDO extends BaseDO {

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
    * 原始申请号
    */
    private String applyNo;

	/**
	 * 标准申请号
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
    * 授权公告号
    */
    private String authorizePublicNo;

    /**
    * 授权公告日
    */
    private LocalDate authorizePublicDate;

    /**
    * 优先权号
    */
    private String priorityNo;

    /**
    * 优先权日
    */
    private LocalDate priorityDate;

	/**
	 * 失效日期
	 */
	private LocalDate invalidDate;

	/**
	 * 国际申请号
	 */
	private String internationalApplyNo;

	/**
	 * 国际申请日期
	 */
	private LocalDate internationalApplyDate;

	/**
	 * 国际公布号
	 */
	private String internationalPublicNo;

	/**
	 * 国际公布日期
	 */
	private LocalDate internationalPublicDate;

	/**
	 * 进入国家日期
	 */
	private LocalDate entryCountryDate;

	/**
	 * 分案申请号
	 */
	private String splitApplyNo;

	/**
	 * 分案申请日期
	 */
	private LocalDate splitApplyDate;

	/**
	 * 原始奖励等级
	 */
	private String rewardLevel;

	/**
	 * 原始奖励名称
	 */
	private String rewardName;

	/**
	 * 原始奖励界次
	 */
	private String rewardSession;

    /**
    * IPC分类号，国际专利分类号，多个以英文逗号分隔
    */
    private String ipcCategoryNos;

    /**
    * 主IPC分类号
    */
    private String ipcMainCategoryNo;

    /**
    * CPC分类号，联合专利分类，多个以英文逗号分隔
    */
    private String cpcCategoryNos;

    /**
    * 主CPC分类号
    */
    private String cpcMainCategoryNo;

	/**
	 * UC分类号，美国专利商标局（USPTO）的专利分类号，多个以英文逗号分隔
	 */
	private String ucNo;

	/**
	 * FI分类号，芬兰的专利分类号，多个以英文逗号分隔
	 */
	private String fiNo;

	/**
	 * fterm分类号，File Term Search，专利检索的工具或方法分类号，多个以英文逗号分隔
	 */
	private String ftermNo;

    /**
    * 专利类型，字典id
    */
    private Long patentTypeDictId;

    /**
    * 受理局名称，如：中国
    */
    private String receivingOfficeName;

	/**
	 * 受理国名称
	 */
	private String receivingCountryName;

	/**
	 * 来源国名称
	 */
	private String originCountryName;

	/**
	 * 文献类型，如：B
	 */
	private String literatureType;

	/**
	 * 外观设计方案号
	 */
	private String appearanceSchemeNo;

	/**
	 * 外观设计方案号补充
	 */
	private String appearanceSchemeNoSupplement;

	/**
	 * 国民经济行业分类
	 */
	private String nationalEconomicClassification;

	/**
	 * 是否为pct专利，1-是，0-否
	 */
	private Boolean isPct;

    /**
    * 专利图片地址
    */
    private String patentImageUrl;

    /**
    * 是否当前有效，1-有效，0=无效，一般认为当前法律状态为申请恢复、授权、部分无效、权利恢复、有效期续展这几个状态时有效
    */
    private Boolean isCurrentValid;

	/**
	 * 当前权利状态，字典id
	 */
	private Long currentRightStatusDictId;

    /**
    * 说明书页数
    */
    private Integer instructionManualPageSize;

    /**
    * 洛迦诺分类号，多个以英文逗号分隔
    */
    private String locarnoCategoryNos;

    /**
    * 专利强度,如：38.69
    */
    private BigDecimal patentStrength;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;


}
