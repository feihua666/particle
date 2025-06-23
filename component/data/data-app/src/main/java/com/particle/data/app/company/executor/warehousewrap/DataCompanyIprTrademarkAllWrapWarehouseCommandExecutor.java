package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkLicenseWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkPartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkPledgeWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprTrademarkTransferWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkLicenseWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkPledgeWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprTrademarkTransferWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.data.domain.gateway.DataDictGateway;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权商标全部入库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkAllWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

	private DataCompanyIprTrademarkLicenseWarehouseCommandExecutor dataCompanyIprTrademarkLicenseWarehouseCommandExecutor;
	private DataCompanyIprTrademarkPartyWarehouseCommandExecutor dataCompanyIprTrademarkPartyWarehouseCommandExecutor;
	private DataCompanyIprTrademarkPledgeWarehouseCommandExecutor dataCompanyIprTrademarkPledgeWarehouseCommandExecutor;
	private DataCompanyIprTrademarkTransferWarehouseCommandExecutor dataCompanyIprTrademarkTransferWarehouseCommandExecutor;
	private DataCompanyIprTrademarkWrapWarehouseCommandExecutor dataCompanyIprTrademarkWrapWarehouseCommandExecutor;

	private DataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor dataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor;

	private DataDictGateway dataDictGateway;
	/**
	 * 企业知识产权商标全部入库
	 * @param dataCompanyIprTrademarkAllExWarehouseVOPageResponse
	 * @return
	 */
	public void warehouse(PageResponse<DataCompanyIprTrademarkAllExWarehouseVO> dataCompanyIprTrademarkAllExWarehouseVOPageResponse) {
		List<DataCompanyIprTrademarkAllExWarehouseVO> data = dataCompanyIprTrademarkAllExWarehouseVOPageResponse.getData();
		if (CollectionUtil.isNotEmpty(data)) {
			for (DataCompanyIprTrademarkAllExWarehouseVO companyIprTrademarkAllExWarehouseVO : data) {
				DataCompanyIprTrademarkExWarehouseVO basic = companyIprTrademarkAllExWarehouseVO.getBasic();
				Long dataCompanyIprTrademarkId = null;
				if (basic != null) {
					SingleResponse<DataCompanyIprTrademarkExWarehouseVO> dataCompanyIprTrademarkExWarehouseVOSingleResponse = dataCompanyIprTrademarkWrapWarehouseCommandExecutor.warehouse(basic);
					dataCompanyIprTrademarkId = dataCompanyIprTrademarkExWarehouseVOSingleResponse.getData().getId();
				}

				List<DataCompanyIprTrademarkPartyExWarehouseVO> parties = companyIprTrademarkAllExWarehouseVO.getParties();
				if (CollectionUtil.isNotEmpty(parties)) {
					dataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor.warehouse(parties, dataCompanyIprTrademarkId);
				}

				List<DataCompanyIprTrademarkLicenseExWarehouseVO> licenses = companyIprTrademarkAllExWarehouseVO.getLicenses();
				if (CollectionUtil.isNotEmpty(licenses)) {
					for (DataCompanyIprTrademarkLicenseExWarehouseVO license : licenses) {
						DataCompanyIprTrademarkLicenseWarehouseCommand dataCompanyIprTrademarkLicenseWarehouseCommand = DataCompanyIprTrademarkLicenseWarehouseCommand.createByDataCompanyIprTrademarkLicenseExWarehouseVO(license);
						dataCompanyIprTrademarkLicenseWarehouseCommand.setCompanyIprTrademarkId(dataCompanyIprTrademarkId);
						dataCompanyIprTrademarkLicenseWarehouseCommandExecutor.warehouse(dataCompanyIprTrademarkLicenseWarehouseCommand);
					}
				}

				List<DataCompanyIprTrademarkPledgeExWarehouseVO> pledges = companyIprTrademarkAllExWarehouseVO.getPledges();
				if (CollectionUtil.isNotEmpty(pledges)) {
					for (DataCompanyIprTrademarkPledgeExWarehouseVO pledge : pledges) {
						DataCompanyIprTrademarkPledgeWarehouseCommand dataCompanyIprTrademarkPledgeWarehouseCommand = DataCompanyIprTrademarkPledgeWarehouseCommand.createByDataCompanyIprTrademarkPledgeExWarehouseVO(pledge);
						dataCompanyIprTrademarkPledgeWarehouseCommand.setCompanyIprTrademarkId(dataCompanyIprTrademarkId);
						dataCompanyIprTrademarkPledgeWarehouseCommandExecutor.warehouse(dataCompanyIprTrademarkPledgeWarehouseCommand);
					}
				}


				List<DataCompanyIprTrademarkTransferExWarehouseVO> transfers = companyIprTrademarkAllExWarehouseVO.getTransfers();
				if (CollectionUtil.isNotEmpty(transfers)) {
					for (DataCompanyIprTrademarkTransferExWarehouseVO transfer : transfers) {
						DataCompanyIprTrademarkTransferWarehouseCommand dataCompanyIprTrademarkTransferWarehouseCommand = DataCompanyIprTrademarkTransferWarehouseCommand.createByDataCompanyIprTrademarkTransferExWarehouseVO(transfer);
						dataCompanyIprTrademarkTransferWarehouseCommand.setCompanyIprTrademarkId(dataCompanyIprTrademarkId);
						dataCompanyIprTrademarkTransferWarehouseCommandExecutor.warehouse(dataCompanyIprTrademarkTransferWarehouseCommand);
					}
				}
			}

		}
	}



	@Autowired
	public void setDataCompanyIprTrademarkLicenseWarehouseCommandExecutor(DataCompanyIprTrademarkLicenseWarehouseCommandExecutor dataCompanyIprTrademarkLicenseWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkLicenseWarehouseCommandExecutor = dataCompanyIprTrademarkLicenseWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkPartyWarehouseCommandExecutor(DataCompanyIprTrademarkPartyWarehouseCommandExecutor dataCompanyIprTrademarkPartyWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkPartyWarehouseCommandExecutor = dataCompanyIprTrademarkPartyWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkPledgeWarehouseCommandExecutor(DataCompanyIprTrademarkPledgeWarehouseCommandExecutor dataCompanyIprTrademarkPledgeWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkPledgeWarehouseCommandExecutor = dataCompanyIprTrademarkPledgeWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkTransferWarehouseCommandExecutor(DataCompanyIprTrademarkTransferWarehouseCommandExecutor dataCompanyIprTrademarkTransferWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkTransferWarehouseCommandExecutor = dataCompanyIprTrademarkTransferWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkWrapWarehouseCommandExecutor(DataCompanyIprTrademarkWrapWarehouseCommandExecutor dataCompanyIprTrademarkWrapWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkWrapWarehouseCommandExecutor = dataCompanyIprTrademarkWrapWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor(DataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor dataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor = dataCompanyIprTrademarkPartyWrapWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataDictGateway(DataDictGateway dataDictGateway) {
		this.dataDictGateway = dataDictGateway;
	}
}
