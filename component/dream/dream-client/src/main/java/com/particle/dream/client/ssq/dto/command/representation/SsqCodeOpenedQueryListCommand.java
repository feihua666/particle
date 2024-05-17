package com.particle.dream.client.ssq.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDate;
/**
 * <p>
 * 双色球开奖 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Data
@Schema
public class SsqCodeOpenedQueryListCommand extends AbstractBaseQueryCommand {



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


    @Schema(description = "中1等奖注数")
    private Integer win1Num;


    @Schema(description = "中1等奖单注金额")
    private Integer win1Amount;


    @Schema(description = "中1等奖总金额")
    private Integer win1TotalAmount;


    @Schema(description = "中2等奖注数")
    private Integer win2Num;


    @Schema(description = "中2等奖单注金额")
    private Integer win2Amount;


    @Schema(description = "中2等奖总金额")
    private Integer win2TotalAmount;


    @Schema(description = "中3等奖注数")
    private Integer win3Num;


    @Schema(description = "中3等奖单注金额")
    private Integer win3Amount;


    @Schema(description = "中3等奖总金额")
    private Integer win3TotalAmount;


    @Schema(description = "中4等奖注数")
    private Integer win4Num;


    @Schema(description = "中4等奖单注金额")
    private Integer win4Amount;


    @Schema(description = "中4等奖总金额")
    private Integer win4TotalAmount;


    @Schema(description = "中5等奖注数")
    private Integer win5Num;


    @Schema(description = "中5等奖单注金额")
    private Integer win5Amount;


    @Schema(description = "中5等奖总金额")
    private Integer win5TotalAmount;


    @Schema(description = "中6等奖注数")
    private Integer win6Num;


    @Schema(description = "中6等奖单注金额")
    private Integer win6Amount;


    @Schema(description = "中6等奖总金额")
    private Integer win6TotalAmount;


    @Schema(description = "中奖总金额")
    private Integer winTotalAmount;


    @Schema(description = "奖池金额")
    private Integer prizePoolAmount;


    @Schema(description = "销售额")
    private Integer saleAmount;


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









}
