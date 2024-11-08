package com.particle.navigation.client.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 导航网站标签 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-07 09:38:23
 */
@Data
@Schema
public class NavigationSiteTagPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "标签编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "标签名称,左前缀匹配")
    private String name;


    @Schema(description = "分组")
    private Long groupDictId;











}
