package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyWarehouseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 企业主体 查询指令对象
 * 需要至少传一个
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Data
@Schema
public class DataCompanyExWarehouseQueryCommand extends AbstractBaseQueryCommand {

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


    public static DataCompanyExWarehouseQueryCommand create(String name, String uscc) {
        return create(name, uscc,null);
    }
    public static DataCompanyExWarehouseQueryCommand create(String name, String uscc,String regNo) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = new DataCompanyExWarehouseQueryCommand();
        dataCompanyExWarehouseQueryCommand.name = name;
        dataCompanyExWarehouseQueryCommand.uscc = uscc;
        dataCompanyExWarehouseQueryCommand.regNo = regNo;
        return dataCompanyExWarehouseQueryCommand;
    }

    public static DataCompanyExWarehouseQueryCommand createByWarehouseCommand(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = new DataCompanyExWarehouseQueryCommand();
        dataCompanyExWarehouseQueryCommand.name = dataCompanyWarehouseCommand.getName();
        dataCompanyExWarehouseQueryCommand.uscc = dataCompanyWarehouseCommand.getUscc();
        dataCompanyExWarehouseQueryCommand.regNo = dataCompanyWarehouseCommand.getRegNo();
        dataCompanyExWarehouseQueryCommand.orgCode = dataCompanyWarehouseCommand.getOrgCode();
        dataCompanyExWarehouseQueryCommand.enName = dataCompanyWarehouseCommand.getEnName();
        return dataCompanyExWarehouseQueryCommand;
    }
}
