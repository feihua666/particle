package com.particle.cms.app.executor;

import com.particle.cms.app.structmapping.CmsContentViewRecordAppStructMapping;
import com.particle.cms.client.dto.command.CmsContentViewRecordUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
import com.particle.cms.domain.CmsContentViewRecord;
import com.particle.cms.domain.CmsContentViewRecordId;
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
 * 内容访问记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class CmsContentViewRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private CmsContentViewRecordGateway cmsContentViewRecordGateway;

	/**
	 * 执行 内容访问记录 更新指令
	 * @param cmsContentViewRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<CmsContentViewRecordVO> execute(@Valid CmsContentViewRecordUpdateCommand cmsContentViewRecordUpdateCommand) {
		CmsContentViewRecord cmsContentViewRecord = createByCmsContentViewRecordUpdateCommand(cmsContentViewRecordUpdateCommand);
		cmsContentViewRecord.setUpdateControl(cmsContentViewRecordUpdateCommand);
		boolean save = cmsContentViewRecordGateway.save(cmsContentViewRecord);
		if (save) {
			return SingleResponse.of(CmsContentViewRecordAppStructMapping.instance.toCmsContentViewRecordVO(cmsContentViewRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据内容访问记录更新指令创建内容访问记录模型
	 * @param cmsContentViewRecordUpdateCommand
	 * @return
	 */
	private CmsContentViewRecord createByCmsContentViewRecordUpdateCommand(CmsContentViewRecordUpdateCommand cmsContentViewRecordUpdateCommand){
		CmsContentViewRecord cmsContentViewRecord = CmsContentViewRecord.create();
		CmsContentViewRecordUpdateCommandToCmsContentViewRecordMapping.instance.fillCmsContentViewRecordByCmsContentViewRecordUpdateCommand(cmsContentViewRecord, cmsContentViewRecordUpdateCommand);
		return cmsContentViewRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface CmsContentViewRecordUpdateCommandToCmsContentViewRecordMapping{
		CmsContentViewRecordUpdateCommandToCmsContentViewRecordMapping instance = Mappers.getMapper(CmsContentViewRecordUpdateCommandToCmsContentViewRecordMapping.class );

		default CmsContentViewRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return CmsContentViewRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param cmsContentViewRecord
		 * @param cmsContentViewRecordUpdateCommand
		 */
		void fillCmsContentViewRecordByCmsContentViewRecordUpdateCommand(@MappingTarget CmsContentViewRecord cmsContentViewRecord, CmsContentViewRecordUpdateCommand cmsContentViewRecordUpdateCommand);
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
