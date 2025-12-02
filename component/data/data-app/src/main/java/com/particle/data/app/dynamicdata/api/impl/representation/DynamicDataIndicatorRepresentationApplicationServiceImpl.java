package com.particle.data.app.dynamicdata.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorWithDynamicTableFieldVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.dynamicdata.executor.representation.DynamicDataIndicatorQueryCommandExecutor;
import com.particle.data.client.dynamicdata.api.representation.IDynamicDataIndicatorRepresentationApplicationService;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorPageQueryCommand;
import com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorQueryListCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 动态数据指标 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Service
@CatchAndLog
public class DynamicDataIndicatorRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicDataIndicatorRepresentationApplicationService {

    private DynamicDataIndicatorQueryCommandExecutor dynamicDataIndicatorQueryCommandExecutor;

    @Override
    public SingleResponse<DynamicDataIndicatorVO> queryDetail(IdCommand detailCommand) {
        return dynamicDataIndicatorQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DynamicDataIndicatorVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dynamicDataIndicatorQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DynamicDataIndicatorVO> pageQuery(DynamicDataIndicatorPageQueryCommand dynamicDataIndicatorPageQueryCommand) {
        return dynamicDataIndicatorQueryCommandExecutor.execute(dynamicDataIndicatorPageQueryCommand);
    }

    @Override
    public MultiResponse<DynamicDataIndicatorVO> queryList(DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand) {
        return dynamicDataIndicatorQueryCommandExecutor.execute(dynamicDataIndicatorQueryListCommand);
    }

    @Override
    public MultiResponse<DynamicDataIndicatorWithDynamicTableFieldVO> queryListWithDynamicTableField(DynamicDataIndicatorQueryListCommand dynamicDataIndicatorQueryListCommand) {
        return dynamicDataIndicatorQueryCommandExecutor.queryListWithDynamicTableField(dynamicDataIndicatorQueryListCommand);
    }


    @Autowired
    public void setDynamicDataIndicatorQueryCommandExecutor(DynamicDataIndicatorQueryCommandExecutor dynamicDataIndicatorQueryCommandExecutor) {
        this.dynamicDataIndicatorQueryCommandExecutor = dynamicDataIndicatorQueryCommandExecutor;
    }
}
