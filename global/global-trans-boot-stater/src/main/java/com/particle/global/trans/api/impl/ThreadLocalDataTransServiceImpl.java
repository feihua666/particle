package com.particle.global.trans.api.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.particle.global.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.helper.TransHelper;
import com.particle.global.trans.helper.TransTool;
import com.particle.global.trans.result.TransResult;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 基于threadLocal提供数据进行翻译支持
 * 需要调用{@link TransTool#putThreadLocalTransData(Object, String)} 设置数据
 * </p>
 *
 * @author yangwei
 * @since 2024/1/6 14:42
 */
@Component
public class ThreadLocalDataTransServiceImpl implements ITransService<Object,Object> {

    public static final String TRANS_BY_THREAD_LOCAL_DATA_KEY = "trans_by_thread_local_data_key";

    public static final String TRANS_BY_THREAD_LOCAL = TransConstants.TRANS_BY_THREAD_LOCAL;


    @Override
    public boolean support(String type) {
        // return type.startsWith(TRANS_BY_THREAD_LOCAL);
        return false;
    }

    @Override
    public boolean supportBatch(String type) {
        return type.startsWith(TRANS_BY_THREAD_LOCAL);
    }

    @Override
    public List<TransResult<Object, Object>> transBatch(String type, Set<Object> keys) {
        TransTool.ThreadLocalTransData threadLocalTransData = TransTool.fetchThreadLocalTransData();
        String byFieldName = parseByFieldName(type);
        if (threadLocalTransData == null
                || byFieldName == null
                || threadLocalTransData.getByFieldNameDataMap() == null
                || threadLocalTransData.getByFieldNameDataMap().isEmpty()
                || threadLocalTransData.getByFieldNameDataMap().get(byFieldName) == null) {
            return null;
        }
        // 如果没有指定直接返回空
        String mapKeyField = parseMapKeyField(type);
        if (StrUtil.isEmpty(mapKeyField)) {
            return null;
        }

        Object o = threadLocalTransData.getByFieldNameDataMap().get(byFieldName);
        if (o == null) {
            return null;
        }
        Object dataList = o;
        // 如果是一个数组，理论是不支持集合的，这里只取第一条
        if (!(o instanceof Collection)) {
            dataList = Lists.newArrayList(o);
        }

        Collection<?> dataListCollection = (Collection<?>) dataList;
        if (dataListCollection.isEmpty()) {
            return null;
        }
        Map<Object, ?> collectMap = dataListCollection.stream().collect(Collectors.toMap(item -> TransHelper.getObjValue(item, mapKeyField), item -> item, (v1, v2) -> v1));

        List<TransResult<Object, Object>> collect = keys.stream().map(key -> {

            Object item = collectMap.get(key);
            if (item == null) {
                // 这里可能有误差，但一般相同的值不是一样，因为页面上定义的都是字符串
                item = collectMap.get(key.toString());
            }
            if (item == null) {
                return null;
            }
            return new TransResult<>(item, key);
        }).filter(Objects::nonNull).collect(Collectors.toList());

        return collect;

    }

    @Override
    public TransResult<Object, Object> trans(String type, Object key) {
        TransTool.ThreadLocalTransData threadLocalTransData = TransTool.fetchThreadLocalTransData();
        String byFieldName = parseByFieldName(type);
        if (threadLocalTransData == null
                || byFieldName == null
                || threadLocalTransData.getByFieldNameDataMap() == null
                || threadLocalTransData.getByFieldNameDataMap().isEmpty()
                || threadLocalTransData.getByFieldNameDataMap().get(byFieldName) == null) {
            return null;
        }

        Object o = threadLocalTransData.getByFieldNameDataMap().get(byFieldName);
        Object item = o;
        // 如果是一个数组，理论是不支持集合的，这里只取第一条
        if (o instanceof Collection) {
            if (!((Collection<?>) o).isEmpty()) {
                item = ((Collection<?>) o).iterator().next();
            }
        }

        return new TransResult<>(item,key);

    }


    /**
     * 给一个后缀转化为支持的形式
     * @param byFieldName
     * @return
     */
    public static String formatByfieldNameKey(String byFieldName,String mapKeyField) {
        return TRANS_BY_THREAD_LOCAL + ":" + byFieldName + ":" + mapKeyField;
    }

    private String parseByFieldName(String type) {
        String[] split = type.split(":");
        if (split.length >= 2) {
            return split[1];
        }
        return null;
    }
    private String parseMapKeyField(String type) {
        String[] split = type.split(":");
        if (split.length >= 3) {
            return split[2];
        }
        return null;
    }
}
