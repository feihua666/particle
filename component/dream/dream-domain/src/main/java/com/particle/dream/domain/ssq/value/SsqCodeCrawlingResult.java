package com.particle.dream.domain.ssq.value;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.Week;
import com.google.common.collect.Lists;
import com.particle.global.dto.basic.Value;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 双色球已开奖号码爬取结果
 * </p>
 *
 * @author yangwei
 * @since 2024/5/20 11:43
 */
@Data
public class SsqCodeCrawlingResult extends Value {

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


    public void computeWin1TotalAmount() {
        this.win1TotalAmount = this.getWin1Num().longValue() * this.getWin1Amount().longValue();
    }

    public void computeWin2TotalAmount() {
        this.win2TotalAmount = this.getWin2Num().longValue() * this.getWin2Amount().longValue();
    }
    public void computeWin3TotalAmount() {
        this.win3TotalAmount = this.getWin3Num().longValue() * this.getWin3Amount().longValue();
    }
    public void computeWin4TotalAmount() {
        this.win4TotalAmount = this.getWin4Num().longValue() * this.getWin4Amount().longValue();
    }
    public void computeWin5TotalAmount() {
        this.win5TotalAmount = this.getWin5Num().longValue() * this.getWin5Amount().longValue();
    }
    public void computeWin6TotalAmount() {
        this.win6TotalAmount = this.getWin6Num().longValue() * this.getWin6Amount().longValue();
    }
    /**
     * 按从小到大顺序排序的红球
     * @return
     */
    public List<Integer> orderedRedBalls() {
        ArrayList<Integer> integers = Lists.newArrayList(openedRed1, openedRed2, openedRed3, openedRed4, openedRed5, openedRed6);
        integers.sort(Integer::compareTo);

        return integers;
    }

    /**
     * 填充中奖号码，按顺序存储，依次为 red1，red2，red3，red4，red5，red6，blue
     * @param balls
     */
    public void fillBalls(List<String> balls) {
        this.openedRed1 = Integer.valueOf(balls.get(0));
        this.openedRed2 = Integer.valueOf(balls.get(1));
        this.openedRed3 = Integer.valueOf(balls.get(2));
        this.openedRed4 = Integer.valueOf(balls.get(3));
        this.openedRed5 = Integer.valueOf(balls.get(4));
        this.openedRed6 = Integer.valueOf(balls.get(5));

        this.openedBlue = Integer.valueOf(balls.get(6));
    }

    /**
     * 根据期号和周几计算开奖日期
     * 本来打算从新浪网抓取，但没有开奖日期，所以这里自己计算，双发现2003年的001期不是从1月1号左右开始的，而是从2月23号第一次开奖（从2003到2004有一部分是每周开两次奖），目前计算不完整，所以换成彩宝贝试试
     * @param firstOpenedPhaseYear 每年第一期年份
     * @param firstOpenedWeekDay 每年第一期周几
     */
    public void computeOpenedDate(Integer firstOpenedPhaseYear,Integer firstOpenedWeekDay) {
        // 每年第一期期号
        Integer firstOpenedPhaseNum = 1;
        // 从每年的1月1号开始计算
        DateTime firstYearStartDate = DateUtil.parse(firstOpenedPhaseYear + "-01-01");
        Week week = DateUtil.dayOfWeekEnum(firstYearStartDate);
        // 第一期的开奖日期肯定是在每年的1月1日之后
        int iso8601Value = week.getIso8601Value();
        int deltaWeekDay = firstOpenedWeekDay - iso8601Value;
        // 将1月1日加上周几的偏移量
        firstYearStartDate = DateUtil.offsetDay(firstYearStartDate, deltaWeekDay);

        // 第一期的开奖日期
        LocalDate firstOpenedDate = LocalDateTimeUtil.of(firstYearStartDate).toLocalDate();

        if (this.openedPhaseYear != null && this.openedPhaseNum != null && this.openedWeekDay != null) {
            if (Objects.equals(firstOpenedPhaseYear, openedPhaseYear)
                    && Objects.equals(firstOpenedPhaseNum, openedPhaseNum)
                    && Objects.equals(firstOpenedWeekDay, openedWeekDay)) {
                this.openedDate = firstOpenedDate;
            }else {
                // 主要思路：将每一期所在的周有多少基数，当前期所在周已经开奖期数，当前基数减去现状的数量，就是中间整周的期数数量
                // 每年第一期那一周开奖次数
                int firstWeekOpenedCount = 0;
                if (firstOpenedWeekDay == 2) {
                    firstWeekOpenedCount = 3;
                }else if (firstOpenedWeekDay == 4) {
                    firstWeekOpenedCount = 2;
                }else if (firstOpenedWeekDay == 7) {
                    firstWeekOpenedCount = 1;
                }
                // 当前期那一周开奖次数
                int endWeekOpenedCount = 0;
                if (openedWeekDay == 2) {
                    endWeekOpenedCount = 1;
                }else if (openedWeekDay == 4) {
                    endWeekOpenedCount = 2;
                }else if (openedWeekDay == 7) {
                    endWeekOpenedCount = 3;
                }
                int beforeEndCount = openedPhaseNum - endWeekOpenedCount;

                int middleCount = beforeEndCount - firstWeekOpenedCount;

                if (middleCount >= 0) {
                // 说明已经到了下一周了
                    this.openedDate = firstOpenedDate.plusDays(
                            // 第一期的所在周
                            (7 - firstOpenedWeekDay)
                                    // 中间天数
                                    + (middleCount / 3 * 7)
                                    // 当期一周
                                    + openedWeekDay);
                }else {
                // 说明在同一周
                    this.openedDate = firstOpenedDate.plusDays(openedWeekDay - firstOpenedWeekDay);

                }

            }

        }
    }

    public static SsqCodeCrawlingResult create() {
        return new SsqCodeCrawlingResult();
    }
}
