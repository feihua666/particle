package com.particle.navigation.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 导航提交 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Data
@Schema
public class NavigationSubmitUpdateCommand extends AbstractBaseUpdateCommand {



    @NotEmpty(message = "网站名称 不能为空")
        @Schema(description = "网站名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;


    @NotEmpty(message = "网站标题 不能为空")
        @Schema(description = "网站标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;


    @NotEmpty(message = "网站地址 不能为空")
        @Schema(description = "网站地址",requiredMode = Schema.RequiredMode.REQUIRED)
    private String url;


    @NotNull(message = "状态 不能为空")
        @Schema(description = "状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long statusDictId;

	@Schema(description = "网站数据json，数据补充时先补充到这里，最后提交到网站表中")
	private String siteDataJson;


    @Schema(description = "描述")
    private String remark;









}
