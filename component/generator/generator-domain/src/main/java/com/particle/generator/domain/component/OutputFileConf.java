package com.particle.generator.domain.component;

import com.particle.generator.domain.MethodEnum;
import com.particle.generator.domain.OutputFileEnum;
import com.particle.global.dto.basic.Value;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 生成文件配置
 * </p>
 *
 * @author yangwei
 * @since 2022-07-07 13:24
 */
@Data
@Builder
public class OutputFileConf extends Value {

	/**
	 * 生成的文件
	 */
	private OutputFileEnum outputFileEnum;
	/**
	 * 生成文件对应的方法
	 */
	private List<MethodEnum> methodEnums;


	/**
	 * 要生成的文件及对应的方法
	 * @param outputFileEnum
	 * @param methodEnums
	 * @return
	 */
	public static OutputFileConf create(OutputFileEnum outputFileEnum, List<MethodEnum> methodEnums) {
		return OutputFileConf.builder()
				.outputFileEnum(outputFileEnum)
				.methodEnums(methodEnums)
				.build();
	}

	/**
	 * 所有的要生成的文件及对应的所有方法
	 * @return
	 */
	public static List<OutputFileConf> createAll(){
		List<OutputFileConf> list = new ArrayList<>();
		for (OutputFileEnum value : OutputFileEnum.values()) {
			OutputFileConf outputFileConf = OutputFileConf.create(value,Arrays.stream(MethodEnum.values()).collect(Collectors.toList()));
			list.add(outputFileConf);
		}
		return list;
	}
}
