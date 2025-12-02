package com.particle.data.app.dynamictable.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableDataPageQueryCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.dynamictable.executor.representation.DynamicTableQueryCommandExecutor;
import com.particle.data.client.dynamictable.api.representation.IDynamicTableRepresentationApplicationService;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTablePageQueryCommand;
import com.particle.data.client.dynamictable.dto.command.representation.DynamicTableQueryListCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 动态数据表格 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Service
@CatchAndLog
public class DynamicTableRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDynamicTableRepresentationApplicationService {

    private DynamicTableQueryCommandExecutor dynamicTableQueryCommandExecutor;

    @Override
    public SingleResponse<DynamicTableVO> queryDetail(IdCommand detailCommand) {
        return dynamicTableQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DynamicTableVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dynamicTableQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DynamicTableVO> pageQuery(DynamicTablePageQueryCommand dynamicTablePageQueryCommand) {
        return dynamicTableQueryCommandExecutor.execute(dynamicTablePageQueryCommand);
    }

    @Override
    public PageResponse<Map<String, Object>> dataPageQuery(DynamicTableDataPageQueryCommand dynamicTableDataPageQueryCommand) {
        return dynamicTableQueryCommandExecutor.dataPageQuery(dynamicTableDataPageQueryCommand);
    }

    @Override
    public MultiResponse<DynamicTableVO> queryList(DynamicTableQueryListCommand dynamicTableQueryListCommand) {
        return dynamicTableQueryCommandExecutor.execute(dynamicTableQueryListCommand);
    }


    @Autowired
    public void setDynamicTableQueryCommandExecutor(DynamicTableQueryCommandExecutor dynamicTableQueryCommandExecutor) {
        this.dynamicTableQueryCommandExecutor = dynamicTableQueryCommandExecutor;
    }
}
