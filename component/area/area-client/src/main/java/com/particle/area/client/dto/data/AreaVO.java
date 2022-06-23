package com.particle.area.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 区域默认数据传输对象
 * </p>
 *
 * @author yangwei
 * @since 2022-05-17 16:39
 */
@Data
public class AreaVO extends AbstractBaseIdVO {


	@ApiModelProperty(value = "编码，编码全局唯一")
	private String code;

	@ApiModelProperty(value = "区域名称")
	private String name;

	/**
	 * 对应 area-domain中 {@code AreaType }
	 */
	@ApiModelProperty(value = "类型")
	private String type;

	@ApiModelProperty(value = "经度，百度地图")
	private String longitude;

	@ApiModelProperty(value = "纬度，百度地图")
	private String latitude;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "排序,默认按该字段升序排序")
	private Integer seq = 10;

	@ApiModelProperty(value = "父级id")
	private Long parentId;

}
