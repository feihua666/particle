package com.particle.openplatform.client.app.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 开放平台应用额度 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Data
@Schema
public class OpenplatformAppQuotaPageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;


    @Schema(description = "限制方式")
    private Long limitTypeDictId;


    @Schema(description = "限制条数")
    private Integer limitCount;


    @Schema(description = "限制金额费用")
    private Integer limitFee;










}
