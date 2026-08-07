package com.particle.global.crawler.pipeline.action;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.particle.global.crawler.action.BaseAction;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Action 类型 ID 解析器
 * <p>
 * 支持动态注册 Action 类，根据 type code 进行反序列化。
 * </p>
 *
 * @author yangwei
 * @since 2026/5/13 10:42
 */
public class ActionTypeIdResolver extends TypeIdResolverBase {

    private JavaType baseType;

    // 注册表：type code -> Action 类
    private static final ConcurrentHashMap<String, Class<? extends BaseAction>> TYPE_CODE_MAP = new ConcurrentHashMap<>();

    // 反向映射：Action 类 -> type code（用于序列化）
    private static final ConcurrentHashMap<Class<?>, String> CLASS_TO_TYPE_CODE = new ConcurrentHashMap<>();
    // 引入一下 ActionRegistryInitializer 默认注册，否则不会注册
    static {
        new ActionRegistryInitializer();
    }
    /**
     * 注册 Action 类
     *
     * @param typeCode 类型码（如 "browser.click"）
     * @param actionClass Action 类
     */
    public static void register(String typeCode, Class<? extends BaseAction> actionClass) {
        TYPE_CODE_MAP.put(typeCode, actionClass);
        CLASS_TO_TYPE_CODE.put(actionClass, typeCode);
    }

    /**
     * 批量注册
     */
    public static void registerAll(Map<String, Class<? extends BaseAction>> mappings) {
        TYPE_CODE_MAP.putAll(mappings);
        for (Map.Entry<String, Class<? extends BaseAction>> entry : mappings.entrySet()) {
            CLASS_TO_TYPE_CODE.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * 获取注册的类
     */
    public static Class<? extends BaseAction> getActionClass(String typeCode) {
        return TYPE_CODE_MAP.get(typeCode);
    }

    @Override
    public void init(JavaType baseType) {
        this.baseType = baseType;
    }

    @Override
    public String idFromValue(Object value) {
        return idFromValueAndType(value, value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        if (value instanceof BaseAction) {
            BaseAction action = (BaseAction) value;
            String typeCode = action.getType();
            if (typeCode != null) {
                return typeCode;
            }
        }
        // 从缓存获取
        String typeCode = CLASS_TO_TYPE_CODE.get(suggestedType);
        if (typeCode != null) {
            return typeCode;
        }
        // fallback：使用类名
        return suggestedType.getName();
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
        return typeFromId(id);
    }

    // 保留旧版本兼容
    public JavaType typeFromId(String id) {
        Class<? extends BaseAction> clazz = TYPE_CODE_MAP.get(id);
        if (clazz != null) {
            return TypeFactory.defaultInstance().constructSpecializedType(baseType, clazz);
        }
        throw new IllegalArgumentException("Unknown action type code: " + id +
                ". Please register it via ActionTypeIdResolver.register()");
    }

    @Override
    public String getDescForKnownTypeIds() {
        return "Action types registered via ActionTypeIdResolver.register()";
    }

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }
}
