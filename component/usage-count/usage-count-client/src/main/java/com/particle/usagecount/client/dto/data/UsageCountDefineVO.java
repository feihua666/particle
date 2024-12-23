package com.particle.usagecount.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 使用次数定义 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Data
@Schema
public class UsageCountDefineVO extends AbstractBaseIdTreeVO {

    @Schema(description = "编码")
    private String code;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "匹配的url地址")
    private String urlPattern;

    @Schema(description = "是否为分组")
    private Boolean isGroup;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "排序")
    private Integer seq;



}
