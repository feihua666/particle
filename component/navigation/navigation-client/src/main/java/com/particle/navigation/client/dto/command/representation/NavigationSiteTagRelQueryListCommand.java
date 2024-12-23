package com.particle.navigation.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 导航网站标签关系 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:39:14
 */
@Data
@Schema
public class NavigationSiteTagRelQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "网站id")
    private Long navigationSiteId;


    @Schema(description = "网站标签id")
    private Long navigationSiteTagId;









}
