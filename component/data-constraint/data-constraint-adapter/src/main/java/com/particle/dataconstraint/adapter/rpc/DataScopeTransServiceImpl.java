package com.particle.dataconstraint.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dataconstraint.client.dto.data.DataScopeTransVO;
import com.particle.dataconstraint.infrastructure.dos.DataScopeDO;
import com.particle.dataconstraint.infrastructure.service.IDataScopeService;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译数据范围实现
 * @author  yangwei
 * Created at 2023-05-26 13:40:03
 */
@Primary
@Component
public class DataScopeTransServiceImpl implements ITransService<DataScopeTransVO,Long> {

    @Autowired
    private IDataScopeService iDataScopeService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DATA_SCOPE_BY_ID);
    }

    @Override
    public TransResult<DataScopeTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DATA_SCOPE_BY_ID)) {
            DataScopeDO dataScopeDO = iDataScopeService.getById(key);
            if (dataScopeDO == null) {
                return null;
            }
            return new TransResult(newDataScopeTransVO(dataScopeDO),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<DataScopeTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DATA_SCOPE_BY_ID)) {
            List<DataScopeDO> dataScopeDOS = iDataScopeService.listByIds(keys);
            return dataScopeDOS.stream()
                    .map(item->new TransResult<DataScopeTransVO, Long>(newDataScopeTransVO(item),item.getId()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    protected static DataScopeTransVO newDataScopeTransVO(DataScopeDO dataScopeDO){
        DataScopeTransVO dataScopeTransVO = new DataScopeTransVO();
        dataScopeTransVO.setId(dataScopeDO.getId());
        dataScopeTransVO.setName(dataScopeDO.getName());

        return dataScopeTransVO;
    }

}
