package com.particle.global.dto.basic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 分页查询指令
 * 该指令应该是应用层门面的入参使用
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 14:47
 */
@Data
public class PageQueryCommand extends QueryCommand{
	private static final long serialVersionUID = 1L;
	/**
	 * 请求页码
	 */
	@Schema(description = "请求页码，从1开始，不传默认为1")
	private Long pageNo = 1L;
	/**
	 * 每页条数
	 */
	@Schema(description = "请求每页条数，不传默认为10")
	private Long pageSize = 10L;

}
