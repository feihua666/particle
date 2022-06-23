package com.particle.area.client.dto.command;

import com.particle.common.client.dto.command.AbstractBaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 区域 创建指令
 * </p>
 *
 * @author yangwei
 * @since 2022-04-27 20:03
 */
@Data
@ApiModel(value="创建区域指令")
public class CreateAreaCommand extends AbstractBaseCommand {

	@ApiModelProperty(value = "编码，编码全局唯一")
	private String code;

	@NotEmpty(message="区域名称不能为空")
	@ApiModelProperty(value = "区域名称",required = true)
	private String name;

	/**
	 * 对应 area-domain中 {@code AreaType }
	 */
	@NotEmpty(message="类型不能为空")
	@ApiModelProperty(value = "类型",required = true)
	private String type;

	@ApiModelProperty(value = "经度，百度地图")
	private String longitude;

	@ApiModelProperty(value = "纬度，百度地图")
	private String latitude;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "排序,默认按该字段升序排序")
	private Integer seq = 10;

	@ApiModelProperty(value = "父级id",notes = "不填写默认为根节点")
	private Long parentId;
}
