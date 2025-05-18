package com.particle.data.common.tool;
import java.util.*;

/**
 * <p>
 * 公司性质判断工具类
 * </p>
 *
 * @author yangwei
 * @since 2025/5/9 10:51
 */
public class CompanyNatureMatchTool {

    // 统一社会信用代码机构类别映射
    private static final Map<String, String> USCC_CATEGORY = new HashMap<>();
    static {
        USCC_CATEGORY.put("11", "机关单位");  // 机关
        USCC_CATEGORY.put("12", "事业单位");  // 事业单位
        USCC_CATEGORY.put("13", "社会组织");  // 社会团体等
        USCC_CATEGORY.put("21", "大陆企业");  // 企业
    }

    // 组织机构代码映射
    private static final Map<String, String> ORG_CODE_CATEGORY = new HashMap<>();
    static {
        ORG_CODE_CATEGORY.put("1", "机关单位");
        ORG_CODE_CATEGORY.put("2", "事业单位");
        ORG_CODE_CATEGORY.put("3", "大陆企业");
        ORG_CODE_CATEGORY.put("4", "大陆企业");
        ORG_CODE_CATEGORY.put("5", "社会组织");
        ORG_CODE_CATEGORY.put("9", "其他未识别性质");
    }

    /**
     * 匹配企业性质
     * @param info
     * @return
     */
    public static String match(EnterpriseInfo info) {
        String name = doMatch(info);
        return name;
    }

    /**
     * 匹配企业性质
     * @param info
     * @return
     */
    private static String doMatch(EnterpriseInfo info) {
        // 优先级1：统一社会信用代码判断
        if (info.uscc != null && info.uscc.length() >= 3) {
            String categoryCode = info.uscc.substring(0, 2);
            String category = USCC_CATEGORY.get(categoryCode);
            if (category != null) return category;
        }

        // 优先级2：组织机构代码判断
        if (info.orgCode != null && info.orgCode.length() >= 2) {
            String orgType = ORG_CODE_CATEGORY.get(info.orgCode.substring(0, 1));
            if (orgType != null) return orgType;
        }

        // 优先级3：特殊类型关键词匹配
        String name = Optional.ofNullable(info.name).orElse("").toLowerCase();
        String regType = Optional.ofNullable(info.regType).orElse("").toLowerCase();
        String regAddress = Optional.ofNullable(info.regAddress).orElse("").toLowerCase();

        // 学校判断
        if (containsAny(name, "学校", "大学", "学院", "中学", "小学", "幼儿园")
                || containsAny(regType, "教育", "学校")) {
            return "学校";
        }

        // 医院判断
        if (containsAny(name, "医院", "卫生院", "诊所", "医科", "保健院")
                || containsAny(regType, "医疗", "卫生")) {
            return "医院";
        }

        // 律师事务所判断
        if (name.contains("律师事务所") || name.contains("律所")
                || regType.contains("律师")) {
            return "律师事务所";
        }

        // 基金会判断
        if (name.contains("基金会") || regType.contains("基金会")) {
            return "基金会";
        }

        // 港台公司判断
        if (isHongKongCompany(name, regType, regAddress)) {
            return "中国香港公司";
        }
        if (isTaiwanCompany(name, regType, regAddress)) {
            return "中国台湾公司";
        }

        // 海外公司判断
        if (isForeignCompany(name, regType, regAddress, info.uscc)) {
            return "海外公司";
        }

        // 社会组织判断
        if (containsAny(name, "协会", "联合会", "促进会", "商会", "学会")
                || containsAny(regType, "社会团体", "民办非企业")) {
            return "社会组织";
        }

        // 大陆企业兜底判断
        if (isMainlandCompany(info)) {
            return "大陆企业";
        }

        return "其他未识别性质";
    }

    private static boolean containsAny(String text, String... keywords) {
        return Arrays.stream(keywords).anyMatch(text::contains);
    }

    private static boolean isHongKongCompany(String name, String regType, String regAddress) {
        return containsAny(name, "香港", "(hk)", "(香港)")
                || containsAny(regType, "香港")
                || containsAny(regAddress, "香港");
    }

    private static boolean isTaiwanCompany(String name, String regType, String regAddress) {
        return containsAny(name, "台湾", "(tw)", "(台湾)")
                || containsAny(regType, "台湾")
                || containsAny(regAddress, "台湾");
    }

    private static boolean isForeignCompany(String name, String regType,
                                            String regAddress, String uscc) {
        // 外企关键词
        String[] foreignKeywords = {
                "美国", "日本", "韩国", "英国", "法国", "德国", "新加坡", "澳大利亚",
                "加拿大", "意大利", "西班牙", " inc", " llc", " co.,ltd", " gmbh"
        };

        // 统一信用代码校验（非1、5、9开头）
        if (uscc != null && !uscc.startsWith("1") && !uscc.startsWith("5") && !uscc.startsWith("9")) {
            return true;
        }

        return Arrays.stream(foreignKeywords).anyMatch(name::contains)
                || Arrays.stream(foreignKeywords).anyMatch(regAddress::contains)
                || (regType != null && regType.matches(".*(外资|外商).*"));
    }

    private static boolean isMainlandCompany(EnterpriseInfo info) {
        // 大陆企业特征校验
        return info.regType != null && (
                info.regType.contains("有限责任公司") ||
                        info.regType.contains("股份有限公司") ||
                        info.regType.contains("分公司") ||
                        info.regType.contains("个体工商户"));
    }

    // 企业信息封装类
    public static class EnterpriseInfo {
        public String name;         // 企业名称
        public String uscc;          // 统一社会信用代码
        public String regType;       // 注册类型、企业类型
        public String orgCode;       // 组织机构代码
        public String regAddress;    // 注册地址

        public EnterpriseInfo(String name, String uscc, String regType,
                              String orgCode, String regAddress) {
            this.name = name;
            this.uscc = uscc;
            this.regType = regType;
            this.orgCode = orgCode;
            this.regAddress = regAddress;
        }

        public static EnterpriseInfo create(String name, String uscc, String regType,
                                            String orgCode, String regAddress) {
            return new EnterpriseInfo(name, uscc, regType, orgCode, regAddress);
        }
    }

    // 使用示例
    public static void main(String[] args) {
        EnterpriseInfo test1 = new EnterpriseInfo(
                "北京市协和医院",
                "12123456789012345X",
                "事业单位",
                null,
                "北京市"
        );
        System.out.println(match(test1)); // 事业单位

        EnterpriseInfo test2 = new EnterpriseInfo(
                "腾讯科技（深圳）有限公司",
                "9144030071526726XG",
                "有限责任公司",
                "67526726-X",
                "深圳市"
        );
        System.out.println(match(test2)); // 大陆企业

        EnterpriseInfo test3 = new EnterpriseInfo(
                "香港长江实业集团",
                null,
                null,
                null,
                "香港"
        );
        System.out.println(match(test3)); // 中国香港公司
    }
}
