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
/**
 * <p>
 * 企业投资机构表
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Accessors(chain = true)
@Data
@TableName("component_data_company_vc_invest_institution")
public class DataCompanyVcInvestInstitutionDO extends BaseDO {

    /**
    * 企业表ID，投资机构可能不对应公司
    */
    private Long companyId;

    /**
    * 是否对应公司，如果company_id为null不确定是数据没处理还是没对应公司，这里加一个字段标识
    */
    private Boolean isRelatedCompany;

    /**
    * 机构名称
    */
    private String name;

    /**
    * 机构英文名称
    */
    private String enName;

    /**
    * 机构logo地址
    */
    private String logoUrl;

    /**
    * 机构网址
    */
    private String websiteUrl;

    /**
    * 成立年份，可以成立日期没有值只有年份
    */
    private Integer establishYear;

    /**
    * 成立日期
    */
    private LocalDate establishDate;
    
    /**
    * 所在省份，区域id
    */
    private Long provinceAreaId;

    /**
    * 所在城市，区域id
    */
    private Long cityAreaId;

    /**
    * 所在区县，区域id
    */
    private Long countyAreaId;

    /**
    * 机构地址
    */
    private String address;

    /**
    * 手机号码
    */
    private String mobile;

    /**
    * 电话号码
    */
    private String telephone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 微博地址
    */
    private String weiboUrl;

    /**
    * 微信号
    */
    private String wechatNo;

    /**
    * 机构介绍
    */
    private String description;

	/**
	 * 数据md5,name + en_name
	 */
	private String dataMd5;

    /**
    * 最后处理时间，不代表数据有变动，用来表示数据处理过，但可能无需处理，不影响版本号变动
    */
    private LocalDateTime latestHandleAt;
    

}