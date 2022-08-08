package com.particle.component.light.share.dict;

/**
 * 原则上字典一般放在实体中，但一些公共的字典，是所有实体可能共用的，放到这里，提供最基础的字典配置
 * Created by yangwei
 * Created at 2020/7/23 18:27
 */
public class PublicDictEnums implements IBaseDictEnum{


    /**
     * 匹配方式组编码
     */
    public enum DictMatchTypeGroupCode implements IDictGroup{
        match_type //匹配方式
        ;
        @Override
        public String groupCode() {
            return this.name();
        }
    }

    /**
     * 匹配方式
     */
    public enum DictMatchType implements IDictItem {
        match_eq, // 等于
        match_like, // 模糊匹配
        match_like_left, // 左模糊匹配
        match_like_right, // 右模糊匹配
        match_regex, // 正则匹配
        ;

        @Override
        public String itemValue() {
            return this.name();
        }

        @Override
        public String groupCode() {
            return DictMatchTypeGroupCode.match_type.groupCode();
        }
    }

    /**
     * 性别字典组编码
     */
    public enum DictGenderGroupCode implements IDictGroup{
        gender
        ;
        @Override
        public String groupCode() {
            return this.name();
        }
    }

    /**
     * 性别字典项
     */
    public enum DictGender implements IDictItem {
        m, // 男
        f, // 女
        ;

        @Override
        public String itemValue() {
           return this.name();
        }

        @Override
        public String groupCode() {
            return DictGenderGroupCode.gender.groupCode();
        }
    }

    /**
     * 婚姻状况组编码
     */
    public enum DictMarriedStatusGroupCode implements IDictGroup{
        married_status_parent
        ;
        @Override
        public String groupCode() {
            return this.name();
        }
    }

    /**
     * 婚姻状况字典项
     */
    public enum DictMarriedStatus implements IDictItem {
        married_status_dissociaton, // 离异
        married_status_dissociaton_kid, // 女
        married_status_single, // 单身
        married_status_married, // 已婚
        ;

        @Override
        public String itemValue() {
            return this.name();
        }

        @Override
        public String groupCode() {
            return DictMarriedStatusGroupCode.married_status_parent.groupCode();
        }
    }

    /**
     * 币种字典组
     */
    public enum CurrencyTypeGroupCode implements IDictGroup {
        currency_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }
    /**
     * 币种字典项
     */
    public enum CurrencyTypeDictItem implements IDictItem {
        CNY; // 人民币

        @Override
        public String itemValue() {
            return this.name();
        }

        @Override
        public String groupCode() {
            return CurrencyTypeGroupCode.currency_type.groupCode();
        }
    }


    /**
     * 用户分组标识，这里写为公共的
     */
    public enum UserGroupFlagGroupCode implements IDictGroup {
        user_group_flag;

        @Override
        public String groupCode() {
            return this.name();
        }
    }

    /**
     * 用户分组具体项
     */
    public enum UserGroupFlagDictItem implements IDictItem {
        backend, // 后台用户
        fronted, // 前台用户
        h5, // 用户
        wx_mp, // 微信公众号用户
        wx_mini; // 微信小程序用户

        @Override
        public String itemValue() {
            return this.name();
        }

        @Override
        public String groupCode() {
            return UserGroupFlagGroupCode.user_group_flag.groupCode();
        }
    }


    /**
     * 注册类型
     */
    public enum RegistTypeGroupCode implements IDictGroup {
        regist_type;

        @Override
        public String groupCode() {
            return this.name();
        }
    }

    /**
     * 注册类型字典项
     */
    public enum RegistTypeDictItem implements IDictItem {
        account_regist,     // 帐号注册
        mobile_regist,     // 手机号注册
        wx_mp_regist,     // 微信公众号注册
        wx_mini_regist,     // 微信小程序注册
        none,     // 无

        ;

        @Override
        public String itemValue() {
            return this.name();
        }

        @Override
        public String groupCode() {
            return RegistTypeGroupCode.regist_type.groupCode();
        }
    }
}
