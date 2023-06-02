package com.particle.tracking.adapter.web.front;

import brave.Tracer;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.common.constant.CommonConstants;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.login.LoginUser;
import com.particle.global.tool.servlet.RequestTool;
import com.particle.tracking.app.structmapping.TrackingPageRecordAppStructMapping;
import com.particle.tracking.client.api.ITrackingPageRecordApplicationService;
import com.particle.tracking.client.dto.command.TrackingPageRecordCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageRecordFrontCreateCommand;
import com.particle.tracking.client.dto.command.TrackingPageRecordUpdateCommand;
import com.particle.tracking.client.dto.data.TrackingPageRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 页面埋点记录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-10 11:44:01
 */
@Api(tags = "页面埋点记录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/tracking_page_record")
public class TrackingPageRecordFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITrackingPageRecordApplicationService iTrackingPageRecordApplicationService;
	@Autowired
	private Tracer tracer;

	@ApiOperation("添加页面埋点记录")
	@PreAuthorize("hasAuthority('user')")
	@PostMapping("/create")
	public SingleResponse<AbstractBaseIdVO> create(@RequestBody TrackingPageRecordFrontCreateCommand trackingPageRecordFrontCreateCommand ,
												   @ApiIgnore LoginUser loginUser,
												   @ApiIgnore HttpServletRequest httpServletRequest){
		TrackingPageRecordCreateCommand trackingPageRecordCreateCommand = TrackingPageRecordAppStructMapping.instance
				.trackingPageRecordFrontCreateCommandToTrackingPageRecordCreateCommand(trackingPageRecordFrontCreateCommand);
		if (trackingPageRecordCreateCommand.getActionAt() == null) {
			trackingPageRecordCreateCommand.setActionAt(LocalDateTime.now());
		}
		if (trackingPageRecordCreateCommand.getEntryAt() == null) {
			trackingPageRecordCreateCommand.setEntryAt(LocalDateTime.now());
		}
		if (trackingPageRecordCreateCommand.getEntryAt() != null && trackingPageRecordCreateCommand.getLeaveAt() != null && trackingPageRecordCreateCommand.getDuration() == null) {
			Duration between = LocalDateTimeUtil.between(trackingPageRecordCreateCommand.getEntryAt(), trackingPageRecordCreateCommand.getLeaveAt());
			trackingPageRecordCreateCommand.setDuration(between.toMillis());
		}

		if (loginUser != null) {
			trackingPageRecordCreateCommand.setUserId(loginUser.getId());
			trackingPageRecordCreateCommand.setUserNickname(loginUser.getNickname());
			trackingPageRecordCreateCommand.setUserAvatar(loginUser.getAvatar());
		}

		String userAgentStr = ServletUtil.getHeaderIgnoreCase(httpServletRequest, "User-Agent");
		UserAgent userAgent = UserAgentUtil.parse(userAgentStr);

		String clientIP = RequestTool.getClientIP(httpServletRequest);
		trackingPageRecordCreateCommand.setIp(clientIP);

		String deviceId = ServletUtil.getHeaderIgnoreCase(httpServletRequest, CommonConstants.request_header_device_id);
		trackingPageRecordCreateCommand.setDeviceId(Optional.ofNullable(StrUtil.emptyToNull(deviceId)).orElse("none"));
		trackingPageRecordCreateCommand.setDeviceName(userAgent.getPlatform().getName());


		trackingPageRecordCreateCommand.setOperatingSystem(userAgent.getOs().getName());

		HttpSession session = httpServletRequest.getSession(false);
		if (session != null) {
			trackingPageRecordCreateCommand.setSession(session.getId());
			trackingPageRecordCreateCommand.setSessionMd5(DigestUtil.md5Hex(session.getId()));
		} else {
			trackingPageRecordCreateCommand.setSession("none");
			trackingPageRecordCreateCommand.setSessionMd5("none");
		}

		trackingPageRecordCreateCommand.setTraceId(tracer.currentSpan().context().traceIdString());

		return iTrackingPageRecordApplicationService.create(trackingPageRecordCreateCommand);
	}


	@ApiOperation("更新页面埋点记录")
	@PreAuthorize("hasAuthority('user')")
	@PutMapping("/update")
	public SingleResponse<TrackingPageRecordVO> update(@RequestBody TrackingPageRecordUpdateCommand trackingPageRecordUpdateCommand){
		return iTrackingPageRecordApplicationService.update(trackingPageRecordUpdateCommand);
	}
}