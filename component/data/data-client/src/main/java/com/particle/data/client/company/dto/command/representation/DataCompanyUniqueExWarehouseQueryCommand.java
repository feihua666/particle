package com.particle.data.client.company.dto.command.representation;

import com.particle.data.client.company.dto.command.DataCompanyWarehouseCommand;
import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业唯一标识 查询指令对象
 * 需要至少传一个
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyUniqueExWarehouseQueryCommand extends AbstractBaseQueryCommand {

    @Schema(description = "企业名称，支持md5")
    private String name;

    @Schema(description = "统一社会信用代码，支持md5")
    private String uscc;

    @Schema(description = "注册号，支持md5")
    private String regNo;

    @Schema(description = "组织机构代码，支持md5")
    private String orgCode;

    @Schema(description = "英文名称，支持md5")
    private String enName;

    @Schema(description = "如果出现多条时的返回策略")
    private String multipleStrategy;


    public static DataCompanyUniqueExWarehouseQueryCommand create(String name, String uscc) {
        DataCompanyUniqueExWarehouseQueryCommand dataCompanyUniqueExWarehouseQueryCommand = new DataCompanyUniqueExWarehouseQueryCommand();
        dataCompanyUniqueExWarehouseQueryCommand.name = name;
        dataCompanyUniqueExWarehouseQueryCommand.uscc = uscc;
        return dataCompanyUniqueExWarehouseQueryCommand;
    }

    public static DataCompanyUniqueExWarehouseQueryCommand createByWarehouseCommand(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        DataCompanyUniqueExWarehouseQueryCommand dataCompanyUniqueExWarehouseQueryCommand = new DataCompanyUniqueExWarehouseQueryCommand();
        dataCompanyUniqueExWarehouseQueryCommand.name = dataCompanyWarehouseCommand.getName();
        dataCompanyUniqueExWarehouseQueryCommand.uscc = dataCompanyWarehouseCommand.getUscc();
        dataCompanyUniqueExWarehouseQueryCommand.regNo = dataCompanyWarehouseCommand.getRegNo();
        dataCompanyUniqueExWarehouseQueryCommand.orgCode = dataCompanyWarehouseCommand.getOrgCode();
        dataCompanyUniqueExWarehouseQueryCommand.enName = dataCompanyWarehouseCommand.getEnName();
        return dataCompanyUniqueExWarehouseQueryCommand;
    }
}
