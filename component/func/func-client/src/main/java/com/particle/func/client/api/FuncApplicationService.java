package com.particle.func.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.func.client.dto.command.CreateFuncCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * 菜单接口类
 * @program: particle
 * @description:
 * @author: 许宝华
 * @create: 2022-07-02 19:53
 */

public interface FuncApplicationService extends IBaseApplicationService {

    /**
     * 创建菜单
     * @param createFuncCommand
     * @return
     */
    SingleResponse<FuncVO> create(CreateFuncCommand createFuncCommand);
}
