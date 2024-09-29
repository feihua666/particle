package com.particle.openplatform.client.doc.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 开放接口文档接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Data
@Schema
public class OpenplatformDocApiVO extends AbstractBaseIdVO {

    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "简称")
    private String nameSimple;

	@Schema(description = "图标地址")
	private String logoUrl;
    
    @Schema(description = "每次价格")
    private Double pricePerTime;
    
    @Schema(description = "价格文本")
    private String pricePerTimeDesc;

	@Schema(description = "开放接口id，这里只存储接口，不存储分组")
	private Long openplatformOpenapiId;
    
    @Schema(description = "描述")
    private String description;
    
    @Schema(description = "排序")
    private Integer seq;
    


}