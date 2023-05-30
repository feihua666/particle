package com.particle.tenant.infrastructure.gateway.impl;

import com.particle.component.light.share.dict.Gender;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.tenant.domain.gateway.TenantUserUserGateway;
import com.particle.user.adapter.feign.client.identifier.rpc.UserIdentifierRpcFeignClient;
import com.particle.user.adapter.feign.client.rpc.UserRpcFeignClient;
import com.particle.user.client.dto.command.UserCreateCommand;
import com.particle.user.client.dto.data.UserVO;
import com.particle.user.client.identifier.dto.command.UserIdentifierPwdCommand;
import com.particle.user.client.identifier.dto.command.UserIdentifierSimpleCreateCommand;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.domain.enums.UserCategory;
import com.particle.user.domain.enums.UserSourceFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户模块依赖远程调用实现类
 * </p>
 *
 * @author yangwei
 * @since 2023-05-15 13:03
 */
@Component
public class TenantUserUserGatewayImpl implements TenantUserUserGateway {

	private UserRpcFeignClient userRpcFeignClient;
	private DictRpcFeignClient dictRpcFeignClient;
	private UserIdentifierRpcFeignClient userIdentifierRpcFeignClient;


	@Override
	public Long createUser(String name, String nickname, List<IdentifierParam> identifiers, String password, String userAddScene) {
		UserCreateCommand userCreateCommand = new UserCreateCommand();
		// 基本设置
		userCreateCommand.setName(name);
		userCreateCommand.setNickname(nickname);
		userCreateCommand.setScene(userAddScene);

		List<UserIdentifierSimpleCreateCommand> userIdentifierSimpleCreateCommands = identifiers.stream().map(item -> {
			UserIdentifierSimpleCreateCommand userIdentifierSimpleCreateCommand = new UserIdentifierSimpleCreateCommand();
			userIdentifierSimpleCreateCommand.setIdentifier(item.getIdentifier());
			userIdentifierSimpleCreateCommand.setIdentityTypeDictId(item.getIdentityTypeDictId());
			userIdentifierSimpleCreateCommand.setIdentityTypeDictValue(item.getIdentityTypeDictValue());
			return userIdentifierSimpleCreateCommand;
		}).collect(Collectors.toList());
		userCreateCommand.setIdentifiers(userIdentifierSimpleCreateCommands);

		// 性别 字典
		SingleResponse<DictVO> genderUnknown = dictRpcFeignClient.getByGroupCodeAndItemValue(Gender.Group.gender.groupCode(), Gender.unknown.itemValue());
		userCreateCommand.setGenderDictId(genderUnknown.getData().getId());
		// 默认真实用户
		userCreateCommand.setIsVirtual(false);

		// 来源 字典
		SingleResponse<DictVO> userSourceFromBackendAdd = dictRpcFeignClient.getByGroupCodeAndItemValue(UserSourceFrom.Group.user_source_from.groupCode(), UserSourceFrom.backend_add.itemValue());
		userCreateCommand.setSourceFromDictId(userSourceFromBackendAdd.getData().getId());

		// 分类 字典
		SingleResponse<DictVO> userCategoryOther = dictRpcFeignClient.getByGroupCodeAndItemValue(UserCategory.Group.user_category.groupCode(),  UserCategory.other.itemValue());
		userCreateCommand.setCategoryDictId(userCategoryOther.getData().getId());


		// 密码
		UserIdentifierPwdCommand userIdentifierPwdCommand = new UserIdentifierPwdCommand();
		userIdentifierPwdCommand.setPassword(password);
		SingleResponse<UserVO> userVOSingleResponse = userRpcFeignClient.create(userCreateCommand, userIdentifierPwdCommand);

		return userVOSingleResponse.getData().getId();
	}

	@Override
	public Long getByUserIdentifier(String identifier) {
		SingleResponse<UserIdentifierVO> byIdentifier = userIdentifierRpcFeignClient.getByIdentifier(identifier);
		if (byIdentifier.getData() == null) {
			return null;
		}
		return byIdentifier.getData().getUserId();
	}

	@Override
	public String getIdentifierByUserIdAndType(Long userId, Long identityTypeDictId) {
		SingleResponse<UserIdentifierVO> byUserIdAndType = userIdentifierRpcFeignClient.getByUserIdAndType(userId, identityTypeDictId);
		if (byUserIdAndType.getData() != null) {
			return byUserIdAndType.getData().getIdentifier();
		}
		return null;
	}

	@Autowired
	public void setUserRpcFeignClient(UserRpcFeignClient userRpcFeignClient) {
		this.userRpcFeignClient = userRpcFeignClient;
	}
	@Autowired
	public void setDictRpcFeignClient(DictRpcFeignClient dictRpcFeignClient) {
		this.dictRpcFeignClient = dictRpcFeignClient;
	}
	@Autowired
	public void setUserIdentifierRpcFeignClient(UserIdentifierRpcFeignClient userIdentifierRpcFeignClient) {
		this.userIdentifierRpcFeignClient = userIdentifierRpcFeignClient;
	}
}
