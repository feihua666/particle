package com.particle.dataquery.adapter.datasource.rpc;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.particle.dataquery.domain.dataapi.gateway.DataApiQueryGateway;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.api.impl.ThreadLocalDataTransServiceImpl;
import com.particle.global.trans.helper.TransHelper;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 翻译数据源接口数据实现
 * @author  yangwei
 * Created at 2024-01-07 21:43:09
 */
@Primary
@Component
public class DataQueryDatasourceApiTransServiceImpl implements ITransService<Object,Object> {

    private static String TRANS_DATASOURCEAPI_BY_CODE = "trans_datasourceapi_by_code";

    @Autowired
    private DataApiQueryGateway dataApiQueryGateway;



    @Override
    public boolean support(String type) {
        return false;
    }

    @Override
    public boolean supportBatch(String type) {
        return type.startsWith(TRANS_DATASOURCEAPI_BY_CODE);
    }

    /**
     * 参考了{@link ThreadLocalDataTransServiceImpl#transBatch(String, Set)}
     * @param type
     * @param keys
     * @return
     */
    @Override
    public List<TransResult<Object, Object>> transBatch(String type, Set<Object> keys) {

        String code = parseCode(type);
        if (StrUtil.isEmpty(code)) {
            return null;
        }
        // 如果没有指定直接返回空
        String mapKeyField = parseMapKeyField(type);
        if (StrUtil.isEmpty(mapKeyField)) {
            return null;
        }
        Object o = dataApiQueryGateway.doExecuteByDatasourceApiCodeForTrans(code, keys, null);
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
    /**
     * 给一个后缀转化为支持的形式
     * @param code
     * @return
     */
    public static String formatByCodeNameKey(String code,String mapKeyField) {
        return TRANS_DATASOURCEAPI_BY_CODE + ":" + code + ":" + mapKeyField;
    }
    private String parseCode(String type) {
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
