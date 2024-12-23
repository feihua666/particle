package com.particle.dept.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dept.app.executor.representation.DeptTreeQueryCommandExecutor;
import com.particle.dept.client.api.representation.IDeptTreeRepresentationApplicationService;
import com.particle.dept.client.dto.command.representation.DeptTreePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 部门树 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Service
@CatchAndLog
public class DeptTreeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDeptTreeRepresentationApplicationService {

    private DeptTreeQueryCommandExecutor deptTreeQueryCommandExecutor;

    @Override
    public SingleResponse<DeptTreeVO> queryDetail(IdCommand detailCommand) {
        return deptTreeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DeptTreeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return deptTreeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DeptTreeVO> pageQuery(DeptTreePageQueryCommand deptTreePageQueryCommand) {
        return deptTreeQueryCommandExecutor.execute(deptTreePageQueryCommand);
    }

    @Override
    public MultiResponse<DeptTreeVO> queryList(DeptTreeQueryListCommand deptTreeQueryListCommand) {
        return deptTreeQueryCommandExecutor.execute(deptTreeQueryListCommand);
    }

    @Autowired
    public void setDeptTreeQueryCommandExecutor(DeptTreeQueryCommandExecutor deptTreeQueryCommandExecutor) {
        this.deptTreeQueryCommandExecutor = deptTreeQueryCommandExecutor;
    }
}
