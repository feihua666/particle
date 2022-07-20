package com.particle.global.mybatis.plus.dto;

import com.particle.global.dto.basic.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 一个关系实体，主要用于关系分配
 * @author yangwei
 * @since 2021/2/7 16:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RelDTO extends DTO {

    private Long mainId;
    private Long otherId;
}
