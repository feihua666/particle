package com.particle.global.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023/10/19 16:48
 */
public class PasswordTest {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("zzhw@2023"));
    }
}
