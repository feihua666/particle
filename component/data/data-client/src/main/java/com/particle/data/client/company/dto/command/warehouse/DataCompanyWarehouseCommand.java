package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.data.common.tool.SomeStrTool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业标识信息入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyWarehouseCommand extends AbstractBaseCommand {

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

    public void normalize() {
        this.name = SomeStrTool.normalizeCompanyName(name);
        this.uscc = SomeStrTool.normalizeCompanyUscc(uscc);
        this.regNo = SomeStrTool.normalizeCompanyRegNo(regNo);
        this.orgCode = SomeStrTool.normalizeCompanyOrgCode(orgCode);
        this.enName = SomeStrTool.normalizeCompanyEnName(enName);
    }
    /**
     * 判断是否所有主要字段都为空
     * @return
     */
    public boolean allMainFieldEmpty() {
        return StrUtil.isEmpty(name)
                && StrUtil.isEmpty(uscc)
                && StrUtil.isEmpty(regNo)
                && StrUtil.isEmpty(orgCode)
                && StrUtil.isEmpty(enName);
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
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
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyExWarehouseVO exWarehouseVO) {
        if (Objects.equals(name,exWarehouseVO.getName())) {
            name = null;
        }
        if (Objects.equals(uscc,exWarehouseVO.getUscc())) {
            uscc = null;
        }
        if (Objects.equals(regNo,exWarehouseVO.getRegNo())) {
            regNo = null;
        }
        if (Objects.equals(orgCode,exWarehouseVO.getOrgCode())) {
            orgCode = null;
        }
        if (Objects.equals(enName,exWarehouseVO.getEnName())) {
            enName = null;
        }
        if (Objects.equals(parentId,exWarehouseVO.getParentId())) {
            parentId = null;
        }
    }

    public static DataCompanyWarehouseCommand create(String name, String uscc) {
        DataCompanyWarehouseCommand dataCompanyWarehouseCommand = new DataCompanyWarehouseCommand();
        dataCompanyWarehouseCommand.name = name;
        dataCompanyWarehouseCommand.uscc = uscc;
        return dataCompanyWarehouseCommand;
    }
    public static DataCompanyWarehouseCommand create(String name, String uscc,String regNo) {
        DataCompanyWarehouseCommand dataCompanyWarehouseCommand = new DataCompanyWarehouseCommand();
        dataCompanyWarehouseCommand.name = name;
        dataCompanyWarehouseCommand.uscc = uscc;
        dataCompanyWarehouseCommand.regNo = regNo;
        return dataCompanyWarehouseCommand;
    }

    public static DataCompanyWarehouseCommand createByDataCompanyExWarehouseVO(DataCompanyExWarehouseVO dataCompanyExWarehouseVO) {
        DataCompanyWarehouseCommand dataCompanyWarehouseCommand = new DataCompanyWarehouseCommand();
        dataCompanyWarehouseCommand.name = dataCompanyExWarehouseVO.getName();
        dataCompanyWarehouseCommand.uscc = dataCompanyExWarehouseVO.getUscc();
        dataCompanyWarehouseCommand.regNo = dataCompanyExWarehouseVO.getRegNo();
        dataCompanyWarehouseCommand.orgCode = dataCompanyExWarehouseVO.getOrgCode();
        dataCompanyWarehouseCommand.enName = dataCompanyExWarehouseVO.getEnName();
        dataCompanyWarehouseCommand.parentId = dataCompanyExWarehouseVO.getParentId();

        return dataCompanyWarehouseCommand;
    }
}
