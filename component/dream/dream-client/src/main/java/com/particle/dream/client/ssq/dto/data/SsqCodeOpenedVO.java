package com.particle.dream.client.ssq.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
/**
 * <p>
 * 双色球开奖 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Data
@Schema
public class SsqCodeOpenedVO extends AbstractBaseIdVO {

    @Schema(description = "双色球号码id")
    private Long ssqCodeId;
    
    @Schema(description = "开奖日期")
    private LocalDate openedDate;
        
    @Schema(description = "开奖期号年份")
    private Integer openedPhaseYear;
    
    @Schema(description = "开奖期号数")
    private Integer openedPhaseNum;
    
    @Schema(description = "开奖星期号")
    private Integer openedWeekDay;
    
    @Schema(description = "开奖期号")
    private String openedPhase;
    
    @Schema(description = "开奖红球1")
    private Integer openedRed1;
    
    @Schema(description = "开奖红球2")
    private Integer openedRed2;
    
    @Schema(description = "开奖红球3")
    private Integer openedRed3;
    
    @Schema(description = "开奖红球4")
    private Integer openedRed4;
    
    @Schema(description = "开奖红球5")
    private Integer openedRed5;
    
    @Schema(description = "开奖红球6")
    private Integer openedRed6;

	@Schema(description = "开奖蓝球")
	private Integer openedBlue;
    
    @Schema(description = "中1等奖注数")
    private Integer win1Num;
    
    @Schema(description = "中1等奖单注金额")
    private Integer win1Amount;
    
    @Schema(description = "中1等奖总金额")
    private Long win1TotalAmount;
    
    @Schema(description = "中2等奖注数")
    private Integer win2Num;
    
    @Schema(description = "中2等奖单注金额")
    private Integer win2Amount;
    
    @Schema(description = "中2等奖总金额")
    private Long win2TotalAmount;
    
    @Schema(description = "中3等奖注数")
    private Integer win3Num;
    
    @Schema(description = "中3等奖单注金额")
    private Integer win3Amount;
    
    @Schema(description = "中3等奖总金额")
    private Long win3TotalAmount;
    
    @Schema(description = "中4等奖注数")
    private Integer win4Num;
    
    @Schema(description = "中4等奖单注金额")
    private Integer win4Amount;
    
    @Schema(description = "中4等奖总金额")
    private Long win4TotalAmount;
    
    @Schema(description = "中5等奖注数")
    private Integer win5Num;
    
    @Schema(description = "中5等奖单注金额")
    private Integer win5Amount;
    
    @Schema(description = "中5等奖总金额")
    private Long win5TotalAmount;
    
    @Schema(description = "中6等奖注数")
    private Integer win6Num;
    
    @Schema(description = "中6等奖单注金额")
    private Integer win6Amount;
    
    @Schema(description = "中6等奖总金额")
    private Long win6TotalAmount;
    
    @Schema(description = "中奖总金额")
    private Long winTotalAmount;
    
    @Schema(description = "奖池金额")
    private Long prizePoolAmount;

	@Schema(description = "下一期奖池金额，单位元")
	private Long nextPrizePoolAmount;
    
    @Schema(description = "销售额")
    private Long saleAmount;
    
    @Schema(description = "红冷号个数")
    private Integer redCold2Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot2Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold3Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot3Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold4Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot4Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold5Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot5Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold6Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot6Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold7Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot7Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold8Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot8Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold9Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot9Ratio;
    
    @Schema(description = "红冷号个数")
    private Integer redCold10Ratio;
    
    @Schema(description = "红热号个数")
    private Integer redHot10Ratio;




    @Schema(description = "序号")
    private Integer seqNo;

	@Schema(description = "红球序号，从1开始递增")
	private Integer redSeqNo;

    @Schema(description = "红球1")
    private Integer red1;

    @Schema(description = "红球2")
    private Integer red2;

    @Schema(description = "红球3")
    private Integer red3;

    @Schema(description = "红球4")
    private Integer red4;

    @Schema(description = "红球5")
    private Integer red5;

    @Schema(description = "红球6")
    private Integer red6;

    @Schema(description = "蓝球")
    private Integer blue;

    @Schema(description = "红球和值")
    private Integer redSum;

    @Schema(description = "红球和尾值")
    private Integer redSumLast;

    @Schema(description = "总和值")
    private Integer redBlueSum;

    @Schema(description = "总和尾值")
    private Integer redBlueSumLast;

    @Schema(description = "红球跨度")
    private Integer redSpan;

    @Schema(description = "ac值")
    private Integer redAc;

    @Schema(description = "红1区个数")
    private Integer redRegion1Ratio;

    @Schema(description = "红2区个数")
    private Integer redRegion2Ratio;

    @Schema(description = "红3区个数")
    private Integer redRegion3Ratio;

    @Schema(description = "红奇数个数")
    private Integer redOddRatio;

    @Schema(description = "红偶数个数")
    private Integer redEvenRatio;

    @Schema(description = "是否包含连号")
    private Boolean isHasSerialNum;

    @Schema(description = "连号个数")
    private Integer serialTimes;

    @Schema(description = "最大连号长度")
    private Integer maxSerialLength;

    @Schema(description = "是否包含偶连号，即间隔2")
    private Boolean isHasEvenSerialNum;

    @Schema(description = "偶连号个数，如：2 4 22 24 25 33 7则为2，因为2 4算一个，22 24 算一个")
    private Integer evenSerialTimes;

    @Schema(description = "最大偶连号长度")
    private Integer evenMaxSerialLength;

    @Schema(description = "是否红蓝重号")
    private Boolean isRedIncludeBlue;

    @Schema(description = "是否蓝球为奇数")
    private Boolean isBlueOdd;
}