package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsChannelViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.CmsChannelViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
import com.particle.cms.domain.CmsChannelViewRecord;
import com.particle.cms.domain.gateway.CmsChannelViewRecordGateway;
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
 * 栏目访问记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Component
@Validated
public class CmsChannelViewRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelViewRecordGateway cmsChannelViewRecordGateway;

	/**
	 * 执行栏目访问记录添加指令
	 * @param cmsChannelViewRecordCreateCommand
	 * @return
	 */
	public SingleResponse<CmsChannelViewRecordVO> execute(@Valid CmsChannelViewRecordCreateCommand cmsChannelViewRecordCreateCommand) {
		CmsChannelViewRecord cmsChannelViewRecord = createByCmsChannelViewRecordCreateCommand(cmsChannelViewRecordCreateCommand);
		cmsChannelViewRecord.setAddControl(cmsChannelViewRecordCreateCommand);
		boolean save = cmsChannelViewRecordGateway.save(cmsChannelViewRecord);
		if (save) {
			return SingleResponse.of(CmsChannelViewRecordAppStructMapping.instance.toCmsChannelViewRecordVO(cmsChannelViewRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据栏目访问记录创建指令创建栏目访问记录模型
	 * @param cmsChannelViewRecordCreateCommand
	 * @return
	 */
	private CmsChannelViewRecord createByCmsChannelViewRecordCreateCommand(CmsChannelViewRecordCreateCommand cmsChannelViewRecordCreateCommand){
		CmsChannelViewRecord cmsChannelViewRecord = CmsChannelViewRecord.create();
		CmsChannelViewRecordCreateCommandToCmsChannelViewRecordMapping.instance.fillCmsChannelViewRecordByCmsChannelViewRecordCreateCommand(cmsChannelViewRecord, cmsChannelViewRecordCreateCommand);
		return cmsChannelViewRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsChannelViewRecordCreateCommandToCmsChannelViewRecordMapping{
		CmsChannelViewRecordCreateCommandToCmsChannelViewRecordMapping instance = Mappers.getMapper( CmsChannelViewRecordCreateCommandToCmsChannelViewRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsChannelViewRecord
		 * @param cmsChannelViewRecordCreateCommand
		 */
		void fillCmsChannelViewRecordByCmsChannelViewRecordCreateCommand(@MappingTarget CmsChannelViewRecord cmsChannelViewRecord, CmsChannelViewRecordCreateCommand cmsChannelViewRecordCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsChannelViewRecordGateway
	 */
	@Autowired
	public void setCmsChannelViewRecordGateway(CmsChannelViewRecordGateway cmsChannelViewRecordGateway) {
		this.cmsChannelViewRecordGateway = cmsChannelViewRecordGateway;
	}
}
