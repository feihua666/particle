package com.particle.global.tool.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

/**
 * <p>
 * 布尔工具类，主要用于判断多个布尔条件同在存在时
 * </p>
 * 用法如下：
 *
 public static void main(String[] args) {
 BooleanTool ccc = BooleanTool.or(BooleanTool.bc(false, "111"), BooleanTool.bc(false, "222"), BooleanTool.bc(false, "ccc"));
 if(ccc.result()){
 System.out.println(ccc.resultMsg());
 }


 BooleanTool ddd = BooleanTool.and(BooleanTool.bc(true, "111"), BooleanTool.bc(false, "222"), BooleanTool.bc(false, "ccc"));
 if(!ddd.result()){
 System.out.println(ddd.resultMsg());
 }
 }
 * @author yangwei
 * @since 2021-06-14 23:20
 */
@Setter
public class BooleanTool {

	private BooleanCondition[] booleanConditions;
	private Boolean isOrType;
	// 是否失败
	private Boolean result;
	// 失败的索引
	private Integer resultIndex;

	/**
	 * 最终的结果
	 * @return
	 */
	public boolean result(){
		if (isOrType == null) {
			throw new IllegalStateException("请先使用or 或 and 方法再获取返回值");
		}
		return Optional.ofNullable(result).orElse(false);
	}

	/**
	 * 最终的结果提示信息
	 * @return
	 */
	public String resultMsg(){
		if (isOrType == null) {
			throw new IllegalStateException("请先使用or 或 and 方法再获取返回值");
		}
		return Optional.ofNullable(resultIndex).map(i -> booleanConditions[i].getMsg()).orElse(null);
	}

	/**
	 * 布尔条件及提示信息
	 * @param bool
	 * @param msg
	 * @return
	 */
	public static BooleanCondition bc(boolean bool,String msg){
		return new BooleanCondition(bool, msg);
	}
	/**
	 * 布尔条件
	 */
	@AllArgsConstructor
	@Getter
	public static class BooleanCondition{
		private boolean bool;

		private String msg;
	}

	/**
	 * 测试or
	 * @param bc
	 * @return
	 */
	public static BooleanTool or(BooleanCondition ...bc){
		BooleanTool booleanTool = new BooleanTool();
		booleanTool.setIsOrType(true);
		for (int i = 0; i < bc.length; i++) {
			if(bc[i].isBool()){
				booleanTool.setResult(true);
				booleanTool.setResultIndex(i);
				break;
			}
		}
		booleanTool.setBooleanConditions(bc);
		return booleanTool;
	}
	/**
	 * 测试 and
	 * @param bc
	 * @return
	 */
	public static BooleanTool and(BooleanCondition ...bc){
		BooleanTool booleanTool = new BooleanTool();
		booleanTool.setIsOrType(false);
		for (int i = 0; i < bc.length; i++) {
			if(!bc[i].isBool()){
				booleanTool.setResult(false);
				booleanTool.setResultIndex(i);
				break;
			}
		}
		booleanTool.setBooleanConditions(bc);
		return booleanTool;
	}

}
