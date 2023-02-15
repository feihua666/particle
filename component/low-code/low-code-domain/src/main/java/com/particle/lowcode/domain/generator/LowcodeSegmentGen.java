package com.particle.lowcode.domain.generator;

import java.time.LocalDateTime;

import cn.hutool.core.util.StrUtil;
import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import com.particle.global.tool.number.NumberIdTool;
import lombok.Data;

/**
 * <p>
 * 低代码生成 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Data
@Entity
public class LowcodeSegmentGen extends AggreateRoot {

	private LowcodeSegmentGenId id;
    /**
     * 生成名称，一般用于显示标识
     */
    private String name;
    /**
     * 低代码片段模板id
     */
    private Long lowcodeSegmentTemplateId;
    /**
     * 低代码模型id
     */
    private Long lowcodeModelId;
    /**
     * 低代码模型数据包括模型项数据
     */
    private String lowcodeModelJson;
    /**
     * 全局可用变量json数据
     */
    private String globalJson;
    /**
     * 是否已生成
     */
    private Boolean isGenerated;
    /**
     * 生成类型字典id
     */
    private Long generateTypeDictId;
    /**
     * 引用生成id，主要用来获取引用的相关变量
     */
    private Long refrenceSegmentGenId;
    /**
     * 描述,注意事项等
     */
    private String remark;


	/**
	 * 变更为未生成，一般用于新创建时使用该方法
	 */
	public void changeToNotGenerated(){
    	isGenerated = false;
	}

	/**
	 * 是否可以初始化模型 json 数据
	 */
	public boolean canInitLowcodeModelJson(){
		return canReloadLowcodeModelJson() && StrUtil.isEmpty(lowcodeModelJson);
	}

	/**
	 * 是否可以重新加载
	 * @return
	 */
	public boolean canReloadLowcodeModelJson(){
		return NumberIdTool.isValid(lowcodeModelId);
	}

	/**
	 * 清空json数据
	 */
	public void clearLowcodeModelJsonIfNecessary(){
		if (!canReloadLowcodeModelJson()) {
			changeLowcodeModelJson("");
		}
	}

	public void changeLowcodeModelJson(String newLowcodeModelJson) {
		lowcodeModelJson = newLowcodeModelJson;
	}

	/**
	 * 创建低代码生成领域模型对象
	 * @return 低代码生成领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static LowcodeSegmentGen create(){
		return DomainFactory.create(LowcodeSegmentGen.class);
	}
}
