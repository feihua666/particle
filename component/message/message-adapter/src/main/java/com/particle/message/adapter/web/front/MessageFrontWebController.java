package com.particle.message.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.message.client.api.IMessageApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 消息前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:32:30
 */
@Tag(name = "消息pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/message")
public class MessageFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IMessageApplicationService iMessageApplicationService;


}