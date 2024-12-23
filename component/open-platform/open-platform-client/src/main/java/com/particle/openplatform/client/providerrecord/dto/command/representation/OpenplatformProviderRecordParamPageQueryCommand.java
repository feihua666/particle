package com.particle.openplatform.client.providerrecord.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Data
@Schema
public class OpenplatformProviderRecordParamPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "供应商调用记录id")
    private Long openplatformProviderRecordId;











}
