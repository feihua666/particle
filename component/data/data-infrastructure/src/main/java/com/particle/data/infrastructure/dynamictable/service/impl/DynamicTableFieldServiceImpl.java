package com.particle.data.infrastructure.dynamictable.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.data.infrastructure.dynamictable.mapper.DynamicTableFieldMapper;
import com.particle.data.infrastructure.dynamictable.mapper.DynamicTableMapper;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableFieldService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 动态数据表格字段 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Component
public class DynamicTableFieldServiceImpl extends IBaseServiceImpl<DynamicTableFieldMapper, DynamicTableFieldDO> implements IDynamicTableFieldService {
	private IBaseQueryCommandMapStruct<DynamicTableFieldDO> queryCommandMapStruct;

    private TableServivce tableServivce;
    private DynamicTableMapper dynamicTableMapper;
	@Override
	protected DynamicTableFieldDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicTableFieldDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicTableFieldDO po) {
        // 字段已存在不能添加
        DynamicTableFieldDO byDynamicTableIdAndName = getByDynamicTableIdAndName(po.getDynamicTableId(), po.getName());
        if (byDynamicTableIdAndName != null) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SAVE_ERROR, "字段名称已存在 " + po.getName());
        }
        // 注释已存在不能添加
        DynamicTableFieldDO byDynamicTableIdAndComment = getByDynamicTableIdAndComment(po.getDynamicTableId(), po.getComment());
        if (byDynamicTableIdAndComment != null) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SAVE_ERROR, "字段注释已存在 " + po.getComment());
        }
    }

    @Override
    protected void postAdd(DynamicTableFieldDO po) {
        DynamicTableDO dynamicTableDO = dynamicTableMapper.selectById(po.getDynamicTableId());

        // 在添加数据后，如果字段不存在，会报异常，但会正常添加数据，
        // 原因是 mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (!tableServivce.isExistColumn(dynamicTableDO.getName(),po.getName())) {
            tableServivce.addColumn(dynamicTableDO.getName(),po.getName(),po.getType(),po.getIsRequired(), StrUtil.emptyToNull(po.getDefaultValue()),po.getComment());
        }
    }

    @Override
	protected void preUpdate(DynamicTableFieldDO po) {
        // 字段已存在不能修改
        DynamicTableFieldDO byDynamicTableIdAndName = getByDynamicTableIdAndName(po.getDynamicTableId(), po.getName());
        if (byDynamicTableIdAndName != null &&!po.getId().equals(byDynamicTableIdAndName.getId())) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SAVE_ERROR, "字段名称已存在 " + po.getName());
        }
        // 注释已存在不能修改
        DynamicTableFieldDO byDynamicTableIdAndComment = getByDynamicTableIdAndComment(po.getDynamicTableId(), po.getComment());
        if (byDynamicTableIdAndComment != null &&!po.getId().equals(byDynamicTableIdAndComment.getId())) {
            throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.SAVE_ERROR, "字段注释已存在 " + po.getComment());
        }
	}

    @Override
    protected void postDeleteById(Long id, DynamicTableFieldDO DO) {
        DynamicTableDO dynamicTableDO = dynamicTableMapper.selectById(DO.getDynamicTableId());

        // 在删除数据后，如果字段不存在，会报异常，但会正常删除数据，
        // 原因是 mysql在执行ddl时会隐式提交当前事件，如果ddl没有执行成功，前面的数据不会回滚
        if (tableServivce.isExistColumn(dynamicTableDO.getName(),DO.getName())) {
            tableServivce.dropColumn(dynamicTableDO.getName(),DO.getName());
        }
    }

    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setDynamicTableMapper(DynamicTableMapper dynamicTableMapper) {
        this.dynamicTableMapper = dynamicTableMapper;
    }
}
