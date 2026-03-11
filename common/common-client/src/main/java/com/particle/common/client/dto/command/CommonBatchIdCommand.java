package com.particle.common.client.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

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
public class CommonBatchIdCommand extends AbstractBatchIdCommand {

    public static CommonBatchIdCommand create(List<Long> ids) {
        CommonBatchIdCommand commonBatchIdCommand = new CommonBatchIdCommand();
        commonBatchIdCommand.setIds(ids);
        return commonBatchIdCommand;
    }
    public static CommonBatchIdCommand create(List<Long> ids, String scene) {
        CommonBatchIdCommand commonBatchIdCommand = create(ids);
        commonBatchIdCommand.setScene(scene);
        return commonBatchIdCommand;
    }
}
