package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;
/**
 * <p>
 * 企业融资产品 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:14
 */
@Data
@Schema
public class DataCompanyVcProductQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业表ID")
    private Long companyId;


    @Schema(description = "产品名称")
    private String productName;


    @Schema(description = "产品logo地址")
    private String productLogoUrl;


    @Schema(description = "产品介绍")
    private String productDescription;


    @Schema(description = "是否是该公司代表性的产品")
    private Boolean isPrimary;


    @Schema(description = "融资次数")
    private Integer roundNum;


    @Schema(description = "竞品数量")
    private Integer competitiveProductNum;


    @Schema(description = "当前融资轮次")
    private Long currentRoundDictId;


    @Schema(description = "融资金额（万元）")
    private BigDecimal amount;

	@Schema(description = "数据md5,product_name")
	private String dataMd5;
    

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;
    








}