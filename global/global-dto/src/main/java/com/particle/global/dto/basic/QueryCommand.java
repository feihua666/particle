package com.particle.global.dto.basic;

import com.particle.global.light.share.mybatis.anno.Ignore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springdoc.api.annotations.ParameterObject;

/**
 * <p>
 * 查询指令
 * 该指令应该是应用层门面的入参使用
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 14:44
 */
@Data
@ParameterObject
public class QueryCommand extends Command{
	private static final long serialVersionUID = 1L;
	/**
	 * 该字段优先级高于直接使用注解 {@link com.particle.global.light.share.mybatis.anno.OrderBy}
	 */
	@Ignore
	@Schema(title = "排序",description = "规则：propertyName[-1|0] 1为升序，0为降序，按id升序排序：id-1或id，多个以逗号分隔：id,name,creatAt-0")
	private String orderBy;
}
