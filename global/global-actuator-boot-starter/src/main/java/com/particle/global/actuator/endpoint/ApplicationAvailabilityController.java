package com.particle.global.actuator.endpoint;

import com.particle.global.dto.response.Response;
import com.particle.global.tool.constant.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 服务可用性控制器
 * </p>
 *
 * @author yangwei
 * @since 2024/1/10 17:54
 */
@Tag(name = "服务可用性控制相关接口")
@RestController
@RequestMapping("/actuator/available")
public class ApplicationAvailabilityController {

    @PreAuthorize("hasAnyRole('"+ Constants.super_admin_role +"','availabel_refusing_traffic')")
    @Operation(summary = "下线")
    @PostMapping("/refusing_traffic")
    public Response refusing_traffic(){
        return AvailableTool.refusing_traffic();
    }
    @PreAuthorize("hasAnyRole('"+ Constants.super_admin_role +"','availabel_accepting_traffic')")
    @Operation(summary = "上线")
    @PostMapping("/accepting_traffic")
    public Response accepting_traffic(){
        return AvailableTool.accepting_traffic();
    }
    @PreAuthorize("hasAnyRole('"+ Constants.super_admin_role +"','availabel_broken')")
    @Operation(summary = "宕机")
    @PostMapping("/broken")
    public Response broken(){
        return AvailableTool.broken();
    }
    @PreAuthorize("hasAnyRole('"+ Constants.super_admin_role +"','availabel_correct')")
    @Operation(summary = "存活")
    @PostMapping("/correct")
    public Response correct(){
        return AvailableTool.correct();
    }
}
