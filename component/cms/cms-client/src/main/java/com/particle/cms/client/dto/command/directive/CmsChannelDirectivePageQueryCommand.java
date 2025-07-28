package com.particle.cms.client.dto.command.directive;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * <p>
 * 栏目指令查询参数
 * </p>
 *
 * @author yangwei
 * @since 2025/7/8 11:38
 */
@Data
@Schema
public class CmsChannelDirectivePageQueryCommand extends CmsDirectivePageQueryCommand{

    @Schema(description = "栏目id")
    private Long id;

    @Schema(description = "站点id")
    private Long cmsSiteId;


    public static CmsChannelDirectivePageQueryCommand create(CmsDirectivePageQueryCommand pageQueryCommand,
                                                             Long id,
                                                             Long cmsSiteId,
                                                             Long parentId) {
        CmsChannelDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand = new CmsChannelDirectivePageQueryCommand();
        cmsSiteDirectivePageQueryCommand.setIsPage(pageQueryCommand.getIsPage());
        cmsSiteDirectivePageQueryCommand.setPageNo(pageQueryCommand.getPageNo());
        cmsSiteDirectivePageQueryCommand.setPageSize(pageQueryCommand.getPageSize());
        cmsSiteDirectivePageQueryCommand.setIsOrderBy(pageQueryCommand.getIsOrderBy());
        cmsSiteDirectivePageQueryCommand.setOrderBy(pageQueryCommand.getOrderBy());

        cmsSiteDirectivePageQueryCommand.setId(id);
        cmsSiteDirectivePageQueryCommand.setCmsSiteId(cmsSiteId);
        cmsSiteDirectivePageQueryCommand.setParentId(parentId);
        return cmsSiteDirectivePageQueryCommand;
    }
}
