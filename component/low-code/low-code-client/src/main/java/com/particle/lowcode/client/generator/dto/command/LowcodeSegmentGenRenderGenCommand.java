package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 低代码生成模块渲染指令
 * </p>
 *
 * @author yangwei
 * @since 2023-02-16 14:54:45
 */
@Data
@Schema
public class LowcodeSegmentGenRenderGenCommand extends AbstractBaseCommand {


	/**
	 * 全局变量数据
	 */
	@Schema(description = "全局变量数据json")
	private Map<String,Object> global;
	/**
	 * 扩展变量数据
	 */
	@Schema(description = "扩展变量数据json")
	private Map<String,Object> ext;
	/**
	 * 片段模板id
	 */
	@NotNull(message = "低代码生成id 不能为空")
	@Schema(description = "低代码生成id")
	private Long lowcodeSegmentGenId;

	/**
	 * 输出文件的父目录路径,尽量不要以文件分隔符结尾
	 * 如：/user/yw/test
	 */
	@Schema(description = "输出文件的父目录绝对路径如：/user/yw/test")
	private String outputFileParentAbsoluteDir;

	@Schema(description = "java包名的key")
	private Set<String> javaPackageKeys;
}
