package com.particle.global.tool.priority;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/1/16 09:43
 */
public class PriorityProcessToolTest {

    @Test
    public void testBasicPriority() {
        System.out.println("=== 测试1：基本优先级执行 ===");

        String result = PriorityProcessTool.<String>create()
                .add(() -> {
                    System.out.println("  执行处理器1 - 返回null");
                    return null;
                })
                .add(() -> {
                    System.out.println("  执行处理器2 - 返回有效结果");
                    return "处理器2的结果";
                })
                .add(() -> {
                    System.out.println("  执行处理器3 - 不会被执行");
                    return "处理器3的结果";
                })
                .execute();

        assertEquals("处理器2的结果", result);
        System.out.println("结果: " + result + "\n");
    }

    @Test
    public void testWithCondition() {
        System.out.println("=== 测试2：带条件的处理器 ===");

        String result = PriorityProcessTool.<String>create()
                .add(() -> false, () -> {
                    System.out.println("  条件为false，不执行处理器1");
                    return "处理器1";
                })
                .add(() -> {
                    System.out.println("  检查条件：总是true");
                    return true;
                }, () -> {
                    System.out.println("  执行处理器2");
                    return "处理器2";
                })
                .add(() -> {
                    System.out.println("  执行无条件的处理器3");
                    return "处理器3";
                })
                .execute();

        assertEquals("处理器2", result);
        System.out.println("结果: " + result + "\n");
    }

    @Test
    public void testBatchAdd() {
        System.out.println("=== 测试3：批量添加处理器 ===");

        List<Supplier<String>> handlers = Arrays.asList(
                () -> {
                    System.out.println("  批量处理器A - 返回null");
                    return null;
                },
                () -> {
                    System.out.println("  批量处理器B - 返回有效值");
                    return "批量处理器B";
                },
                () -> {
                    System.out.println("  批量处理器C - 不会执行");
                    return "批量处理器C";
                }
        );

        String result = PriorityProcessTool.<String>create()
                .addAll(handlers)
                .execute();

        assertEquals("批量处理器B", result);
        System.out.println("结果: " + result + "\n");

        // 测试动态参数
        System.out.println("=== 测试3.1：动态参数批量添加 ===");
        String result2 = PriorityProcessTool.<String>create()
                .addAll(
                        () -> "第一个",
                        () -> "第二个",
                        () -> "第三个"
                )
                .execute();

        assertEquals("第一个", result2);
        System.out.println("动态参数结果: " + result2 + "\n");
    }

    @Test
    public void testWithDefaultValue() {
        System.out.println("=== 测试4：默认值处理 ===");

        // 所有处理器都返回null，使用默认值
        String result = PriorityProcessTool.<String>create()
                .add(() -> null)
                .add(() -> null)
                .withDefault(() -> {
                    System.out.println("  执行默认处理器");
                    return "默认值";
                })
                .execute();

        assertEquals("默认值", result);
        System.out.println("结果: " + result + "\n");

        // 有处理器返回有效值，不使用默认值
        String result2 = PriorityProcessTool.<String>create()
                .add(() -> "有效值")
                .withDefault(() -> "默认值")
                .execute();

        assertEquals("有效值", result2);
        System.out.println("有有效值时结果: " + result2 + "\n");
    }

    @Test
    public void testExecuteOptional() {
        System.out.println("=== 测试5：Optional结果 ===");

        // 有结果的情况
        Optional<String> result1 = PriorityProcessTool.<String>create()
                .add(() -> "有结果")
                .executeOptional();

        assertTrue(result1.isPresent());
        assertEquals("有结果", result1.get());
        System.out.println("有结果: " + result1.get());

        // 无结果的情况
        Optional<String> result2 = PriorityProcessTool.<String>create()
                .add(() -> null)
                .executeOptional();

        assertFalse(result2.isPresent());
        System.out.println("无结果: Optional.empty\n");
    }

    @Test
    public void testExecuteOrThrow() {
        System.out.println("=== 测试6：必须返回结果（异常测试） ===");

        // 有结果的情况
        String result = PriorityProcessTool.<String>create()
                .add(() -> "有效结果")
                .executeOrThrow();

        assertEquals("有效结果", result);
        System.out.println("有结果时正常返回: " + result);

        // 无结果的情况 - 抛出默认异常
        try {
            PriorityProcessTool.<String>create()
                    .add(() -> null)
                    .executeOrThrow();
            fail("应该抛出IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("No processor returned a result", e.getMessage());
            System.out.println("捕获默认异常: " + e.getMessage());
        }

        // 无结果的情况 - 抛出自定义异常
        try {
            PriorityProcessTool.<String>create()
                    .add(() -> null)
                    .executeOrThrow(() -> new RuntimeException("自定义错误信息"));
            fail("应该抛出RuntimeException");
        } catch (RuntimeException e) {
            assertEquals("自定义错误信息", e.getMessage());
            System.out.println("捕获自定义异常: " + e.getMessage() + "\n");
        }
    }

    @Test
    public void testRealWorldScenario() {
        System.out.println("=== 测试7：实际应用场景 - 设置indexUrl ===");

        // 模拟实际业务场景：按优先级确定indexUrl
        String indexUrl = PriorityProcessTool.<String>create()
                // 1. 从系统属性获取（最高优先级）
                .add(() -> {
                    System.out.println("  1. 检查系统属性 app.index.url");
                    String value = System.getProperty("app.index.url");
                    return value != null ? "系统属性: " + value : null;
                })
                // 2. 从环境变量获取
                .add(() -> {
                    System.out.println("  2. 检查环境变量 APP_INDEX_URL");
                    String value = System.getenv("APP_INDEX_URL");
                    return value != null ? "环境变量: " + value : null;
                })
                // 3. 根据用户角色获取（带条件）
                .add(() -> {
                    System.out.println("  3. 检查用户是否为管理员");
                    return isUserAdmin();
                }, () -> {
                    System.out.println("  4. 返回管理员首页");
                    return "管理员首页: /admin/dashboard";
                })
                // 4. 根据设备类型获取
                .add(() -> {
                    System.out.println("  5. 检查设备类型");
                    return isMobileDevice() ? "移动端: /mobile/index" : null;
                })
                // 5. 默认首页
                .add(() -> {
                    System.out.println("  6. 使用默认首页");
                    return "默认: /index";
                })
                // 6. 保底默认值
                .withDefault(() -> {
                    System.out.println("  7. 所有来源都失败，使用保底默认值");
                    return "保底默认: /default";
                })
                .execute();

        System.out.println("最终确定的indexUrl: " + indexUrl + "\n");
        assertNotNull(indexUrl);
    }

    @Test
    public void testGenericType() {
        System.out.println("=== 测试8：泛型类型测试 ===");

        // 测试Integer类型
        Integer intResult = PriorityProcessTool.<Integer>create()
                .add(() -> null)
                .add(() -> 100)
                .withDefault(() -> 0)
                .execute();

        assertEquals(100, intResult);
        System.out.println("Integer结果: " + intResult);

        // 测试Boolean类型
        Boolean boolResult = PriorityProcessTool.<Boolean>create()
                .add(() -> null)
                .add(() -> false)
                .add(() -> true)
                .withDefault(() -> true)
                .execute();

        assertEquals(false, boolResult);
        System.out.println("Boolean结果: " + boolResult);

        // 测试自定义对象类型
        class User {
            String name;
            User(String name) { this.name = name; }
            @Override public String toString() { return "User{" + name + "}"; }
        }

        User userResult = PriorityProcessTool.<User>create()
                .add(() -> null)
                .add(() -> new User("张三"))
                .withDefault(() -> new User("默认用户"))
                .execute();

        assertEquals("张三", userResult.name);
        System.out.println("自定义对象结果: " + userResult + "\n");
    }

    @Test
    public void testNullHandling() {
        System.out.println("=== 测试9：null值处理 ===");

        // 条件返回null应该被当作false
        String result = PriorityProcessTool.<String>create()
                .add(() -> {
                    Boolean condition = null;
                    return condition; // 返回null
                }, () -> "处理器1")
                .add(() -> "处理器2")
                .execute();

        assertEquals("处理器2", result);
        System.out.println("条件为null时跳过处理器: " + result);

        // 处理器返回null应该继续下一个
        String result2 = PriorityProcessTool.<String>create()
                .add(() -> null)
                .add(() -> null)
                .add(() -> "最后一个处理器")
                .execute();

        assertEquals("最后一个处理器", result2);
        System.out.println("处理器返回null时继续执行: " + result2 + "\n");
    }

    @Test
    public void testEmptyProcessorList() {
        System.out.println("=== 测试10：空处理器列表 ===");

        // 没有处理器，没有默认值
        String result1 = PriorityProcessTool.<String>create()
                .execute();

        assertNull(result1);
        System.out.println("无处理器无默认值: " + result1);

        // 没有处理器，有默认值
        String result2 = PriorityProcessTool.<String>create()
                .withDefault(() -> "默认值")
                .execute();

        assertEquals("默认值", result2);
        System.out.println("无处理器有默认值: " + result2 + "\n");
    }

    // 模拟业务方法
    private boolean isUserAdmin() {
        return false; // 模拟普通用户
    }

    private boolean isMobileDevice() {
        return false; // 模拟PC设备
    }

    public static void main(String[] args) {
        System.out.println("=== PriorityProcessTool 测试程序开始 ===\n");

        PriorityProcessToolTest test = new PriorityProcessToolTest();

        // 运行所有测试
        test.testBasicPriority();
        test.testWithCondition();
        test.testBatchAdd();
        test.testWithDefaultValue();
        test.testExecuteOptional();
        test.testExecuteOrThrow();
        test.testRealWorldScenario();
        test.testGenericType();
        test.testNullHandling();
        test.testEmptyProcessorList();

        System.out.println("=== 所有测试完成 ===");
    }
}
