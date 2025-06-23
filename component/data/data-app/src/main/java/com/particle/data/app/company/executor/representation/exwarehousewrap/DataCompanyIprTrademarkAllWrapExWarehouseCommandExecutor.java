package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.*;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprTrademarkExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.*;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业知识产权商标出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprTrademarkAllWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor {

	private DataCompanyIprTrademarkExWarehouseCommandExecutor dataCompanyIprTrademarkExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkPartyExWarehouseCommandExecutor dataCompanyIprTrademarkPartyExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;
	private DataCompanyIprTrademarkTransferExWarehouseCommandExecutor dataCompanyIprTrademarkTransferExWarehouseCommandExecutor;

	/**
	 * 企业知识产权商标出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprTrademarkAllExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
																		  DataCompanyIprTrademarkExWarehouseQueryCommand dataCompanyIprTrademarkExWarehouseQueryCommand) {
		if (dataCompanyIprTrademarkExWarehouseQueryCommand.getCompanyId() == null) {
			dataCompanyIprTrademarkExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
		}
		if (dataCompanyIprTrademarkExWarehouseQueryCommand.getCompanyId() == null) {
			Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
			dataCompanyIprTrademarkExWarehouseQueryCommand.setCompanyId(companyId);
		}
		if (dataCompanyIprTrademarkExWarehouseQueryCommand.getCompanyId() == null
				&& StrUtil.isEmpty(dataCompanyIprTrademarkExWarehouseQueryCommand.getApplyNo())
				&& StrUtil.isEmpty(dataCompanyIprTrademarkExWarehouseQueryCommand.getRegNo())
		) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		PageResponse<DataCompanyIprTrademarkExWarehouseVO> response = dataCompanyIprTrademarkExWarehouseCommandExecutor.exWarehouse(dataCompanyIprTrademarkExWarehouseQueryCommand);
		List<DataCompanyIprTrademarkExWarehouseVO> dataCompanyIprTrademarkExWarehouseVOS = response.getData();
		if (CollectionUtil.isEmpty(dataCompanyIprTrademarkExWarehouseVOS)) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		int size = dataCompanyIprTrademarkExWarehouseVOS.size();
		List<DataCompanyIprTrademarkAllExWarehouseVO> dataCompanyIprTrademarkAllExWarehouseVOS = new ArrayList<>(size);
		List<Long> iprTrademarkIds = dataCompanyIprTrademarkExWarehouseVOS.stream()
				.map(item -> item.getId())
				.collect(Collectors.toList());

		// 查询所有相关的信息
		Map<Long, List<DataCompanyIprTrademarkPartyExWarehouseVO>> parties = parties(iprTrademarkIds);
		Map<Long, List<DataCompanyIprTrademarkLicenseExWarehouseVO>> licenses = licenses(iprTrademarkIds);
		Map<Long, List<DataCompanyIprTrademarkPledgeExWarehouseVO>> pledges = pledges(iprTrademarkIds);
		Map<Long, List<DataCompanyIprTrademarkTransferExWarehouseVO>> transfers = transfers(iprTrademarkIds);

		// 遍历基础数据，填充完整信息
		for (DataCompanyIprTrademarkExWarehouseVO dataCompanyIprTrademarkExWarehouseVO : dataCompanyIprTrademarkExWarehouseVOS) {
			DataCompanyIprTrademarkAllExWarehouseVO dataCompanyIprTrademarkAllExWarehouseVO = new DataCompanyIprTrademarkAllExWarehouseVO();
			// 基本信息
			dataCompanyIprTrademarkAllExWarehouseVO.setBasic(dataCompanyIprTrademarkExWarehouseVO);
			// 当事人信息
			dataCompanyIprTrademarkAllExWarehouseVO.setParties(parties.get(dataCompanyIprTrademarkExWarehouseVO.getId()));
			// 许可信息
			dataCompanyIprTrademarkAllExWarehouseVO.setLicenses(licenses.get(dataCompanyIprTrademarkExWarehouseVO.getId()));
			// 质押信息
			dataCompanyIprTrademarkAllExWarehouseVO.setPledges(pledges.get(dataCompanyIprTrademarkExWarehouseVO.getId()));
			// 转让信息
			dataCompanyIprTrademarkAllExWarehouseVO.setTransfers(transfers.get(dataCompanyIprTrademarkExWarehouseVO.getId()));
			// 将填充好的对象添加到结果列表中
			dataCompanyIprTrademarkAllExWarehouseVOS.add(dataCompanyIprTrademarkAllExWarehouseVO);
		}
		return PageResponse.of(dataCompanyIprTrademarkAllExWarehouseVOS, response.getTotalCount(), response.getPageSize(), response.getPageNo());
	}
	/**
	 * 获取当事人信息
	 * @param companyIprTrademarkIds 商标 ID 列表
	 * @return 按商标 ID 分组的当事人信息
	 */
	private Map<Long, List<DataCompanyIprTrademarkPartyExWarehouseVO>> parties(List<Long> companyIprTrademarkIds) {
		// 调用命令执行器查询当事人信息
		MultiResponse<DataCompanyIprTrademarkPartyExWarehouseVO> multiResponse = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIds(companyIprTrademarkIds);
		List<DataCompanyIprTrademarkPartyExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按商标 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprTrademarkPartyExWarehouseVO::getCompanyIprTrademarkId));
	}



	/**
	 * 获取许可信息
	 * @param companyIprTrademarkIds 商标 ID 列表
	 * @return 按商标 ID 分组的许可信息
	 */
	private Map<Long, List<DataCompanyIprTrademarkLicenseExWarehouseVO>> licenses(List<Long> companyIprTrademarkIds) {
		// 调用命令执行器查询许可信息
		MultiResponse<DataCompanyIprTrademarkLicenseExWarehouseVO> multiResponse = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIds(companyIprTrademarkIds);
		List<DataCompanyIprTrademarkLicenseExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按商标 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprTrademarkLicenseExWarehouseVO::getCompanyIprTrademarkId));
	}

	/**
	 * 获取质押信息
	 * @param companyIprTrademarkIds 商标 ID 列表
	 * @return 按商标 ID 分组的质押信息
	 */
	private Map<Long, List<DataCompanyIprTrademarkPledgeExWarehouseVO>> pledges(List<Long> companyIprTrademarkIds) {
		// 调用命令执行器查询质押信息
		MultiResponse<DataCompanyIprTrademarkPledgeExWarehouseVO> multiResponse = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIds(companyIprTrademarkIds);
		List<DataCompanyIprTrademarkPledgeExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按商标 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprTrademarkPledgeExWarehouseVO::getCompanyIprTrademarkId));
	}

	/**
	 * 获取转让信息
	 * @param companyIprTrademarkIds 商标 ID 列表
	 * @return 按商标 ID 分组的转让信息
	 */
	private Map<Long, List<DataCompanyIprTrademarkTransferExWarehouseVO>> transfers(List<Long> companyIprTrademarkIds) {
		// 调用命令执行器查询转让信息
		MultiResponse<DataCompanyIprTrademarkTransferExWarehouseVO> multiResponse = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor.exWarehouseByCompanyIprTrademarkIds(companyIprTrademarkIds);
		List<DataCompanyIprTrademarkTransferExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按商标 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprTrademarkTransferExWarehouseVO::getCompanyIprTrademarkId));
	}


	@Autowired
	public void setDataCompanyIprTrademarkExWarehouseCommandExecutor(DataCompanyIprTrademarkExWarehouseCommandExecutor dataCompanyIprTrademarkExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkExWarehouseCommandExecutor = dataCompanyIprTrademarkExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkLicenseExWarehouseCommandExecutor(DataCompanyIprTrademarkLicenseExWarehouseCommandExecutor dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor = dataCompanyIprTrademarkLicenseExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkPartyExWarehouseCommandExecutor(DataCompanyIprTrademarkPartyExWarehouseCommandExecutor dataCompanyIprTrademarkPartyExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkPartyExWarehouseCommandExecutor = dataCompanyIprTrademarkPartyExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkPledgeExWarehouseCommandExecutor(DataCompanyIprTrademarkPledgeExWarehouseCommandExecutor dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor = dataCompanyIprTrademarkPledgeExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprTrademarkTransferExWarehouseCommandExecutor(DataCompanyIprTrademarkTransferExWarehouseCommandExecutor dataCompanyIprTrademarkTransferExWarehouseCommandExecutor) {
		this.dataCompanyIprTrademarkTransferExWarehouseCommandExecutor = dataCompanyIprTrademarkTransferExWarehouseCommandExecutor;
	}
}
