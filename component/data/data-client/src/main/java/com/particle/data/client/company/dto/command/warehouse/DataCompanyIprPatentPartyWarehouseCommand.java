package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPartyExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业知识产权专利当事人入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprPatentPartyWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权专利表id 不能为空")
    @Schema(description = "企业知识产权专利表id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprPatentId;


    @Schema(description = "当事人名称原始名称")
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


    @Schema(description = "是否申请人")
    private Boolean isApplicant;


    @Schema(description = "是否发明人")
    private Boolean isInvent;


    @Schema(description = "是否代理人")
    private Boolean isAgent;


    @Schema(description = "是否代理机构")
    private Boolean isAgency;


    @Schema(description = "是否审查员")
    private Boolean isExaminer;


    @Schema(description = "是否权利人")
    private Boolean isRight;


    @Schema(description = "是否当前权利人")
    private Boolean isCurrentRight;


    @Schema(description = "地址")
    private String address;


    @Schema(description = "区域编码")
    private String areaCode;


    @Schema(description = "类型")
    private String typeName;

    @Schema(description = "代码，主要是代理机构代码")
    private String code;

    @Schema(description = "数据md5")
    private String dataMd5;

    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprPatentPartyDataMd5(partyName, isApplicant, isInvent, isAgent, isAgency, isExaminer, isRight, isCurrentRight);
        }
        return dataMd5;
    }

    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprPatentId)
                && StrUtil.isEmpty(partyName)
                && StrUtil.isEmpty(partyNameEn)
                && StrUtil.isEmpty(partyNameCn)
                && Objects.isNull(isPartyNaturalPerson)
                && Objects.isNull(partyCompanyId)
                && Objects.isNull(partyCompanyPersonId)
                && Objects.isNull(isApplicant)
                && Objects.isNull(isInvent)
                && Objects.isNull(isAgent)
                && Objects.isNull(isAgency)
                && Objects.isNull(isExaminer)
                && Objects.isNull(isRight)
                && Objects.isNull(isCurrentRight)
                && StrUtil.isEmpty(address)
                && StrUtil.isEmpty(areaCode)
                && StrUtil.isEmpty(typeName)
                && StrUtil.isEmpty(code);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprPatentPartyExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyIprPatentId, exWarehouseVO.getCompanyIprPatentId())) {
            this.companyIprPatentId = null;
        }
        if (Objects.equals(partyName, exWarehouseVO.getPartyName())) {
            this.partyName = null;
        }
        if (Objects.equals(partyNameEn, exWarehouseVO.getPartyNameEn())) {
            this.partyNameEn = null;
        }
        if (Objects.equals(partyNameCn, exWarehouseVO.getPartyNameCn())) {
            this.partyNameCn = null;
        }
        if (Objects.equals(isPartyNaturalPerson, exWarehouseVO.getIsPartyNaturalPerson())) {
            this.isPartyNaturalPerson = null;
        }
        if (Objects.equals(partyCompanyId, exWarehouseVO.getPartyCompanyId())) {
            this.partyCompanyId = null;
        }
        if (Objects.equals(partyCompanyPersonId, exWarehouseVO.getPartyCompanyPersonId())) {
            this.partyCompanyPersonId = null;
        }
        if (Objects.equals(isApplicant, exWarehouseVO.getIsApplicant())) {
            this.isApplicant = null;
        }
        if (Objects.equals(isInvent, exWarehouseVO.getIsInvent())) {
            this.isInvent = null;
        }
        if (Objects.equals(isAgent, exWarehouseVO.getIsAgent())) {
            this.isAgent = null;
        }
        if (Objects.equals(isAgency, exWarehouseVO.getIsAgency())) {
            this.isAgency = null;
        }
        if (Objects.equals(isExaminer, exWarehouseVO.getIsExaminer())) {
            this.isExaminer = null;
        }
        if (Objects.equals(isRight, exWarehouseVO.getIsRight())) {
            this.isRight = null;
        }
        if (Objects.equals(isCurrentRight, exWarehouseVO.getIsCurrentRight())) {
            this.isCurrentRight = null;
        }
        if (Objects.equals(address, exWarehouseVO.getAddress())) {
            this.address = null;
        }
        if (Objects.equals(areaCode, exWarehouseVO.getAreaCode())) {
            this.areaCode = null;
        }
        if (Objects.equals(typeName, exWarehouseVO.getTypeName())) {
            this.typeName = null;
        }
        if (Objects.equals(code, exWarehouseVO.getCode())) {
            this.code = null;
        }
    }

    public static DataCompanyIprPatentPartyWarehouseCommand createByDataCompanyIprPatentPartyExWarehouseVO(DataCompanyIprPatentPartyExWarehouseVO exWarehouseVO) {
        DataCompanyIprPatentPartyWarehouseCommand command = new DataCompanyIprPatentPartyWarehouseCommand();
        command.companyIprPatentId = exWarehouseVO.getCompanyIprPatentId();
        command.partyName = exWarehouseVO.getPartyName();
        command.partyNameEn = exWarehouseVO.getPartyNameEn();
        command.partyNameCn = exWarehouseVO.getPartyNameCn();
        command.isPartyNaturalPerson = exWarehouseVO.getIsPartyNaturalPerson();
        command.partyCompanyId = exWarehouseVO.getPartyCompanyId();
        command.partyCompanyPersonId = exWarehouseVO.getPartyCompanyPersonId();
        command.isApplicant = exWarehouseVO.getIsApplicant();
        command.isInvent = exWarehouseVO.getIsInvent();
        command.isAgent = exWarehouseVO.getIsAgent();
        command.isAgency = exWarehouseVO.getIsAgency();
        command.isExaminer = exWarehouseVO.getIsExaminer();
        command.isRight = exWarehouseVO.getIsRight();
        command.isCurrentRight = exWarehouseVO.getIsCurrentRight();
        command.address = exWarehouseVO.getAddress();
        command.areaCode = exWarehouseVO.getAreaCode();
        command.typeName = exWarehouseVO.getTypeName();
        command.code = exWarehouseVO.getCode();


        return command;

    }
}
