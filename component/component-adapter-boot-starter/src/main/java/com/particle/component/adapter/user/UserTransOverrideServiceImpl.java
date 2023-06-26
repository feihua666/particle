package com.particle.component.adapter.user;

import com.particle.tenant.infrastructure.dos.TenantUserDO;
import com.particle.tenant.infrastructure.service.ITenantUserService;
import com.particle.user.adapter.rpc.UserTransOverrideService;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
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
	private ITenantUserService iTenantUserService;

	@Override
	public List<UserTransOverrideDTO> getOverrideData(List<Long> userIds) {
		List<TenantUserDO> byUserIds = iTenantUserService.getByUserIds(userIds);
		return byUserIds.stream().filter(item -> Strings.isNotEmpty(item.getName())).map(item -> UserTransOverrideDTO.create(item.getId(), item.getName())).collect(Collectors.toList());
	}
}
