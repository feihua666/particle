package com.particle.component.autoconfigure.user;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.BatchIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import com.particle.tenant.client.dto.data.TenantUserVO;
import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import com.particle.user.adapter.rpc.UserTransOverrideService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户翻译覆盖，主要是使用租户的人员姓名
 * </p>
 *
 * @author yangwei
 * @since 2023-06-26 15:57
 */
public class UserTransOverrideServiceImpl implements UserTransOverrideService {

	@Autowired
	private TenantUserRpcFeignClient tenantUserRpcFeignClient;

	@Override
	public List<UserTransOverrideDTO> getOverrideData(List<Long> userIds) {
        BatchIdCommand batchIdCommand = BatchIdCommand.create(userIds);
        MultiResponse<TenantUserVO> tenantUserVOMultiResponse = tenantUserRpcFeignClient.queryListByUserIds(batchIdCommand);
        List<TenantUserVO> tenantUserVOS = tenantUserVOMultiResponse.getData();
		return tenantUserVOS.stream().filter(item -> StrUtil.isNotEmpty(item.getName())).map(item -> UserTransOverrideDTO.create(item.getId(), item.getName())).collect(Collectors.toList());
	}
}
