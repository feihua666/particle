package com.particle.dict.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dict.client.api.IDictApplicationService;
import com.particle.dict.client.api.representation.IDictRepresentationApplicationService;
import com.particle.dict.client.dto.command.DictCreateCommand;
import com.particle.dict.client.dto.command.DictUpdateCommand;
import com.particle.dict.client.dto.command.representation.DictItemsQueryListCommand;
import com.particle.dict.client.dto.command.representation.DictPageQueryCommand;
import com.particle.dict.client.dto.command.representation.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictGroupItemsVO;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 字典后台管理pc或平板端前端适配器
 * 主要用于pc或平板端后台管理
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "字典pc或平板端后台管理相关接口")
@RestController
@RequestMapping("/admin/web/dict")
public class DictAdminWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IDictApplicationService iDictApplicationService;
	@Autowired
	private IDictRepresentationApplicationService iDictRepresentationApplicationService;

	@PreAuthorize("hasAuthority('admin:web:dict:create')")
	@ApiOperation("添加字典")
	@PostMapping("/create")
	public SingleResponse<DictVO> create(@RequestBody DictCreateCommand dictCreateCommand){
		return iDictApplicationService.create(dictCreateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:delete')")
	@ApiOperation("删除字典")
	@DeleteMapping("/delete")
	public SingleResponse<DictVO> delete(@RequestBody IdCommand dictDeleteCommand){
		return iDictApplicationService.delete(dictDeleteCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:update')")
	@ApiOperation("更新字典")
	@PutMapping("/update")
	public SingleResponse<DictVO> update(@RequestBody DictUpdateCommand dictUpdateCommand){
		return iDictApplicationService.update(dictUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:update')")
	@ApiOperation("字典更新详情")
	@GetMapping("/detail-for-update")
	public SingleResponse<DictVO> queryDetailForUpdate(IdCommand dictQueryDetailForUpdateCommand){
		return iDictRepresentationApplicationService.queryDetailForUpdate(dictQueryDetailForUpdateCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:detail')")
	@ApiOperation("字典详情展示")
	@GetMapping("/detail")
	public SingleResponse<DictVO> queryDetail(IdCommand dictQueryDetailCommand){
		return iDictRepresentationApplicationService.queryDetail(dictQueryDetailCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:queryList')")
	@ApiOperation("列表查询字典")
	@GetMapping("/list")
	public MultiResponse<DictVO> queryList(DictQueryListCommand dictQueryListCommand){
		return iDictRepresentationApplicationService.queryList(dictQueryListCommand);
	}

	@PreAuthorize("hasAuthority('admin:web:dict:pageQuery')")
	@ApiOperation("分页查询字典")
	@GetMapping("/page")
	public PageResponse<DictVO> pageQueryList(DictPageQueryCommand dictPageQueryCommand){
		return iDictRepresentationApplicationService.pageQuery(dictPageQueryCommand);
	}

}