package com.particle.data.client.company.dto.command;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseUpdateCommand;

import com.particle.data.client.company.dto.data.DataCompanyVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 企业md5 通用更新指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Data
@Schema
public class DataCompanyMd5UpdateCommand extends AbstractBaseUpdateCommand {



    @NotNull(message = "企业id 不能为空")
        @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;


    @NotEmpty(message = "企业名称md5 不能为空")
        @Schema(description = "企业名称md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String nameMd5;


    @NotEmpty(message = "统一社会信用代码md5 不能为空")
        @Schema(description = "统一社会信用代码md5",requiredMode = Schema.RequiredMode.REQUIRED)
    private String usccMd5;


    @Schema(description = "注册号md5")
    private String regNoMd5;


    @Schema(description = "组织机构代码md5")
    private String orgCodeMd5;


    @Schema(description = "英文名称md5")
    private String enNameMd5;






    /**
     * 转换，主要用于在添加企业数据后，再添加对应的md5的数据
     * @param dataCompanyVO
     * @return
     */
    public static DataCompanyMd5UpdateCommand createByDataCompanyVO(Long id,Integer version,DataCompanyVO dataCompanyVO) {
        DataCompanyMd5UpdateCommand dataCompanyMd5UpdateCommand = new DataCompanyMd5UpdateCommand();

        dataCompanyMd5UpdateCommand.setId(id);
        dataCompanyMd5UpdateCommand.setVersion(version);

        dataCompanyMd5UpdateCommand.companyId = dataCompanyVO.getId();
        if (StrUtil.isNotEmpty(dataCompanyVO.getName())) {
            dataCompanyMd5UpdateCommand.nameMd5 = DigestUtil.md5Hex(dataCompanyVO.getName());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getUscc())) {
            dataCompanyMd5UpdateCommand.usccMd5 = DigestUtil.md5Hex(dataCompanyVO.getUscc());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getRegNo())) {
            dataCompanyMd5UpdateCommand.regNoMd5 = DigestUtil.md5Hex(dataCompanyVO.getRegNo());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getOrgCode())) {
            dataCompanyMd5UpdateCommand.orgCodeMd5 = DigestUtil.md5Hex(dataCompanyVO.getOrgCode());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getEnName())) {
            dataCompanyMd5UpdateCommand.enNameMd5 = DigestUtil.md5Hex(dataCompanyVO.getEnName());
        }
        return dataCompanyMd5UpdateCommand;

    }


}
