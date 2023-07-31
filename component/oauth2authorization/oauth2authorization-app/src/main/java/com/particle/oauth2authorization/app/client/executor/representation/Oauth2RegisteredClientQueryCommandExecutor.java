package com.particle.oauth2authorization.app.client.executor.representation;

import com.particle.oauth2authorization.app.client.structmapping.Oauth2RegisteredClientAppStructMapping;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientQueryListCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.oauth2authorization.infrastructure.client.service.IOauth2RegisteredClientService;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * oauth2客户端 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Component
@Validated
public class Oauth2RegisteredClientQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOauth2RegisteredClientService iOauth2RegisteredClientService;

	/**
	 * 执行 oauth2客户端 列表查询指令
	 * @param oauth2RegisteredClientQueryListCommand
	 * @return
	 */
	public MultiResponse<Oauth2RegisteredClientVO> execute(@Valid Oauth2RegisteredClientQueryListCommand oauth2RegisteredClientQueryListCommand) {
		List<Oauth2RegisteredClientDO> oauth2RegisteredClientDO = iOauth2RegisteredClientService.list(oauth2RegisteredClientQueryListCommand);
		List<Oauth2RegisteredClientVO> oauth2RegisteredClientVOs = Oauth2RegisteredClientAppStructMapping.instance.oauth2RegisteredClientDOsToOauth2RegisteredClientVOs(oauth2RegisteredClientDO);
		return MultiResponse.of(oauth2RegisteredClientVOs);
	}
	/**
	 * 执行 oauth2客户端 分页查询指令
	 * @param oauth2RegisteredClientPageQueryCommand
	 * @return
	 */
	public PageResponse<Oauth2RegisteredClientVO> execute(@Valid Oauth2RegisteredClientPageQueryCommand oauth2RegisteredClientPageQueryCommand) {
		Page<Oauth2RegisteredClientDO> page = iOauth2RegisteredClientService.listPage(oauth2RegisteredClientPageQueryCommand);
		return Oauth2RegisteredClientAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 oauth2客户端 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<Oauth2RegisteredClientVO> executeDetail(IdCommand detailCommand) {
		Oauth2RegisteredClientDO byId = iOauth2RegisteredClientService.getById(detailCommand.getId());
		Oauth2RegisteredClientVO oauth2RegisteredClientVO = Oauth2RegisteredClientAppStructMapping.instance.oauth2RegisteredClientDOToOauth2RegisteredClientVO(byId);
		return SingleResponse.of(oauth2RegisteredClientVO);
	}
	/**
	 * 执行 oauth2客户端 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<Oauth2RegisteredClientVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		Oauth2RegisteredClientDO byId = iOauth2RegisteredClientService.getById(detailForUpdateCommand.getId());
		Oauth2RegisteredClientVO oauth2RegisteredClientVO = Oauth2RegisteredClientAppStructMapping.instance.oauth2RegisteredClientDOToOauth2RegisteredClientVO(byId);
		return SingleResponse.of(oauth2RegisteredClientVO);
	}

	@Autowired
	public void setIOauth2RegisteredClientService(IOauth2RegisteredClientService iOauth2RegisteredClientService) {
		this.iOauth2RegisteredClientService = iOauth2RegisteredClientService;
	}
}
