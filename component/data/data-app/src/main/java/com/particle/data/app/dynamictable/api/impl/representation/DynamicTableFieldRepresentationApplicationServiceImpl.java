package com.particle.data.app.dynamictable.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.dynamictable.executor.representation.DynamicTableFieldQueryCommandExecutor;
import com.particle.data.client.dynamictable.api.representation.IDynamicTableFieldRepresentationApplicationService;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldPageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableFieldQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 动态数据表格字段 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Service
@CatchAndLog
public class DynamicTableFieldRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicTableFieldRepresentationApplicationService {

    private DynamicTableFieldQueryCommandExecutor dynamicTableFieldQueryCommandExecutor;

    @Override
    public SingleResponse<DynamicTableFieldVO> queryDetail(IdCommand detailCommand) {
        return dynamicTableFieldQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DynamicTableFieldVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dynamicTableFieldQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DynamicTableFieldVO> pageQuery(DynamicTableFieldPageQueryCommand dynamicTableFieldPageQueryCommand) {
        return dynamicTableFieldQueryCommandExecutor.execute(dynamicTableFieldPageQueryCommand);
    }

    @Override
    public MultiResponse<DynamicTableFieldVO> queryList(DynamicTableFieldQueryListCommand dynamicTableFieldQueryListCommand) {
        return dynamicTableFieldQueryCommandExecutor.execute(dynamicTableFieldQueryListCommand);
    }


    @Autowired
    public void setDynamicTableFieldQueryCommandExecutor(DynamicTableFieldQueryCommandExecutor dynamicTableFieldQueryCommandExecutor) {
        this.dynamicTableFieldQueryCommandExecutor = dynamicTableFieldQueryCommandExecutor;
    }
}
