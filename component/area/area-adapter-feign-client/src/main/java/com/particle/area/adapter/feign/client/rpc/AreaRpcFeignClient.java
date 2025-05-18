package com.particle.area.adapter.feign.client.rpc;

import com.particle.area.client.dto.data.AreaVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 区域远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-18
 */
@FeignClient(name = "${particle.feign-client.name.area:area}",path = "/rpc/area")
public interface AreaRpcFeignClient {


    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping("/queryById")
    public SingleResponse<AreaVO> queryById(Long id);

    /**
     * 根据code获取
     * @param code
     * @return
     */
    @GetMapping("/queryByCode")
    public SingleResponse<AreaVO> queryByCode(String code);

    /**
     * 根据名称查询省
     * @param provinceName
     * @return
     */
    @GetMapping("/queryProvinceByName")
    public SingleResponse<AreaVO> queryProvinceByName(String provinceName);

    /**
     * 根据名称查询市,限定要某个省下
     * @param cityName
     * @param provinceId
     * @return
     */
    @GetMapping("/queryCityByNameAndProvinceId")
    public SingleResponse<AreaVO> queryCityByNameAndProvinceId(String cityName,Long provinceId);
    /**
     * 根据名称查询区县,限定要某个市下
     * @param countyName
     * @param cityId
     * @return
     */
    @GetMapping("/queryCountyByNameAndCityId")
    public SingleResponse<AreaVO> queryCountyByNameAndCityId(String countyName,Long cityId);

}
