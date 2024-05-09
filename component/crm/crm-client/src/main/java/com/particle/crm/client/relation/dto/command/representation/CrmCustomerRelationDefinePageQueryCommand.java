package com.particle.crm.client.relation.dto.command.representation;
import com.particle.common.client.dto.command.AbstractBasePageQueryCommand;
import com.particle.global.light.share.mybatis.anno.Like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.mybatis.anno.Like;
/**
 * <p>
 * 客户关系定义 通用分页查询指令对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Data
@Schema
public class CrmCustomerRelationDefinePageQueryCommand extends AbstractBasePageQueryCommand {



    @Like
        @Schema(description = "关系定义编码,左前缀匹配")
    private String code;


    @Like
        @Schema(description = "关系定义名称,左前缀匹配")
    private String name;

	@Schema(description = "是否为双向关系,不是双向就是单身")
	private Boolean isBidirectional;

	@Schema(description = "双向关系id，如果为单向关系，则必填，存储对应的双向关系id")
	private Long bidirectionalId;


    @Schema(description = "备注")
    private String remark;









}