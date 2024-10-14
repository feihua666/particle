package com.particle.openplatform.app.bill.executor;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.Response;
import com.particle.global.mybatis.plus.crud.ServiceHelperTool;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import com.particle.openplatform.infrastructure.openapirecord.dos.view.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO;
import com.particle.openplatform.infrastructure.openapirecord.dto.OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * 开放平台应用开放接口日汇总 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway;

	private IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;

	private IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService;

	private IOpenplatformAppService iOpenplatformAppService;

	/**
	 * 统计昨天的统计数据
	 * @param openplatformAppId
	 * @return 要统计的应用id，如果不传递，则统计所有应用
	 */
	public Response yesterdayStatistic(Long openplatformAppId) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime yesterday = now.minusDays(1);
		return statistic(yesterday.toLocalDate(),openplatformAppId);
	}

	/**
	 * 统计某一天的数据
	 * 历史所有应用，再根据应用id去统计
	 * @param localDate
	 * @param openplatformAppId 要统计的应用id，如果不传递，则统计所有应用
	 */
	public Response statistic(LocalDate localDate,Long openplatformAppId) {
		LocalDateTime localDateTime = LocalDateTime.of(localDate, LocalTime.now());
		LocalDateTime yesterdayStart = LocalDateTimeUtil.beginOfDay(localDateTime);
		LocalDateTime yesterdayEnd = LocalDateTimeUtil.endOfDay(localDateTime);
		ServiceHelperTool.<OpenplatformAppDO>pageExecute(page -> {
					LambdaQueryWrapper<OpenplatformAppDO> lambdaQueryWrapper = Wrappers.<OpenplatformAppDO>lambdaQuery().eq(openplatformAppId != null,OpenplatformAppDO::getId, openplatformAppId);
					return iOpenplatformAppService.page(page,lambdaQueryWrapper);
				},
				page ->{
					List<OpenplatformAppDO> records = page.getRecords();
					for (OpenplatformAppDO record : records) {
						singleOneDayStatistic(record.getId(),
								record.getAppId(),
								record.getOwnerCustomerId(),
								yesterdayStart,
								yesterdayEnd,
								localDate);
					}
				},"openplatformOpenapiRecordAppOpenapiDaySummary statistic");

		return Response.buildSuccess();
	}
	/**
	 * 单日统计，统计指定应用id的某一日的汇总数据
	 * @param openplatformAppId 要统计的应用id，可以根据该id去统计
	 * @param appId 要统计的应用id对应的appId
	 * @param ownerCustomerId 要统计的应用id对应的客户id
	 * @param requestHandleAtStart 某一日的开始时间
	 * @param requestHandleAtEnd 某一日的结束时间
	 * @param dayAt 某一日的日期
	 */
	private void singleOneDayStatistic(Long openplatformAppId,
								 String appId,
								 Long ownerCustomerId,
								 LocalDateTime requestHandleAtStart,
								 LocalDateTime requestHandleAtEnd,
								 LocalDate dayAt) {


		OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam = OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam.create(openplatformAppId, requestHandleAtStart, requestHandleAtEnd);
		List<OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO> openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDOS = iOpenplatformOpenapiRecordService.openAppIdOpenapiIdStatistics(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsParam);


		for (OpenplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO : openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDOS) {
			OpenplatformOpenapiRecordAppOpenapiDaySummaryDO openplatformOpenapiRecordAppOpenapiDaySummaryDO = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.getByOpenplatformAppIdAndOpenplatformOpenapiIdAndDayAt(
					openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformAppId(),
					openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformOpenapiId(),
					dayAt
			);
			boolean exist = false;
			if (openplatformOpenapiRecordAppOpenapiDaySummaryDO == null) {
				openplatformOpenapiRecordAppOpenapiDaySummaryDO = new OpenplatformOpenapiRecordAppOpenapiDaySummaryDO();
				exist = false;
			}else {
				exist = true;
			}
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setOpenplatformAppId(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformAppId());
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setAppId(appId);
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setOpenplatformOpenapiId(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getOpenplatformOpenapiId());
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setDayAt(dayAt);
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setCustomerId(ownerCustomerId);
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setTotalCall(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalCall());
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setTotalFeeCall(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeCall());
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setAverageUnitPriceAmount(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getAverageUnitPriceAmount());
			openplatformOpenapiRecordAppOpenapiDaySummaryDO.setTotalFeeAmount(openplatformOpenapiRecordOpenAppIdOpenapiIdStatisticsVIEWDO.getTotalFeeAmount());

			if (exist) {
				iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.updateById(openplatformOpenapiRecordAppOpenapiDaySummaryDO);
			} else {
				iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.save(openplatformOpenapiRecordAppOpenapiDaySummaryDO);
			}
		}
	}
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryGateway(OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiDaySummaryGateway = openplatformOpenapiRecordAppOpenapiDaySummaryGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDaySummaryService(IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDaySummaryService = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordService(IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService) {
		this.iOpenplatformOpenapiRecordService = iOpenplatformOpenapiRecordService;
	}
	@Autowired
	public void setIOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
		this.iOpenplatformAppService = iOpenplatformAppService;
	}
}
