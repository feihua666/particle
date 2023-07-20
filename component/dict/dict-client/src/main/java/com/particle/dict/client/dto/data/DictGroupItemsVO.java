package com.particle.dict.client.dto.data;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 字典分组项响应对象
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Data
@Schema
public class DictGroupItemsVO extends DictVO {

    /**
     * 分组下面的项
     */
    private List<DictVO> items;

}
