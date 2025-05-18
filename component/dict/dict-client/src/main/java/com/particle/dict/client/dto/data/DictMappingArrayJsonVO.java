package com.particle.dict.client.dto.data;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 字典映射 响应对象
 * </p>
 *
 * @author yw
 * @since 2025-05-09 11:41:18
 */
@Data
@Schema
public class DictMappingArrayJsonVO extends VO {

    @Schema(description = "映射值")
    private String mappingValue;

    @Schema(description = "备注")
    private String remark;

    /**
     * 匹配
     * @param mappingValue
     * @return
     */
    public boolean match(String mappingValue) {
        return StrUtil.equals(this.mappingValue, mappingValue);
    }
}
