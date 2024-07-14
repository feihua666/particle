package com.particle.data.client.temp.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 企业md5ids 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Data
@Schema
public class DataCompanyMd5IdsCreateCommand extends AbstractBaseCommand {



    @NotNull(message = "企业id 不能为空")
        @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotEmpty(message = "uuid0 不能为空")
        @Schema(description = "uuid0",requiredMode = Schema.RequiredMode.REQUIRED)
    private String parentIdUuid0;


    @Schema(description = "uuid1")
    private String parentIdUuid1;


    @Schema(description = "uuid2")
    private String parentIdUuid2;


    @Schema(description = "uuid3")
    private String parentIdUuid3;


    @Schema(description = "uuid4")
    private String parentIdUuid4;


    @Schema(description = "uuid5")
    private String parentIdUuid5;


    @Schema(description = "uuid6")
    private String parentIdUuid6;


    @Schema(description = "uuid7")
    private String parentIdUuid7;


    @Schema(description = "uuid8")
    private String parentIdUuid8;


    @Schema(description = "uuid9")
    private String parentIdUuid9;


    @Schema(description = "uuid10")
    private String parentIdUuid10;


    @Schema(description = "uuid11")
    private String parentIdUuid11;









}
