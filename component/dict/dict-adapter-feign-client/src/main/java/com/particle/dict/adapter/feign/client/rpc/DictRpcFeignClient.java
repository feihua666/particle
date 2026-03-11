package com.particle.dict.adapter.feign.client.rpc;

import com.particle.dict.client.dto.data.DictVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.dict.name:dict-start}", contextId = "dictRpcFeignClient", url = "${particle.feign-client.dict.url:}",path = "/rpc/dict")
public interface DictRpcFeignClient{

	/**
	 * 根据字典id查询子级
	 * @param groupId
	 * @return
	 */
	@GetMapping("/getItemsByGroupId")
	public MultiResponse<DictVO> getItemsByGroupId(@RequestParam Long groupId);

	/**
	 * 根据字典id查询子级
	 * @param groupIds
	 * @return key为字典id，value为字典子级
	 */
	@GetMapping("/getItemsByGroupIds")
	public SingleResponse<Map<Long,List<DictVO>>> getItemsByGroupIds(@RequestParam List<Long> groupIds);
	/**
	 * 根据字典编码查询子级
	 * @param groupCode
	 * @return
	 */
	@GetMapping("/itemsByGroupCode")
	public MultiResponse<DictVO> getItemsByGroupCode(@RequestParam String groupCode);

	/**
	 * 根据字典组编码，和字典项值查询
	 * @param groupCode
	 * @param value
	 * @return
	 */
	@GetMapping("/itemByGroupCodeAndItemValue")
	public SingleResponse<DictVO> getByGroupCodeAndItemValue(@RequestParam String groupCode,
															 @RequestParam String value);

	/**
	 * 根据id获取字典
	 * @param id
	 * @return
	 */
	@GetMapping("/queryById")
	public SingleResponse<DictVO> queryById(@RequestParam Long id);

	/**
	 * 根据code获取字典
	 * @param code
	 * @return
	 */
	@GetMapping("/queryByCode")
	public SingleResponse<DictVO> queryByCode(@RequestParam String code);
	/**
	 * 根据映射值获取字典
	 * @param mappingValue
	 * @param groupCode
	 * @param includeName
	 * @param includeValue
	 * @return
	 */
	@GetMapping("/queryByMappingValue")
	public SingleResponse<DictVO> queryByMappingValue(@RequestParam String mappingValue,
													  @RequestParam String groupCode,
													  @RequestParam Boolean includeName,
													  @RequestParam Boolean includeValue);
	/**
	 * 根据字典id获取字典，多个
	 * @param ids
	 * @return
	 */
	@GetMapping("/queryByIds")
	public MultiResponse<DictVO> queryByIds(@RequestParam List<Long> ids);
}
