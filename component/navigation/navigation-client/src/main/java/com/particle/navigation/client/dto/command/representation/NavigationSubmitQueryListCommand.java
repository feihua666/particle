package com.particle.navigation.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
import java.time.LocalDateTime;
/**
 * <p>
 * 导航提交 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Data
@Schema
public class NavigationSubmitQueryListCommand extends AbstractBaseQueryCommand {



    @Like
        @Schema(description = "网站名称,左前缀匹配")
    private String name;


    @Like
        @Schema(description = "网站标题,左前缀匹配")
    private String title;



    @Schema(description = "提交时间")
    private LocalDateTime submitAt;
    

    @Schema(description = "状态")
    private Long statusDictId;

	@Schema(description = "网站数据json，数据补充时先补充到这里，最后提交到网站表中")
	private String siteDataJson;










}