package com.particle.dream.app.ssq.executor;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dream.domain.ssq.SsqCode;
import com.particle.dream.domain.ssq.gateway.SsqCodeGateway;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeService;
import com.particle.dream.infrastructure.ssq.structmapping.SsqCodeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.Assert;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 双色球号码 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Slf4j
@Component
@Validated
public class SsqCodeCreateCommandExecutor  extends AbstractBaseExecutor {

	private SsqCodeGateway ssqCodeGateway;
	private ISsqCodeService iSsqCodeService;

	private static boolean isInterrupt = false;
	private static boolean isRunning = false;


	/**
	 * 初始化所有号码
	 * @return
	 */
	public Response allCodeInit() {

		if (isRunning) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "已经有任务在处理，请勿重复执行");
		}
		isRunning = true;
		isInterrupt = false;

		try {
			int batchSize = 1000;
			List<SsqCode> toBeInsert = new ArrayList<>(batchSize);
			SsqCodeDO maxSeqNo = iSsqCodeService.getMaxSeqNo();
			if (maxSeqNo != null && 17721008 == maxSeqNo.getSeqNo()) {
				Assert.isTrue(17721008 != maxSeqNo.getSeqNo(),"所有数据已添加完成，不需要再初始化");
			}
			// 暂停10s,
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
			}
			SsqCodeDO maxSeqNo1 = iSsqCodeService.getMaxSeqNo();
			if (maxSeqNo == null) {
				Assert.isTrue(maxSeqNo1 == null,"好像初始化添加已经在跑，请勿重复执行");
			}else {
				Assert.isTrue(maxSeqNo.getSeqNo().equals(maxSeqNo1.getSeqNo()),"好像初始化添加已经在跑，请勿重复执行");
			}

			SsqCode.generateAllSsqCode(ssqCodeValue -> {
				// 如果数据为空或已经添加了一部分，继续添加
				if (maxSeqNo == null || maxSeqNo.getSeqNo() < ssqCodeValue.getSeqNo()) {
					SsqCode ssqCode = SsqCode.create(ssqCodeValue);
					toBeInsert.add(ssqCode);
					if (toBeInsert.size() >= batchSize) {
						ssqCodeGateway.addBatch(toBeInsert);
						toBeInsert.clear();
					}
				}

				if (isInterrupt) {
					isRunning = false;
					// 程序被中断
					if (toBeInsert.size() > 0) {
						ssqCodeGateway.addBatch(toBeInsert);
					}
					throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.PLACEHOLDER_ERROR, "初始化所有号码程序被中断");
				}


			});
			if (toBeInsert.size() > 0) {
				ssqCodeGateway.addBatch(toBeInsert);
			}
		} finally {
			isRunning = false;
		}
		return Response.buildSuccess();
	}

	/**
	 * 更新所有号码
	 * @return
	 */
	public Response allCodeUpdate() {
		if (isRunning) {
			throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.BAD_REQUEST_ERROR, "已经有任务在处理，请勿重复执行");
		}
		isRunning = true;
		isInterrupt = false;

		try {
			int batchSize = 1000;
			long pageNo = 0;
			List<SsqCodeDO> records = null;


			SsqCodeDO lastestUpdate = iSsqCodeService.getLastestUpdate();
			Assert.notNull(lastestUpdate,"暂无数据可更新");

			// 暂停10s,
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
			}
			SsqCodeDO lastestUpdate1 = iSsqCodeService.getLastestUpdate();
			Assert.isTrue(lastestUpdate.getId().equals(lastestUpdate1.getId()),"好像更新已经在跑，请勿重复执行");


			do {
				Page page = new Page(++pageNo, batchSize, false);
				Page<SsqCodeDO> pageResult = iSsqCodeService.page(page, Wrappers.<SsqCodeDO>lambdaQuery().gt(lastestUpdate.getSeqNo() != 17721008,SsqCodeDO::getSeqNo,lastestUpdate.getSeqNo()));
				records = pageResult.getRecords();
				if (records != null && !records.isEmpty()) {
					List<SsqCode> ssqCodeList = records.stream().map(ssqCodeDO -> {
						SsqCode ssqCode = DomainFactory.create(SsqCode.class);
						ssqCode = SsqCodeInfrastructureStructMapping.instance.ssqCodeDOToSsqCode(ssqCode, ssqCodeDO);
						// 重新填充
						ssqCode.fillSelf();
						return ssqCode;
					}).collect(Collectors.toList());

					ssqCodeGateway.updateBatch(ssqCodeList);
				}
				if (isInterrupt) {
					isRunning = false;
					throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.PLACEHOLDER_ERROR, "更新所有号码程序被中断");
				}

			} while (records != null && !records.isEmpty());
		} finally {
			isRunning = false;
		}


		return Response.buildSuccess();
	}


	public Response allCodeStop() {
		isInterrupt = true;
		return Response.buildSuccess();
	}

	/**
	 * 注入使用set方法
	 * @param ssqCodeGateway
	 */
	@Autowired
	public void setSsqCodeGateway(SsqCodeGateway ssqCodeGateway) {
		this.ssqCodeGateway = ssqCodeGateway;
	}
	@Autowired
	public void setiSsqCodeService(ISsqCodeService iSsqCodeService) {
		this.iSsqCodeService = iSsqCodeService;
	}
}
