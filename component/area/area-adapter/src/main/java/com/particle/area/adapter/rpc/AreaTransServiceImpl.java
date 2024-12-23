package com.particle.area.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.area.client.dto.data.AreaTransVO;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.area.infrastructure.service.IAreaService;
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
 * Created at 2024-05-31 12:59:50
 */
@Primary
@Component
public class AreaTransServiceImpl implements ITransService<AreaTransVO,Long> {

    @Autowired
    private IAreaService iAreaService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_AREA_BY_ID);
    }

    @Override
    public TransResult<AreaTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_AREA_BY_ID)) {
            AreaDO byId = iAreaService.getById(key);
            if (byId == null) {
                return null;
            }
            return new TransResult(newAreaTransVO(byId),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<AreaTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_AREA_BY_ID)) {
            return iAreaService.listByIds(keys).stream().map(item->new TransResult<AreaTransVO, Long>(newAreaTransVO(item),item.getId())).collect(Collectors.toList());
        }
        return null;
    }

    private AreaTransVO newAreaTransVO(AreaDO areaDO){
        AreaTransVO areaTransVO = new AreaTransVO();
        areaTransVO.setId(areaDO.getId());
        areaTransVO.setCode(areaDO.getCode());
        areaTransVO.setName(areaDO.getName());
        areaTransVO.setLongitude(areaDO.getLongitude());
        areaTransVO.setLatitude(areaDO.getLatitude());
        return areaTransVO;
    }

}
