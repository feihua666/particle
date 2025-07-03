package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsSiteIndexViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordUpdateCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
import com.particle.cms.domain.CmsSiteIndexViewRecord;
import com.particle.cms.domain.CmsSiteIndexViewRecordId;
import com.particle.cms.domain.gateway.CmsSiteIndexViewRecordGateway;
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
 * 站点首页访问记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsSiteIndexViewRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteIndexViewRecordGateway cmsSiteIndexViewRecordGateway;

	/**
	 * 执行 站点首页访问记录 更新指令
	 * @param cmsSiteIndexViewRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsSiteIndexViewRecordVO> execute(@Valid CmsSiteIndexViewRecordUpdateCommand cmsSiteIndexViewRecordUpdateCommand) {
		CmsSiteIndexViewRecord cmsSiteIndexViewRecord = createByCmsSiteIndexViewRecordUpdateCommand(cmsSiteIndexViewRecordUpdateCommand);
		cmsSiteIndexViewRecord.setUpdateControl(cmsSiteIndexViewRecordUpdateCommand);
		boolean save = cmsSiteIndexViewRecordGateway.save(cmsSiteIndexViewRecord);
		if (save) {
			return SingleResponse.of(CmsSiteIndexViewRecordAppStructMapping.instance.toCmsSiteIndexViewRecordVO(cmsSiteIndexViewRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据站点首页访问记录更新指令创建站点首页访问记录模型
	 * @param cmsSiteIndexViewRecordUpdateCommand
	 * @return
	 */
	private CmsSiteIndexViewRecord createByCmsSiteIndexViewRecordUpdateCommand(CmsSiteIndexViewRecordUpdateCommand cmsSiteIndexViewRecordUpdateCommand){
		CmsSiteIndexViewRecord cmsSiteIndexViewRecord = CmsSiteIndexViewRecord.create();
		CmsSiteIndexViewRecordUpdateCommandToCmsSiteIndexViewRecordMapping.instance.fillCmsSiteIndexViewRecordByCmsSiteIndexViewRecordUpdateCommand(cmsSiteIndexViewRecord, cmsSiteIndexViewRecordUpdateCommand);
		return cmsSiteIndexViewRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsSiteIndexViewRecordUpdateCommandToCmsSiteIndexViewRecordMapping{
		CmsSiteIndexViewRecordUpdateCommandToCmsSiteIndexViewRecordMapping instance = Mappers.getMapper(CmsSiteIndexViewRecordUpdateCommandToCmsSiteIndexViewRecordMapping.class );

		default CmsSiteIndexViewRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsSiteIndexViewRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsSiteIndexViewRecord
		 * @param cmsSiteIndexViewRecordUpdateCommand
		 */
		void fillCmsSiteIndexViewRecordByCmsSiteIndexViewRecordUpdateCommand(@MappingTarget CmsSiteIndexViewRecord cmsSiteIndexViewRecord, CmsSiteIndexViewRecordUpdateCommand cmsSiteIndexViewRecordUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsSiteIndexViewRecordGateway
	 */
	@Autowired
	public void setCmsSiteIndexViewRecordGateway(CmsSiteIndexViewRecordGateway cmsSiteIndexViewRecordGateway) {
		this.cmsSiteIndexViewRecordGateway = cmsSiteIndexViewRecordGateway;
	}
}
