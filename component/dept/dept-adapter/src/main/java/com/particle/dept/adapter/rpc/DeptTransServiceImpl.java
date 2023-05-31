package com.particle.dept.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dept.client.dto.data.DeptTransVO;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译部门实现
 * @author  yangwei
 * Created at 2023-05-26 13:40:03
 */
@Primary
@Component
public class DeptTransServiceImpl implements ITransService<DeptTransVO,Long> {

    @Autowired
    private IDeptService iDeptService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DEPT_BY_ID,TransConstants.TRANS_DEPT_BY_USER_ID);
    }

    @Override
    public TransResult<DeptTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DEPT_BY_ID)) {
            DeptDO deptDO = iDeptService.getById(key);
            return new TransResult(newDeptTransVO(deptDO),key);
        }else if (StrUtil.containsAny(type,TransConstants.TRANS_DEPT_BY_USER_ID)) {
            DeptDO deptDO = iDeptService.getByUserId(key);
            return new TransResult(newDeptTransVO(deptDO),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<DeptTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DEPT_BY_ID)) {
            List<DeptDO> deptDOS = iDeptService.listByIds(keys);
            return deptDOS.stream()
                    .map(item->new TransResult<DeptTransVO, Long>(newDeptTransVO(item),item.getId()))
                    .collect(Collectors.toList());
        }else if (StrUtil.containsAny(type,TransConstants.TRANS_DEPT_BY_USER_ID)) {

            Map<Long, DeptDO> mapByUserIds = iDeptService.getMapByUserIds(new ArrayList<>(keys));
            if (mapByUserIds != null) {
                List<TransResult<DeptTransVO, Long>> result = new ArrayList<>();
                for (Long userId : mapByUserIds.keySet()) {
                    result.add(new TransResult<DeptTransVO, Long>(newDeptTransVO(mapByUserIds.get(userId)),userId));
                }
                return result;
            }
        }
        return null;
    }

    private DeptTransVO newDeptTransVO(DeptDO deptDO){
        DeptTransVO deptTransVO = new DeptTransVO();
        deptTransVO.setId(deptDO.getId());
        deptTransVO.setName(deptDO.getName());

        return deptTransVO;
    }

}
