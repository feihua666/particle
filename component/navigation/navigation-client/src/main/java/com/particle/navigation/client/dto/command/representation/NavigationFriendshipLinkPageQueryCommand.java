package com.particle.navigation.client.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 导航友情链接 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:01
 */
@Data
@Schema
public class NavigationFriendshipLinkPageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "网站名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "网站标题,左前缀匹配")
    private String title;





    @Schema(description = "是否已发布")
    private Boolean isPublished;











}