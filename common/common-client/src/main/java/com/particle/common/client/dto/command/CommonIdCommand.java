package com.particle.common.client.dto.command;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 适合于通用的根据id处理的情况，如：根据id查询或根据id删除
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@Schema
public class CommonIdCommand extends AbstractIdCommand {
    public static CommonIdCommand create(Long id) {
        CommonIdCommand commonIdCommand = new CommonIdCommand();
        commonIdCommand.setId(id);
        return commonIdCommand;
    }
    public static CommonIdCommand create(Long id, String scene) {
        CommonIdCommand commonIdCommand = create(id);
        commonIdCommand.setScene(scene);
        return commonIdCommand;
    }
}
