package com.particle.data.client.company.dto.data.exwarehouse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 企业主体 数据响应对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyExWarehouseCandidateVO extends DataCompanyExWarehouseVO {
    /**
     * 候选数据，如果输出多条时，这里是候选数据
     */
    @Schema(description = "候选数据")
    private List<DataCompanyExWarehouseVO> candidates;

    public DataCompanyExWarehouseVO toDataCompanyExWarehouseVO() {
        return super.copyNew();
    }
}
