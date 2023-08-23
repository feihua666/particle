package com.particle.dataquery.adapter.provider.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderTransVO;
import com.particle.dataquery.infrastructure.provider.dos.DataQueryProviderDO;
import com.particle.dataquery.infrastructure.provider.service.IDataQueryProviderService;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译数据查询供应商实现
 * @author  yangwei
 * Created at 2023-08-17 10:18:00
 */
@Primary
@Component
public class DataQueryProviderTransServiceImpl implements ITransService<DataQueryProviderTransVO,Long> {

    @Autowired
    private IDataQueryProviderService iDataQueryProviderService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DATAQUERY_PROVIDER_BY_USER_ID);
    }

    @Override
    public TransResult<DataQueryProviderTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DATAQUERY_PROVIDER_BY_USER_ID)) {
            DataQueryProviderDO byId = iDataQueryProviderService.getById(key);
            if (byId == null) {
                return null;
            }
            return new TransResult(newDataQueryProviderTransVO(byId),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<DataQueryProviderTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DATAQUERY_PROVIDER_BY_USER_ID)) {
            return iDataQueryProviderService.listByIds(keys).stream().map(item->new TransResult<DataQueryProviderTransVO, Long>(newDataQueryProviderTransVO(item),item.getId())).collect(Collectors.toList());
        }
        return null;
    }

    private DataQueryProviderTransVO newDataQueryProviderTransVO(DataQueryProviderDO dataQueryProviderDO){
        DataQueryProviderTransVO dataQueryProviderTransVO = new DataQueryProviderTransVO();
        dataQueryProviderTransVO.setId(dataQueryProviderDO.getId());
        dataQueryProviderTransVO.setName(dataQueryProviderDO.getName());
        return dataQueryProviderTransVO;
    }

}
