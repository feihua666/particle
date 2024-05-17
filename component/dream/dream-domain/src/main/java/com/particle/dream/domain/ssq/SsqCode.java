package com.particle.dream.domain.ssq;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.NumberUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * <p>
 * 双色球号码 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Data
@Entity
public class SsqCode extends AggreateRoot {

    private SsqCodeId id;

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


    /**
     * 生成所有双色球号
     * 总共有 17721088 个号码
     * @param ssqCodeValueConsumer
     */
    public static void generateAllSsqCode(Consumer<SsqCodeValue> ssqCodeValueConsumer){
        int MIN_RED_BALL = 1;
        int MAX_RED_BALL = 33;
        int MIN_BLUE_BALL = 1;
        int MAX_BLUE_BALL = 16;

        int seqNo = 0;
        for (int red1 = MIN_RED_BALL; red1 <= MAX_RED_BALL - 5; red1++) {
            for (int red2 = red1 + 1; red2 <= MAX_RED_BALL - 4; red2++) {
                for (int red3 = red2 + 1; red3 <= MAX_RED_BALL - 3; red3++) {
                    for (int red4 = red3 + 1; red4 <= MAX_RED_BALL - 2; red4++) {
                        for (int red5 = red4 + 1; red5 <= MAX_RED_BALL - 1; red5++) {
                            for (int red6 = red5 + 1; red6 <= MAX_RED_BALL; red6++) {
                                for (int blue = MIN_BLUE_BALL; blue <= MAX_BLUE_BALL; blue++) {
                                    seqNo++;
                                    SsqCodeValue ssqCodeValue = SsqCodeValue.create(seqNo, red1, red2, red3, red4, red5, red6, blue);
                                    if (ssqCodeValueConsumer != null) {
                                        ssqCodeValueConsumer.accept(ssqCodeValue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 自我填充，主要用于更新
     */
    public void fillSelf(){
        fill(SsqCodeValue.create(this.seqNo, this.red1, this.red2, this.red3, this.red4, this.red5, this.red6, this.blue));
    }
    /**
     * 根据生成的号码填充
     * @param ssqCodeValue
     */
    public void fill(SsqCodeValue ssqCodeValue){
        this.seqNo = ssqCodeValue.getSeqNo();
        this.red1 = ssqCodeValue.getRed1();
        this.red2 = ssqCodeValue.getRed2();
        this.red3 = ssqCodeValue.getRed3();
        this.red4 = ssqCodeValue.getRed4();
        this.red5 = ssqCodeValue.getRed5();
        this.red6 = ssqCodeValue.getRed6();
        this.blue = ssqCodeValue.getBlue();

        this.redSum = this.red1 + this.red2 + this.red3 + this.red4 + this.red5 + this.red6;
        // 在Java中，要获取一个整数的个位数（也称为最低位数字或最后一位数字），你可以使用模运算符（%）来除以10并获取余数。因为任何整数除以10的余数都会是个位数。
        this.redSumLast = this.redSum % 10;
        this.redBlueSum = this.redSum + this.blue;
        this.redBlueSumLast = this.redBlueSum % 10;
        this.redSpan = NumberUtil.max(this.red1, this.red2, this.red3, this.red4, this.red5, this.red6)
                - NumberUtil.min(this.red1, this.red2, this.red3, this.red4, this.red5, this.red6);
        this.redAc = computeRedAc();

        this.redRegion1Ratio = computeRedRegionRatio(1,11);
        this.redRegion2Ratio = computeRedRegionRatio(12,22);
        this.redRegion3Ratio = computeRedRegionRatio(23,33);

        this.redOddRatio = computeRedOddRatio();
        this.redEvenRatio = computeRedEvenRatio();

        Pair<Integer,Integer> computeSerialTimes = computeSerialTimes(1);
        int serialTimes = computeSerialTimes.getKey();
        this.isHasSerialNum = serialTimes > 0;
        this.serialTimes = serialTimes;
        this.maxSerialLength = computeSerialTimes.getValue();


        Pair<Integer,Integer> computeEvenSerialTimes = computeSerialTimes(2);
        int serialEvenTimes = computeEvenSerialTimes.getKey();
        this.isHasEvenSerialNum = serialEvenTimes > 0;
        this.evenSerialTimes = serialEvenTimes;
        this.evenMaxSerialLength = computeEvenSerialTimes.getValue();

        this.isRedIncludeBlue = this.red1 == this.blue || this.red2 == this.blue || this.red3 == this.blue || this.red4 == this.blue || this.red5 == this.blue || this.red6 == this.blue;
        this.isBlueOdd = NumberUtil.isOdd(this.blue);


    }

    /**
     * 计算连号个数
     * @return key=个数，value=最大连号长度
     */
    private Pair<Integer,Integer> computeSerialTimes(int step) {
        int result = 0;
        // 当前连号序列的长度
        int currentSequenceLength = 0;
        int maxSequenceLength = 0;

        int nums[] = {red1, red2, red3, red4, red5, red6};
        for (int i = 0; i < nums.length -1; i++) {
            if (nums[i] + step == nums[i + 1]) {
                currentSequenceLength++;
                maxSequenceLength = NumberUtil.max(maxSequenceLength, currentSequenceLength);
            }else {
                if (currentSequenceLength > 0) {
                    result++;
                    currentSequenceLength = 0;
                }
            }
        }
        if (currentSequenceLength > 0) {
            result++;
        }
        return Pair.of(result,maxSequenceLength == 0 ? maxSequenceLength : maxSequenceLength + 1);

    }

    /**
     * 计算红球奇数个数
     * @return
     */
    private Integer computeRedOddRatio() {
        int result = 0;
        if (NumberUtil.isOdd(this.red1)) {
            result++;
        }
        if (NumberUtil.isOdd(this.red2)) {
            result++;
        }
        if (NumberUtil.isOdd(this.red3)) {
            result++;
        }
        if (NumberUtil.isOdd(this.red4)) {
            result++;
        }
        if (NumberUtil.isOdd(this.red5)) {
            result++;
        }
        if (NumberUtil.isOdd(this.red6)) {
            result++;
        }
        return result;
    }
    /**
     * 计算红球偶数个数
     * @return
     */
    private Integer computeRedEvenRatio() {
        int result = 0;
        if (NumberUtil.isEven(this.red1)) {
            result++;
        }
        if (NumberUtil.isEven(this.red2)) {
            result++;
        }
        if (NumberUtil.isEven(this.red3)) {
            result++;
        }
        if (NumberUtil.isEven(this.red4)) {
            result++;
        }
        if (NumberUtil.isEven(this.red5)) {
            result++;
        }
        if (NumberUtil.isEven(this.red6)) {
            result++;
        }
        return result;
    }
    /**
     * 计算区间个数
     * @param start
     * @param end
     * @return
     */
    private Integer computeRedRegionRatio(Integer start, Integer end) {
        int result = 0;
        if (this.red1 >= start && this.red1 <= end) {
            result++;
        }
        if (this.red2 >= start && this.red2 <= end) {
            result++;
        }
        if (this.red3 >= start && this.red3 <= end) {
            result++;
        }
        if (this.red4 >= start && this.red4 <= end) {
            result++;
        }
        if (this.red5 >= start && this.red5 <= end) {
            result++;
        }
        if (this.red6 >= start && this.red6 <= end) {
            result++;
        }
        return result;
    }
    /**
     * ac值计算
     * @return
     */
    private Integer computeRedAc() {
        Set<Integer> redSet = new HashSet<>();
        redSet.add(Math.abs(this.red1 - this.red2));
        redSet.add(Math.abs(this.red1 - this.red3));
        redSet.add(Math.abs(this.red1 - this.red4));
        redSet.add(Math.abs(this.red1 - this.red5));
        redSet.add(Math.abs(this.red1 - this.red6));

        redSet.add(Math.abs(this.red2 - this.red3));
        redSet.add(Math.abs(this.red2 - this.red4));
        redSet.add(Math.abs(this.red2 - this.red5));
        redSet.add(Math.abs(this.red2 - this.red6));

        redSet.add(Math.abs(this.red3 - this.red4));
        redSet.add(Math.abs(this.red3 - this.red5));
        redSet.add(Math.abs(this.red3 - this.red6));

        redSet.add(Math.abs(this.red4 - this.red5));
        redSet.add(Math.abs(this.red4 - this.red6));

        redSet.add(Math.abs(this.red5 - this.red6));

        // 6 = 红球个数
        return redSet.size() - (6 - 1);
    }
    /**
     * 创建双色球号码领域模型对象
     * @return 双色球号码领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SsqCode create(){
        return DomainFactory.create(SsqCode.class);
    }
    /**
     * 创建双色球号码领域模型对象
     * @return 双色球号码领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static SsqCode create(SsqCodeValue ssqCodeValue){
        SsqCode ssqCode = create();

        ssqCode.fill(ssqCodeValue);
        return ssqCode;
    }

    /**
     * 用于生成双色球号码使用
     */
    @Data
    public static class SsqCodeValue {

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

        public static SsqCodeValue create(Integer seqNo,
                                          Integer red1,
                                          Integer red2,
                                          Integer red3,
                                          Integer red4,
                                          Integer red5,
                                          Integer red6,
                                          Integer blue
                                          ){
            SsqCodeValue ssqCodeValue = new SsqCodeValue();
            ssqCodeValue.setSeqNo(seqNo);
            ssqCodeValue.setRed1(red1);
            ssqCodeValue.setRed2(red2);
            ssqCodeValue.setRed3(red3);
            ssqCodeValue.setRed4(red4);
            ssqCodeValue.setRed5(red5);
            ssqCodeValue.setRed6(red6);
            ssqCodeValue.setBlue(blue);
            return ssqCodeValue;
        }
    }
}