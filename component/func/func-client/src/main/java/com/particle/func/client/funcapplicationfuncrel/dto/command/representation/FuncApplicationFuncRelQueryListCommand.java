package com.particle.func.client.funcapplicationfuncrel.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 功能应用功能关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Data
@Schema
public class FuncApplicationFuncRelQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "功能应用id")
    private Long funcApplicationId;


    @Schema(description = "功能id")
    private Long funcId;









}
