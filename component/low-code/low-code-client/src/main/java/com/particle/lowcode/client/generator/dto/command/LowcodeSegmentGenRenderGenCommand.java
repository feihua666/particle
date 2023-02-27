package com.particle.lowcode.client.generator.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
@ApiModel
public class LowcodeSegmentGenRenderGenCommand extends AbstractBaseCommand {


	/**
	 * 全局变量数据
	 */
	@ApiModelProperty("全局变量数据json")
	private Map<String,Object> global;
	/**
	 * 扩展变量数据
	 */
	@ApiModelProperty("扩展变量数据json")
	private Map<String,Object> ext;
	/**
	 * 片段模板id
	 */
	@NotNull(message = "低代码生成id 不能为空")
	@ApiModelProperty("低代码生成id")
	private Long lowcodeSegmentGenId;

	/**
	 * 输出文件的父目录路径,尽量不要以文件分隔符结尾
	 * 如：/user/yw/test
	 */
	@ApiModelProperty("输出文件的父目录绝对路径如：/user/yw/test")
	private String outputFileParentAbsoluteDir;

	@ApiModelProperty("java包名的key")
	private Set<String> javaPackageKeys;
}
