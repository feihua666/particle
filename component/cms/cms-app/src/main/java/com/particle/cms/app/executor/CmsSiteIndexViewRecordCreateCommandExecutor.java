package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsSiteIndexViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
import com.particle.cms.domain.CmsSiteIndexViewRecord;
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
 * 站点首页访问记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Component
@Validated
public class CmsSiteIndexViewRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsSiteIndexViewRecordGateway cmsSiteIndexViewRecordGateway;

	/**
	 * 执行站点首页访问记录添加指令
	 * @param cmsSiteIndexViewRecordCreateCommand
	 * @return
	 */
	public SingleResponse<CmsSiteIndexViewRecordVO> execute(@Valid CmsSiteIndexViewRecordCreateCommand cmsSiteIndexViewRecordCreateCommand) {
		CmsSiteIndexViewRecord cmsSiteIndexViewRecord = createByCmsSiteIndexViewRecordCreateCommand(cmsSiteIndexViewRecordCreateCommand);
		cmsSiteIndexViewRecord.setAddControl(cmsSiteIndexViewRecordCreateCommand);
		boolean save = cmsSiteIndexViewRecordGateway.save(cmsSiteIndexViewRecord);
		if (save) {
			return SingleResponse.of(CmsSiteIndexViewRecordAppStructMapping.instance.toCmsSiteIndexViewRecordVO(cmsSiteIndexViewRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据站点首页访问记录创建指令创建站点首页访问记录模型
	 * @param cmsSiteIndexViewRecordCreateCommand
	 * @return
	 */
	private CmsSiteIndexViewRecord createByCmsSiteIndexViewRecordCreateCommand(CmsSiteIndexViewRecordCreateCommand cmsSiteIndexViewRecordCreateCommand){
		CmsSiteIndexViewRecord cmsSiteIndexViewRecord = CmsSiteIndexViewRecord.create();
		CmsSiteIndexViewRecordCreateCommandToCmsSiteIndexViewRecordMapping.instance.fillCmsSiteIndexViewRecordByCmsSiteIndexViewRecordCreateCommand(cmsSiteIndexViewRecord, cmsSiteIndexViewRecordCreateCommand);
		return cmsSiteIndexViewRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsSiteIndexViewRecordCreateCommandToCmsSiteIndexViewRecordMapping{
		CmsSiteIndexViewRecordCreateCommandToCmsSiteIndexViewRecordMapping instance = Mappers.getMapper( CmsSiteIndexViewRecordCreateCommandToCmsSiteIndexViewRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsSiteIndexViewRecord
		 * @param cmsSiteIndexViewRecordCreateCommand
		 */
		void fillCmsSiteIndexViewRecordByCmsSiteIndexViewRecordCreateCommand(@MappingTarget CmsSiteIndexViewRecord cmsSiteIndexViewRecord, CmsSiteIndexViewRecordCreateCommand cmsSiteIndexViewRecordCreateCommand);
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
