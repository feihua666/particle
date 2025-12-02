package com.particle.data.app.dynamicdata.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.dynamicdata.executor.representation.DynamicDataCategoryQueryCommandExecutor;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataCategoryRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataCategoryQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 动态数据分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:37
 */
@Service
@CatchAndLog
public class DynamicDataCategoryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataCategoryRepresentationApplicationService {

    private DynamicDataCategoryQueryCommandExecutor dynamicDataCategoryQueryCommandExecutor;

    @Override
    public SingleResponse<DynamicDataCategoryVO> queryDetail(IdCommand detailCommand) {
        return dynamicDataCategoryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DynamicDataCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dynamicDataCategoryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DynamicDataCategoryVO> pageQuery(DynamicDataCategoryPageQueryCommand dynamicDataCategoryPageQueryCommand) {
        return dynamicDataCategoryQueryCommandExecutor.execute(dynamicDataCategoryPageQueryCommand);
    }

    @Override
    public MultiResponse<DynamicDataCategoryVO> queryList(DynamicDataCategoryQueryListCommand dynamicDataCategoryQueryListCommand) {
        return dynamicDataCategoryQueryCommandExecutor.execute(dynamicDataCategoryQueryListCommand);
    }


    @Autowired
    public void setDynamicDataCategoryQueryCommandExecutor(DynamicDataCategoryQueryCommandExecutor dynamicDataCategoryQueryCommandExecutor) {
        this.dynamicDataCategoryQueryCommandExecutor = dynamicDataCategoryQueryCommandExecutor;
    }
}
