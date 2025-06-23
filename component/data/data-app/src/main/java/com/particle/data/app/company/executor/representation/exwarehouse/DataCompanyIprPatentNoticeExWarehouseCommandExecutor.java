package com.particle.data.app.company.executor.representation.exwarehouse;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentNoticeAppStructMapping;
import com.particle.data.app.company.structmapping.DataCompanyIprPatentNoticeAppStructMapping;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentNoticeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentNoticeService;
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
 * 企业知识产权专利通知书信息出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentNoticeExWarehouseCommandExecutor extends AbstractBaseQueryExecutor {

	private IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService;

	/**
	 * 企业知识产权专利通知书信息出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentNoticeExWarehouseVO> exWarehouse(@Valid DataCompanyIprPatentNoticeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
		Page<DataCompanyIprPatentNoticeDO> dataCompanyIprPatentNoticeDOPage = null;
        if (dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId() != null) {
			dataCompanyIprPatentNoticeDOPage = iDataCompanyIprPatentNoticeService.listPageByCompanyIprPatentId(dataCompanyExWarehouseQueryCommand.getCompanyIprPatentId(),
					dataCompanyExWarehouseQueryCommand);
        }

		if (dataCompanyIprPatentNoticeDOPage == null || dataCompanyIprPatentNoticeDOPage.getRecords() == null || dataCompanyIprPatentNoticeDOPage.getRecords().isEmpty()) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
		return DataCompanyIprPatentNoticeAppStructMapping.instance.infrastructureExWarehousePageToPageResponse(dataCompanyIprPatentNoticeDOPage);
	}
	/**
	 * 企业知识产权专利通知书信息出库
	 * @param companyIprPatentIds
	 * @return
	 */
	public MultiResponse<DataCompanyIprPatentNoticeExWarehouseVO> exWarehouseByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
		List<DataCompanyIprPatentNoticeDO> dataCompanyIprPatentNoticeDOList = iDataCompanyIprPatentNoticeService.listByCompanyIprPatentIds(companyIprPatentIds);
		if (CollectionUtil.isEmpty(dataCompanyIprPatentNoticeDOList)) {
			return MultiResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		List<DataCompanyIprPatentNoticeExWarehouseVO> dataCompanyIprPatentNoticeExWarehouseVOS = DataCompanyIprPatentNoticeAppStructMapping.instance.dataCompanyIprPatentNoticeDOsToDataCompanyIprPatentNoticeExWarehouseVOs(dataCompanyIprPatentNoticeDOList);
		return MultiResponse.of(dataCompanyIprPatentNoticeExWarehouseVOS);
	}
	/**
	 * 企业知识产权专利通知书信息出库
	 * @param dataMd5
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeExWarehouseVO> exWarehouseByCompanyIprPatentIdAndDataMd5(Long companyIprPatentId,String dataMd5) {
		DataCompanyIprPatentNoticeDO dataCompanyIprPatentNoticeDO = iDataCompanyIprPatentNoticeService.getByCompanyIprPatentIdAndDataMd5(companyIprPatentId,dataMd5);
		if (dataCompanyIprPatentNoticeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentNoticeExWarehouseVO dataCompanyIprPatentNoticeExWarehouseVO = DataCompanyIprPatentNoticeAppStructMapping.instance.dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNoticeExWarehouseVO(dataCompanyIprPatentNoticeDO);
		return SingleResponse.of(dataCompanyIprPatentNoticeExWarehouseVO);
	}
	/**
	 * 企业知识产权专利通知书信息出库
	 * @param id
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentNoticeExWarehouseVO> exWarehouseById(Long id) {
		DataCompanyIprPatentNoticeDO dataCompanyIprPatentNoticeDO = iDataCompanyIprPatentNoticeService.getById(id);
		if (dataCompanyIprPatentNoticeDO == null) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		DataCompanyIprPatentNoticeExWarehouseVO dataCompanyIprPatentNoticeExWarehouseVO = DataCompanyIprPatentNoticeAppStructMapping.instance.dataCompanyIprPatentNoticeDOToDataCompanyIprPatentNoticeExWarehouseVO(dataCompanyIprPatentNoticeDO);
		return SingleResponse.of(dataCompanyIprPatentNoticeExWarehouseVO);
	}

	@Autowired
	public void setiDataCompanyIprPatentNoticeService(IDataCompanyIprPatentNoticeService iDataCompanyIprPatentNoticeService) {
		this.iDataCompanyIprPatentNoticeService = iDataCompanyIprPatentNoticeService;
	}
}
