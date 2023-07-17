package com.particle.global.notification.endpoint;

import com.particle.global.dto.response.Response;
import com.particle.global.notification.notify.NotifyParam;
import com.particle.global.notification.notify.NotifyTool;
import com.particle.global.tool.constant.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 通知相关服务接口
 * </p>
 *
 * @author yangwei
 * @since 2023-07-17 20:13:42
 */
@Api(tags = "全局服务通知相关接口")
@RestController
@RequestMapping("/notify")
public class NotifyController {
	/**
	 * 手动发送通知是一个危险操作，只有超级管理员才能使用
	 * @param notifyParam
	 */
	@PreAuthorize("hasAnyRole('"+ Constants.super_admin_role +"','manualNotify')")
	@ApiOperation("手动发送通知")
	@PostMapping( "/manualNotify")
	public Response manualNotify(@RequestBody NotifyParam notifyParam){
		NotifyTool.notify(notifyParam);
		return Response.buildSuccess();
	}


}