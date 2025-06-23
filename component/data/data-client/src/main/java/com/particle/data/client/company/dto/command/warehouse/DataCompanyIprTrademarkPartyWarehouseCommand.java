package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprTrademarkPartyExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业知识产权商标当事人入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyIprTrademarkPartyWarehouseCommand extends AbstractBaseCommand {



    @NotNull(message = "企业知识产权商标id 不能为空")
    @Schema(description = "企业知识产权商标id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyIprTrademarkId;


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


    @Schema(description = "是否代理人")
    private Boolean isAgent;


    @Schema(description = "原始地址")
    private String address;


    @Schema(description = "中文地址")
    private String addressCn;


    @Schema(description = "英文地址")
    private String addressEn;


    @Schema(description = "区域编码")
    private String areaCode;


    @Schema(description = "数据md5")
    private String dataMd5;


    public String obtainDataMd5() {
        if (StrUtil.isEmpty(dataMd5)) {
            dataMd5 = SomeMd5Tool.dataCompanyIprTrademarkPartyDataMd5(partyName,isApplicant,isAgent);
        }
        return dataMd5;
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyIprTrademarkId)
                && StrUtil.isEmpty(partyName)
                && StrUtil.isEmpty(partyNameEn)
                && StrUtil.isEmpty(partyNameCn)
                && Objects.isNull(isPartyNaturalPerson)
                && Objects.isNull(partyCompanyId)
                && Objects.isNull(partyCompanyPersonId)
                && Objects.isNull(isApplicant)
                && Objects.isNull(isAgent)
                && StrUtil.isEmpty(address)
                && StrUtil.isEmpty(addressCn)
                && StrUtil.isEmpty(addressEn)
                && StrUtil.isEmpty(areaCode)
;
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyIprTrademarkPartyExWarehouseVO exWarehouseVO) {
                if (Objects.equals(companyIprTrademarkId, exWarehouseVO.getCompanyIprTrademarkId())) {
            this.companyIprTrademarkId = null;
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
        if (Objects.equals(isAgent, exWarehouseVO.getIsAgent())) {
            this.isAgent = null;
        }
        if (Objects.equals(address, exWarehouseVO.getAddress())) {
            this.address = null;
        }
        if (Objects.equals(addressCn, exWarehouseVO.getAddressCn())) {
            this.addressCn = null;
        }
        if (Objects.equals(addressEn, exWarehouseVO.getAddressEn())) {
            this.addressEn = null;
        }
        if (Objects.equals(areaCode, exWarehouseVO.getAreaCode())) {
            this.areaCode = null;
        }

    }

    public static DataCompanyIprTrademarkPartyWarehouseCommand createByDataCompanyIprTrademarkPartyExWarehouseVO(DataCompanyIprTrademarkPartyExWarehouseVO exWarehouseVO) {
        DataCompanyIprTrademarkPartyWarehouseCommand command = new DataCompanyIprTrademarkPartyWarehouseCommand();
        command.companyIprTrademarkId = exWarehouseVO.getCompanyIprTrademarkId();
        command.partyName = exWarehouseVO.getPartyName();
        command.partyNameEn = exWarehouseVO.getPartyNameEn();
        command.partyNameCn = exWarehouseVO.getPartyNameCn();
        command.isPartyNaturalPerson = exWarehouseVO.getIsPartyNaturalPerson();
        command.partyCompanyId = exWarehouseVO.getPartyCompanyId();
        command.partyCompanyPersonId = exWarehouseVO.getPartyCompanyPersonId();
        command.isApplicant = exWarehouseVO.getIsApplicant();
        command.isAgent = exWarehouseVO.getIsAgent();
        command.address = exWarehouseVO.getAddress();
        command.addressCn = exWarehouseVO.getAddressCn();
        command.addressEn = exWarehouseVO.getAddressEn();
        command.areaCode = exWarehouseVO.getAreaCode();

        return command;
    }

}
