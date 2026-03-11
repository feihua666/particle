package com.particle.component.autoconfigure.global.webfilter.usagecount;

import com.particle.global.dto.response.SingleResponse;
import com.particle.global.web.filter.UsageCountFilter;
import com.particle.usagecount.adapter.feign.client.rpc.UsageCountRecordRpcFeignClient;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2026/1/30 20:10
 */
public class UsageCountMarkerImpl implements UsageCountFilter.UsageCountMarker {

    @Autowired
    private UsageCountRecordRpcFeignClient usageCountRecordRpcFeignClient;

    @Override
    public UsageCountFilter.UsageCountMarkResult mark(String requestURI, Long loginUserId, Long currentTenantId) {
        UsageCountRecordMarkCommand usageCountRecordMarkCommand = new UsageCountRecordMarkCommand();
        usageCountRecordMarkCommand.setUrlPattern(requestURI);
        usageCountRecordMarkCommand.setCurrentUserId(loginUserId);
        usageCountRecordMarkCommand.setCurrentTenantId(currentTenantId);

        SingleResponse<UsageCountRecordMarkVO> mark = usageCountRecordRpcFeignClient.mark(usageCountRecordMarkCommand);
        UsageCountRecordMarkVO data = mark.getData();
        if (data == null) {
            return null;
        }
        return UsageCountFilter.UsageCountMarkResult.create(data.getIsExceed(),data.getExceedTip());
    }
}
