package com.particle.global.workflow.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 执行记录 VO（基类，只含 executionId）
 * <p>
 * execute 接口返回此对象。
 * </p>
 *
 * @author particle
 * @since 2026-05-02
 */
@Data
public class ExecutionVO {

    @Schema(description = "执行记录ID")
    private Long executionId;
}
