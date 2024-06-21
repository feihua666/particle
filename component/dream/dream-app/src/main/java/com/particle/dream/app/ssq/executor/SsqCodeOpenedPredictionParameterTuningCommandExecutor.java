package com.particle.dream.app.ssq.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedPredictionParameterTuningCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedPredictionParameterTuningRegionVO;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeOpenedService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.exception.Assert;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 双色球开奖 调参预测指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-05 13:27:03
 */
@Component
@Validated
public class SsqCodeOpenedPredictionParameterTuningCommandExecutor extends AbstractBaseExecutor {

	private ISsqCodeOpenedService iSsqCodeOpenedService;

	/**
	 * 参数调优预测
	 * @param predictionParameterTuningCommand
	 * @return
	 */
	public MultiResponse<SsqCodeOpenedPredictionParameterTuningRegionVO> predictionParameterTuning(@Valid SsqCodeOpenedPredictionParameterTuningCommand predictionParameterTuningCommand) {

		List<SsqCodeOpenedDO> ssqCodeOpenedDOList = iSsqCodeOpenedService.list();
		// 基本的训练数据
		List<SsqCodeOpenedDO> trainingssqCodeOpenedDOS = ssqCodeOpenedDOList.stream()
				.filter(ssqCodeOpenedDO ->
						ssqCodeOpenedDO.getOpenedPhaseYear().equals( predictionParameterTuningCommand.getOpenedPhaseYear())
								)
				.collect(Collectors.toList());

		Assert.notEmpty(trainingssqCodeOpenedDOS, "预测需要使用历史的开奖数据，将选择合适的条件以过滤基础的训练数据");

		// 用于预测的数据，作为用于验证的数据
		List<SsqCodeOpenedDO> predictionSsqCodeOpenedDOS = ssqCodeOpenedDOList.stream()
				.filter(ssqCodeOpenedDO ->
						ssqCodeOpenedDO.getOpenedPhaseYear() >= predictionParameterTuningCommand.getPredictOpenedPhaseYearStart()
						&& ssqCodeOpenedDO.getOpenedPhaseNum() >= predictionParameterTuningCommand.getPredictOpenedPhaseNumStart()
								&& (predictionParameterTuningCommand.getPredictOpenedPhaseYearEnd() == null || ssqCodeOpenedDO.getOpenedPhaseYear() <= predictionParameterTuningCommand.getPredictOpenedPhaseYearEnd())
								&& (predictionParameterTuningCommand.getPredictOpenedPhaseNumEnd() == null || ssqCodeOpenedDO.getOpenedPhaseNum() <= predictionParameterTuningCommand.getPredictOpenedPhaseNumEnd())
				)
				.collect(Collectors.toList());

		Assert.notEmpty(predictionSsqCodeOpenedDOS, "预测需要使用历史的开奖数据，将选择合适的条件以过滤用于验证的数据");


		// 支持区间自动范围
		Integer regionCountMin = predictionParameterTuningCommand.getRegionCountMin();
		Integer regionCountMax = predictionParameterTuningCommand.getRegionCountMax();

		List<RegionData> result = new ArrayList<>();
		// 根据区间范围遍历区间
		for (int i = regionCountMin; i <= regionCountMax; i++) {
			for (double j = predictionParameterTuningCommand.getPredictBasePercentStart(); j <= predictionParameterTuningCommand.getPredictBasePercentEnd() ; j += predictionParameterTuningCommand.getPredictBasePercentStep()) {
				double predictBasePercent = j;
				List<RegionData> regionDataList = null;
				// 计算当前区间下的开奖数据
				if (predictionParameterTuningCommand.getIsUserRedSeqNo()) {
					regionDataList = computeRegionData(trainingssqCodeOpenedDOS, 1107568, i, SsqCodeOpenedDO::getRedSeqNo);
				}else {
					regionDataList = computeRegionData(trainingssqCodeOpenedDOS, 17721008, i, SsqCodeOpenedDO::getSeqNo);
				}

				// 上面将开奖数量数据计算好后，过滤需要的开奖数量范围内的数据
				regionDataList = regionDataList.stream().filter(
						regionData ->
								regionData.getOpenedCount() >= predictionParameterTuningCommand.getOpenedCountMin()
										&& regionData.getOpenedCount() <= predictionParameterTuningCommand.getOpenedCountMax()
				).collect(Collectors.toList());

				// 开始预测，根据选定好的开奖数量范围预测
				predict(regionDataList,
						predictionParameterTuningCommand.getIsUserRedSeqNo(),
						predictBasePercent,
						predictionParameterTuningCommand.getPredictPerRegionCount(),
						predictionSsqCodeOpenedDOS
				);
				// 过滤没有命中的
				regionDataList = regionDataList.stream().filter(regionData -> regionData.getIsPredictHit()).collect(Collectors.toList());
				result.addAll(regionDataList);
			}

		}

		// 倒序排一下序
		List<SsqCodeOpenedPredictionParameterTuningRegionVO> resultVOs = result.stream()
				.sorted((o1,o2) -> o2.getPredictHitOpenedPhaseSize() - o1.getPredictHitOpenedPhaseSize())
				.map(this::regionDataToPredictionParameterTuningRegionVO)

				.collect(Collectors.toList());

		List<SsqCodeOpenedPredictionParameterTuningRegionVO> subResultVOs = CollectionUtil.sub(resultVOs, 0, 100);
		return MultiResponse.of(subResultVOs);
	}

	/**
	 * 对象转换
	 * @param regionData
	 * @return
	 */
	private SsqCodeOpenedPredictionParameterTuningRegionVO regionDataToPredictionParameterTuningRegionVO(RegionData regionData) {
		SsqCodeOpenedPredictionParameterTuningRegionVO predictionParameterTuningRegionVO = new SsqCodeOpenedPredictionParameterTuningRegionVO();

		predictionParameterTuningRegionVO.setOpenedCount(regionData.getOpenedCount());
		predictionParameterTuningRegionVO.setRegionSeqNoMin(regionData.getRegionSeqNoMin());
		predictionParameterTuningRegionVO.setRegionSeqNoMax(regionData.getRegionSeqNoMax());
		predictionParameterTuningRegionVO.setRegionNo(regionData.getRegionNo());
		predictionParameterTuningRegionVO.setRegionCount(regionData.getRegionCount());
		predictionParameterTuningRegionVO.setPredictSeqNoMin(regionData.getPredictSeqNoMin());
		predictionParameterTuningRegionVO.setPredictSeqNoMax(regionData.getPredictSeqNoMax());
		predictionParameterTuningRegionVO.setPredictPercent(regionData.getPredictPercent());
		predictionParameterTuningRegionVO.setIsPredictHit(regionData.getIsPredictHit());
		predictionParameterTuningRegionVO.setPredictHitOpenedPhase(regionData.getPredictHitOpenedPhase());

		return predictionParameterTuningRegionVO;
	}

	/**
	 * 预测
	 * @param regionDataList
	 * @param isUserRedSeqNo
	 * @param predictBasePercent
	 * @param predictPerRegionCount
	 * @param ssqCodeOpenedDOS 用来作为预测验证的开奖数据
	 */
	private void predict(List<RegionData> regionDataList,
						 Boolean isUserRedSeqNo,
						 Double predictBasePercent,
						 Integer predictPerRegionCount,
						 List<SsqCodeOpenedDO> ssqCodeOpenedDOS) {
		for (RegionData regionData : regionDataList) {
			int delta = regionData.getRegionSeqNoMax() - regionData.getRegionSeqNoMin();
			int predictPerRegionCountHalf = predictPerRegionCount / 2;
			int baseSeqNo = regionData.getRegionSeqNoMin() + (int)(delta * predictBasePercent / 100);


			int predictSeqNoMin = baseSeqNo - predictPerRegionCountHalf;
			int predictSeqNoMax = predictSeqNoMin + predictPerRegionCount + 1;

			List<Integer> predictHitOpenedPhase = ssqCodeOpenedDOS.stream().filter(ssqCodeOpenedDO -> {
				if (isUserRedSeqNo) {
					return ssqCodeOpenedDO.getRedSeqNo() > predictSeqNoMin && ssqCodeOpenedDO.getRedSeqNo() < predictSeqNoMax;
				}else {
					return ssqCodeOpenedDO.getSeqNo() > predictSeqNoMin && ssqCodeOpenedDO.getSeqNo() < predictSeqNoMax;
				}
			}).map(ssqCodeOpenedDO -> ssqCodeOpenedDO.getOpenedPhase()).collect(Collectors.toList());
			regionData.afterPredictFill(predictSeqNoMin, predictSeqNoMax,predictBasePercent,predictHitOpenedPhase);

		}

	}

	/**
	 * 区域计算
	 * @param ssqCodeOpenedDOS 已开奖基础数据
	 * @param poolCount 总数量，所有号码穷举后的数量
	 * @param regionCount 分为多少个区
	 * @param fieldValueFunction 获取号码的函数，取哪个字段的值，一般有两种，一种是红球号，一种是全部号码
	 * @return
	 */
	private List<RegionData> computeRegionData(List<SsqCodeOpenedDO> ssqCodeOpenedDOS, int poolCount, int regionCount, Function<SsqCodeOpenedDO, Integer> fieldValueFunction) {
		// 将总的号码池分为n个区域，每个区域的个数
		double perRegionCountDouble = poolCount / regionCount;
		int perRegionCountInt = (int) perRegionCountDouble;

		List<RegionData> regionDataList = new ArrayList<>(regionCount);
		for (int i = 0; i < regionCount; i++) {
			int perRegionMax = (i + 1) * perRegionCountInt;
			int perRegionMin = perRegionMax - perRegionCountInt;
			int openedCount = (int) ssqCodeOpenedDOS.stream()
					.filter(ssqCodeOpenedDO -> fieldValueFunction.apply(ssqCodeOpenedDO) >= perRegionMin && fieldValueFunction.apply(ssqCodeOpenedDO) < perRegionMax).count();

			regionDataList.add(RegionData.create(openedCount, perRegionMin, perRegionMax,i + 1,regionCount));
		}

		return regionDataList;

	}

	/**
	 * 区域数据
	 */
	@Data
	private static class RegionData {
		/**
		 * 当前区已开奖数量
		 */
		private int openedCount;
		/**
		 * 当前区序号最小值
		 */
		private int regionSeqNoMin;
		/**
		 * 当前区最大值
		 */
		private int regionSeqNoMax;
		/**
		 * 区序号
		 */
		private int regionNo;
		/**
		 * 总多少个区
		 */
		private int regionCount;

		/**
		 * 预测序号最小值，一般不包括值本身
		 * 预测后有值
		 */
		private Integer predictSeqNoMin;
		/**
		 * 预测序号最大值，一般不包括值本身
		 * 预测后有值
		 */
		private Integer predictSeqNoMax;

		/**
		 * 预测基线百分比数
		 */
		private Double predictPercent;
		/**
		 * 是否预测命中
		 */
		private Boolean isPredictHit;

		/**
		 * 如果预测命中，命中的期号
		 */
		private List<Integer> predictHitOpenedPhase;
		/**
		 * 预测命中期号个数
		 */
		private Integer predictHitOpenedPhaseSize;

		public void afterPredictFill(Integer predictSeqNoMin, Integer predictSeqNoMax,Double predictPercent,List<Integer> predictHitOpenedPhase) {
			this.predictSeqNoMin = predictSeqNoMin;
			this.predictSeqNoMax = predictSeqNoMax;
			this.predictPercent = predictPercent;
			boolean isPredictHit = predictHitOpenedPhase != null && predictHitOpenedPhase.size() > 0;
			this.isPredictHit = isPredictHit;
			this.predictHitOpenedPhase = predictHitOpenedPhase;
			this.predictHitOpenedPhaseSize = 0;
			if (isPredictHit) {
				this.predictHitOpenedPhaseSize = predictHitOpenedPhase.size();
			}
		}

		public static RegionData create(int openedCount,int regionMin,int regionMax,int regionSeqNo,int regionCount) {
			RegionData regionData = new RegionData();
			regionData.openedCount = openedCount;
			regionData.regionSeqNoMin = regionMin;
			regionData.regionSeqNoMax = regionMax;
			regionData.regionNo = regionSeqNo;
			regionData.regionCount = regionCount;
			return regionData;
		}
	}

	@Autowired
	public void setiSsqCodeOpenedService(ISsqCodeOpenedService iSsqCodeOpenedService) {
		this.iSsqCodeOpenedService = iSsqCodeOpenedService;
	}

}
