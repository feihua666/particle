package com.particle.tenant.adapter.login;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.AbstractUserDetailsService;
import com.particle.global.security.security.login.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 租户功能，登录用户相关
 * </p>
 *
 * @author yangwei
 * @since 2023-04-14 16:13
 */
@RestController
@RequestMapping("/tenant/login")
@Api(tags = "租户功能，登录用户相关")
public class TenantLoginController {

}
