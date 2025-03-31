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
public class IdCommand extends AbstractIdCommand {
    public static IdCommand create(Long id) {
        IdCommand idCommand = new IdCommand();
        idCommand.setId(id);
        return idCommand;
    }
    public static IdCommand create(Long id,String scene) {
        IdCommand idCommand = create(id);
        idCommand.setScene(scene);
        return idCommand;
    }
}
