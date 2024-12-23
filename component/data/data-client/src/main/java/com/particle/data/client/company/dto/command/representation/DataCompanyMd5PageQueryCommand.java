package com.particle.data.client.company.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 企业md5 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Data
@Schema
public class DataCompanyMd5PageQueryCommand extends AbstractBasePageQueryCommand {



    @Schema(description = "企业id")
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









}
