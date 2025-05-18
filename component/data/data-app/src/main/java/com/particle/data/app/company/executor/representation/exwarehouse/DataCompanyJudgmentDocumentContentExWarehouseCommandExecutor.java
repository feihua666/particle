package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyJudgmentDocumentContentAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyJudgmentDocumentContentDO;
import com.particle.data.infrastructure.company.service.IDataCompanyJudgmentDocumentContentService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业裁判文书内容出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService;

	/**
	 * 企业裁判文书内容出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public SingleResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> exWarehouse(@Valid DataCompanyJudgmentDocumentContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		DataCompanyJudgmentDocumentContentDO dataCompanyJudgmentDocumentContentDO = iDataCompanyJudgmentDocumentContentService.getByCompanyJudgmentDocumentId(dataCompanyExWarehouseQueryCommand.getCompanyJudgmentDocumentId());
        if (dataCompanyJudgmentDocumentContentDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		DataCompanyJudgmentDocumentContentExWarehouseVO dataCompanyJudgmentDocumentContentExWarehouseVO = DataCompanyJudgmentDocumentContentAppStructMapping.instance.dataCompanyJudgmentDocumentContentDOToDataCompanyJudgmentDocumentContentExWarehouseVO(dataCompanyJudgmentDocumentContentDO);
		return SingleResponse.of(dataCompanyJudgmentDocumentContentExWarehouseVO);
	}
	/**
	 * 企业法院公告内容出库
	 * @param companyJudgmentDocumentIds
	 * @return
	 */
	public MultiResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> exWarehouseByCompanyJudgmentDocumentIds(List<Long> companyJudgmentDocumentIds) {
		List<DataCompanyJudgmentDocumentContentDO> dataCompanyJudgmentDocumentContentDOList = iDataCompanyJudgmentDocumentContentService.listByCompanyJudgmentDocumentIds(companyJudgmentDocumentIds);
		if (CollectionUtil.isEmpty(dataCompanyJudgmentDocumentContentDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyJudgmentDocumentContentExWarehouseVO> dataCompanyJudgmentDocumentContentExWarehouseVOS = DataCompanyJudgmentDocumentContentAppStructMapping.instance.dataCompanyJudgmentDocumentContentDOsToDataCompanyJudgmentDocumentContentExWarehouseVOs(dataCompanyJudgmentDocumentContentDOList);
		return MultiResponse.of(dataCompanyJudgmentDocumentContentExWarehouseVOS);
	}
	@Autowired
	public void setiDataCompanyJudgmentDocumentContentService(IDataCompanyJudgmentDocumentContentService iDataCompanyJudgmentDocumentContentService) {
		this.iDataCompanyJudgmentDocumentContentService = iDataCompanyJudgmentDocumentContentService;
	}
}
