package com.particle.data.infrastructure.dynamictable.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.mapper.DynamicTableFieldMapper;
import com.particle.data.infrastructure.dynamictable.mapper.DynamicTableMapper;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 动态数据表格 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Component
public class DynamicTableServiceImpl extends IBaseServiceImpl<DynamicTableMapper, DynamicTableDO> implements IDynamicTableService {
	private IBaseQueryCommandMapStruct<DynamicTableDO> queryCommandMapStruct;

    private TableServivce tableServivce;
    private DynamicTableFieldMapper dynamicTableFieldMapper;
	@Override
	protected DynamicTableDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicTableDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicTableDO po) {
        // 表名 已存在不能添加
        assertByColumn(po.getName(),DynamicTableDO::getName,false);

    }

    @Override
    protected void postAdd(DynamicTableDO po) {
        // 在添加数据后，如果直接创建表，如果表已存在会报异常，但会正常插入数据
        // 原因是 mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (!tableServivce.isExistTable(po.getName())) {
            tableServivce.createTable(po.getName(),po.getComment());
        }
    }

    @Override
	protected void preUpdate(DynamicTableDO po) {
        DynamicTableDO byId = null;
        if (StrUtil.isNotEmpty(po.getName())) {
            byId = byId == null ? getById(po.getId()) : byId;
            // 如果表名有改动
            if (!po.getName().equals(byId.getName())) {
                // 表名已存在不能修改
                assertByColumn(po.getName(),DynamicTableDO::getName,false);
            }
        }
	}

    @Override
    protected void postDeleteById(Long id, DynamicTableDO DO) {
        // 在删除数据后，如果表不存在，会报异常，但会正常删除数据，
        // 原因是 mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (tableServivce.isExistTable(DO.getName())) {
            tableServivce.dropTable(DO.getName());
        }
    }

    @Override
    public boolean updateDynamicTableFieldNum(Long dynamicTableId) {
        Long count = dynamicTableFieldMapper.selectCount(Wrappers.<DynamicTableFieldDO>lambdaQuery().eq(DynamicTableFieldDO::getDynamicTableId, dynamicTableId));
        DynamicTableDO dynamicTableDO = getById(dynamicTableId);
        dynamicTableDO.setDynamicTableFieldNum(count.intValue());
        return updateById(dynamicTableDO);
    }

    @Override
    public boolean updateDynamicTableDataNum(Long dynamicTableId,Integer dynamicTableDataNum) {
        DynamicTableDO dynamicTableDO = getById(dynamicTableId);
        if (dynamicTableDataNum == null) {
            Integer count = countDynamicTableDataNum(dynamicTableDO.getName(),true);
            dynamicTableDO.setDynamicTableDataNum(count.intValue());
        }else {
            dynamicTableDO.setDynamicTableDataNum(dynamicTableDataNum);
        }

        return updateById(dynamicTableDO);
    }

    @Override
    public Integer countDynamicTableDataNum(String tableName, Boolean isPublic) {
        Long count = tableServivce.count(tableName,isPublic);
        return count.intValue();
    }

    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }

    @Autowired
    public void setDynamicTableFieldMapper(DynamicTableFieldMapper dynamicTableFieldMapper) {
        this.dynamicTableFieldMapper = dynamicTableFieldMapper;
    }
}
