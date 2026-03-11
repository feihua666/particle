package com.particle.common.client.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 基础VO，带id
 * </p>
 *
 * @author yangwei
 * @since 2022-05-17 16:57
 */
@Data
public class AbstractBaseIdTreeVO extends AbstractBaseVO{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@Schema(description = "主键id")
	private Long id;

	@Schema(description = "父级parentId")
	private Long parentId;

    @Schema(description = "父级parentId1")
    private Long parentId1;

    @Schema(description = "父级parentId2")
    private Long parentId2;

     @Schema(description = "父级parentId3")
    private Long parentId3;


     @Schema(description = "父级parentId4")
    private Long parentId4;


     @Schema(description = "父级parentId5")
    private Long parentId5;

    @Schema(description = "父级parentId6")
    private Long parentId6;

    @Schema(description = "父级parentId7")
    private Long parentId7;

    @Schema(description = "父级parentId8")
    private Long parentId8;

    @Schema(description = "父级parentId9")
    private Long parentId9;


     @Schema(description = "父级parentId10")
    private Long parentId10;

	@Schema(description = "层级")
	private Integer level;
}
