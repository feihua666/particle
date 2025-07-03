package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsSiteAppStructMapping;
import com.particle.cms.client.dto.command.CmsSiteCreateCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.domain.CmsSite;
import com.particle.cms.domain.gateway.CmsSiteGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 站点 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Component
@Validated
public class CmsSiteCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteGateway cmsSiteGateway;

	/**
	 * 执行站点添加指令
	 * @param cmsSiteCreateCommand
	 * @return
	 */
	public SingleResponse<CmsSiteVO> execute(@Valid CmsSiteCreateCommand cmsSiteCreateCommand) {
		CmsSite cmsSite = createByCmsSiteCreateCommand(cmsSiteCreateCommand);
		cmsSite.initForAdd();
		cmsSite.setAddControl(cmsSiteCreateCommand);
		boolean save = cmsSiteGateway.save(cmsSite);
		if (save) {
			return SingleResponse.of(CmsSiteAppStructMapping.instance.toCmsSiteVO(cmsSite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据站点创建指令创建站点模型
	 * @param cmsSiteCreateCommand
	 * @return
	 */
	private CmsSite createByCmsSiteCreateCommand(CmsSiteCreateCommand cmsSiteCreateCommand){
		CmsSite cmsSite = CmsSite.create();
		CmsSiteCreateCommandToCmsSiteMapping.instance.fillCmsSiteByCmsSiteCreateCommand(cmsSite, cmsSiteCreateCommand);
		return cmsSite;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsSiteCreateCommandToCmsSiteMapping{
		CmsSiteCreateCommandToCmsSiteMapping instance = Mappers.getMapper( CmsSiteCreateCommandToCmsSiteMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsSite
		 * @param cmsSiteCreateCommand
		 */
		void fillCmsSiteByCmsSiteCreateCommand(@MappingTarget CmsSite cmsSite, CmsSiteCreateCommand cmsSiteCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsSiteGateway
	 */
	@Autowired
	public void setCmsSiteGateway(CmsSiteGateway cmsSiteGateway) {
		this.cmsSiteGateway = cmsSiteGateway;
	}
}
