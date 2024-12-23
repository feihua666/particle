package com.particle.navigation.client.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 导航网站静态部署 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Data
@Schema
public class NavigationStaticDeployQueryListCommand extends AbstractBaseQueryCommand {

	@Schema(description = "部署路径，如：/data/nav")
	private String deployPath;

	@Schema(description = "部署成功后执行的groovy脚本，方便进行额外处理")
	private String deployPostGroovyScript;



    @Like
        @Schema(description = "部署编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "部署名称,左前缀匹配")
    private String name;















}
