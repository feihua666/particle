package com.particle.global.logging.test;

import org.apache.logging.log4j.core.pattern.FormattingInfo;
import org.apache.logging.log4j.core.pattern.LoggerPatternConverter;
import org.apache.logging.log4j.core.pattern.NameAbbreviator;

/**
 * <p>
 * 测试Log4j2模式相关功能
 * </p>
 *
 * @author yangwei
 * @since 2025/12/30 16:29
 */
public class PatternTest {
    public static void main(String[] args) {
        System.out.println("=== 测试NameAbbreviator不同模式 ===");
        
        // 测试1: 简单缩写 (只保留第一个字符)
        System.out.println("1. 简单缩写 (1.):");
        NameAbbreviator abbreviator1 = NameAbbreviator.getAbbreviator("1.");
        StringBuilder sb1 = new StringBuilder();
        abbreviator1.abbreviate("com.particle.global.web.filter.RequestResponseLogFilter", sb1);
        System.out.println("  原名称: com.particle.global.web.filter.RequestResponseLogFilter");
        System.out.println("  缩写后: " + sb1.toString());
        
        // 测试2: 保留2个字符
        System.out.println("\n2. 保留2个字符 (2.):");
        NameAbbreviator abbreviator2 = NameAbbreviator.getAbbreviator("2.");
        StringBuilder sb2 = new StringBuilder();
        abbreviator2.abbreviate("com.particle.global.web.filter.RequestResponseLogFilter", sb2);
        System.out.println("  原名称: com.particle.global.web.filter.RequestResponseLogFilter");
        System.out.println("  缩写后: " + sb2.toString());
        
        // 测试3: 保留3个字符
        System.out.println("\n3. 保留3个字符 (3.):");
        NameAbbreviator abbreviator3 = NameAbbreviator.getAbbreviator("3.");
        StringBuilder sb3 = new StringBuilder();
        abbreviator3.abbreviate("com.particle.global.web.filter.RequestResponseLogFilter", sb3);
        System.out.println("  原名称: com.particle.global.web.filter.RequestResponseLogFilter");
        System.out.println("  缩写后: " + sb3.toString());
        
        // 测试4: 保留首尾模式 (1~.): 保留首字母和最后部分
        System.out.println("\n4. 首尾模式 (1~.):");
        NameAbbreviator abbreviator4 = NameAbbreviator.getAbbreviator("1~.");
        StringBuilder sb4 = new StringBuilder();
        abbreviator4.abbreviate("com.particle.global.web.filter.RequestResponseLogFilter", sb4);
        System.out.println("  原名称: com.particle.global.web.filter.RequestResponseLogFilter");
        System.out.println("  缩写后: " + sb4.toString());
        
        // 测试5: 保留首尾模式 (2~.): 保留首2字母和最后部分
        System.out.println("\n5. 首尾模式 (2~.):");
        NameAbbreviator abbreviator5 = NameAbbreviator.getAbbreviator("2~.");
        StringBuilder sb5 = new StringBuilder();
        abbreviator5.abbreviate("com.particle.global.web.filter.RequestResponseLogFilter", sb5);
        System.out.println("  原名称: com.particle.global.web.filter.RequestResponseLogFilter");
        System.out.println("  缩写后: " + sb5.toString());
        
        // 测试6: 复杂类名缩写
        System.out.println("\n6. 复杂类名缩写测试:");
        String complexClassName = "org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration";
        testAbbreviator("1.", complexClassName);
        testAbbreviator("2.", complexClassName);
        testAbbreviator("3.", complexClassName);
        testAbbreviator("1~.", complexClassName);
        
        // 测试7: 简单类名
        System.out.println("\n7. 简单类名缩写测试:");
        String simpleClassName = "java.lang.String";
        testAbbreviator("1.", simpleClassName);
        testAbbreviator("2.", simpleClassName);
        testAbbreviator("1~.", simpleClassName);
        
        // 测试8: 没有点号的类名
        System.out.println("\n8. 没有点号的类名测试:");
        String noDotClassName = "String";
        testAbbreviator("1.", noDotClassName);
        testAbbreviator("2.", noDotClassName);
        
        // 测试9: 带数字的类名
        System.out.println("\n9. 带数字的类名测试:");
        String numericClassName = "com.particle.component.v1.api.UserServiceV2";
        testAbbreviator("1.", numericClassName);
        testAbbreviator("2.", numericClassName);
        testAbbreviator("1~.", numericClassName);
    }
    
    private static void testAbbreviator(String pattern, String className) {
        NameAbbreviator abbreviator = NameAbbreviator.getAbbreviator(pattern);
        StringBuilder sb = new StringBuilder();
        abbreviator.abbreviate(className, sb);
        System.out.println("  模式 \"" + pattern + "\": " + className + " -> " + sb.toString());
    }
}
