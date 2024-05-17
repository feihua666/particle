package com.particle.dream.infrastructure.ssq.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 双色球号码表
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Data
@TableName("component_dream_ssq_code")
public class SsqCodeDO extends BaseDO {

    /**
    * 序号，从1开始递增
    */
    private Integer seqNo;

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
    * ac值，也可称为数字复杂度，是指在一组号码组合中，任意两个数字之间不相同的正差值的总个数再减去“正选号码数量-1”的值
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
    * 连号个数，如：2 3 22 23 25 33 7则为2，因为2 3算一个，22 23 算一个
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
	 * 偶连号个数，如：2 4 22 24 25 33 7则为2，因为2 4算一个，22 24 算一个
	 */
	private Integer evenSerialTimes;

	/**
	 * 最大偶连号长度
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

	/**
	 * 是否已开奖
	 */
	private Boolean isOpened;


}