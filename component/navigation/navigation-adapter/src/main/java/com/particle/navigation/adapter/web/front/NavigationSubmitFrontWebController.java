package com.particle.navigation.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.global.dataaudit.op.OpLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.navigation.client.api.INavigationSubmitApplicationService;
import com.particle.navigation.client.dto.command.NavigationSubmitCreateCommand;
import com.particle.navigation.client.dto.data.NavigationSubmitVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 导航提交前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-11-03 11:09:19
 */
@Tag(name = "导航提交pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/navigation_submit")
public class NavigationSubmitFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private INavigationSubmitApplicationService iNavigationSubmitApplicationService;

	@Operation(summary = "导航提交")
	@PostMapping("/create")
	@OpLog(name = "导航提交",module = OpLogConstants.Module.navigation,type = OpLogConstants.Type.create)
	public SingleResponse<NavigationSubmitVO> create(@RequestBody NavigationSubmitCreateCommand navigationSubmitCreateCommand){
		return iNavigationSubmitApplicationService.create(navigationSubmitCreateCommand);
	}
}
