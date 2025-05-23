package com.particle.usagecount.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.security.security.login.LoginUser;
import com.particle.usagecount.client.api.IUsageCountRecordApplicationService;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 使用次数记录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Tag(name = "使用次数记录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/usage_count_record")
public class UsageCountRecordFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IUsageCountRecordApplicationService iUsageCountRecordApplicationService;


	@PreAuthorize("hasAuthority('user')")
	@Operation(summary = "记录使用次数记录")
	@PostMapping("/mark")
	public SingleResponse<UsageCountRecordMarkVO> mark(@RequestBody UsageCountRecordMarkCommand usageCountRecordMarkCommand, @Parameter(hidden = true) LoginUser loginUser){
		usageCountRecordMarkCommand.setCurrentUserId(loginUser.getId());
		if (loginUser.getCurrentTenant() != null) {
			usageCountRecordMarkCommand.setCurrentTenantId(loginUser.getCurrentTenant().getId());
		}

		SingleResponse<UsageCountRecordMarkVO> mark = iUsageCountRecordApplicationService.mark(usageCountRecordMarkCommand);
		if (!mark.isSuccess() && ErrorCodeGlobalEnum.CONFIG_ERROR.getErrCode().equals(mark.getErrCode())) {
			// 如果没有配置时，直接不限制，不报 500 错误
			return SingleResponse.of(UsageCountRecordMarkVO.createEmpty());
		}

		return mark;
	}
}
