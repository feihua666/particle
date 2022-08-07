package com.particle.global.trans.api.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import com.particle.global.tool.collection.CollectionTool;
import com.particle.global.tool.str.StringTool;
import com.particle.global.trans.api.DataObtainForTableNameTrans;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 根据表名翻译，可以不再写翻译实现类，这个实现类有点人脉关系打通了TransHelper，特殊对待了该类的支持
 * 仅支持单表单字段
 * </p>
 *
 * @author yangwei
 * @since 2021-01-23 17:25
 */
@Component
@Order
public class TableNameTransServiceImpl implements ITransService<Object,Object> {

    @Autowired
    private DataObtainForTableNameTrans dataObtainForTableNameTrans;

    public static final String TRANS_BY_TABLE_NAME = "trans_by_table_name";

    @Override
    public boolean support(String type) {
        return false;
    }

    @Override
    public boolean supportBatch(String type) {
        return type.startsWith(TRANS_BY_TABLE_NAME);
    }

    @Override
    public List<TransResult<Object,Object>> transBatch(String type, Set<Object> keys) {
        String meta = type.replaceFirst(TRANS_BY_TABLE_NAME, "");
        String[] metas = meta.split(":");
        String selectColumn = StringTool.humpToLine(metas[0]);
        String tableName = metas[1];
        String whereColumn = StringTool.humpToLine(metas[2]);
        // 如果查询的中字段名是下划线格式，下面需要转一下
        List<Map<String, Object>> list = dataObtainForTableNameTrans.dataObtain(tableName, selectColumn, whereColumn, keys);
        list = list.stream().map(item -> {
            Map<String, Object> m = new HashMap<>();
            for (String key : item.keySet()) {
                m.put(StringTool.lineToHump(key), item.get(key));
            }
            return m;
        }).collect(Collectors.toList());
        List<TransResult<Object, Object>> r = new ArrayList<>(keys.size());
        List<Map<String, Object>> collect = null;

        for (Object key : keys) {
            collect = list.stream().filter(item -> key.equals(item.get(StringTool.lineToHump(whereColumn)))).collect(Collectors.toList());
            if (!CollectionUtil.isEmpty(collect)) {
                r.add( new TransResult<Object, Object>(collect,key));
            }
        }
        return r;

    }

    @Override
    public TransResult<Object,Object> trans(String type, Object key) {
        return null;
    }
}
