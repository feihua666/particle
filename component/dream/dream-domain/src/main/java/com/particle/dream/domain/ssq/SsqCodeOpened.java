package com.particle.dream.domain.ssq;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
import java.time.LocalDate;
/**
 * <p>
 * 双色球开奖 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Data
@Entity
public class SsqCodeOpened extends AggreateRoot {

    private SsqCodeOpenedId id;

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
    private String openedPhase;

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
    private Integer win1TotalAmount;

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
    private Integer win2TotalAmount;

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
    private Integer win3TotalAmount;

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
    private Integer win4TotalAmount;

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
    private Integer win5TotalAmount;

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
    private Integer win6TotalAmount;

    /**
    * 中奖总金额，单位元
    */
    private Integer winTotalAmount;

    /**
    * 奖池金额，单位元
    */
    private Integer prizePoolAmount;

    /**
    * 销售额，单位元
    */
    private Integer saleAmount;

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
     * 创建双色球开奖领域模型对象
     * @return 双色球开奖领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SsqCodeOpened create(){
        return DomainFactory.create(SsqCodeOpened.class);
    }
}
