package com.particle.dict.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.dict.client.api.IDictApplicationService;
import com.particle.dict.client.api.representation.IDictRepresentationApplicationService;
import com.particle.dict.client.dto.command.representation.DictItemsQueryListCommand;
import com.particle.dict.client.dto.data.DictGroupItemsVO;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "字典pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/dict")
public class DictFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;
	@Autowired
	private IDictRepresentationApplicationService iDictRepresentationApplicationService;



	@ApiOperation("根据字典组编码查询字典项")
	@GetMapping("/items")
	public MultiResponse<DictVO> getItems(DictItemsQueryListCommand dictQueryListCommand) {
		return iDictRepresentationApplicationService.getItemsByGroupCode(dictQueryListCommand);
	}

	@ApiOperation("根据字典组编码查询分组字典项")
	@GetMapping("/groupItems")
	public MultiResponse<DictGroupItemsVO> getGroupItems(DictItemsQueryListCommand dictQueryListCommand) {
		return iDictRepresentationApplicationService.getGroupItemsByGroupCode(dictQueryListCommand);
	}

	@ApiOperation("根据字典组编码查询字典组")
	@GetMapping("/groups")
	public MultiResponse<DictVO> getGroups(DictItemsQueryListCommand dictQueryListCommand) {
		return iDictRepresentationApplicationService.getGroupsByGroupCode(dictQueryListCommand);
	}






}