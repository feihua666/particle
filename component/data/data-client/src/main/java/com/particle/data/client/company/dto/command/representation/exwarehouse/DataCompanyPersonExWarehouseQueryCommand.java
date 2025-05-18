package com.particle.data.client.company.dto.command.representation.exwarehouse;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 企业个人 查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-07 11:37:00
 */
@Data
@Schema
public class DataCompanyPersonExWarehouseQueryCommand extends AbstractBaseQueryCommand {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "证号")
    private String idNo;

    @Schema(description = "证号md5")
    private String idNoMd5;

    @Schema(description = "证号sha256")
    private String idNoSha256;

    @Schema(description = "证号sm3")
    private String idNoSm3;


    public static DataCompanyPersonExWarehouseQueryCommand create(Long id,
                                                                  String idNo,
                                                                  String idNoMd5,
                                                                  String idNoSha256,
                                                                  String idNoSm3
                                                                  ) {
        DataCompanyPersonExWarehouseQueryCommand command = new DataCompanyPersonExWarehouseQueryCommand();
        command.id = id;
        command.idNo = idNo;
        command.idNoMd5 = idNoMd5;
        command.idNoSha256 = idNoSha256;
        command.idNoSm3 = idNoSm3;
        return command;
    }
}
