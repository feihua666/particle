package com.particle.data.client.company.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 企业唯一标识 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyUniqueExWarehouseCandidateVO extends DataCompanyUniqueExWarehouseVO {
    /**
     * 候选数据，如果只出多条时，这里是候选数据
     */
    @Schema(description = "候选数据")
    private List<DataCompanyUniqueExWarehouseVO> candidates;

    public DataCompanyUniqueExWarehouseVO toDataCompanyUniqueExWarehouseVO() {
        return super.createNew();
    }
}
