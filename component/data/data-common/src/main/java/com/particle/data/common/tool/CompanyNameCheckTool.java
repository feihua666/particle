package com.particle.data.common.tool;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * <p>
 * 公司名称检查工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/5/16 16:09
 */
public class CompanyNameCheckTool {

    // 正则表达式匹配常见企业后缀（中文和英文）
    private static String chineseSuffix = "有限公司|有限责任公司|股份有限公司|集团有限公司|集团股份有限公司|个体工商戶|个体工商户|个体经营|"
            + "经营部|工作室|店|商行|协会|联合会|基金会|促进会|中心|研究所|研究院|学院|大学|学会|委员会|"
            + "股份有限公司|有限公?司|企業|集團|實業|社|医院|学校|合作社|合伙企業|無限公司|控股集团";

    private static String englishSuffix = "(Co\\.,?\\s*Ltd\\.?|Ltd\\.?|Inc\\.?|Corp\\.?|LLC|GmbH|B\\.V\\.|S\\.A\\.|"
            + "Company\\s+Limited|Limited|Enterprises|Holdings|Group|国际|Global|Technologies|Tech|"
            + "Solutions|Capital|投资|控股|实业|集团)";

    // 构建正则表达式：匹配以任意中英后缀结尾的字符串
    private static String regex = "^.*?(?:" + chineseSuffix + "|(?i)" + englishSuffix + ")$";

    private static Pattern pattern = Pattern.compile(regex);
    /**
     * 检查是否为企业名称
     * @param name
     * @return
     */
    public static boolean checkIsCompanyName(String name) {
        if (StrUtil.isBlank(name)) {
            return false;
        }
        Matcher matcher = pattern.matcher(name.trim());
        return matcher.matches();
    }

    public static void main(String[] args) {
        // 测试用例
        String[] testCases = {
                "北京科技有限公司",          // 国内企业
                "张三个体工商户",           // 个体户
                "Apple Inc.",              // 海外企业
                "Google LLC",              // 海外企业
                "香港電訊有限公司",          // 香港企业
                "台積電股份有限公司",        // 台湾企业
                "XX研究院",                // 组织机构
                "Example Co., Ltd.",       // 英文企业
                "Test GmbH",               // 德国企业
                "Invalid Name",             // 无效名称
                "商业银行"             // 简称
        };

        for (String testCase : testCases) {
            System.out.printf("'%s' 是否为企业名称: %b%n", testCase, checkIsCompanyName(testCase));
        }
    }
}
