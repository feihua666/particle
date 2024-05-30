package com.particle.config.adapter.feign.client.system.rpc;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.component.light.share.dict.oplog.OpLogConstants;
import com.particle.config.client.system.dto.command.SystemConfigCreateCommand;
import com.particle.config.client.system.dto.command.SystemConfigUpdateCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigPageQueryCommand;
import com.particle.config.client.system.dto.command.representation.SystemConfigQueryListCommand;
import com.particle.config.client.system.dto.data.SystemConfigVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统参数配置远程调用
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@FeignClient(name = "${particle.feign-client.name.config:config}",path = "/rpc/system_config")
public interface SystemConfigRpcFeignClient {

    /**
     * 添加系统参数配置
     * @param systemConfigCreateCommand
     * @return
     */
    @PostMapping("/create")
    public SingleResponse<SystemConfigVO> create(SystemConfigCreateCommand systemConfigCreateCommand);

    /**
     * 删除系统参数配置
     * @param deleteCommand
     * @return
     */
    @DeleteMapping("/delete")
    public SingleResponse<SystemConfigVO> delete(IdCommand deleteCommand);

    /**
     * 更新系统参数配置
     * @param systemConfigUpdateCommand
     * @return
     */
    @PutMapping("/update")
    public SingleResponse<SystemConfigVO> update(SystemConfigUpdateCommand systemConfigUpdateCommand);

    /**
     * 系统参数配置更新详情
     * @param detailForUpdateCommand
     * @return
     */
    @GetMapping("/detail-for-update")
    public SingleResponse<SystemConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

    /**
     * 系统参数配置详情
     * @param detailCommand
     * @return
     */
    @GetMapping("/detail")
    public SingleResponse<SystemConfigVO> queryDetail(IdCommand detailCommand);

    /**
     * 列表查询系统参数配置
     * @param systemConfigQueryListCommand
     * @return
     */
    @GetMapping("/list")
    public MultiResponse<SystemConfigVO> queryList(SystemConfigQueryListCommand systemConfigQueryListCommand);

    /**
     * 分页查询系统参数配置
     * @param systemConfigPageQueryCommand
     * @return
     */
    @GetMapping("/page")
    public PageResponse<SystemConfigVO> pageQueryList(SystemConfigPageQueryCommand systemConfigPageQueryCommand);


    /**
     * 根据code获取配置
     * @param code
     * @return
     */
    @GetMapping("/queryByCode")
    public SingleResponse<SystemConfigVO> queryByCode(String code);


    /**
     * 根据 tag 获取配置
     * @param tag
     * @return
     */
    @GetMapping("/queryByTag")
    public MultiResponse<SystemConfigVO> queryByTag(String tag);


}
