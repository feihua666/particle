package com.particle.dream.infrastructure.ssq.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import lombok.Data;

import java.time.LocalDate;
/**
 * <p>
 * 双色球开奖表
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Data
@TableName("component_dream_ssq_code_opened")
public class SsqCodeOpenedDO extends BaseDO {

    /**
    * 双色球号码id
    */
    private Long ssqCodeId;

    /**
    * 开奖日期
    */
    private LocalDate openedDate;

    /**
    * 开奖期号年份
    */
    private Integer openedPhaseYear;

	/**
	 * 开奖期号月份
	 */
	private Integer openedPhaseMonth;

    /**
    * 开奖期号数
    */
    private Integer openedPhaseNum;

    /**
    * 开奖星期号，从1-7，7代表星期日
    */
    private Integer openedWeekDay;

    /**
    * 开奖期号
    */
    private Integer openedPhase;

    /**
    * 开奖红球1
    */
    private Integer openedRed1;

    /**
    * 开奖红球2
    */
    private Integer openedRed2;

    /**
    * 开奖红球3
    */
    private Integer openedRed3;

    /**
    * 开奖红球4
    */
    private Integer openedRed4;

    /**
    * 开奖红球5
    */
    private Integer openedRed5;

    /**
    * 开奖红球6
    */
    private Integer openedRed6;

	/**
	 * 开奖蓝球
	 */
	private Integer openedBlue;

    /**
    * 中1等奖注数
    */
    private Integer win1Num;

    /**
    * 中1等奖单注金额，单位元
    */
    private Integer win1Amount;

    /**
    * 中1等奖总金额，单位元
    */
    private Long win1TotalAmount;

    /**
    * 中2等奖注数
    */
    private Integer win2Num;

    /**
    * 中2等奖单注金额，单位元
    */
    private Integer win2Amount;

    /**
    * 中2等奖总金额，单位元
    */
    private Long win2TotalAmount;

    /**
    * 中3等奖注数
    */
    private Integer win3Num;

    /**
    * 中3等奖单注金额，单位元
    */
    private Integer win3Amount;

    /**
    * 中3等奖总金额，单位元
    */
    private Long win3TotalAmount;

    /**
    * 中4等奖注数
    */
    private Integer win4Num;

    /**
    * 中4等奖单注金额，单位元
    */
    private Integer win4Amount;

    /**
    * 中4等奖总金额，单位元
    */
    private Long win4TotalAmount;

    /**
    * 中5等奖注数
    */
    private Integer win5Num;

    /**
    * 中5等奖单注金额，单位元
    */
    private Integer win5Amount;

    /**
    * 中5等奖总金额，单位元
    */
    private Long win5TotalAmount;

    /**
    * 中6等奖注数
    */
    private Integer win6Num;

    /**
    * 中6等奖单注金额，单位元
    */
    private Integer win6Amount;

    /**
    * 中6等奖总金额，单位元
    */
    private Long win6TotalAmount;

    /**
    * 中奖总金额，单位元
    */
    private Long winTotalAmount;

    /**
    * 奖池金额，单位元
    */
    private Long prizePoolAmount;

	/**
	 * 下一期奖池金额，单位元
	 */
	private Long nextPrizePoolAmount;

    /**
    * 销售额，单位元
    */
    private Long saleAmount;

    /**
    * 红冷号个数，最近2期以内未出现的号码
    */
    private Integer redCold2Ratio;

    /**
    * 红热号个数，最近2期以内出现过的号码
    */
    private Integer redHot2Ratio;

    /**
    * 红冷号个数，最近3期以内未出现的号码
    */
    private Integer redCold3Ratio;

    /**
    * 红热号个数，最近3期以内出现过的号码
    */
    private Integer redHot3Ratio;

    /**
    * 红冷号个数，最近4期以内未出现的号码
    */
    private Integer redCold4Ratio;

    /**
    * 红热号个数，最近4期以内出现过的号码
    */
    private Integer redHot4Ratio;

    /**
    * 红冷号个数，最近5期以内未出现的号码
    */
    private Integer redCold5Ratio;

    /**
    * 红热号个数，最近5期以内出现过的号码
    */
    private Integer redHot5Ratio;

    /**
    * 红冷号个数，最近6期以内未出现的号码
    */
    private Integer redCold6Ratio;

    /**
    * 红热号个数，最近6期以内出现过的号码
    */
    private Integer redHot6Ratio;

    /**
    * 红冷号个数，最近7期以内未出现的号码
    */
    private Integer redCold7Ratio;

    /**
    * 红热号个数，最近7期以内出现过的号码
    */
    private Integer redHot7Ratio;

    /**
    * 红冷号个数，最近8期以内未出现的号码
    */
    private Integer redCold8Ratio;

    /**
    * 红热号个数，最近8期以内出现过的号码
    */
    private Integer redHot8Ratio;

    /**
    * 红冷号个数，最近9期以内未出现的号码
    */
    private Integer redCold9Ratio;

    /**
    * 红热号个数，最近9期以内出现过的号码
    */
    private Integer redHot9Ratio;

    /**
    * 红冷号个数，最近10期以内未出现的号码
    */
    private Integer redCold10Ratio;

    /**
    * 红热号个数，最近10期以内出现过的号码
    */
    private Integer redHot10Ratio;



    /**
     * 序号，从1开始递增
     */
    private Integer seqNo;

	/**
	 * 红球序号，从1开始递增
	 */
	private Integer redSeqNo;

    /**
     * 红球1
     */
    private Integer red1;

    /**
     * 红球2
     */
    private Integer red2;

    /**
     * 红球3
     */
    private Integer red3;

    /**
     * 红球4
     */
    private Integer red4;

    /**
     * 红球5
     */
    private Integer red5;

    /**
     * 红球6
     */
    private Integer red6;

    /**
     * 蓝球
     */
    private Integer blue;

    /**
     * 红球和值，红球开奖号码相加之和
     */
    private Integer redSum;

    /**
     * 红球和尾值，开奖号码和值的个位数，范围为0-9。示例：和值15的和尾为5
     */
    private Integer redSumLast;

    /**
     * 总和值，红蓝球开奖号码相加之和
     */
    private Integer redBlueSum;

    /**
     * 总和尾值，开奖号码和值的个位数，范围为0-9。示例：和值15的和尾为5
     */
    private Integer redBlueSumLast;

    /**
     * 红球跨度，红球开奖号码中最大号与最小号的差值
     */
    private Integer redSpan;

    /**
     * 红球ac值，也可称为数字复杂度，是指在一组号码组合中，任意两个数字之间不相同的正差值的总个数再减去“正选号码数量-1”的值
     */
    private Integer redAc;

    /**
     * 红1区个数，1-11
     */
    private Integer redRegion1Ratio;

    /**
     * 红2区个数，12-22
     */
    private Integer redRegion2Ratio;

    /**
     * 红3区个数，23-33
     */
    private Integer redRegion3Ratio;

    /**
     * 红奇数个数
     */
    private Integer redOddRatio;

    /**
     * 红偶数个数
     */
    private Integer redEvenRatio;

    /**
     * 是否包含连号
     */
    private Boolean isHasSerialNum;

    /**
     * 红连号个数，如：2 3 22 23 25 33 7则为2，因为2 3算一个，22 23 算一个
     */
    private Integer serialTimes;

    /**
     * 最大连号长度
     */
    private Integer maxSerialLength;

    /**
     * 是否包含偶连号，即间隔2
     */
    private Boolean isHasEvenSerialNum;

    /**
     * 偶红连号个数，如：2 4 22 24 25 33 7则为2，因为2 4算一个，22 24 算一个
     */
    private Integer evenSerialTimes;

    /**
     * 最大偶红连号长度
     */
    private Integer evenMaxSerialLength;

    /**
     * 是否红蓝重号，是否红球有和蓝球相等
     */
    private Boolean isRedIncludeBlue;

    /**
     * 是否蓝球为奇数，1=奇数，0=偶数
     */
    private Boolean isBlueOdd;
}
