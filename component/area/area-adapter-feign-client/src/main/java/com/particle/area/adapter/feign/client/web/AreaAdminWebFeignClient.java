package com.particle.area.adapter.feign.client.web;

import com.particle.area.client.dto.command.AreaCreateCommand;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 区域远程调用
 * </p>
 *
 * @author yangwei
 * @since 2022-06-29 19:34
 */
@FeignClient(name = "${particle.feign-client.name.area:area}",path = "")
@RequestMapping("/admin/area")
public interface AreaAdminWebFeignClient {
	/**
	 * 添加区域
	 * @param areaCreateCommand
	 * @return
	 */
	@PostMapping
	public SingleResponse<AreaVO> create(@RequestBody AreaCreateCommand areaCreateCommand);
}
