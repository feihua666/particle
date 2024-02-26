package com.particle.feedback.adapter.reply.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.feedback.client.reply.api.IFeedbackReplyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 意见反馈回复前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-02-23 14:43:38
 */
@Tag(name = "意见反馈回复pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/feedback_reply")
public class FeedbackReplyFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IFeedbackReplyApplicationService iFeedbackReplyApplicationService;


}