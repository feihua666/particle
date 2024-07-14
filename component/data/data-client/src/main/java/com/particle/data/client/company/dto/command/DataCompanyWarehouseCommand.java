package com.particle.data.client.company.dto.command;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.DataCompanyUniqueExWarehouseCandidateVO;
import com.particle.data.client.company.dto.data.DataCompanyUniqueExWarehouseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 企业入库 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyWarehouseCommand extends AbstractBaseCommand {

    @NotEmpty(message = "企业名称 不能为空")
    @Schema(description = "企业名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotEmpty(message = "统一社会信用代码 不能为空")
    @Schema(description = "统一社会信用代码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String uscc;

    @Schema(description = "注册号")
    private String regNo;

    @Schema(description = "组织机构代码")
    private String orgCode;

    @Schema(description = "英文名称")
    private String enName;

    @Schema(description = "父级id")
    private Long parentId;

    /**
     * 判断是否所有字段都为空
     * @return
     */
    public boolean allFieldEmpty() {
        return StrUtil.isEmpty(name)
                && StrUtil.isEmpty(uscc)
                && StrUtil.isEmpty(regNo)
                && StrUtil.isEmpty(orgCode)
                && StrUtil.isEmpty(enName)
                && Objects.isNull(parentId);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param uniqueExWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyUniqueExWarehouseVO uniqueExWarehouseVO) {
        if (Objects.equals(name,uniqueExWarehouseVO.getName())) {
            name = null;
        }
        if (Objects.equals(uscc,uniqueExWarehouseVO.getUscc())) {
            uscc = null;
        }
        if (Objects.equals(regNo,uniqueExWarehouseVO.getRegNo())) {
            regNo = null;
        }
        if (Objects.equals(orgCode,uniqueExWarehouseVO.getOrgCode())) {
            orgCode = null;
        }
        if (Objects.equals(enName,uniqueExWarehouseVO.getEnName())) {
            enName = null;
        }
        if (Objects.equals(parentId,uniqueExWarehouseVO.getParentId())) {
            enName = null;
        }
    }

    public static DataCompanyWarehouseCommand create(String name, String uscc) {
        DataCompanyWarehouseCommand dataCompanyWarehouseCommand = new DataCompanyWarehouseCommand();
        dataCompanyWarehouseCommand.name = name;
        dataCompanyWarehouseCommand.uscc = uscc;
        return dataCompanyWarehouseCommand;
    }
}
