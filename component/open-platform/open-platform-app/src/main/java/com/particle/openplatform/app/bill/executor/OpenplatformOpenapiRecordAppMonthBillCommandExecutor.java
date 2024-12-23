package com.particle.openplatform.app.bill.executor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.mybatis.plus.crud.ServiceHelperTool;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppMonthBillGateway;
import com.particle.openplatform.domain.enums.OpenPlatformBillStatus;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppMonthBillDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppMonthBillService;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 开放平台应用月账单 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppMonthBillCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppMonthBillGateway openplatformOpenapiRecordAppMonthBillGateway;
	private IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService;
	private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor;
	private IOpenplatformAppService iOpenplatformAppService;

	private OpenplatformDictGateway openplatformDictGateway;

	/**
	 * 统计上月的统计数据
	 * @param isIncludeMonthSummary
	 * @param isIncludeDaySummary 只有在 isIncludeMonthSummary=true 时生效
	 * @param openplatformAppId 要统计的应用id，如果不传递，则统计所有应用
	 * @return
	 */
	public Response LastMonthStatistic(Boolean isIncludeMonthSummary,Boolean isIncludeDaySummary,Long openplatformAppId) {
		LocalDate today = LocalDate.now();
		LocalDate lastMonth = today.minusMonths(1);
		Integer year = lastMonth.getYear();
		Integer month = lastMonth.getMonthValue();
		return statistic(year,month,isIncludeMonthSummary,isIncludeDaySummary,openplatformAppId);
	}

	/**
	 * 统计某一月的数据
	 * @param year
	 * @param month
	 * @param isIncludeMonthSummary
	 * @param isIncludeDaySummary 只有在 isIncludeMonthSummary=true 时生效
	 * @param openplatformAppId 要统计的应用id，如果不传递，则统计所有应用
	 */
	public Response statistic(Integer year, Integer month,Boolean isIncludeMonthSummary,Boolean isIncludeDaySummary,Long openplatformAppId) {
		if (isIncludeMonthSummary != null && isIncludeMonthSummary) {
			openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor.statistic(year,month,isIncludeDaySummary,openplatformAppId);
		}
		Long initStatusDictId = openplatformDictGateway.getDictIdByGroupCodeAndItemValue(OpenPlatformBillStatus.init.groupCode(), OpenPlatformBillStatus.init.itemValue());


		ServiceHelperTool.<OpenplatformAppDO>pageExecute(page -> {
					// 保证 customerId查询不重复，这里使用了group by 没有使用 distinct
					LambdaQueryWrapper<OpenplatformAppDO> lambdaQueryWrapper = Wrappers.<OpenplatformAppDO>lambdaQuery()
							.eq(openplatformAppId != null,OpenplatformAppDO::getId,openplatformAppId);
					return iOpenplatformAppService.page(page, lambdaQueryWrapper);
				},
				page ->{
					List<OpenplatformAppDO> records = page.getRecords();
					for (OpenplatformAppDO record : records) {
						singleOneMonthStatistic(record.getId(),record.getAppId(),record.getOwnerCustomerId(),
								year,month,initStatusDictId);
					}
				},"openplatformOpenapiRecordAppMonthBill statistic");

		return Response.buildSuccess();
	}
	/**
	 * 单月统计,统计指定应用的月数据
	 * @param openplatformAppId 统计该应用的月数据
	 * @param year 年
	 * @param month 月
	 */
	private void singleOneMonthStatistic(Long openplatformAppId,
										 String appId,
										 Long customerId,
										 Integer year,Integer month,Long initStatusDictId) {


		OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam = OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam.create(openplatformAppId, year, month);
		List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO> openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDOS = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.openAppIdStatistics(openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsParam);


		for (OpenplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDO openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO : openplatformOpenapiRecordAppOpenapiMonthSummaryOpenAppIdStatisticsVIEWDOS) {
			OpenplatformOpenapiRecordAppMonthBillDO openplatformOpenapiRecordAppMonthBillDO = iOpenplatformOpenapiRecordAppMonthBillService.getByopenplatformAppIdAndYearAndMonth(
					openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformAppId(),
					year,
					month
			);
			boolean exist = false;
			if (openplatformOpenapiRecordAppMonthBillDO == null) {
				openplatformOpenapiRecordAppMonthBillDO = new OpenplatformOpenapiRecordAppMonthBillDO();
				exist = false;
			}else {
				exist = true;
			}
			openplatformOpenapiRecordAppMonthBillDO.setOpenplatformAppId(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformAppId());
			openplatformOpenapiRecordAppMonthBillDO.setAppId(appId);
			openplatformOpenapiRecordAppMonthBillDO.setCustomerId(customerId);
			openplatformOpenapiRecordAppMonthBillDO.setYear(year);
			openplatformOpenapiRecordAppMonthBillDO.setMonth(month);
			openplatformOpenapiRecordAppMonthBillDO.setTotalCall(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalCall());
			openplatformOpenapiRecordAppMonthBillDO.setTotalFeeCall(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeCall());
			openplatformOpenapiRecordAppMonthBillDO.setTotalFeeAmount(openapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeAmount());
			openplatformOpenapiRecordAppMonthBillDO.setStatusDictId(initStatusDictId);


			if (exist) {
				iOpenplatformOpenapiRecordAppMonthBillService.updateById(openplatformOpenapiRecordAppMonthBillDO);
			} else {
				iOpenplatformOpenapiRecordAppMonthBillService.save(openplatformOpenapiRecordAppMonthBillDO);
			}
		}
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppMonthBillGateway(OpenplatformOpenapiRecordAppMonthBillGateway openplatformOpenapiRecordAppMonthBillGateway) {
		this.openplatformOpenapiRecordAppMonthBillGateway = openplatformOpenapiRecordAppMonthBillGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppMonthBillService(IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService) {
		this.iOpenplatformOpenapiRecordAppMonthBillService = iOpenplatformOpenapiRecordAppMonthBillService;
	}
	@Autowired
	public void setiOpenplatformOpenapiRecordAppOpenapiMonthSummaryService(IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	}

	@Autowired
	public void setIOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
		this.iOpenplatformAppService = iOpenplatformAppService;
	}
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor) {
		this.openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor = openplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor;
	}

	@Autowired
	public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
		this.openplatformDictGateway = openplatformDictGateway;
	}
}
