package com.particle.data.client.company.dto.command;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.particle.common.client.dto.command.AbstractBaseCommand;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 企业md5 通用创建指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Data
@Schema
public class DataCompanyMd5CreateCommand extends AbstractBaseCommand {

    @NotNull(message = "企业id 不能为空")
    @Schema(description = "企业id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long companyId;

    @Schema(description = "企业名称md5")
    private String nameMd5;

    @Schema(description = "统一社会信用代码md5")
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
    public static DataCompanyMd5CreateCommand createByDataCompanyVO(DataCompanyVO dataCompanyVO) {
        DataCompanyMd5CreateCommand dataCompanyMd5CreateCommand = new DataCompanyMd5CreateCommand();
        dataCompanyMd5CreateCommand.companyId = dataCompanyVO.getId();
        if (StrUtil.isNotEmpty(dataCompanyVO.getName())) {
            dataCompanyMd5CreateCommand.nameMd5 = DigestUtil.md5Hex(dataCompanyVO.getName());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getUscc())) {
            dataCompanyMd5CreateCommand.usccMd5 = DigestUtil.md5Hex(dataCompanyVO.getUscc());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getRegNo())) {
            dataCompanyMd5CreateCommand.regNoMd5 = DigestUtil.md5Hex(dataCompanyVO.getRegNo());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getOrgCode())) {
            dataCompanyMd5CreateCommand.orgCodeMd5 = DigestUtil.md5Hex(dataCompanyVO.getOrgCode());
        }
        if (StrUtil.isNotEmpty(dataCompanyVO.getEnName())) {
            dataCompanyMd5CreateCommand.enNameMd5 = DigestUtil.md5Hex(dataCompanyVO.getEnName());
        }
        return dataCompanyMd5CreateCommand;

    }

}
