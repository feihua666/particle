package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.*;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentExWarehouseQueryCommand;
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
 * 企业知识产权专利出库 查询指令执行器
 * </p>
 * @author yw
 * @since 2025-04-07 11:30:08
 */
@Component
@Validated
public class DataCompanyIprPatentAllWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor {

	private DataCompanyIprPatentCertificateExWarehouseCommandExecutor dataCompanyIprPatentCertificateExWarehouseCommandExecutor;
	private DataCompanyIprPatentCitedExWarehouseCommandExecutor dataCompanyIprPatentCitedExWarehouseCommandExecutor;
	private DataCompanyIprPatentContentExWarehouseCommandExecutor dataCompanyIprPatentContentExWarehouseCommandExecutor;
	private DataCompanyIprPatentExWarehouseCommandExecutor dataCompanyIprPatentExWarehouseCommandExecutor;
	private DataCompanyIprPatentFamilyExWarehouseCommandExecutor dataCompanyIprPatentFamilyExWarehouseCommandExecutor;
	private DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;
	private DataCompanyIprPatentLicenseExWarehouseCommandExecutor dataCompanyIprPatentLicenseExWarehouseCommandExecutor;
	private DataCompanyIprPatentNoticeExWarehouseCommandExecutor dataCompanyIprPatentNoticeExWarehouseCommandExecutor;
	private DataCompanyIprPatentPartyExWarehouseCommandExecutor dataCompanyIprPatentPartyExWarehouseCommandExecutor;
	private DataCompanyIprPatentPaymentExWarehouseCommandExecutor dataCompanyIprPatentPaymentExWarehouseCommandExecutor;
	private DataCompanyIprPatentPledgeExWarehouseCommandExecutor dataCompanyIprPatentPledgeExWarehouseCommandExecutor;
	private DataCompanyIprPatentQuoteExWarehouseCommandExecutor dataCompanyIprPatentQuoteExWarehouseCommandExecutor;
	private DataCompanyIprPatentStatisticExWarehouseCommandExecutor dataCompanyIprPatentStatisticExWarehouseCommandExecutor;
	private DataCompanyIprPatentTransferExWarehouseCommandExecutor dataCompanyIprPatentTransferExWarehouseCommandExecutor;

	/**
	 * 企业知识产权专利出库
	 * @param dataCompanyExWarehouseQueryCommand
	 * @return
	 */
	public PageResponse<DataCompanyIprPatentAllExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
																		  @Valid DataCompanyIprPatentExWarehouseQueryCommand dataCompanyIprPatentExWarehouseQueryCommand) {
		if (dataCompanyIprPatentExWarehouseQueryCommand.getCompanyId() == null) {
			dataCompanyIprPatentExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
		}
		if (dataCompanyIprPatentExWarehouseQueryCommand.getCompanyId() == null) {
			Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
			dataCompanyIprPatentExWarehouseQueryCommand.setCompanyId(companyId);
		}
		if (dataCompanyIprPatentExWarehouseQueryCommand.getCompanyId() == null
				&& StrUtil.isEmpty(dataCompanyIprPatentExWarehouseQueryCommand.getApplyNo())
				&& StrUtil.isEmpty(dataCompanyIprPatentExWarehouseQueryCommand.getPublicNo())
		) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		PageResponse<DataCompanyIprPatentExWarehouseVO> response = dataCompanyIprPatentExWarehouseCommandExecutor.exWarehouse(dataCompanyIprPatentExWarehouseQueryCommand);
		List<DataCompanyIprPatentExWarehouseVO> dataCompanyIprPatentExWarehouseVOS = response.getData();
		if (CollectionUtil.isEmpty(dataCompanyIprPatentExWarehouseVOS)) {
			return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		}
		int size = dataCompanyIprPatentExWarehouseVOS.size();
		List<DataCompanyIprPatentAllExWarehouseVO> dataCompanyIprPatentAllExWarehouseVOS = new ArrayList<>(size);
		List<Long> iprPatentIds = dataCompanyIprPatentExWarehouseVOS.stream()
				.map(item -> item.getId())
				.collect(Collectors.toList());

		// 查询所有相关的信息
		Map<Long, List<DataCompanyIprPatentPartyExWarehouseVO>> parties = parties(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentCertificateExWarehouseVO>> certificates = certificates(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentCitedExWarehouseVO>> cites = cites(iprPatentIds);
		Map<Long, DataCompanyIprPatentContentExWarehouseVO> content = content(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentFamilyExWarehouseVO>> families = families(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentLegalStatusExWarehouseVO>> legalStatuses = legalStatuses(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentLicenseExWarehouseVO>> licenses = licenses(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentNoticeExWarehouseVO>> notices = notices(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentPaymentExWarehouseVO>> payments = payments(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentPledgeExWarehouseVO>> pledges = pledges(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentQuoteExWarehouseVO>> quotes = quotes(iprPatentIds);
		Map<Long, DataCompanyIprPatentStatisticExWarehouseVO> statistic = statistic(iprPatentIds);
		Map<Long, List<DataCompanyIprPatentTransferExWarehouseVO>> transfers = transfers(iprPatentIds);

		// 遍历基础数据，填充完整信息
		for (DataCompanyIprPatentExWarehouseVO dataCompanyIprPatentExWarehouseVO : dataCompanyIprPatentExWarehouseVOS) {
			DataCompanyIprPatentAllExWarehouseVO dataCompanyIprPatentAllExWarehouseVO = new DataCompanyIprPatentAllExWarehouseVO();
			// 基本信息
			dataCompanyIprPatentAllExWarehouseVO.setBasic(dataCompanyIprPatentExWarehouseVO);
			// 当事人信息
			dataCompanyIprPatentAllExWarehouseVO.setParties(parties.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 证书信息
			dataCompanyIprPatentAllExWarehouseVO.setCertificates(certificates.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 被引证信息
			dataCompanyIprPatentAllExWarehouseVO.setCites(cites.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 内容信息
			dataCompanyIprPatentAllExWarehouseVO.setContent(content.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 同族信息
			dataCompanyIprPatentAllExWarehouseVO.setFamilies(families.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 法律状态信息
			dataCompanyIprPatentAllExWarehouseVO.setLegalStatuses(legalStatuses.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 许可信息
			dataCompanyIprPatentAllExWarehouseVO.setLicenses(licenses.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 通知书信息
			dataCompanyIprPatentAllExWarehouseVO.setNotices(notices.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 缴费信息
			dataCompanyIprPatentAllExWarehouseVO.setPayments(payments.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 质押信息
			dataCompanyIprPatentAllExWarehouseVO.setPledges(pledges.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 引证信息
			dataCompanyIprPatentAllExWarehouseVO.setQuotes(quotes.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 统计信息
			dataCompanyIprPatentAllExWarehouseVO.setStatistic(statistic.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 转让信息
			dataCompanyIprPatentAllExWarehouseVO.setTransfers(transfers.get(dataCompanyIprPatentExWarehouseVO.getId()));
			// 将填充好的对象添加到结果列表中
			dataCompanyIprPatentAllExWarehouseVOS.add(dataCompanyIprPatentAllExWarehouseVO);
		}
		return PageResponse.of(dataCompanyIprPatentAllExWarehouseVOS, response.getTotalCount(), response.getPageSize(), response.getPageNo());
	}
	/**
	 * 获取当事人信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的当事人信息
	 */
	private Map<Long, List<DataCompanyIprPatentPartyExWarehouseVO>> parties(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询当事人信息
		MultiResponse<DataCompanyIprPatentPartyExWarehouseVO> multiResponse = dataCompanyIprPatentPartyExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentPartyExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentPartyExWarehouseVO::getCompanyIprPatentId));
	}
	/**
	 * 获取内容信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的内容信息
	 */
	private Map<Long, DataCompanyIprPatentContentExWarehouseVO> content(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询内容信息
		MultiResponse<DataCompanyIprPatentContentExWarehouseVO> multiResponse = dataCompanyIprPatentContentExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentContentExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.toMap(DataCompanyIprPatentContentExWarehouseVO::getCompanyIprPatentId, Function.identity()));
	}
	/**
	 * 获取证书信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的证书信息
	 */
	private Map<Long, List<DataCompanyIprPatentCertificateExWarehouseVO>> certificates(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询证书信息
		MultiResponse<DataCompanyIprPatentCertificateExWarehouseVO> multiResponse = dataCompanyIprPatentCertificateExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentCertificateExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentCertificateExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取被引证信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的被引证信息
	 */
	private Map<Long, List<DataCompanyIprPatentCitedExWarehouseVO>> cites(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询被引证信息
		MultiResponse<DataCompanyIprPatentCitedExWarehouseVO> multiResponse = dataCompanyIprPatentCitedExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentCitedExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentCitedExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取同族信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的同族信息
	 */
	private Map<Long, List<DataCompanyIprPatentFamilyExWarehouseVO>> families(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询同族信息
		MultiResponse<DataCompanyIprPatentFamilyExWarehouseVO> multiResponse = dataCompanyIprPatentFamilyExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentFamilyExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentFamilyExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取法律状态信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的法律状态信息
	 */
	private Map<Long, List<DataCompanyIprPatentLegalStatusExWarehouseVO>> legalStatuses(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询法律状态信息
		MultiResponse<DataCompanyIprPatentLegalStatusExWarehouseVO> multiResponse = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentLegalStatusExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentLegalStatusExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取许可信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的许可信息
	 */
	private Map<Long, List<DataCompanyIprPatentLicenseExWarehouseVO>> licenses(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询许可信息
		MultiResponse<DataCompanyIprPatentLicenseExWarehouseVO> multiResponse = dataCompanyIprPatentLicenseExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentLicenseExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentLicenseExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取通知书信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的通知书信息
	 */
	private Map<Long, List<DataCompanyIprPatentNoticeExWarehouseVO>> notices(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询通知书信息
		MultiResponse<DataCompanyIprPatentNoticeExWarehouseVO> multiResponse = dataCompanyIprPatentNoticeExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentNoticeExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentNoticeExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取缴费信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的缴费信息
	 */
	private Map<Long, List<DataCompanyIprPatentPaymentExWarehouseVO>> payments(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询缴费信息
		MultiResponse<DataCompanyIprPatentPaymentExWarehouseVO> multiResponse = dataCompanyIprPatentPaymentExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentPaymentExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentPaymentExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取质押信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的质押信息
	 */
	private Map<Long, List<DataCompanyIprPatentPledgeExWarehouseVO>> pledges(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询质押信息
		MultiResponse<DataCompanyIprPatentPledgeExWarehouseVO> multiResponse = dataCompanyIprPatentPledgeExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentPledgeExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentPledgeExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取引证信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的引证信息
	 */
	private Map<Long, List<DataCompanyIprPatentQuoteExWarehouseVO>> quotes(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询引证信息
		MultiResponse<DataCompanyIprPatentQuoteExWarehouseVO> multiResponse = dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentQuoteExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentQuoteExWarehouseVO::getCompanyIprPatentId));
	}

	/**
	 * 获取统计信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的统计信息
	 */
	private Map<Long, DataCompanyIprPatentStatisticExWarehouseVO> statistic(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询统计信息
		MultiResponse<DataCompanyIprPatentStatisticExWarehouseVO> multiResponse = dataCompanyIprPatentStatisticExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentStatisticExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.toMap(DataCompanyIprPatentStatisticExWarehouseVO::getCompanyIprPatentId, Function.identity()));
	}

	/**
	 * 获取转让信息
	 * @param companyIprPatentIds 专利 ID 列表
	 * @return 按专利 ID 分组的转让信息
	 */
	private Map<Long, List<DataCompanyIprPatentTransferExWarehouseVO>> transfers(List<Long> companyIprPatentIds) {
		// 调用命令执行器查询转让信息
		MultiResponse<DataCompanyIprPatentTransferExWarehouseVO> multiResponse = dataCompanyIprPatentTransferExWarehouseCommandExecutor.exWarehouseByCompanyIprPatentIds(companyIprPatentIds);
		List<DataCompanyIprPatentTransferExWarehouseVO> data = multiResponse.getData();
		// 如果数据为空，返回空 Map
		if (CollectionUtil.isEmpty(data)) {
			return Collections.emptyMap();
		}
		// 按专利 ID 分组返回结果
		return data.stream()
				.collect(Collectors.groupingBy(DataCompanyIprPatentTransferExWarehouseVO::getCompanyIprPatentId));
	}


	@Autowired
	public void setDataCompanyIprPatentCertificateExWarehouseCommandExecutor(DataCompanyIprPatentCertificateExWarehouseCommandExecutor dataCompanyIprPatentCertificateExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentCertificateExWarehouseCommandExecutor = dataCompanyIprPatentCertificateExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentCitedExWarehouseCommandExecutor(DataCompanyIprPatentCitedExWarehouseCommandExecutor dataCompanyIprPatentCitedExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentCitedExWarehouseCommandExecutor = dataCompanyIprPatentCitedExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentContentExWarehouseCommandExecutor(DataCompanyIprPatentContentExWarehouseCommandExecutor dataCompanyIprPatentContentExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentContentExWarehouseCommandExecutor = dataCompanyIprPatentContentExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentExWarehouseCommandExecutor(DataCompanyIprPatentExWarehouseCommandExecutor dataCompanyIprPatentExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentExWarehouseCommandExecutor = dataCompanyIprPatentExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentFamilyExWarehouseCommandExecutor(DataCompanyIprPatentFamilyExWarehouseCommandExecutor dataCompanyIprPatentFamilyExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentFamilyExWarehouseCommandExecutor = dataCompanyIprPatentFamilyExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentLegalStatusExWarehouseCommandExecutor(DataCompanyIprPatentLegalStatusExWarehouseCommandExecutor dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor = dataCompanyIprPatentLegalStatusExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentLicenseExWarehouseCommandExecutor(DataCompanyIprPatentLicenseExWarehouseCommandExecutor dataCompanyIprPatentLicenseExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentLicenseExWarehouseCommandExecutor = dataCompanyIprPatentLicenseExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentNoticeExWarehouseCommandExecutor(DataCompanyIprPatentNoticeExWarehouseCommandExecutor dataCompanyIprPatentNoticeExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentNoticeExWarehouseCommandExecutor = dataCompanyIprPatentNoticeExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentPartyExWarehouseCommandExecutor(DataCompanyIprPatentPartyExWarehouseCommandExecutor dataCompanyIprPatentPartyExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPartyExWarehouseCommandExecutor = dataCompanyIprPatentPartyExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentPaymentExWarehouseCommandExecutor(DataCompanyIprPatentPaymentExWarehouseCommandExecutor dataCompanyIprPatentPaymentExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPaymentExWarehouseCommandExecutor = dataCompanyIprPatentPaymentExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentPledgeExWarehouseCommandExecutor(DataCompanyIprPatentPledgeExWarehouseCommandExecutor dataCompanyIprPatentPledgeExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentPledgeExWarehouseCommandExecutor = dataCompanyIprPatentPledgeExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentQuoteExWarehouseCommandExecutor(DataCompanyIprPatentQuoteExWarehouseCommandExecutor dataCompanyIprPatentQuoteExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentQuoteExWarehouseCommandExecutor = dataCompanyIprPatentQuoteExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentStatisticExWarehouseCommandExecutor(DataCompanyIprPatentStatisticExWarehouseCommandExecutor dataCompanyIprPatentStatisticExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentStatisticExWarehouseCommandExecutor = dataCompanyIprPatentStatisticExWarehouseCommandExecutor;
	}

	@Autowired
	public void setDataCompanyIprPatentTransferExWarehouseCommandExecutor(DataCompanyIprPatentTransferExWarehouseCommandExecutor dataCompanyIprPatentTransferExWarehouseCommandExecutor) {
		this.dataCompanyIprPatentTransferExWarehouseCommandExecutor = dataCompanyIprPatentTransferExWarehouseCommandExecutor;
	}
}
