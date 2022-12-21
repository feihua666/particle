package com.particle.dict.adapter.feign.client.rpc;

import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 * 字典远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.dict:dict}",path = "/rpc/dict")
public interface DictRpcFeignClient extends ITransService<Object,Long> {

	/**
	 * 根据字典编码查询子级
	 * @param groupCode
	 * @return
	 */
	@GetMapping("/itemsByGroupCode")
	public MultiResponse<DictVO> getItemsByGroupCode(String groupCode);

	/**
	 * 根据字典组编码，和字典项值查询
	 * @param groupCode
	 * @param value
	 * @return
	 */
	@GetMapping("/itemByGroupCodeAndItemValue")
	public SingleResponse<DictVO> getByGroupCodeAndItemValue(String groupCode,String value);

	/**
	 * 根据id获取字典
	 * @param id
	 * @return
	 */
	@GetMapping("/queryById")
	public SingleResponse<DictVO> queryById(Long id);

	/**
	 * 根据字典id获取字典，多个
	 * @param ids
	 * @return
	 */
	@GetMapping("/queryByIds")
	public MultiResponse<DictVO> queryByIds(List<Long> ids);
}
