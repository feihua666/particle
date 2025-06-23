package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprGeograApproveAnnouncementAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograApproveAnnouncementDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprGeograApproveAnnouncementService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权地理标识核准公告出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService;

	/**
	 * 企业知识产权地理标识核准公告出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> exWarehouse(@Valid DataCompanyIprGeograApproveAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprGeograApproveAnnouncementDO> dataCompanyIprGeograApproveAnnouncementDOPage = iDataCompanyIprGeograApproveAnnouncementService.listPageByCompanyIprGeograId(dataCompanyExWarehouseQueryCommand.getCompanyIprGeograId(), dataCompanyExWarehouseQueryCommand);
		if (dataCompanyIprGeograApproveAnnouncementDOPage == null || dataCompanyIprGeograApproveAnnouncementDOPage.getRecords() == null || dataCompanyIprGeograApproveAnnouncementDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprGeograApproveAnnouncementDOPage);
	}
	/**
	 * 企业知识产权地理标识核准公告出库
	 * @param approvePublicNo
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> exWarehouseByCompanyIprGeograIdAndApprovePublicNo(Long companyIprGeograId,String approvePublicNo) {
		DataCompanyIprGeograApproveAnnouncementDO dataCompanyIprGeograApproveAnnouncementDO = iDataCompanyIprGeograApproveAnnouncementService.getByCompanyIprGeograIdAndApprovePublicNo(companyIprGeograId,approvePublicNo);
		if (dataCompanyIprGeograApproveAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprGeograApproveAnnouncementExWarehouseVO dataCompanyIprGeograApproveAnnouncementExWarehouseVO = DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncementExWarehouseVO(dataCompanyIprGeograApproveAnnouncementDO);
		return SingleResponse.of(dataCompanyIprGeograApproveAnnouncementExWarehouseVO);
	}
	/**
	 * 企业知识产权地理标识核准公告出库
	 * @param companyIprGeograIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> exWarehouseByCompanyIprGeograIds(List<Long> companyIprGeograIds) {
		List<DataCompanyIprGeograApproveAnnouncementDO> dataCompanyIprGeograApproveAnnouncementDOList = iDataCompanyIprGeograApproveAnnouncementService.listByCompanyIprGeograIds(companyIprGeograIds);
		if (CollectionUtil.isEmpty(dataCompanyIprGeograApproveAnnouncementDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> dataCompanyIprGeograApproveAnnouncementExWarehouseVOS = DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.dataCompanyIprGeograApproveAnnouncementDOsToDataCompanyIprGeograApproveAnnouncementExWarehouseVOs(dataCompanyIprGeograApproveAnnouncementDOList);
		return MultiResponse.of(dataCompanyIprGeograApproveAnnouncementExWarehouseVOS);
	}
	/**
	 * 企业知识产权地理标识核准公告出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprGeograApproveAnnouncementDO dataCompanyIprGeograApproveAnnouncementDO = iDataCompanyIprGeograApproveAnnouncementService.getById(id);
		if (dataCompanyIprGeograApproveAnnouncementDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprGeograApproveAnnouncementExWarehouseVO dataCompanyIprGeograApproveAnnouncementExWarehouseVO = DataCompanyIprGeograApproveAnnouncementAppStructMapping.instance.dataCompanyIprGeograApproveAnnouncementDOToDataCompanyIprGeograApproveAnnouncementExWarehouseVO(dataCompanyIprGeograApproveAnnouncementDO);
		return SingleResponse.of(dataCompanyIprGeograApproveAnnouncementExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprGeograApproveAnnouncementService(IDataCompanyIprGeograApproveAnnouncementService iDataCompanyIprGeograApproveAnnouncementService) {
		this.iDataCompanyIprGeograApproveAnnouncementService = iDataCompanyIprGeograApproveAnnouncementService;
	}
}
