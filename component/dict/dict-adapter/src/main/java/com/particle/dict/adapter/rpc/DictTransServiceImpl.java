package com.particle.dict.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.dict.client.dto.data.DictTransVO;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译字典实现
 * @author  yangwei
 * Created at 2020/11/26 18:49
 */
@Primary
@Component
public class DictTransServiceImpl implements ITransService<DictTransVO,Long> {

    @Autowired
    private IDictService iDictService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DICT_BY_ID);
    }

    @Override
    public TransResult<DictTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DICT_BY_ID)) {
            DictDO byId = iDictService.getById(key);
            return new TransResult(newDictTransVO(byId),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<DictTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DICT_BY_ID)) {
            return iDictService.listByIds(keys).stream().map(item->new TransResult<DictTransVO, Long>(newDictTransVO(item),item.getId())).collect(Collectors.toList());
        }
        return null;
    }

    private DictTransVO newDictTransVO(DictDO dictDO){
        DictTransVO dictTransVO = new DictTransVO();
        dictTransVO.setId(dictDO.getId());
        dictTransVO.setCode(dictDO.getCode());
        dictTransVO.setName(dictDO.getName());
        dictTransVO.setValue(dictDO.getValue());
        return dictTransVO;
    }

}
