package com.particle.dream.client.ssq.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 双色球号码 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Data
@Schema
public class SsqCodePageQueryCommand extends AbstractBasePageQueryCommand {



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


    @Schema(description = "红球ac值")
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


    @Schema(description = "红连号个数")
    private Integer serialTimes;

	@Schema(description = "最大连号长度")
	private Integer maxSerialLength;

	@Schema(description = "是否包含偶连号，即间隔2")
	private Boolean isHasEvenSerialNum;

	@Schema(description = "偶红连号个数，如：2 4 22 24 25 33 7则为2，因为2 4算一个，22 24 算一个")
	private Integer evenSerialTimes;

	@Schema(description = "最大偶红连号长度")
	private Integer evenMaxSerialLength;


    @Schema(description = "是否红蓝重号")
    private Boolean isRedIncludeBlue;


    @Schema(description = "是否蓝球为奇数")
    private Boolean isBlueOdd;

	@Schema(description = "是否已开奖")
	private Boolean isOpened;









}