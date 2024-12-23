package com.particle.dept.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.executor.representation.DeptQueryCommandExecutor;
import com.particle.dept.client.api.representation.IDeptRepresentationApplicationService;
import com.particle.dept.client.dto.command.representation.DeptPageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 部门 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Service
@CatchAndLog
public class DeptRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptRepresentationApplicationService {

    private DeptQueryCommandExecutor deptQueryCommandExecutor;

    @Override
    public SingleResponse<DeptVO> queryDetail(IdCommand detailCommand) {
        return deptQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DeptVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return deptQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DeptVO> pageQuery(DeptPageQueryCommand deptPageQueryCommand) {
        return deptQueryCommandExecutor.execute(deptPageQueryCommand);
    }

    @Override
    public MultiResponse<DeptVO> queryList(DeptQueryListCommand deptQueryListCommand) {
        return deptQueryCommandExecutor.execute(deptQueryListCommand);
    }

    @Autowired
    public void setDeptQueryCommandExecutor(DeptQueryCommandExecutor deptQueryCommandExecutor) {
        this.deptQueryCommandExecutor = deptQueryCommandExecutor;
    }
}
