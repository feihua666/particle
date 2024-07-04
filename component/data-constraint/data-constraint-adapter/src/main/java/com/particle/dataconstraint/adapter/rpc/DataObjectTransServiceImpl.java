package com.particle.dataconstraint.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.dataconstraint.client.dto.data.DataObjectTransVO;
import com.particle.dataconstraint.infrastructure.dos.DataObjectDO;
import com.particle.dataconstraint.infrastructure.service.IDataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译数据对象实现
 * @author  yangwei
 * Created at 2023-05-26 13:40:03
 */
@Primary
@Component
public class DataObjectTransServiceImpl implements ITransService<DataObjectTransVO,Long> {

    @Autowired
    private IDataObjectService iDataObjectService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DATA_OBJECT_BY_ID);
    }

    @Override
    public TransResult<DataObjectTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DATA_OBJECT_BY_ID)) {
            DataObjectDO dataObjectDO = iDataObjectService.getById(key);
            if (dataObjectDO == null) {
                return null;
            }
            return new TransResult(newDataObjectTransVO(dataObjectDO),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<DataObjectTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DATA_OBJECT_BY_ID)) {
            List<DataObjectDO> dataObjectDOS = iDataObjectService.listByIds(keys);
            return dataObjectDOS.stream()
                    .map(item->new TransResult<DataObjectTransVO, Long>(newDataObjectTransVO(item),item.getId()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    protected static DataObjectTransVO newDataObjectTransVO(DataObjectDO dataObjectDO){
        DataObjectTransVO dataObjectTransVO = new DataObjectTransVO();
        dataObjectTransVO.setId(dataObjectDO.getId());
        dataObjectTransVO.setName(dataObjectDO.getName());

        return dataObjectTransVO;
    }

}
