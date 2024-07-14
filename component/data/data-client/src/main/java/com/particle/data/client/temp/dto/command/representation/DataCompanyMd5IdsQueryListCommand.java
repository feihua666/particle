package com.particle.data.client.temp.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 企业md5ids 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Data
@Schema
public class DataCompanyMd5IdsQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "企业id")
    private Long companyId;


    @Schema(description = "uuid0")
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
