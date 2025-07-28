package com.particle.cms.client.dto.command.directive;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 内容指令查询参数
 * </p>
 *
 * @author yangwei
 * @since 2025/7/8 11:38
 */
@Data
@Schema
public class CmsContentDirectivePageQueryCommand extends CmsDirectivePageQueryCommand{

    @Schema(description = "栏目id")
    private Long id;

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @Schema(description = "栏目id")
    private Long cmsChannelId;

    @Schema(description = "内容分类id")
    private Long cmsContentCategoryId;

    public static CmsContentDirectivePageQueryCommand create(CmsDirectivePageQueryCommand pageQueryCommand,
                                                             Long id,
                                                             Long cmsSiteId,
                                                             Long cmsChannelId,
                                                             Long cmsContentCategoryId) {
        CmsContentDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand = new CmsContentDirectivePageQueryCommand();
        cmsSiteDirectivePageQueryCommand.setIsPage(pageQueryCommand.getIsPage());
        cmsSiteDirectivePageQueryCommand.setPageNo(pageQueryCommand.getPageNo());
        cmsSiteDirectivePageQueryCommand.setPageSize(pageQueryCommand.getPageSize());
        cmsSiteDirectivePageQueryCommand.setIsOrderBy(pageQueryCommand.getIsOrderBy());
        cmsSiteDirectivePageQueryCommand.setOrderBy(pageQueryCommand.getOrderBy());

        cmsSiteDirectivePageQueryCommand.setId(id);
        cmsSiteDirectivePageQueryCommand.setCmsSiteId(cmsSiteId);
        cmsSiteDirectivePageQueryCommand.setCmsChannelId(cmsChannelId);
        cmsSiteDirectivePageQueryCommand.setCmsContentCategoryId(cmsContentCategoryId);
        return cmsSiteDirectivePageQueryCommand;
    }
}
