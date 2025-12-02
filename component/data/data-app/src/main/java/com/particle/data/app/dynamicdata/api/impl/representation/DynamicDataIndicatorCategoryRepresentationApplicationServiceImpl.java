package com.particle.data.app.dynamicdata.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryDataPageQueryCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.dynamicdata.executor.representation.DynamicDataIndicatorCategoryQueryCommandExecutor;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataIndicatorCategoryRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorCategoryQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 动态数据指标分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:58
 */
@Service
@CatchAndLog
public class DynamicDataIndicatorCategoryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataIndicatorCategoryRepresentationApplicationService {

    private DynamicDataIndicatorCategoryQueryCommandExecutor dynamicDataIndicatorCategoryQueryCommandExecutor;

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryVO> queryDetail(IdCommand detailCommand) {
        return dynamicDataIndicatorCategoryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dynamicDataIndicatorCategoryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DynamicDataIndicatorCategoryVO> pageQuery(DynamicDataIndicatorCategoryPageQueryCommand dynamicDataIndicatorCategoryPageQueryCommand) {
        return dynamicDataIndicatorCategoryQueryCommandExecutor.execute(dynamicDataIndicatorCategoryPageQueryCommand);
    }

    @Override
    public PageResponse<Map<String, Object>> dataPageQuery(DynamicDataIndicatorCategoryDataPageQueryCommand dynamicDataIndicatorCategoryDataPageQueryCommand) {
        return dynamicDataIndicatorCategoryQueryCommandExecutor.dataPageQuery(dynamicDataIndicatorCategoryDataPageQueryCommand);
    }

    @Override
    public MultiResponse<DynamicDataIndicatorCategoryVO> queryList(DynamicDataIndicatorCategoryQueryListCommand dynamicDataIndicatorCategoryQueryListCommand) {
        return dynamicDataIndicatorCategoryQueryCommandExecutor.execute(dynamicDataIndicatorCategoryQueryListCommand);
    }


    @Autowired
    public void setDynamicDataIndicatorCategoryQueryCommandExecutor(DynamicDataIndicatorCategoryQueryCommandExecutor dynamicDataIndicatorCategoryQueryCommandExecutor) {
        this.dynamicDataIndicatorCategoryQueryCommandExecutor = dynamicDataIndicatorCategoryQueryCommandExecutor;
    }
}
