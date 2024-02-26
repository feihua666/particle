package com.particle.feedback.adapter.feedback.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.feedback.client.feedback.api.IFeedbackApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Tag(name = "意见反馈wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/feedback")
public class FeedbackAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IFeedbackApplicationService iFeedbackApplicationService;


}