package com.particle.global.dto.basic;

import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;

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
public class QueryCommand extends Command{
	private static final long serialVersionUID = 1L;
	@Ignore
	@ApiModelProperty(value = "排序",example = "规则：propertyName[-1|0] 1为升序，0为降序，按id升序排序：id-1或id，多个以逗号分隔：id,name,creatAt-0")
	private String orderBy;
}
