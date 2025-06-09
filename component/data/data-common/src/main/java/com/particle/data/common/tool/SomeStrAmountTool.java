package com.particle.data.common.tool;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 处理字符串金额解析为统一格式
 * </p>
 *
 * @author yangwei
 * @since 2025-05-09 14:07:01
 */
public class SomeStrAmountTool {
    public static Pattern prefixNumberPattern = Pattern.compile("^(\\d+\\.?\\d*)(.*)$");
    public static String defaultCurrency = "人民币";

    /**
     * 解析金额字符串，返回金额和单位
     * @param input
     * @return
     */
    public static AmountParseResult parseAmount(String input) {
        if (StrUtil.isBlank(input)) {
            return null;
        }
        AmountParseResult result = new AmountParseResult();
        Matcher matcher = prefixNumberPattern.matcher(input.trim());

        if (matcher.find()) {
            // 提取数字部分
            String numberStr = matcher.group(1);
            BigDecimal number = new BigDecimal(numberStr);
            String remaining = matcher.group(2).trim();

            String unit = "";
            String currency = "";

            // 处理单位和币种
            if (remaining.startsWith("亿")) {
                unit = "亿";
                remaining = remaining.substring(1).trim();
                // 去除可能跟在亿后面的"元"
                if (remaining.startsWith("元")) {
                    remaining = remaining.substring(1).trim();
                }
                currency = remaining;
            }else if (remaining.startsWith("万")) {
                unit = "万";
                remaining = remaining.substring(1).trim();
                // 去除可能跟在万后面的"元"
                if (remaining.startsWith("元")) {
                    remaining = remaining.substring(1).trim();
                }
                currency = remaining;
            } else if (remaining.startsWith("元")) {
                unit = "元";
                remaining = remaining.substring(1).trim();
                currency = remaining;
            } else {
                // 默认单位为元
                unit = "元";
                currency = remaining;
            }

            // 转换为万元单位
            if ("元".equals(unit)) {
                result.setAmount(number.divide(BigDecimal.valueOf(10000)));
            }else if ("亿".equals(unit)) {
                result.setAmount(number.divide(BigDecimal.valueOf(10000 * 10000)));
            } else {
                result.setAmount(number);
            }

            result.setOriginUnit(unit);
            result.setCurrency(currency.isEmpty() ? null : currency);

        } else {
            return null;
        }

        return result;
    }

    /**
     * 如果没有币种，则默认为人民币
     * @param input
     * @return
     */
    public static AmountParseResult parseAmountWithDefaultCny(String input) {
        AmountParseResult amountParseResult = parseAmount(input);
        if (amountParseResult != null) {
            if (StrUtil.isEmpty(amountParseResult.getCurrency())) {
                amountParseResult.setCurrency(defaultCurrency);
            }
        }
        return amountParseResult;
    }
    /**
     * 解析结果
     */
    public static class AmountParseResult {
        /**
         * 解析后的金额，单位万元，已转为万元单位
         */
        private BigDecimal amount;
        /**
         * 解析后原始单位，如元、万元等
         */
        private String originUnit;
        /**
         * 解析后的币种，如人民币、欧元、港元等
         */
        private String currency;

        // Getters and Setters
        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getOriginUnit() {
            return originUnit;
        }

        public void setOriginUnit(String originUnit) {
            this.originUnit = originUnit;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        @Override
        public String toString() {
            return "ParseResult{" +
                    "amount=" + amount.toPlainString() +
                    ", originUnit='" + originUnit + '\'' +
                    ", currency='" + currency + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {

        System.out.println(new BigDecimal("123456789.12").toPlainString());
        System.out.println(new BigDecimal("123456789.523").setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());

        String[] testCases = {
                "1342128万元人民币",
                "1342123.22",
                "1980.22",
                "1342128万元",
                "1342128百万元",
                "1342128元人民币",
                "1342128万欧元",
                "123.45万人民币",
                "12345美元",
                "1234.56万",
                "1234港元",
                "1234元港元",
                "1234亿港元",
                "1234亿元港元",
                "1234十亿元港元",
                "00亿元港元",
        };

        for (String testCase : testCases) {
            System.out.println(testCase + " => " + parseAmount(testCase));
        }
    }
}
