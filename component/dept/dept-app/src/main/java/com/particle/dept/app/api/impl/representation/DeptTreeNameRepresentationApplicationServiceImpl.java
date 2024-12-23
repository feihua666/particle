package com.particle.dept.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.executor.representation.DeptTreeNameQueryCommandExecutor;
import com.particle.dept.client.api.representation.IDeptTreeNameRepresentationApplicationService;
import com.particle.dept.client.dto.command.representation.DeptTreeNamePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeNameQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 部门树名称 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Service
@CatchAndLog
public class DeptTreeNameRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptTreeNameRepresentationApplicationService {

    private DeptTreeNameQueryCommandExecutor deptTreeNameQueryCommandExecutor;

    @Override
    public SingleResponse<DeptTreeNameVO> queryDetail(IdCommand detailCommand) {
        return deptTreeNameQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DeptTreeNameVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return deptTreeNameQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DeptTreeNameVO> pageQuery(DeptTreeNamePageQueryCommand deptTreeNamePageQueryCommand) {
        return deptTreeNameQueryCommandExecutor.execute(deptTreeNamePageQueryCommand);
    }

    @Override
    public MultiResponse<DeptTreeNameVO> queryList(DeptTreeNameQueryListCommand deptTreeNameQueryListCommand) {
        return deptTreeNameQueryCommandExecutor.execute(deptTreeNameQueryListCommand);
    }

    @Autowired
    public void setDeptTreeNameQueryCommandExecutor(DeptTreeNameQueryCommandExecutor deptTreeNameQueryCommandExecutor) {
        this.deptTreeNameQueryCommandExecutor = deptTreeNameQueryCommandExecutor;
    }
}
