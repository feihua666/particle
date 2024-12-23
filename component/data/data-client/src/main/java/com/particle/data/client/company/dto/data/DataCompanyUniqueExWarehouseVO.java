package com.particle.data.client.company.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

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
public class DataCompanyUniqueExWarehouseVO extends AbstractBaseIdVO {

    @Schema(description = "企业名称")
    private String name;

    @Schema(description = "统一社会信用代码")
    private String uscc;

    @Schema(description = "注册号")
    private String regNo;

    @Schema(description = "组织机构代码")
    private String orgCode;

    @Schema(description = "英文名称")
    private String enName;

    @Schema(description = "父级id")
    private Long parentId;

    @Schema(description = "创建时间")
    private LocalDateTime createAt;

    @Schema(description = "更新时间")
    private LocalDateTime updateAt;

    @Schema(description = "最后更新时间，企业相关的做任意数据变更，都会体现在这里")
    private LocalDateTime latestUpdateAt;

    public DataCompanyUniqueExWarehouseVO createNew() {
        DataCompanyUniqueExWarehouseVO dataCompanyUniqueExWarehouseVO = new DataCompanyUniqueExWarehouseVO();
        dataCompanyUniqueExWarehouseVO.setId(this.getId());
        dataCompanyUniqueExWarehouseVO.setName(this.getName());
        dataCompanyUniqueExWarehouseVO.setUscc(this.getUscc());
        dataCompanyUniqueExWarehouseVO.setRegNo(this.getRegNo());
        dataCompanyUniqueExWarehouseVO.setOrgCode(this.getOrgCode());
        dataCompanyUniqueExWarehouseVO.setEnName(this.getEnName());
        dataCompanyUniqueExWarehouseVO.setParentId(this.getParentId());
        dataCompanyUniqueExWarehouseVO.setCreateAt(this.getCreateAt());
        dataCompanyUniqueExWarehouseVO.setUpdateAt(this.getUpdateAt());
        dataCompanyUniqueExWarehouseVO.setLatestUpdateAt(this.getLatestUpdateAt());
        return dataCompanyUniqueExWarehouseVO;
    }
}
