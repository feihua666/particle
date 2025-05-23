package com.particle.scheduler.domain.value;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 字典项信息
 * </p>
 *
 * @author yangwei
 * @since 2025-05-23 09:21:53
 */
@Data
public class SchedulerDictItemInfo {

    @Schema(description = "字典id")
    private Long id;

    @Schema(description = "字典编码")
    private String code;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典值")
    private String value;

    public static SchedulerDictItemInfo create(Long id,String code, String name, String value) {
        SchedulerDictItemInfo schedulerDictItemInfo = new SchedulerDictItemInfo();
        schedulerDictItemInfo.setId(id);
        schedulerDictItemInfo.setCode(code);
        schedulerDictItemInfo.setName(name);
        schedulerDictItemInfo.setValue(value);
        return schedulerDictItemInfo;
    }
}
