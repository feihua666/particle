package com.particle.data.common.tool;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;

import java.util.regex.Pattern;

/**
 * <p>
 * 人名检查工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/5/16 16:09
 */
public class PersonNameCheckTool {

    // 中文人名正则：2-20个汉字，允许中间的点分隔符（全角·）
    private static final String CHINESE_NAME_REGEX = "^[\\u4e00-\\u9fa5·]{2,20}$";

    // 英文及其他语言人名正则（兼容韩文、日文等连续字符）
    private static final String OTHER_NAME_REGEX =
            "^[\\p{L}\\p{M}]{2,}(?:[-'’\\s][\\p{L}\\p{M}]+)*$";  // 允许连续字符（如韩文）或带分隔符的复合姓名

    // 禁止匹配的关键词（公司、机构特征词）
    private static final String FORBIDDEN_KEYWORDS_REGEX =
            "公司|有限|集团|银行|支行|投资|基金|证券|股份|科技|内资股|限售|商业|事务所|研究院|中心|学校";

    private static final Pattern CHINESE_PATTERN = Pattern.compile(CHINESE_NAME_REGEX);
    private static final Pattern OTHER_PATTERN = Pattern.compile(OTHER_NAME_REGEX, Pattern.UNICODE_CHARACTER_CLASS);
    private static final Pattern FORBIDDEN_PATTERN = Pattern.compile(FORBIDDEN_KEYWORDS_REGEX);

    /**
     * 检查字符串是否符合常见人名格式（兼容中外）
     * @param name 待检查字符串
     * @return 是否为人名格式
     */
    public static boolean checkIsPersonName(String name) {
        if (StrUtil.isBlank(name)) {
            return false;
        }

        String trimmedName = name.trim();
        // 排除包含特殊符号的情况

        // 排除包含禁止关键词的情况
        if (FORBIDDEN_PATTERN.matcher(trimmedName).find()) {
            return false;
        }
        // 同时匹配中文人名规则和西文人名规则
        return CHINESE_PATTERN.matcher(trimmedName).matches()
                || OTHER_PATTERN.matcher(trimmedName).matches();
    }

    public static void main(String[] args) {
        String[] testCases = {
                "张三",                  // 中文普通姓名
                "欧阳修",                // 中文复姓
                "阿依努尔·买买提",       // 含分隔符的中文姓名
                "John Doe",             // 英文普通姓名
                "Mary-Jane O'Connor",    // 含连字符和撇号
                "Jean Paul Gaultier",   // 多段西文姓名
                "André François",       // 含重音符号
                "山田太郎",              // 日文汉字姓名
                "김철수",                // 韩文姓名
                "Nguyễn Văn A",         // 越南语姓名
                "A",                    // 过短
                "北京科技有限公司",        // 公司名称
                "无限售条件内资股（A股）",        // 投资人名称
                "商业银行"        // 公司简称
        };

        for (String testCase : testCases) {
            System.out.printf("'%s' \t是否为人名: %b%n", testCase, checkIsPersonName(testCase));
        }
    }
}
