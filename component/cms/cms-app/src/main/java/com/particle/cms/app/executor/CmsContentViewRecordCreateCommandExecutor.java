package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
import com.particle.cms.domain.CmsContentViewRecord;
import com.particle.cms.domain.gateway.CmsContentViewRecordGateway;
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
 * 内容访问记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Component
@Validated
public class CmsContentViewRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentViewRecordGateway cmsContentViewRecordGateway;

	/**
	 * 执行内容访问记录添加指令
	 * @param cmsContentViewRecordCreateCommand
	 * @return
	 */
	public SingleResponse<CmsContentViewRecordVO> execute(@Valid CmsContentViewRecordCreateCommand cmsContentViewRecordCreateCommand) {
		CmsContentViewRecord cmsContentViewRecord = createByCmsContentViewRecordCreateCommand(cmsContentViewRecordCreateCommand);
		cmsContentViewRecord.setAddControl(cmsContentViewRecordCreateCommand);
		boolean save = cmsContentViewRecordGateway.save(cmsContentViewRecord);
		if (save) {
			return SingleResponse.of(CmsContentViewRecordAppStructMapping.instance.toCmsContentViewRecordVO(cmsContentViewRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据内容访问记录创建指令创建内容访问记录模型
	 * @param cmsContentViewRecordCreateCommand
	 * @return
	 */
	private CmsContentViewRecord createByCmsContentViewRecordCreateCommand(CmsContentViewRecordCreateCommand cmsContentViewRecordCreateCommand){
		CmsContentViewRecord cmsContentViewRecord = CmsContentViewRecord.create();
		CmsContentViewRecordCreateCommandToCmsContentViewRecordMapping.instance.fillCmsContentViewRecordByCmsContentViewRecordCreateCommand(cmsContentViewRecord, cmsContentViewRecordCreateCommand);
		return cmsContentViewRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  CmsContentViewRecordCreateCommandToCmsContentViewRecordMapping{
		CmsContentViewRecordCreateCommandToCmsContentViewRecordMapping instance = Mappers.getMapper( CmsContentViewRecordCreateCommandToCmsContentViewRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContentViewRecord
		 * @param cmsContentViewRecordCreateCommand
		 */
		void fillCmsContentViewRecordByCmsContentViewRecordCreateCommand(@MappingTarget CmsContentViewRecord cmsContentViewRecord, CmsContentViewRecordCreateCommand cmsContentViewRecordCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param cmsContentViewRecordGateway
	 */
	@Autowired
	public void setCmsContentViewRecordGateway(CmsContentViewRecordGateway cmsContentViewRecordGateway) {
		this.cmsContentViewRecordGateway = cmsContentViewRecordGateway;
	}
}
