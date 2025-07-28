package com.particle.cms.client.dto.command.directive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 站点指令查询参数
 * </p>
 *
 * @author yangwei
 * @since 2025/7/8 11:38
 */
@Data
@Schema
public class CmsSiteDirectivePageQueryCommand extends CmsDirectivePageQueryCommand{

    @Schema(description = "站点id")
    private Long id;

    @Schema(description = "是否主站点")
    private Boolean isPrimeSite;


    public static CmsSiteDirectivePageQueryCommand create(CmsDirectivePageQueryCommand pageQueryCommand,
                                                          Long id,
                                                          Boolean isPrimeSite) {
        CmsSiteDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand = new CmsSiteDirectivePageQueryCommand();
        cmsSiteDirectivePageQueryCommand.setIsPage(pageQueryCommand.getIsPage());
        cmsSiteDirectivePageQueryCommand.setPageNo(pageQueryCommand.getPageNo());
        cmsSiteDirectivePageQueryCommand.setPageSize(pageQueryCommand.getPageSize());
        cmsSiteDirectivePageQueryCommand.setIsOrderBy(pageQueryCommand.getIsOrderBy());
        cmsSiteDirectivePageQueryCommand.setOrderBy(pageQueryCommand.getOrderBy());

        cmsSiteDirectivePageQueryCommand.setId(id);
        cmsSiteDirectivePageQueryCommand.setIsPrimeSite(isPrimeSite);
        return cmsSiteDirectivePageQueryCommand;
    }
}
