package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业投资机构 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyVcInvestInstitutionExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业表ID")
    private Long companyId;
    
    @Schema(description = "是否对应公司")
    private Boolean isRelatedCompany;
    
    @Schema(description = "机构名称")
    private String name;
    
    @Schema(description = "机构英文名称")
    private String enName;
    
    @Schema(description = "机构logo地址")
    private String logoUrl;
    
    @Schema(description = "机构网址")
    private String websiteUrl;
    
    @Schema(description = "成立年份")
    private Integer establishYear;
    
    @Schema(description = "成立日期")
    private LocalDate establishDate;
        
    @Schema(description = "所在省份")
    private Long provinceAreaId;
    
    @Schema(description = "所在城市")
    private Long cityAreaId;
    
    @Schema(description = "所在区县")
    private Long countyAreaId;
    
    @Schema(description = "机构地址")
    private String address;
    
    @Schema(description = "手机号码")
    private String mobile;
    
    @Schema(description = "电话号码")
    private String telephone;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "微博地址")
    private String weiboUrl;
    
    @Schema(description = "微信号")
    private String wechatNo;
    
    @Schema(description = "机构介绍")
    private String description;

	@Schema(description = "数据md5,name + en_name")
	private String dataMd5;
    
    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
        


}