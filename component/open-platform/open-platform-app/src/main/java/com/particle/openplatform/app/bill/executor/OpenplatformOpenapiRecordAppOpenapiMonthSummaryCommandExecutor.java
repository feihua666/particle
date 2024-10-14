package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.mybatis.plus.crud.ServiceHelperTool;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.dos.view.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.bill.dto.OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口月汇总 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
	private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;

	private IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;

	private IOpenplatformAppService iOpenplatformAppService;

	private OpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor;

	/**
	 * 统计上月的统计数据
	 * @param isIncludeDaySummary
	 * @param openplatformAppId 要统计的应用id，如果不传递，则统计所有应用
	 */
	public Response LastMonthStatistic(Boolean isIncludeDaySummary, Long openplatformAppId) {
		LocalDate today = LocalDate.now();
		LocalDate lastMonth = today.minusMonths(1);
		Integer year = lastMonth.getYear();
		Integer month = lastMonth.getMonthValue();
		return statistic(year,month,isIncludeDaySummary,openplatformAppId);
	}

	/**
	 * 统计某一月的数据
	 * @param year
	 * @param month
	 * @param isIncludeDaySummary
	 * @param openplatformAppId 要统计的应用id，如果不传递，则统计所有应用
	 */
	public Response statistic(Integer year, Integer month,Boolean isIncludeDaySummary, Long openplatformAppId) {

		LocalDate localDate = LocalDate.of(year, month, 1);
		LocalDate monthStart = localDate;
		LocalDate monthEnd = YearMonth.of(year, month).atEndOfMonth();

		if (isIncludeDaySummary != null && isIncludeDaySummary) {
			int startDay = monthStart.getDayOfMonth();
			int endDay = monthEnd.getDayOfMonth();
			for (int i = startDay; i <= endDay; i++) {
				LocalDate localDateDay = LocalDate.of(year, month, i);
				openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor.statistic(localDateDay,openplatformAppId);
			}
		}


		ServiceHelperTool.<OpenplatformAppDO>pageExecute(page -> iOpenplatformAppService.page(page),
				page ->{
					List<OpenplatformAppDO> records = page.getRecords();
					for (OpenplatformAppDO record : records) {
						singleOneMonthStatistic(record.getId(),
								record.getAppId(),
								record.getOwnerCustomerId(),
								monthStart,
								monthEnd,
								year,month);
					}
				},"openplatformOpenapiRecordAppOpenapiMonthSummary statistic");

		return Response.buildSuccess();
	}
	/**
	 * 单月统计
	 * @param openplatformAppId
	 * @param appId
	 * @param ownerCustomerId
	 * @param dayAtStart 某一月的开始时间
	 * @param dayAtEnd 某一月的结束时间
	 * @param year 年
	 * @param month 月
	 */
	private void singleOneMonthStatistic(Long openplatformAppId,
									   String appId,
									   Long ownerCustomerId,
									   LocalDate dayAtStart,
									   LocalDate dayAtEnd,
									   Integer year,Integer month) {


		OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam = OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam.create(openplatformAppId, dayAtStart, dayAtEnd);
		List<OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO> openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDOS = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.openAppIdOpenapiIdStatistics(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsParam);


		for (OpenplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO : openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDOS) {
			OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO openplatformOpenapiRecordAppOpenapiMonthSummaryDO = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.getByOpenplatformAppIdAndOpenplatformOpenapiIdAndYearAndMonth(
					openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformAppId(),
					openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformOpenapiId(),
					year,
					month
			);
			boolean exist = false;
			if (openplatformOpenapiRecordAppOpenapiMonthSummaryDO == null) {
				openplatformOpenapiRecordAppOpenapiMonthSummaryDO = new OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO();
				exist = false;
			}else {
				exist = true;
			}
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setOpenplatformAppId(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformAppId());
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setAppId(appId);
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setOpenplatformOpenapiId(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformOpenapiId());
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setYear(year);
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setMonth(month);
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setCustomerId(ownerCustomerId);
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setTotalCall(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalCall());
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setTotalFeeCall(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeCall());
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setAverageUnitPriceAmount(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getAverageUnitPriceAmount());
			openplatformOpenapiRecordAppOpenapiMonthSummaryDO.setTotalFeeAmount(openplatformOpenapiRecordAppOpenapiDaySummaryOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeAmount());

			if (exist) {
				iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.updateById(openplatformOpenapiRecordAppOpenapiMonthSummaryDO);
			} else {
				iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.save(openplatformOpenapiRecordAppOpenapiMonthSummaryDO);
			}
		}
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway(OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiMonthSummaryGateway = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiMonthSummaryService(IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDaySummaryService(IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDaySummaryService = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
	}
	@Autowired
	public void setIOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
		this.iOpenplatformAppService = iOpenplatformAppService;
	}
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor(OpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor) {
		this.openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor = openplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor;
	}
}
