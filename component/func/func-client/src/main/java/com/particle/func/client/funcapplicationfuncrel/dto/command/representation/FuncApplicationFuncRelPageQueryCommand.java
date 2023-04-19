package com.particle.func.client.funcapplicationfuncrel.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * <p>
 * 功能应用功能关系 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Data
@ApiModel
public class FuncApplicationFuncRelPageQueryCommand extends AbstractBasePageQueryCommand {



    @ApiModelProperty(value = "功能应用id")
    private Long funcApplicationId;


    @ApiModelProperty(value = "功能id")
    private Long funcId;









}
