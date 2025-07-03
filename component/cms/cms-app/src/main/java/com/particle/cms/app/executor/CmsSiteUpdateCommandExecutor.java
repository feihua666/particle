package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsSiteAppStructMapping;
import com.particle.cms.client.dto.command.CmsSiteUpdateCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.domain.CmsSite;
import com.particle.cms.domain.CmsSiteId;
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
 * 站点 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsSiteUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteGateway cmsSiteGateway;

	/**
	 * 执行 站点 更新指令
	 * @param cmsSiteUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsSiteVO> execute(@Valid CmsSiteUpdateCommand cmsSiteUpdateCommand) {
		CmsSite cmsSite = createByCmsSiteUpdateCommand(cmsSiteUpdateCommand);
		cmsSite.setUpdateControl(cmsSiteUpdateCommand);
		boolean save = cmsSiteGateway.save(cmsSite);
		if (save) {
			return SingleResponse.of(CmsSiteAppStructMapping.instance.toCmsSiteVO(cmsSite));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据站点更新指令创建站点模型
	 * @param cmsSiteUpdateCommand
	 * @return
	 */
	private CmsSite createByCmsSiteUpdateCommand(CmsSiteUpdateCommand cmsSiteUpdateCommand){
		CmsSite cmsSite = CmsSite.create();
		CmsSiteUpdateCommandToCmsSiteMapping.instance.fillCmsSiteByCmsSiteUpdateCommand(cmsSite, cmsSiteUpdateCommand);
		return cmsSite;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsSiteUpdateCommandToCmsSiteMapping{
		CmsSiteUpdateCommandToCmsSiteMapping instance = Mappers.getMapper(CmsSiteUpdateCommandToCmsSiteMapping.class );

		default CmsSiteId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsSiteId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsSite
		 * @param cmsSiteUpdateCommand
		 */
		void fillCmsSiteByCmsSiteUpdateCommand(@MappingTarget CmsSite cmsSite, CmsSiteUpdateCommand cmsSiteUpdateCommand);
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
