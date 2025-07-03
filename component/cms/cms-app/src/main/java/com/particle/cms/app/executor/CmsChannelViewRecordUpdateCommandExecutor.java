package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsChannelViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.CmsChannelViewRecordUpdateCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
import com.particle.cms.domain.CmsChannelViewRecord;
import com.particle.cms.domain.CmsChannelViewRecordId;
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
 * 栏目访问记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsChannelViewRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsChannelViewRecordGateway cmsChannelViewRecordGateway;

	/**
	 * 执行 栏目访问记录 更新指令
	 * @param cmsChannelViewRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsChannelViewRecordVO> execute(@Valid CmsChannelViewRecordUpdateCommand cmsChannelViewRecordUpdateCommand) {
		CmsChannelViewRecord cmsChannelViewRecord = createByCmsChannelViewRecordUpdateCommand(cmsChannelViewRecordUpdateCommand);
		cmsChannelViewRecord.setUpdateControl(cmsChannelViewRecordUpdateCommand);
		boolean save = cmsChannelViewRecordGateway.save(cmsChannelViewRecord);
		if (save) {
			return SingleResponse.of(CmsChannelViewRecordAppStructMapping.instance.toCmsChannelViewRecordVO(cmsChannelViewRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据栏目访问记录更新指令创建栏目访问记录模型
	 * @param cmsChannelViewRecordUpdateCommand
	 * @return
	 */
	private CmsChannelViewRecord createByCmsChannelViewRecordUpdateCommand(CmsChannelViewRecordUpdateCommand cmsChannelViewRecordUpdateCommand){
		CmsChannelViewRecord cmsChannelViewRecord = CmsChannelViewRecord.create();
		CmsChannelViewRecordUpdateCommandToCmsChannelViewRecordMapping.instance.fillCmsChannelViewRecordByCmsChannelViewRecordUpdateCommand(cmsChannelViewRecord, cmsChannelViewRecordUpdateCommand);
		return cmsChannelViewRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsChannelViewRecordUpdateCommandToCmsChannelViewRecordMapping{
		CmsChannelViewRecordUpdateCommandToCmsChannelViewRecordMapping instance = Mappers.getMapper(CmsChannelViewRecordUpdateCommandToCmsChannelViewRecordMapping.class );

		default CmsChannelViewRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsChannelViewRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsChannelViewRecord
		 * @param cmsChannelViewRecordUpdateCommand
		 */
		void fillCmsChannelViewRecordByCmsChannelViewRecordUpdateCommand(@MappingTarget CmsChannelViewRecord cmsChannelViewRecord, CmsChannelViewRecordUpdateCommand cmsChannelViewRecordUpdateCommand);
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
