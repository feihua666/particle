package com.particle.dream.app.ssq.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dream.app.ssq.structmapping.SsqCodeAppStructMapping;
import com.particle.dream.domain.ssq.SsqCode;
import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.gateway.SsqCodeCrawlingGateway;
import com.particle.dream.domain.ssq.gateway.SsqCodeGateway;
import com.particle.dream.domain.ssq.gateway.SsqCodeOpenedGateway;
import com.particle.dream.domain.ssq.value.SsqCodeCrawlingResult;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeOpenedService;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeService;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 双色球开奖 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Component
@Validated
public class SsqCodeOpenedCreateCommandExecutor  extends AbstractBaseExecutor {

	private SsqCodeOpenedGateway ssqCodeOpenedGateway;

	private SsqCodeCrawlingGateway ssqCodeCrawlingGateway;

	private ISsqCodeOpenedService iSsqCodeOpenedService;

	private ISsqCodeService iSsqCodeService;

	private SsqCodeGateway ssqCodeGateway;

	private static boolean isInterrupt = false;
	private static boolean isRunning = false;

	/**
	 * 初始化所有开奖号码
	 * @return
	 */
	public Response allCodeInit() {
		if (isRunning) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "已经有任务在处理，请勿重复执行");
		}
		isRunning = true;
		isInterrupt = false;

		try {
			SsqCodeOpenedDO maxOpenedPhaseYear = iSsqCodeOpenedService.getMaxOpenedPhaseYear();
			int beginYear =2003;
			if (maxOpenedPhaseYear != null) {
				beginYear = maxOpenedPhaseYear.getOpenedPhaseYear();
			}

			// 暂停10s,
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
			}
			SsqCodeOpenedDO maxOpenedPhaseYear1 = iSsqCodeOpenedService.getMaxOpenedPhaseYear();

			if (maxOpenedPhaseYear == null) {
				Assert.isTrue(maxOpenedPhaseYear1 == null,"好像初始化添加已经在跑，请勿重复执行");
			}else {
				Assert.isTrue(maxOpenedPhaseYear.getId().equals(maxOpenedPhaseYear1.getId()),"好像初始化添加已经在跑，请勿重复执行");
			}

			int maxYear = LocalDate.now().getYear();
			for (int i = beginYear; i <= maxYear; i++) {
				int year = i;
				List<SsqCodeCrawlingResult> results = ssqCodeCrawlingGateway.crawlingByYear(year);
				if (results != null && !results.isEmpty()) {
					List<SsqCodeOpenedDO> ssqCodeOpenedDOS = iSsqCodeOpenedService.listByOpenedPhaseYear(year);
					if (ssqCodeOpenedDOS != null && !ssqCodeOpenedDOS.isEmpty()) {
						// 过滤到已经存在的
						results = results.stream()
								.filter(
										SsqCodeCrawlingResult -> !ssqCodeOpenedDOS.stream()
												.anyMatch(ssqCodeOpenedDO -> ssqCodeOpenedDO.getOpenedPhase().equals(SsqCodeCrawlingResult.getOpenedPhase()))
								)
								.collect(Collectors.toList());
					}
					List<SsqCodeOpened> ssqCodeOpeneds = results.stream().map(ssqCodeCrawlingResult -> {

						SsqCodeOpened ssqCodeOpened = SsqCodeOpened.create(ssqCodeCrawlingResult);
						List<Integer> orderedRedBalls = ssqCodeCrawlingResult.orderedRedBalls();
						SsqCode byBall = ssqCodeGateway.getByBall(
								orderedRedBalls.get(0),
								orderedRedBalls.get(1),
								orderedRedBalls.get(2),
								orderedRedBalls.get(3),
								orderedRedBalls.get(4),
								orderedRedBalls.get(5),
								ssqCodeCrawlingResult.getOpenedBlue()
						);
						Assert.notNull(byBall,"好像还没有初始化双色球号码，请先初始化双色球号码");
						ssqCodeOpened.changeSsqCodeId(byBall.getId().getId());
						ssqCodeOpened.fillWithSsqCode(byBall);
						return ssqCodeOpened;
					}).collect(Collectors.toList());

					if (CollectionUtil.isNotEmpty(ssqCodeOpeneds)) {
						ssqCodeOpenedGateway.addBatch(ssqCodeOpeneds);
						for (SsqCodeOpened ssqCodeOpened : ssqCodeOpeneds) {
							iSsqCodeService.updateSetIsOpened(ssqCodeOpened.getSsqCodeId(), true);
						}
					}
				}
				if (isInterrupt) {
					isRunning = false;
					throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.PLACEHOLDER_ERROR, "初始化所有开奖号码程序被中断");
				}
			}
		} finally {
			isRunning = false;
		}


		return Response.buildSuccess();
	}

	/**
	 * 停止初始化
	 * @return
	 */
	public Response allCodeStop() {
		isInterrupt = true;
		return Response.buildSuccess();
	}


	/**
	 * 注入使用set方法
	 * @param ssqCodeOpenedGateway
	 */
	@Autowired
	public void setSsqCodeOpenedGateway(SsqCodeOpenedGateway ssqCodeOpenedGateway) {
		this.ssqCodeOpenedGateway = ssqCodeOpenedGateway;
	}
	@Autowired
	public void setSsqCodeCrawlingGateway(SsqCodeCrawlingGateway ssqCodeCrawlingGateway) {
		this.ssqCodeCrawlingGateway = ssqCodeCrawlingGateway;
	}
	@Autowired
	public void setiSsqCodeOpenedService(ISsqCodeOpenedService iSsqCodeOpenedService) {
		this.iSsqCodeOpenedService = iSsqCodeOpenedService;
	}
	@Autowired
	public void setiSsqCodeService(ISsqCodeService iSsqCodeService) {
		this.iSsqCodeService = iSsqCodeService;
	}

	@Autowired
	public void setSsqCodeGateway(SsqCodeGateway ssqCodeGateway) {
		this.ssqCodeGateway = ssqCodeGateway;
	}
}
