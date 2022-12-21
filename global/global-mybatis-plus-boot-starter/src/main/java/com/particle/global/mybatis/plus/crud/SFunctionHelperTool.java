package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.global.tool.str.StringTool;

/**
 * mybatis plus 工具类
 * @author yangwei
 * @since 2020/11/9 18:08
 */
public class SFunctionHelperTool {

    private static class EmptyLambda extends LambdaQueryWrapper{
        @Override
		public String columnToString(SFunction column) {
            return super.columnToString(column);
        }
    }

    /**
     * lambda表达式获取对应的数据库字段名
     * @param column User::getNickname
     * @return 数据库字段名
     */
    public static String columnToString(SFunction column) {
        return new EmptyLambda().columnToString(column);
    }

    /**
     * lambda表达式获取对应的实体属性名
     * 这里只是将数据库字段转为了下划线转驼峰
     * @param column
     * @return
     */
    public static String columnPropertyString(SFunction column){
        String columnString = columnToString(column);
        if (StrUtil.isEmpty(columnString)) {
            return null;
        }
        return StringTool.lineToHump(columnString);
    }
}
