package com.particle.data.infrastructure.dynamicdata.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryDO;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.mapper.DynamicDataIndicatorCategoryMapper;
import com.particle.data.infrastructure.dynamicdata.mapper.DynamicDataIndicatorMapper;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableDO;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableFieldDO;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.table.TableServivce;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 动态数据指标分类 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Component
public class DynamicDataIndicatorCategoryServiceImpl extends IBaseServiceImpl<DynamicDataIndicatorCategoryMapper, DynamicDataIndicatorCategoryDO> implements IDynamicDataIndicatorCategoryService {
	private IBaseQueryCommandMapStruct<DynamicDataIndicatorCategoryDO> queryCommandMapStruct;

    private TableServivce tableServivce;
    private DynamicDataIndicatorMapper dynamicDataIndicatorMapper;
	@Override
	protected DynamicDataIndicatorCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicDataIndicatorCategoryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicDataIndicatorCategoryDO po) {
	}
	@Override
	protected void preUpdate(DynamicDataIndicatorCategoryDO po) {

	}

    @Override
    public boolean updateDynamicDataIndicatorNum(Long dynamicDataIndicatorCategoryId) {
        Long count = dynamicDataIndicatorMapper.selectCount(Wrappers.<DynamicDataIndicatorDO>lambdaQuery().eq(DynamicDataIndicatorDO::getDynamicDataIndicatorCategoryId, dynamicDataIndicatorCategoryId));
        DynamicDataIndicatorCategoryDO dynamicDataIndicatorCategoryDO = getById(dynamicDataIndicatorCategoryId);
        dynamicDataIndicatorCategoryDO.setDynamicDataIndicatorNum(count.intValue());
        return updateById(dynamicDataIndicatorCategoryDO);
    }

    @Override
    public boolean updateDynamicDataIndicatorCategoryDataNum(Long dynamicDataIndicatorCategoryId, String tableName,Integer dynamicDataIndicatorCategoryDataNum) {
        DynamicDataIndicatorCategoryDO dynamicDataIndicatorCategoryDO = getById(dynamicDataIndicatorCategoryId);
        if (dynamicDataIndicatorCategoryDataNum == null) {
            Long count = tableServivce.count(tableName,true);
            dynamicDataIndicatorCategoryDO.setDynamicDataIndicatorCategoryDataNum(count.intValue());
        }else {
            dynamicDataIndicatorCategoryDO.setDynamicDataIndicatorCategoryDataNum(dynamicDataIndicatorCategoryDataNum);
        }

        return updateById(dynamicDataIndicatorCategoryDO);
    }
    @Autowired
    public void setTableServivce(TableServivce tableServivce) {
        this.tableServivce = tableServivce;
    }
    @Autowired
    public void setDynamicDataIndicatorMapper(DynamicDataIndicatorMapper dynamicDataIndicatorMapper) {
        this.dynamicDataIndicatorMapper = dynamicDataIndicatorMapper;
    }
}
