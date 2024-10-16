package com.particle.openplatform.app.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.app.executor.representation.OpenplatformAppQuotaQueryCommandExecutor;
import com.particle.openplatform.client.app.api.representation.IOpenplatformAppQuotaRepresentationApplicationService;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 开放平台应用额度 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Service
@CatchAndLog
public class OpenplatformAppQuotaRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOpenplatformAppQuotaRepresentationApplicationService {

    private OpenplatformAppQuotaQueryCommandExecutor openplatformAppQuotaQueryCommandExecutor;

    @Override
    public SingleResponse<OpenplatformAppQuotaVO> queryDetail(IdCommand detailCommand) {
        return openplatformAppQuotaQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<OpenplatformAppQuotaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return openplatformAppQuotaQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<OpenplatformAppQuotaVO> pageQuery(OpenplatformAppQuotaPageQueryCommand openplatformAppQuotaPageQueryCommand) {
        return openplatformAppQuotaQueryCommandExecutor.execute(openplatformAppQuotaPageQueryCommand);
    }

    @Override
    public MultiResponse<OpenplatformAppQuotaVO> queryList(OpenplatformAppQuotaQueryListCommand openplatformAppQuotaQueryListCommand) {
        return openplatformAppQuotaQueryCommandExecutor.execute(openplatformAppQuotaQueryListCommand);
    }


    @Autowired
    public void setOpenplatformAppQuotaQueryCommandExecutor(OpenplatformAppQuotaQueryCommandExecutor openplatformAppQuotaQueryCommandExecutor) {
        this.openplatformAppQuotaQueryCommandExecutor = openplatformAppQuotaQueryCommandExecutor;
    }
}
