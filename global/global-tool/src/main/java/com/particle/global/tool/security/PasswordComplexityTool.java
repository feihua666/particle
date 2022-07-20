package com.particle.global.tool.security;

import cn.hutool.core.util.ReUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 密码复杂度工具
 * 密码复杂度
 * @author yangwei
 * @since 2021/3/29 16:10
 */
public class PasswordComplexityTool {

    /**
     * 规定密码长度
     * 如果实际密码长度不够直接减5个等级，
     */
    public static int minPasswordLength = 8;
    /**
     * 密码复杂度等级，默认分10个等级
     */
    public static int maxComplexityLevel = 10;
    public static int intComplexityLevel = 0;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PasswordComplexity{
        private int complexityLevel;
        private String evaluate;
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PasswordComplexityResult{
        private int complexityLevel;
        private List<PasswordComplexity> passwordComplexities;
    }

    /**
     * 获取评估结果
     * @param password
     * @return
     */
    public static PasswordComplexityResult getComplexityLevel(String password) {

        int result = intComplexityLevel;
        List<PasswordComplexity> passwordComplexities = new ArrayList<>();
        passwordComplexities.add(lengthEvaluate(password));
        passwordComplexities.add(sameCharEvaluate(password));
        passwordComplexities.add(continuousCharEvaluate(password));
        passwordComplexities.add(keyBoardContinuousEvaluate(password));
        passwordComplexities.add(categoryEvaluate(password));
        for (PasswordComplexity passwordComplexity : passwordComplexities) {
            result += passwordComplexity.getComplexityLevel();
        }

        result = result<intComplexityLevel?intComplexityLevel:result;
        result = result>maxComplexityLevel?maxComplexityLevel:result;

        PasswordComplexityResult passwordComplexityResult = new PasswordComplexityResult();
        passwordComplexityResult.setComplexityLevel(result<intComplexityLevel?intComplexityLevel:result);
        passwordComplexityResult.setPasswordComplexities(passwordComplexities);
        return passwordComplexityResult;
    }

    /**
     * 长度评估
     * @param password
     * @return
     */
    private static PasswordComplexity lengthEvaluate(String password) {
        PasswordComplexity passwordComplexity = new PasswordComplexity();
        passwordComplexity.setEvaluate("lengthEvaluate");
        int level = 0;
        if (password.length() >= 2 * minPasswordLength) {
            level = 2;
        }else
        if (password.length() >  minPasswordLength) {
            level = 1;
        }else
        if (password.length() ==  minPasswordLength) {
            level = 0;
        }else if (password.length() <  minPasswordLength) {
            level = password.length() - minPasswordLength;
        }
        passwordComplexity.setComplexityLevel(level);
        return passwordComplexity;
    }
    /**
     * 相同字符评估
     * @param password
     * @return
     */
    private static PasswordComplexity sameCharEvaluate(String password) {

        PasswordComplexity passwordComplexity = new PasswordComplexity();
        passwordComplexity.setEvaluate("sameCharEvaluate");

        int flag = 0;
        int level = 0;
        for (int i = 0 ; i < password.length();i++){
            if(i > 0){
                if((password.charAt(i)) == (password.charAt(i - 1)) ){
                    flag++;
                }
            }
        }


        if (flag > 0) {
            level = -flag/2;
        }

        passwordComplexity.setComplexityLevel(level);
        return passwordComplexity;
    }
    /**
     * 连续3个字符评估
     * @param password
     * @return
     */
    private static PasswordComplexity continuousCharEvaluate(String password) {

        PasswordComplexity passwordComplexity = new PasswordComplexity();
        passwordComplexity.setEvaluate("continuousCharEvaluate");

        boolean flag = false;
        int level = 0;
        for (int i = 0 ; i < password.length();i++){
            if(i > 0){
                if((password.charAt(i)) == (password.charAt(i - 1)) + 1 ){
                    if (flag) {
                        level = 1;
                        break;
                    }
                    flag = true;
                }else {
                    flag = false;
                }
            }
        }

        if (password.length() < 2) {
            level = 0;
        }else
        if (!flag) {
            level = 2;
        }

        passwordComplexity.setComplexityLevel(level);
        return passwordComplexity;
    }

    /**
     * 键盘连接3个字符评估
     * @param password
     * @return
     */
    private static PasswordComplexity keyBoardContinuousEvaluate(String password) {
        PasswordComplexity passwordComplexity = new PasswordComplexity();
        passwordComplexity.setEvaluate("keyBoardContinuousEvaluate");
        int level = 0;
        boolean flag = false;
        char[][] c1 = {
        {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+'},
        {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '{', '}', '|'},
        {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ':', '"'},
        {'z', 'x', 'c', 'v', 'b', 'n', 'm', '<', '>', '?'}
    };
        char[][] c2 = {
        {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '='},
        {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', '[', ']', '\\'},
        {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', ';', '\''},
        {'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'}
    };
        //获取坐标位置
        int[] y = new int[password.length()];
        int[] x = new int[password.length()];
        for (int c = 0; c < password.length(); c++) {
            y[c] = 0;//当做~`键处理
            x[c] = -1;
            for (int i = 0; i < c1.length; i++) {
                for (int j = 0; j < c1[i].length; j++) {
                    if (password.charAt(c) == c1[i][j]) {
                        y[c] = i;
                        x[c] = j;
                    }
                }
            }
            if (x[c] != -1){
                continue;
            }
            for (int i = 0; i < c2.length; i++) {
                for (int j = 0; j < c2[i].length; j++) {
                    if (password.charAt(c) == c2[i][j]) {
                        y[c] = i;
                        x[c] = j;
                    }
                }
            }
        }
        //匹配坐标连线
        for (int c = 1; c < password.length() - 1; c++) {
            if (y[c - 1] == y[c] && y[c] == y[c + 1]) {
                if ((x[c - 1] + 1 == x[c] && x[c] + 1 == x[c + 1]) || (x[c + 1] + 1 == x[c] && x[c] + 1 == x[c - 1])) {
                    level = 1;
                    flag = true;
                    break;
                }
            } else if (x[c - 1] == x[c] && x[c] == x[c + 1]) {
                if ((y[c - 1] + 1 == y[c] && y[c] + 1 == y[c + 1]) || (y[c + 1] + 1 == y[c] && y[c] + 1 == y[c - 1])) {
                    level = 1;
                    flag = true;
                    break;
                }
            }
        }
        if (password.length() < 2) {
            level = 0;
        }else
        if (!flag) {
            level = 2;
        }
        passwordComplexity.setComplexityLevel(level);
        return passwordComplexity;
    }

    /**
     * 字符种类评估
     * @param password
     * @return
     */
    private static PasswordComplexity categoryEvaluate(String password) {
        PasswordComplexity passwordComplexity = new PasswordComplexity();
        passwordComplexity.setEvaluate("categoryEvaluate");
        int level = 0;

        boolean l = ReUtil.contains("[a-z]",password);
        boolean L = ReUtil.contains("[A-Z]",password);
        boolean n = ReUtil.contains("[0-9]",password);

        if (l){
            level++;
        }
        if (L){
            level++;
        }
        if (n){
            level++;
        }
        if (!l && !L && !n) {
            level++;
        }

        passwordComplexity.setComplexityLevel(level);
        return passwordComplexity;
    }
}
