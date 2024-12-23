package com.particle.lowcode.client.generator.dto.command.representation;

import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 低代码模型 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Data
@Schema
public class LowcodeModelPageQueryCommand extends AbstractBasePageQueryCommand {

	@Schema(description = "额外扩展json，目前添加主要是rel相互分配信息")
	private String extJson;


    @Like
    @Schema(description = "名称")
    private String name;

    @Like
    @Schema(description = "英文名称")
    private String nameEn;

    @Like
    @Schema(title = "实体名称",description = "首字母大写，符合java类名规范")
    private String nameEnEntity;

    @Like
    @Schema(title = "实体变量名称",description = "nameEnEntity 的首字母小写")
    private String nameEnEntityVar;

    @Like
    @Schema(description = "表名称")
    private String tableName;

    @Schema(description = "模型表类型，rel,tree,normal")
    private Long tableTypeDictId;

}
