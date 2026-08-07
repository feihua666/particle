package com.particle.global.mybatis.plus.dto;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * 一个关系实体，主要用于关系分配
 * @author yangwei
 * @since 2021/2/7 16:12
 */
@Data
public class RelDTO extends DTO {

    public RelDTO(Long mainId, Long otherId) {
        this.mainId = mainId;
        this.otherId = otherId;
    }
    private Long mainId;

    private Long otherId;

    public static RelDTO create(Long mainId, Long otherId) {
        return new RelDTO(mainId, otherId);
    }
}
