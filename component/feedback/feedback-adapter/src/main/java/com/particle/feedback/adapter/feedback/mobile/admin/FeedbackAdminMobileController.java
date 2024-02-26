package com.particle.feedback.adapter.feedback.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.feedback.client.feedback.api.IFeedbackApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:42:51
 */
@Tag(name = "意见反馈移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/feedback")
public class FeedbackAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IFeedbackApplicationService iFeedbackApplicationService;


}