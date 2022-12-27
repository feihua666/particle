package com.particle.func.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.global.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.func.client.dto.data.FuncTransVO;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.service.IFuncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台管理用户翻译实现
 * </p>
 *
 * @author yw
 * @since 2020-12-08
 */
@Component
public class FuncTransServiceImpl implements ITransService<FuncTransVO,Long> {

    @Autowired
    private IFuncService funcService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_FUNC_BY_ID);
    }

    @Override
    public TransResult<FuncTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type, TransConstants.TRANS_FUNC_BY_ID)) {
            FuncDO byId = funcService.getById(key);
            return new TransResult(funcMapFuncForTrans(byId),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<FuncTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_FUNC_BY_ID)) {
            return funcService.listByIds(keys).stream().map(item->new TransResult<FuncTransVO, Long>(funcMapFuncForTrans(item),item.getId())).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 转换实体
     * @param func
     * @return
     */
    private FuncTransVO funcMapFuncForTrans(FuncDO func){
        FuncTransVO funcForTrans = new FuncTransVO();
        funcForTrans.setId(func.getId());

        funcForTrans.setName(func.getName());
        funcForTrans.setCode(func.getCode());
        return funcForTrans;
    }
}
