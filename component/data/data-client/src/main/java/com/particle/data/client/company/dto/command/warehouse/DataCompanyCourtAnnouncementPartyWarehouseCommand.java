package com.particle.data.client.company.dto.command.warehouse;

import cn.hutool.core.util.StrUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.common.tool.SomeMd5Tool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Objects;

/**
 * <p>
 * 企业法院公告当事人入库 指令对象
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Data
@Schema
public class DataCompanyCourtAnnouncementPartyWarehouseCommand extends AbstractBaseCommand {


    @NotNull(message = "法院公告id 不能为空")
    @Schema(description = "法院公告id")
    private Long companyCourtAnnouncementId;


    @Schema(description = "当事人名称")
    private String partyName;


    @Schema(description = "是否法人为自然人")
    private Boolean isPartyNaturalPerson;


    @Schema(description = "当事人公司id")
    private Long partyCompanyId;


    @Schema(description = "当事人个人id")
    private Long partyCompanyPersonId;


    @Schema(description = "当事人角色")
    private Long partyRoleDictId;


    @Schema(description = "当事人描述信息")
    private String partyDescription;


    public String obtainDataMd5() {
        return SomeMd5Tool.dataCompanyCourtAnnouncementPartyDataMd5(partyName);
    }
    /**
     * 判断是否所有字段都为空,主要用来检查是否需要更新数据
     * @return
     */
    public boolean allFieldEmpty() {
        return Objects.isNull(companyCourtAnnouncementId)
                && StrUtil.isEmpty(partyName)
                && Objects.isNull(isPartyNaturalPerson)
                && Objects.isNull(partyCompanyId)
                && Objects.isNull(partyCompanyPersonId)
                && Objects.isNull(partyRoleDictId)
                && StrUtil.isEmpty(partyDescription);
    }

    /**
     * 主要用于更新，如果字段与现有数据一致，则设置为null
     * @param exWarehouseVO
     */
    public void compareAndSetNullWhenEquals(DataCompanyCourtAnnouncementPartyExWarehouseVO exWarehouseVO) {
        if (Objects.equals(companyCourtAnnouncementId, exWarehouseVO.getCompanyCourtAnnouncementId())) {
            this.companyCourtAnnouncementId = null;
        }
        if (Objects.equals(partyName, exWarehouseVO.getPartyName())) {
            this.partyName = null;
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
        if (Objects.equals(partyRoleDictId, exWarehouseVO.getPartyRoleDictId())) {
            this.partyRoleDictId = null;
        }
        if (Objects.equals(partyDescription, exWarehouseVO.getPartyDescription())) {
            this.partyDescription = null;
        }

    }

    public static DataCompanyCourtAnnouncementPartyWarehouseCommand createByDataCompanyCourtAnnouncementPartyExWarehouseVO(DataCompanyCourtAnnouncementPartyExWarehouseVO exWarehouseVO) {
        DataCompanyCourtAnnouncementPartyWarehouseCommand command = new DataCompanyCourtAnnouncementPartyWarehouseCommand();
        command.companyCourtAnnouncementId = exWarehouseVO.getCompanyCourtAnnouncementId();
        command.partyName = exWarehouseVO.getPartyName();
        command.isPartyNaturalPerson = exWarehouseVO.getIsPartyNaturalPerson();
        command.partyCompanyId = exWarehouseVO.getPartyCompanyId();
        command.partyCompanyPersonId = exWarehouseVO.getPartyCompanyPersonId();
        command.partyRoleDictId = exWarehouseVO.getPartyRoleDictId();
        command.partyDescription = exWarehouseVO.getPartyDescription();

        return command;
    }
}
