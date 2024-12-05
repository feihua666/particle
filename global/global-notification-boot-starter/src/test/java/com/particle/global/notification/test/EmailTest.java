package com.particle.global.notification.test;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/12/4 17:19
 */
public class EmailTest {
    public static void main(String[] args) {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost("smtp.163.com");
        mailAccount.setPort(25);
        mailAccount.setFrom("particle_particle@163.com");
        // 注意用户名和密码需要匹配，否则认证失败
        mailAccount.setUser("particle_particle");
        // 163 邮箱密码不是用户登录密码，是邮箱的授权码
        mailAccount.setPass("xxxxxx");
        MailUtil.send(mailAccount, "particle_particle@163.com", "测试", "测试", false);
    }
}
