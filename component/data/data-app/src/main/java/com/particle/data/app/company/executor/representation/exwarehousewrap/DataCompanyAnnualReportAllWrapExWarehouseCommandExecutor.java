package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.*;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAnnualReportExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
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
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业年报全部出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-05-16 16:14:51
 */
@Component
@Validated
public class DataCompanyAnnualReportAllWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor {

	private DataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor;
	private DataCompanyAnnualReportAssetsExWarehouseCommandExecutor dataCompanyAnnualReportAssetsExWarehouseCommandExecutor;
	private DataCompanyAnnualReportChangeExWarehouseCommandExecutor dataCompanyAnnualReportChangeExWarehouseCommandExecutor;
	private DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;
	private DataCompanyAnnualReportExWarehouseCommandExecutor dataCompanyAnnualReportExWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;
	private DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;
	private DataCompanyAnnualReportShareholderExWarehouseCommandExecutor dataCompanyAnnualReportShareholderExWarehouseCommandExecutor;
	private DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;
	private DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;

	/**
	 * 企业年报全部出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyAnnualReportAllExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
																		  @Valid DataCompanyAnnualReportExWarehouseQueryCommand dataCompanyAnnualReportExWarehouseQueryCommand) {
		if (dataCompanyAnnualReportExWarehouseQueryCommand.getCompanyId() == null) {
			dataCompanyAnnualReportExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
		}
        if (dataCompanyAnnualReportExWarehouseQueryCommand.getCompanyId() == null) {
			Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
			dataCompanyAnnualReportExWarehouseQueryCommand.setCompanyId(companyId);
		}
		if (dataCompanyAnnualReportExWarehouseQueryCommand.getCompanyId() == null) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}

		PageResponse<DataCompanyAnnualReportExWarehouseVO> dataCompanyAnnualReportExWarehouseVOPageResponse = dataCompanyAnnualReportExWarehouseCommandExecutor.exWarehouse(dataCompanyAnnualReportExWarehouseQueryCommand);
		List<DataCompanyAnnualReportExWarehouseVO> dataCompanyAnnualReportExWarehouseVOs = dataCompanyAnnualReportExWarehouseVOPageResponse.getData();
		if (CollectionUtil.isEmpty(dataCompanyAnnualReportExWarehouseVOs)) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		int size = dataCompanyAnnualReportExWarehouseVOs.size();
		List<DataCompanyAnnualReportAllExWarehouseVO> dataCompanyAnnualReportAllExWarehouseVOs = new ArrayList<>(size);
		List<Long> annualReportIds = dataCompanyAnnualReportExWarehouseVOs.stream().map(item -> item.getId()).collect(Collectors.toList());

		Map<Long, DataCompanyAnnualReportSocialSecurityExWarehouseVO> socialSecurityMap = socialSecurity(annualReportIds);
		Map<Long, DataCompanyAnnualReportAssetsExWarehouseVO> assetsMap = assets(annualReportIds);
		Map<Long, List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO>> administrativeLicensesMap = administrativeLicense(annualReportIds);
		Map<Long, List<DataCompanyAnnualReportChangeExWarehouseVO>> changesMap = change(annualReportIds);
		Map<Long, List<DataCompanyAnnualReportEquityChangeExWarehouseVO>> equityChangesMap = equityChange(annualReportIds);
		Map<Long, List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO>> foreignGuaranteesMap = foreignGuarantee(annualReportIds);
		Map<Long, List<DataCompanyAnnualReportForeignInvestExWarehouseVO>> foreignInvestsMap = foreignInvest(annualReportIds);
		Map<Long, List<DataCompanyAnnualReportShareholderExWarehouseVO>> shareholdersMap = shareholder(annualReportIds);
		Map<Long, List<DataCompanyAnnualReportWebsiteExWarehouseVO>> websitesMap = website(annualReportIds);


		for (DataCompanyAnnualReportExWarehouseVO dataCompanyAnnualReportExWarehouseVO : dataCompanyAnnualReportExWarehouseVOs) {
			DataCompanyAnnualReportAllExWarehouseVO dataCompanyAnnualReportAllExWarehouseVO = new DataCompanyAnnualReportAllExWarehouseVO();
			dataCompanyAnnualReportAllExWarehouseVO.setBasic(dataCompanyAnnualReportExWarehouseVO);
			dataCompanyAnnualReportAllExWarehouseVO.setSocialSecurity(socialSecurityMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setAssets(assetsMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setAdministrativeLicenses(administrativeLicensesMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setChanges(changesMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setEquityChanges(equityChangesMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setForeignGuarantees(foreignGuaranteesMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setForeignInvests(foreignInvestsMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setShareholders(shareholdersMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));
			dataCompanyAnnualReportAllExWarehouseVO.setWebsites(websitesMap.get(dataCompanyAnnualReportExWarehouseVO.getId()));

			dataCompanyAnnualReportAllExWarehouseVOs.add(dataCompanyAnnualReportAllExWarehouseVO);
		}
		return PageResponse.of(dataCompanyAnnualReportAllExWarehouseVOs,
				dataCompanyAnnualReportExWarehouseVOPageResponse.getTotalCount(),
				dataCompanyAnnualReportExWarehouseVOPageResponse.getPageSize(),
				dataCompanyAnnualReportExWarehouseVOPageResponse.getPageNo());

	}

	/**
	 * 获取社保信息
	 * @param annualReportIds
	 * @return
	 */
	private Map<Long, DataCompanyAnnualReportSocialSecurityExWarehouseVO> socialSecurity(List<Long> annualReportIds) {

		MultiResponse<DataCompanyAnnualReportSocialSecurityExWarehouseVO> dataCompanyAnnualReportSocialSecurityExWarehouseVOMultiResponse = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportSocialSecurityExWarehouseVO> data = dataCompanyAnnualReportSocialSecurityExWarehouseVOMultiResponse.getData();
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		return data.stream().collect(Collectors.toMap(DataCompanyAnnualReportSocialSecurityExWarehouseVO::getCompanyAnnualReportId, Function.identity()));
	}
	/**
	 * 获取资产信息
	 * @param annualReportIds
	 * @return
	 */
	private Map<Long, DataCompanyAnnualReportAssetsExWarehouseVO> assets(List<Long> annualReportIds) {

		MultiResponse<DataCompanyAnnualReportAssetsExWarehouseVO> dataCompanyAnnualReportAssetsExWarehouseVOMultiResponse = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportAssetsExWarehouseVO> data = dataCompanyAnnualReportAssetsExWarehouseVOMultiResponse.getData();
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		return data.stream().collect(Collectors.toMap(DataCompanyAnnualReportAssetsExWarehouseVO::getCompanyAnnualReportId, Function.identity()));
	}
	/**
	 * 获取企业年报行政许可信息
	 * @param annualReportIds 年报 ID 列表
	 * @return 按年报 ID 分组的行政许可信息
	 */
	private Map<Long, List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO>> administrativeLicense(List<Long> annualReportIds) {
		// 调用命令执行器查询行政许可信息
		MultiResponse<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> multiResponse = dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按年报 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyAnnualReportAdministrativeLicenseExWarehouseVO::getCompanyAnnualReportId));
	}

	/**
	 * 获取企业年报变更信息
	 * @param annualReportIds 年报 ID 列表
	 * @return 按年报 ID 分组的变更信息
	 */
	private Map<Long, List<DataCompanyAnnualReportChangeExWarehouseVO>> change(List<Long> annualReportIds) {
		// 调用命令执行器查询变更信息
		MultiResponse<DataCompanyAnnualReportChangeExWarehouseVO> multiResponse = dataCompanyAnnualReportChangeExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportChangeExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按年报 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyAnnualReportChangeExWarehouseVO::getCompanyAnnualReportId));
	}

	/**
	 * 获取企业年报股权变更信息
	 * @param annualReportIds 年报 ID 列表
	 * @return 按年报 ID 分组的股权变更信息
	 */
	private Map<Long, List<DataCompanyAnnualReportEquityChangeExWarehouseVO>> equityChange(List<Long> annualReportIds) {
		// 调用命令执行器查询股权变更信息
		MultiResponse<DataCompanyAnnualReportEquityChangeExWarehouseVO> multiResponse = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportEquityChangeExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按年报 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyAnnualReportEquityChangeExWarehouseVO::getCompanyAnnualReportId));
	}

	/**
	 * 获取企业年报对外担保信息
	 * @param annualReportIds 年报 ID 列表
	 * @return 按年报 ID 分组的对外担保信息
	 */
	private Map<Long, List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO>> foreignGuarantee(List<Long> annualReportIds) {
		// 调用命令执行器查询对外担保信息
		MultiResponse<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> multiResponse = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportForeignGuaranteeExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按年报 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyAnnualReportForeignGuaranteeExWarehouseVO::getCompanyAnnualReportId));
	}

	/**
	 * 获取企业年报对外投资信息
	 * @param annualReportIds 年报 ID 列表
	 * @return 按年报 ID 分组的对外投资信息
	 */
	private Map<Long, List<DataCompanyAnnualReportForeignInvestExWarehouseVO>> foreignInvest(List<Long> annualReportIds) {
		// 调用命令执行器查询对外投资信息
		MultiResponse<DataCompanyAnnualReportForeignInvestExWarehouseVO> multiResponse = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportForeignInvestExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按年报 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyAnnualReportForeignInvestExWarehouseVO::getCompanyAnnualReportId));
	}

	/**
	 * 获取企业年报股东信息
	 * @param annualReportIds 年报 ID 列表
	 * @return 按年报 ID 分组的股东信息
	 */
	private Map<Long, List<DataCompanyAnnualReportShareholderExWarehouseVO>> shareholder(List<Long> annualReportIds) {
		// 调用命令执行器查询股东信息
		MultiResponse<DataCompanyAnnualReportShareholderExWarehouseVO> multiResponse = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportShareholderExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按年报 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyAnnualReportShareholderExWarehouseVO::getCompanyAnnualReportId));
	}

	/**
	 * 获取企业年报网站网店信息
	 * @param annualReportIds 年报 ID 列表
	 * @return 按年报 ID 分组的网站网店信息
	 */
	private Map<Long, List<DataCompanyAnnualReportWebsiteExWarehouseVO>> website(List<Long> annualReportIds) {
		// 调用命令执行器查询网站网店信息
		MultiResponse<DataCompanyAnnualReportWebsiteExWarehouseVO> multiResponse = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor.exWarehouseByCompanyAnnualReportIds(annualReportIds);
		List<DataCompanyAnnualReportWebsiteExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按年报 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyAnnualReportWebsiteExWarehouseVO::getCompanyAnnualReportId));
	}



	@Autowired
	public void setDataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor(DataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor = dataCompanyAnnualReportAdministrativeLicenseExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportAssetsExWarehouseCommandExecutor(DataCompanyAnnualReportAssetsExWarehouseCommandExecutor dataCompanyAnnualReportAssetsExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportAssetsExWarehouseCommandExecutor = dataCompanyAnnualReportAssetsExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportChangeExWarehouseCommandExecutor(DataCompanyAnnualReportChangeExWarehouseCommandExecutor dataCompanyAnnualReportChangeExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportChangeExWarehouseCommandExecutor = dataCompanyAnnualReportChangeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor(DataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor = dataCompanyAnnualReportEquityChangeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportExWarehouseCommandExecutor(DataCompanyAnnualReportExWarehouseCommandExecutor dataCompanyAnnualReportExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportExWarehouseCommandExecutor = dataCompanyAnnualReportExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor(DataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor = dataCompanyAnnualReportForeignGuaranteeExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor(DataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor = dataCompanyAnnualReportForeignInvestExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportShareholderExWarehouseCommandExecutor(DataCompanyAnnualReportShareholderExWarehouseCommandExecutor dataCompanyAnnualReportShareholderExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportShareholderExWarehouseCommandExecutor = dataCompanyAnnualReportShareholderExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor(DataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor = dataCompanyAnnualReportSocialSecurityExWarehouseCommandExecutor;
	}
	@Autowired
	public void setDataCompanyAnnualReportWebsiteExWarehouseCommandExecutor(DataCompanyAnnualReportWebsiteExWarehouseCommandExecutor dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor) {
		this.dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor = dataCompanyAnnualReportWebsiteExWarehouseCommandExecutor;
	}
}
