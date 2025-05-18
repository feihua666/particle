package com.particle.tools.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.tool.id.SnowflakeIdTool;
import com.particle.tools.client.api.IParticleApplicationService;
import com.particle.tools.client.dto.command.AddFieldCommand;
import com.particle.tools.client.dto.command.AddWarehouseAndExWarehouseCommand;
import com.particle.tools.client.dto.command.BatchGenIdsCommand;
import com.particle.tools.client.dto.command.DeleteModelServiceCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2023-05-11 12:38:09
 */
@Tag(name = "Particle项目相关接口")
@RestController
@RequestMapping("front/web/particle")
public class ParticleController extends AbstractBaseWebAdapter {


    @Autowired
    private IParticleApplicationService iParticleApplicationService;

    /**
     * 主要用于对已生成的代码，添加字段，包括常用的po、command、vo等，注意不包括sql
     * @param addFieldCommand
     * @return
     */
    @Operation(summary = "添加字段")
    @PostMapping("/addField")
    @ResponseStatus(HttpStatus.OK)
    public Response addField(@RequestBody AddFieldCommand addFieldCommand) {
        return iParticleApplicationService.addField(addFieldCommand);
    }
    /**
     * 主要用于对已生成的代码，删除模型服务，主要用于生成错误，或者删除多余的服务
     * @param deleteModelServiceCommand
     * @return
     */
    @Operation(summary = "删除模型服务")
    @PostMapping("/deleteModelService")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteModelService(@RequestBody DeleteModelServiceCommand deleteModelServiceCommand) {
        return iParticleApplicationService.deleteModelService(deleteModelServiceCommand);
    }

    /**
     * 添加出库和入库,主要针对后端
     * @param addWarehouseAndExWarehouseCommand
     * @return
     */
    @Operation(summary = "添加出库和入库")
    @PostMapping("/addWarehouseAndExWarehouse")
    @ResponseStatus(HttpStatus.OK)
    public Response addWarehouseAndExWarehouse(@RequestBody AddWarehouseAndExWarehouseCommand addWarehouseAndExWarehouseCommand) {
        return iParticleApplicationService.addWarehouseAndExWarehouse(addWarehouseAndExWarehouseCommand);
    }

    /**
     * 指生成id
     * @param batchGenIdsCommand
     * @return
     */
    @Operation(summary = "批量生成id")
    @PostMapping("/batchGenIds")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse batchGenIds(@RequestBody @Validated BatchGenIdsCommand batchGenIdsCommand) {
        List<Long> list = new ArrayList<>(batchGenIdsCommand.getNum());
        for (Integer i = 0; i < batchGenIdsCommand.getNum(); i++) {
            list.add(SnowflakeIdTool.nextId());
        }
        return MultiResponse.of(list);
    }

}
