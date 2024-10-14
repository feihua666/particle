package com.particle.openplatform.app.bill.executor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.crud.ServiceHelperTool;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordCustomerMonthBillGateway;
import com.particle.openplatform.domain.enums.OpenPlatformBillStatus;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordCustomerMonthBillService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;

import com.particle.global.dto.response.Response;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**
 * <p>
 * 开放平台客户月账单 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordCustomerMonthBillCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway;
	private IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService;
	private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor;
	private IOpenplatformAppService iOpenplatformAppService;
	private OpenplatformDictGateway openplatformDictGateway;

	/**
	 * 统计上月的统计数据
	 * @param isIncludeMonthSummary
	 * @param isIncludeDaySummary 只有在 isIncludeMonthSummary=true 时生效
	 * @return
	 */
	public Response LastMonthStatistic(Boolean isIncludeMonthSummary,Boolean isIncludeDaySummary) {
		LocalDate today = LocalDate.now();
		LocalDate lastMonth = today.minusMonths(1);
		Integer year = lastMonth.getYear();
		Integer month = lastMonth.getMonthValue();
		return statistic(year,month,isIncludeMonthSummary,isIncludeDaySummary);
	}

	/**
	 * 统计某一月的数据
	 * @param year
	 * @param month
	 * @param isIncludeMonthSummary
	 * @param isIncludeDaySummary 只有在 isIncludeMonthSummary=true 时生效
	 */
	public Response statistic(Integer year, Integer month,Boolean isIncludeMonthSummary,Boolean isIncludeDaySummary) {
		if (isIncludeMonthSummary != null && isIncludeMonthSummary) {
			openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor.statistic(year,month,isIncludeDaySummary,null);
		}
		Long initStatusDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformBillStatus.init.groupCode(), OpenPlatformBillStatus.init.itemValue());

		ServiceHelperTool.<OpenplatformAppDO>pageExecute(page -> {
					// 保证 customerId查询不重复，这里使用了group by 没有使用 distinct
					LambdaQueryWrapper<OpenplatformAppDO> lambdaQueryWrapper = Wrappers.<OpenplatformAppDO>lambdaQuery()
							.select(OpenplatformAppDO::getOwnerCustomerId)
							.isNotNull(OpenplatformAppDO::getOwnerCustomerId).groupBy(OpenplatformAppDO::getOwnerCustomerId);
					return iOpenplatformAppService.page(page, lambdaQueryWrapper);
				},
				page ->{
					List<OpenplatformAppDO> records = page.getRecords();
					for (OpenplatformAppDO record : records) {
						singleOneMonthStatistic(record.getOwnerCustomerId(),
								year,month,initStatusDictId);
					}
				},"openplatformOpenapiRecordCustomerMonthBill statistic");

		return Response.buildSuccess();
	}
	/**
	 * 单月统计
	 * @param ownerCustomerId
	 * @param year 年
	 * @param month 月
	 */
	private void singleOneMonthStatistic(Long ownerCustomerId,
										 Integer year,Integer month,Long initStatusDictId) {


		OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam openplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam = OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam.create(ownerCustomerId, year, month);
		List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO> openplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDOS = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.customerIdStatistics(openplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsParam);


		for (OpenplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDO openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO : openplatformOpenapiRecordAppOpenapiMonthSummaryCustomerIdStatisticsVIEWDOS) {
			OpenplatformOpenapiRecordCustomerMonthBillDO openplatformOpenapiRecordCustomerMonthBillDO = iOpenplatformOpenapiRecordCustomerMonthBillService.getByCustomerIdAndYearAndMonth(
					openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getCustomerId(),
					year,
					month
			);
			boolean exist = false;
			if (openplatformOpenapiRecordCustomerMonthBillDO == null) {
				openplatformOpenapiRecordCustomerMonthBillDO = new OpenplatformOpenapiRecordCustomerMonthBillDO();
				exist = false;
			}else {
				exist = true;
			}
			openplatformOpenapiRecordCustomerMonthBillDO.setCustomerId(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getCustomerId());
			openplatformOpenapiRecordCustomerMonthBillDO.setYear(year);
			openplatformOpenapiRecordCustomerMonthBillDO.setMonth(month);
			openplatformOpenapiRecordCustomerMonthBillDO.setTotalCall(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalCall());
			openplatformOpenapiRecordCustomerMonthBillDO.setTotalFeeCall(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeCall());
			openplatformOpenapiRecordCustomerMonthBillDO.setTotalFeeAmount(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeAmount());
			openplatformOpenapiRecordCustomerMonthBillDO.setStatusDictId(initStatusDictId);

			if (exist) {
				iOpenplatformOpenapiRecordCustomerMonthBillService.updateById(openplatformOpenapiRecordCustomerMonthBillDO);
			} else {
				iOpenplatformOpenapiRecordCustomerMonthBillService.save(openplatformOpenapiRecordCustomerMonthBillDO);
			}
		}
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordCustomerMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordCustomerMonthBillGateway(OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway) {
		this.openplatformOpenapiRecordCustomerMonthBillGateway = openplatformOpenapiRecordCustomerMonthBillGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordCustomerMonthBillService(IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService) {
		this.iOpenplatformOpenapiRecordCustomerMonthBillService = iOpenplatformOpenapiRecordCustomerMonthBillService;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiMonthSummaryService(IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	}
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor) {
		this.openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor = openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor;
	}
	@Autowired
	public void setIOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
		this.iOpenplatformAppService = iOpenplatformAppService;
	}
	@Autowired
	public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
		this.openplatformDictGateway = openplatformDictGateway;
	}
}
