package com.particle.usagecount.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 使用次数记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-10-23 15:38:33
 */
@Data
@Schema
public class UsageCountRecordMarkVO extends AbstractBaseIdVO {

    @Schema(description = "使用次数key")
    private String usageCountKey;
    
    @Schema(description = "已使用次数")
    private Integer usageCount;

    @Schema(description = "最大限制次数，为空或0为不限制")
    private Integer maxLimitCount;

    @Schema(description = "超出提示信息")
    private String exceedTip;


    @Schema(description = "是否超出")
    private Boolean isExceed;

    public static UsageCountRecordMarkVO create(Long id,String usageCountKey,
                                                Integer usageCount,
                                                Integer maxLimitCount,
                                                String exceedTip,
                                                Integer version) {
        UsageCountRecordMarkVO usageCountRecordMarkVO = new UsageCountRecordMarkVO();
        usageCountRecordMarkVO.setId(id);
        usageCountRecordMarkVO.usageCountKey = usageCountKey;
        usageCountRecordMarkVO.usageCount = usageCount;
        usageCountRecordMarkVO.maxLimitCount = maxLimitCount;
        usageCountRecordMarkVO.exceedTip = exceedTip;
        usageCountRecordMarkVO.setVersion(version);

        usageCountRecordMarkVO.isExceed = usageCount > maxLimitCount;

        return usageCountRecordMarkVO;
    }
}
