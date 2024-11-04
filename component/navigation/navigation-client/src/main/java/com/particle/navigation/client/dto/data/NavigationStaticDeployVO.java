package com.particle.navigation.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 导航网站静态部署 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-11-01 10:02:52
 */
@Data
@Schema
public class NavigationStaticDeployVO extends AbstractBaseIdVO {

    @Schema(description = "部署编码")
    private String code;
    
    @Schema(description = "部署名称")
    private String name;
    
    @Schema(description = "前端域名地址")
    private String frontDomain;
    
    @Schema(description = "前端上下文路径")
    private String frontContextPath;
    
    @Schema(description = "前端上下文路径的下一级路径")
    private String frontSubContextPath;
    
    @Schema(description = "是否纯静态部署")
    private Boolean isPureStaticDeploy;

	@Schema(description = "部署路径，如：/data/nav")
	private String deployPath;

	@Schema(description = "部署成功后执行的groovy脚本，方便进行额外处理")
	private String deployPostGroovyScript;
    
    @Schema(description = "上一次部署时间")
    private LocalDateTime lastDeployAt;
        
    @Schema(description = "描述")
    private String remark;
    


}