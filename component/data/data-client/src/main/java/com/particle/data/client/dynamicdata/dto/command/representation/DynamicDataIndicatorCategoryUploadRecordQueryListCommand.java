package com.particle.data.client.dynamicdata.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBaseQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 动态数据指标分类上传记录 通用列表查询指令对象
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Data
@Schema
public class DynamicDataIndicatorCategoryUploadRecordQueryListCommand extends AbstractBaseQueryCommand {



    @Schema(description = "动态数据指标分类id")
    private Long dynamicDataIndicatorCategoryId;

    @Like(left = true,right = true)
    @Schema(description = "上传文件名称")
    private String uploadFileName;


    @Schema(description = "上传文件地址")
    private String uploadFileUrl;


    @Schema(description = "上传指标数量")
    private Integer uploadIndicatorNum;


    @Schema(description = "上传数据数量")
    private Integer uploadIndicatorDataNum;

	@Schema(description = "是否发布，1=是，0=否")
	private Boolean isPublic;









}
