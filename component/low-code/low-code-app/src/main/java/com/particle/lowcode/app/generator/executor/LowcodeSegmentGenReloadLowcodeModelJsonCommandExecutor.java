package com.particle.lowcode.app.generator.executor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.trans.helper.TransHelper;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelAppStructMapping;
import com.particle.lowcode.app.generator.structmapping.LowcodeModelItemAppStructMapping;
import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentGenAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import com.particle.lowcode.domain.generator.LowcodeModel;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.LowcodeSegmentGenId;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelGateway;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelItemGateway;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentGenGateway;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelItemDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelItemService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 低代码生成 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Component
@Validated
public class LowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor extends AbstractBaseExecutor {

	private LowcodeSegmentGenGateway lowcodeSegmentGenGateway;
	private LowcodeModelGateway lowcodeModelGateway;
	private LowcodeModelItemGateway lowcodeModelItemGateway;

	private ILowcodeModelItemService lowcodeModelItemService;

	private TransHelper transHelper;

	public SingleResponse<LowcodeSegmentGenVO> reloadLowcodeModelJson(@Valid IdCommand reloadCommand) {
		LowcodeSegmentGen lowcodeSegmentGen = lowcodeSegmentGenGateway.getById(LowcodeSegmentGenId.of(reloadCommand.getId()));

		if (!lowcodeSegmentGen.canReloadLowcodeModelJson()) {
			return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR,"模型id不存在");
		}
		lowcodeSegmentGen.changeLowcodeModelJson(loadLowcodeModelJson(LowcodeModelId.of(lowcodeSegmentGen.getLowcodeModelId())));
		boolean save = lowcodeSegmentGenGateway.save(lowcodeSegmentGen);
		if (save) {
			return SingleResponse.of(LowcodeSegmentGenAppStructMapping.instance.toLowcodeSegmentGenVO(lowcodeSegmentGen));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 加载模型数据
	 * @param lowcodeModelId
	 * @return
	 */
	public String loadLowcodeModelJson(LowcodeModelId lowcodeModelId) {
		LowcodeModel lowcodeModel = lowcodeModelGateway.getById(lowcodeModelId);

		LowcodeModelVO lowcodeModelVO = LowcodeModelAppStructMapping.instance.toLowcodeModelVO(lowcodeModel);
		// 翻译一下字典
		transHelper.trans(lowcodeModelVO);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("model", lowcodeModelVO);

		List<LowcodeModelItemDO> lowcodeModelItemDOS = lowcodeModelItemService.listByColumn(lowcodeModelVO.getId(), LowcodeModelItemDO::getLowcodeModelId);
		List<LowcodeModelItemVO> modelItemVOS = LowcodeModelItemAppStructMapping.instance.lowcodeModelItemDOsToLowcodeModelItemVOs(lowcodeModelItemDOS);
		if (CollectionUtil.isNotEmpty(modelItemVOS)) {
			modelItemVOS.forEach(item->{
				item.initDesignJsonMap();
				item.clearDesignJson();
			});
		}
		resultMap.put("modelItems", modelItemVOS);

		return JsonTool.toJsonStr(resultMap);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeSegmentGenGateway
	 */
	@Autowired
	public void setLowcodeSegmentGenGateway(LowcodeSegmentGenGateway lowcodeSegmentGenGateway) {
		this.lowcodeSegmentGenGateway = lowcodeSegmentGenGateway;
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeModelGateway
	 */
	@Autowired
	public void setLowcodeModelGateway(LowcodeModelGateway lowcodeModelGateway) {
		this.lowcodeModelGateway = lowcodeModelGateway;
	}
	@Autowired
	public void setLowcodeModelItemGateway(LowcodeModelItemGateway lowcodeModelItemGateway) {
		this.lowcodeModelItemGateway = lowcodeModelItemGateway;
	}

	@Autowired
	public void setLowcodeModelItemService(ILowcodeModelItemService lowcodeModelItemService) {
		this.lowcodeModelItemService = lowcodeModelItemService;
	}

	@Autowired
	public void setTransHelper(TransHelper transHelper) {
		this.transHelper = transHelper;
	}
}
