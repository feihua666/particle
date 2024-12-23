package com.particle.common.client.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 适合于通用的根据id批量处理的情况，如：根据id查询或根据id删除
 * </p>
 *
 * @author yw
 * @since 2023-06-21 10:00:47
 */
@Data
@Schema
public class BatchIdCommand extends AbstractBatchIdCommand {

}
