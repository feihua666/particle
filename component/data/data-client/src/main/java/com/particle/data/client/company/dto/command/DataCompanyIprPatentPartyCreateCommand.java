package com.particle.data.client.company.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentPartyWarehouseCommand;

/**
 * <p>
 * 企业知识产权当事人 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-27 18:00:12
 */
@Data
@Schema
public class DataCompanyIprPatentPartyCreateCommand extends AbstractBaseCommand {


    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;

    @NotEmpty(message = "当事人名称原始名称 不能为空")
    @Schema(description = "当事人名称原始名称",requiredMode = Schema.RequiredMode.REQUIRED)
    private String partyName;


    @Schema(description = "当事人名称英文名称")
    private String partyNameEn;


    @Schema(description = "当事人名称中文名称")
    private String partyNameCn;


    @Schema(description = "是否当事人为自然人")
    private Boolean isPartyNaturalPerson;


    @Schema(description = "当事人公司id")
    private Long partyCompanyId;


    @Schema(description = "当事人个人id")
    private Long partyCompanyPersonId;


    @NotNull(message = "是否申请人 不能为空")
    @Schema(description = "是否申请人",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isApplicant;

    @NotNull(message = "是否发明人 不能为空")
    @Schema(description = "是否发明人",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isInvent;

    @NotNull(message = "是否代理人 不能为空")
    @Schema(description = "是否代理人",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isAgent;

    @NotNull(message = "是否代理机构 不能为空")
    @Schema(description = "是否代理机构",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isAgency;

    @NotNull(message = "是否审查员 不能为空")
    @Schema(description = "是否审查员",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isExaminer;

    @NotNull(message = "是否权利人 不能为空")
    @Schema(description = "是否权利人",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isRight;

    @NotNull(message = "是否当前权利人 不能为空")
    @Schema(description = "是否当前权利人",requiredMode = Schema.RequiredMode.REQUIRED)
    private Boolean isCurrentRight;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "区域编码")
    private String areaCode;

    @Schema(description = "类型")
    private String typeName;

	@Schema(description = "代码，主要是代理机构代码")
	private String code;

    @NotEmpty(message = "数据md5 不能为空")
    @Schema(description = "数据md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String dataMd5;

    public static DataCompanyIprPatentPartyCreateCommand createByWarehouseCommand(DataCompanyIprPatentPartyWarehouseCommand dataCompanyBasicWarehouseCommand){
        DataCompanyIprPatentPartyCreateCommand command = new DataCompanyIprPatentPartyCreateCommand();
        command.companyIprPatentId = dataCompanyBasicWarehouseCommand.getCompanyIprPatentId();
        command.partyName = dataCompanyBasicWarehouseCommand.getPartyName();
        command.partyNameEn = dataCompanyBasicWarehouseCommand.getPartyNameEn();
        command.partyNameCn = dataCompanyBasicWarehouseCommand.getPartyNameCn();
        command.isPartyNaturalPerson = dataCompanyBasicWarehouseCommand.getIsPartyNaturalPerson();
        command.partyCompanyId = dataCompanyBasicWarehouseCommand.getPartyCompanyId();
        command.partyCompanyPersonId = dataCompanyBasicWarehouseCommand.getPartyCompanyPersonId();
        command.isApplicant = dataCompanyBasicWarehouseCommand.getIsApplicant();
        command.isInvent = dataCompanyBasicWarehouseCommand.getIsInvent();
        command.isAgent = dataCompanyBasicWarehouseCommand.getIsAgent();
        command.isAgency = dataCompanyBasicWarehouseCommand.getIsAgency();
        command.isExaminer = dataCompanyBasicWarehouseCommand.getIsExaminer();
        command.isRight = dataCompanyBasicWarehouseCommand.getIsRight();
        command.isCurrentRight = dataCompanyBasicWarehouseCommand.getIsCurrentRight();
        command.address = dataCompanyBasicWarehouseCommand.getAddress();
        command.areaCode = dataCompanyBasicWarehouseCommand.getAreaCode();
        command.typeName = dataCompanyBasicWarehouseCommand.getTypeName();
        command.code = dataCompanyBasicWarehouseCommand.getCode();
        command.dataMd5 = dataCompanyBasicWarehouseCommand.obtainDataMd5();


        return command;
    }
}
