package com.particle.dream.app.ssq.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedCheckWinCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedCheckWinVO;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.dream.infrastructure.ssq.service.ISsqCodeOpenedService;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>
 * 双色球开奖 检查是否中奖指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-30 14:53:52
 */
@Component
@Validated
public class SsqCodeOpenedCheckWinCommandExecutor extends AbstractBaseExecutor {

	private ISsqCodeOpenedService iSsqCodeOpenedService;
	public SingleResponse<SsqCodeOpenedCheckWinVO> checkWin(@Valid SsqCodeOpenedCheckWinCommand ssqCodeOpenedCheckWinCommand) {
		SsqCodeOpenedDO ssqCodeOpenedDO = null;
		Integer openedPhase = ssqCodeOpenedCheckWinCommand.getOpenedPhase();
		if (openedPhase == null) {
			ssqCodeOpenedDO = iSsqCodeOpenedService.getMaxOpenedPhase();
		} else {
			ssqCodeOpenedDO = iSsqCodeOpenedService.getByOpenedPhase(openedPhase);
		}
		Assert.notNull(ssqCodeOpenedDO,"开奖数据不存在");


		SsqCodeOpenedCheckWinVO ssqCodeOpenedCheckWinVO = new SsqCodeOpenedCheckWinVO();
		ssqCodeOpenedCheckWinVO.setOpenedPhase(ssqCodeOpenedDO.getOpenedPhase());
		ssqCodeOpenedCheckWinVO.setOpenedPhaseNum(
				StrUtil.format("{},{},{},{},{},{} {}",
						ssqCodeOpenedDO.getOpenedRed1(),
						ssqCodeOpenedDO.getOpenedRed2(),
						ssqCodeOpenedDO.getOpenedRed3(),
						ssqCodeOpenedDO.getOpenedRed4(),
						ssqCodeOpenedDO.getOpenedRed5(),
						ssqCodeOpenedDO.getOpenedRed6(),
						ssqCodeOpenedDO.getOpenedBlue()
						)
		);
		for (String selectedNum : ssqCodeOpenedCheckWinCommand.getSelectedNums()) {
			String[] split = selectedNum.split(" ");
			Integer checkLotteryResult = checkLottery(
					StrUtil.format("{},{},{},{},{},{}",
							ssqCodeOpenedDO.getOpenedRed1(),
							ssqCodeOpenedDO.getOpenedRed2(),
							ssqCodeOpenedDO.getOpenedRed3(),
							ssqCodeOpenedDO.getOpenedRed4(),
							ssqCodeOpenedDO.getOpenedRed5(),
							ssqCodeOpenedDO.getOpenedRed6()
					).split(","),
					ssqCodeOpenedDO.getOpenedBlue().toString(),
					split[0].split(","),
					split[1]
			);
			ssqCodeOpenedCheckWinVO.addCheckWinItem(selectedNum,checkLotteryResult);
		}

		return SingleResponse.of(ssqCodeOpenedCheckWinVO);
	}
	private Integer checkLottery(String[] winningRed, String winningBlue, String[] yourRed, String yourBlue) {
		int correctRedCount = 0;
		// 计算红球正确的个数
		for (String red : yourRed) {
			if (Arrays.stream(winningRed).collect(Collectors.toList()).contains(red)) {
				correctRedCount++;
			}
		}

		// 判断中奖等级
		if (correctRedCount == 6 && yourBlue == winningBlue) {
			return 1;
		} else if (correctRedCount == 6) {
			return 2;
		} else if (correctRedCount == 5 && yourBlue == winningBlue) {
			return 3;
		} else if (correctRedCount == 5 || correctRedCount == 4 && yourBlue == winningBlue) {
			return 4;
		} else if (correctRedCount == 4 || correctRedCount == 3 && yourBlue == winningBlue) {
			return 5;
		} else if (yourBlue == winningBlue && correctRedCount <= 2) {
			return 6;
		} else {
			return 0;
		}

	}
	@Autowired
	public void setiSsqCodeOpenedService(ISsqCodeOpenedService iSsqCodeOpenedService) {
		this.iSsqCodeOpenedService = iSsqCodeOpenedService;
	}

}
