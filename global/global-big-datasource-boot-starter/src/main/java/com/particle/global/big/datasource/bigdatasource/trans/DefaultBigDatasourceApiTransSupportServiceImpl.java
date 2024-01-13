package com.particle.global.big.datasource.bigdatasource.trans;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiTransConfig;
import com.particle.global.big.datasource.bigdatasource.api.config.DictGroup;
import com.particle.global.big.datasource.bigdatasource.api.config.DictItem;
import com.particle.global.light.share.trans.anno.TransItem;
import com.particle.global.trans.helper.TransHelper;
import com.particle.global.trans.helper.TransTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 默认翻译支持，依赖 {@link TransHelper}
 * </p>
 *
 * @author yangwei
 * @since 2024/1/7 15:03
 */
public class DefaultBigDatasourceApiTransSupportServiceImpl implements IBigDatasourceApiTransSupportService{
    @Override
    public void trans(Object result, BigDatasourceApiTransConfig bigDatasourceApiTransConfig, List<DictGroup> dictGroups) {
        List<BigDatasourceApiTransConfig.TransItem> transItems = bigDatasourceApiTransConfig.getTransItems();
        if (CollectionUtil.isEmpty(transItems)) {
            return;
        }
        // 翻译元数据
        List<TransHelper.TransMeta> transMetas = new ArrayList<>();
        // 翻译数据
        Map<String,Object> hitDataMap = new HashMap<>();
        for (BigDatasourceApiTransConfig.TransItem transItem : transItems) {
            // 翻译元数据
            TransHelper.TransMeta transMeta = newTransMeta(transItem);
            transMetas.add(transMeta);

            // 收集提示数据
            if (CollectionUtil.isNotEmpty(dictGroups) && StrUtil.isNotEmpty(transItem.getDicGroupCode())) {
                DictGroup dictGroup = dictGroups.stream().filter(item -> StrUtil.equals(item.getCode(), transItem.getDicGroupCode())).findFirst().orElse(null);
                if (dictGroup != null) {
                    List<DictItem> dictItems = dictGroup.getDictItems();
                    if (CollectionUtil.isNotEmpty(dictItems)) {
                        hitDataMap.put(transItem.getByFieldName(), dictItems);
                    }
                }
            }
        }

        try {
            // 提示并翻译
            TransTool.putThreadLocalTransData(TransTool.ThreadLocalTransData.create(transMetas,hitDataMap));
            if (result instanceof Page) {
                TransTool.trans(((Page<?>) result).getRecords());
            }else {
                TransTool.trans(result);
            }

        } finally {
            TransTool.clearThreadLocalTransData();
        }
    }


    /**
     * 将TransItem生成对应元数据
     * 参考 {@link TransHelper#newTransMeta(TransItem, Object)}
     * @param transItem
     * @return
     */
    private TransHelper.TransMeta newTransMeta (BigDatasourceApiTransConfig.TransItem transItem){
        TransHelper.TransMeta transMeta = new TransHelper.TransMeta();
        transMeta.setByFieldName(transItem.getByFieldName());
        transMeta.setType(transItem.getType());

        // 暂不支持
        // transMeta.setTableName(transItem.tableName());
        // transMeta.setTableNameClass(transItem.tableNameClass());
        // transMeta.setTableField(transItem.tableField());

        transMeta.setForFieldName(transItem.getForFieldName());
        if (StrUtil.isEmpty(transItem.getMapValueField())) {
            transMeta.setMapValueField("name");
        }else {
            transMeta.setMapValueField(transItem.getMapValueField());
        }

        if (StrUtil.isEmpty(transItem.getMapKeyField())) {
            transMeta.setMapKeyField("value");
        }else {
            transMeta.setMapKeyField(transItem.getMapKeyField());
        }

        if (transItem.getIsMapValueCollectionJoin() != null) {
            transMeta.setMapValueCollectionJoin(transItem.getIsMapValueCollectionJoin());
        }

        if (StrUtil.isEmpty(transItem.getMapValueCollectionJoinSeparator())) {
            transMeta.setMapValueCollectionJoinSeparator("/");
        }else {
            transMeta.setMapValueCollectionJoinSeparator(transItem.getMapValueCollectionJoinSeparator());
        }

        if (transItem.getIsByFieldValueGroup() != null) {
            transMeta.setByFieldValueGroup(transItem.getIsByFieldValueGroup());
        }

        if (StrUtil.isEmpty(transItem.getByFieldValueGroupSeparator())) {
            transItem.setByFieldValueGroupSeparator(",");
        }else {
            transItem.setByFieldValueGroupSeparator(transItem.getByFieldValueGroupSeparator());
        }

        if (StrUtil.isEmpty(transItem.getMapFieldValueGroupSeparator())) {
            transItem.setMapFieldValueGroupSeparator(",");
        }else {
            transItem.setMapFieldValueGroupSeparator(transItem.getMapFieldValueGroupSeparator());
        }


        // transMeta.setBatchOnly(transItem.batchOnly());
        if (transItem.getIsNotTransWhenExist() != null) {
            transMeta.setNotTransWhenExist(transItem.getIsNotTransWhenExist());
        }
        // body 不设置，
        // transMeta.setItem(body);
        // Object fieldValue = ReflectUtil.getFieldValue(body, transItem.byFieldName());
        // transMeta.setByFieldValue(fieldValue);


        return transMeta;
    }
}
