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
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.trans.result.TransResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * <p>
 * 字典远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Api(tags = "字典远程调用相关接口")
@RestController
@RequestMapping("/rpc/dict")
public class DictRpcController extends AbstractBaseRpcAdapter implements DictRpcFeignClient {

	@Autowired
	private IDictApplicationService iDictApplicationService;

	@Autowired
	private DictTransServiceImpl dictTransService;

	@Autowired
	private IDictService iDictService;

	@Override
	public boolean supportBatch(String type) {
		return dictTransService.supportBatch(type);
	}


	@Override
	public List<TransResult<Object, Long>> transBatch(String type, Set<Long> keys) {
		return dictTransService.transBatch(type, keys);
	}



	@ApiOperation("根据编号查询字段子一级")
	@Override
	public MultiResponse<DictVO> getItemsByGroupCode(String groupCode) {
		List<DictDO> byCode = iDictService.getItemsByGroupCode(groupCode);

		List<DictVO> dictVOS = DictAppStructMapping.instance.dictDOsToDictVOs(byCode);
		return MultiResponse.of(dictVOS);
	}


	@ApiOperation("根据字典组编码和字典项值查询")
	@Override
	public SingleResponse<DictVO> getByGroupCodeAndItemValue(String groupCode, String value) {
		DictDO dictDO = iDictService.getByGroupCodeAndItemValue(groupCode, value);
		return SingleResponse.of(DictAppStructMapping.instance.dictDOToDictVO(dictDO));
	}

	@ApiOperation("根据字典id查询")
	@Override
	public SingleResponse<DictVO> queryById(Long id) {
		DictDO dictDO = iDictService.getById(id);
		return SingleResponse.of(DictAppStructMapping.instance.dictDOToDictVO(dictDO));
	}

	@ApiOperation("根据字典id查询,多个")
	@Override
	public MultiResponse<DictVO> queryByIds(List<Long> ids) {
		List<DictDO> list = iDictService.list(Wrappers.<DictDO>lambdaQuery().in(DictDO::getId, ids));
		List<DictVO> dictVOS = DictAppStructMapping.instance.dictDOsToDictVOs(list);
		return MultiResponse.of(dictVOS);
	}

}