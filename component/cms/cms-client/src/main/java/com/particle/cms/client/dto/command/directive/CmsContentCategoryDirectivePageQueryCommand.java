package com.particle.cms.client.dto.command.directive;

import com.particle.global.light.share.mybatis.anno.QueryNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 分类指令查询参数
 * </p>
 *
 * @author yangwei
 * @since 2025/7/8 11:38
 */
@Data
@Schema
public class CmsContentCategoryDirectivePageQueryCommand extends CmsDirectivePageQueryCommand{

    @Schema(description = "栏目id")
    private Long id;

    @Schema(description = "站点id")
    private Long cmsSiteId;

    @Schema(description = "栏目id")
    private Long cmsChannelId;

    @Schema(description = "是否使用 is null 查询 id字段,如果为true,则查询 id 为 null 的记录，cmsChannelId 字段不要赋值")
    private Boolean isChannelIdNull;

    public static CmsContentCategoryDirectivePageQueryCommand create(CmsDirectivePageQueryCommand pageQueryCommand,
                                                                     Long id,
                                                                     Long cmsSiteId,
                                                                     Long cmsChannelId,
                                                                     Boolean isChannelIdNull,
                                                                     Long parentId) {
        CmsContentCategoryDirectivePageQueryCommand cmsSiteDirectivePageQueryCommand = new CmsContentCategoryDirectivePageQueryCommand();
        cmsSiteDirectivePageQueryCommand.setIsPage(pageQueryCommand.getIsPage());
        cmsSiteDirectivePageQueryCommand.setPageNo(pageQueryCommand.getPageNo());
        cmsSiteDirectivePageQueryCommand.setPageSize(pageQueryCommand.getPageSize());
        cmsSiteDirectivePageQueryCommand.setIsOrderBy(pageQueryCommand.getIsOrderBy());
        cmsSiteDirectivePageQueryCommand.setOrderBy(pageQueryCommand.getOrderBy());

        cmsSiteDirectivePageQueryCommand.setId(id);
        cmsSiteDirectivePageQueryCommand.setCmsSiteId(cmsSiteId);
        cmsSiteDirectivePageQueryCommand.setCmsChannelId(cmsChannelId);
        cmsSiteDirectivePageQueryCommand.setIsChannelIdNull(isChannelIdNull);
        cmsSiteDirectivePageQueryCommand.setParentId(parentId);
        return cmsSiteDirectivePageQueryCommand;
    }
}
