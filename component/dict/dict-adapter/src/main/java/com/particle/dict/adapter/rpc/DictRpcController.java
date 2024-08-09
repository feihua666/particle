package com.particle.dict.adapter.rpc;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.dict.app.structmapping.DictAppStructMapping;
import com.particle.dict.client.api.IDictApplicationService;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Tag(name = "字典远程调用相关接口")
@RestController
@RequestMapping("/rpc/dict")
public class DictRpcController extends AbstractBaseRpcAdapter implements DictRpcFeignClient {

	@Autowired
	private IDictApplicationService iDictApplicationService;

	@Autowired
	private IDictService iDictService;

	@Operation(summary = "根据id查询字段子一级")
	@Override
	public MultiResponse<DictVO> getItemsByGroupId(Long groupId) {
		List<DictDO> byCode = iDictService.getItemsByGroupId(groupId);

		List<DictVO> dictVOS = DictAppStructMapping.instance.dictDOsToDictVOs(byCode);
		return MultiResponse.of(dictVOS);
	}

	@Override
	public SingleResponse<Map<Long, List<DictVO>>> getItemsByGroupIds(List<Long> groupIds) {
		List<DictDO> list = iDictService.list(Wrappers.<DictDO>lambdaQuery().in(DictDO::getParentId, groupIds));
		Map<Long, List<DictVO>> collect = list.stream().collect(Collectors.groupingBy(DictDO::getParentId, Collectors.mapping(DictAppStructMapping.instance::dictDOToDictVO, Collectors.toList())));
		return SingleResponse.of(collect);
	}

	@Operation(summary = "根据编号查询字段子一级")
	@Override
	public MultiResponse<DictVO> getItemsByGroupCode(String groupCode) {
		List<DictDO> byCode = iDictService.getItemsByGroupCode(groupCode);

		List<DictVO> dictVOS = DictAppStructMapping.instance.dictDOsToDictVOs(byCode);
		return MultiResponse.of(dictVOS);
	}


	@Operation(summary = "根据字典组编码和字典项值查询")
	@Override
	public SingleResponse<DictVO> getByGroupCodeAndItemValue(String groupCode, String value) {
		DictDO dictDO = iDictService.getByGroupCodeAndItemValue(groupCode, value);
		return SingleResponse.of(DictAppStructMapping.instance.dictDOToDictVO(dictDO));
	}

	@Cacheable(cacheNames = {"DictRpcControllerCache_queryById"})
	@Operation(summary = "根据字典id查询")
	@Override
	public SingleResponse<DictVO> queryById(Long id) {
		DictDO dictDO = iDictService.getById(id);
		return SingleResponse.of(DictAppStructMapping.instance.dictDOToDictVO(dictDO));
	}
	@Cacheable(cacheNames = {"DictRpcControllerCache_queryByCode"})
	@Operation(summary = "根据字典code查询")
	@Override
	public SingleResponse<DictVO> queryByCode(String code) {
		DictDO dictDO = iDictService.getByCode(code);
		return SingleResponse.of(DictAppStructMapping.instance.dictDOToDictVO(dictDO));
	}
	@Operation(summary = "根据字典id查询,多个")
	@Override
	public MultiResponse<DictVO> queryByIds(List<Long> ids) {
		List<DictDO> list = iDictService.list(Wrappers.<DictDO>lambdaQuery().in(DictDO::getId, ids).orderByAsc(DictDO::getSeq));
		List<DictVO> dictVOS = DictAppStructMapping.instance.dictDOsToDictVOs(list);
		return MultiResponse.of(dictVOS);
	}

}