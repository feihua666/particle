package com.particle.dept.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.dept.client.dto.data.DeptTreeTransVO;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.dept.infrastructure.service.IDeptTreeService;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 翻译字典实现
 * @author  yangwei
 * Created at 2020/11/26 18:49
 */
@Primary
@Component
public class DictTreeTransServiceImpl implements ITransService<DeptTreeTransVO,Long> {

    @Autowired
    private IDeptService iDeptService;
    @Autowired
    private IDeptTreeService iDeptTreeService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_DEPT_TREE_BY_ID);
    }

    @Override
    public TransResult<DeptTreeTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DEPT_TREE_BY_ID)) {
            DeptTreeDO deptTreeDO = iDeptTreeService.getById(key);
            if (deptTreeDO == null) {
                return null;
            }
            DeptDO deptDO = iDeptService.getById(deptTreeDO.getDeptId());

            return new TransResult(newDeptTreeTransVO(deptTreeDO,deptDO),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<DeptTreeTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_DEPT_TREE_BY_ID)) {
            List<DeptTreeDO> deptTreeDOS = iDeptTreeService.listByIds(keys);
            Set<Long> deptIds = deptTreeDOS.stream().map(DeptTreeDO::getDeptId).collect(Collectors.toSet());
            Map<Long, DeptDO> longDeptDOMap = iDeptService.listByIds(deptIds).stream().collect(Collectors.toMap(DeptDO::getId, Function.identity()));
            return deptTreeDOS.stream()
                    .map(item->new TransResult<DeptTreeTransVO, Long>(newDeptTreeTransVO(item,longDeptDOMap.get(item.getDeptId())),item.getId()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private DeptTreeTransVO newDeptTreeTransVO(DeptTreeDO deptTreeDO,DeptDO deptDO){
        DeptTreeTransVO deptTreeTransVO = new DeptTreeTransVO();
        deptTreeTransVO.setId(deptTreeDO.getId());

        deptTreeTransVO.setDeptId(deptTreeDO.getDeptId());
        deptTreeTransVO.setDeptName(deptDO.getName());

        return deptTreeTransVO;
    }

}
