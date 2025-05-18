package com.particle.data.client.company.dto.data.exwarehouse;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 企业融资产品 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2025-04-06 11:53:55
 */
@Data
@Schema
public class DataCompanyVcProductExWarehouseVO extends AbstractBaseIdVO {

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

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "currentRoundDictId",mapValueField = "name")
    @Schema(description = "当前融资轮次对应字典名称")
    private String currentRoundDictName;

    @Schema(description = "融资金额（万元）")
    private BigDecimal amount;

	@Schema(description = "数据md5,product_name")
	private String dataMd5;

    @Schema(description = "最后处理时间")
    private LocalDateTime latestHandleAt;

    @Schema(description = "产品竞品信息")
    private List<DataCompanyVcProductExWarehouseVO> competitiveProducts;

}
