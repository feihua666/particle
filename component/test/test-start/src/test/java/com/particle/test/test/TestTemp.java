package com.particle.test.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2022-11-14 09:35
 */
public class TestTemp {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
		System.out.println(DigestUtils.md5Hex("123456"));

		String serverName = "escrow-data.idfactory.cn";
		boolean isIp = true;
		for (String s : serverName.split("\\.")) {
			try {
				Integer.parseInt(s);
			} catch (NumberFormatException e) {
				isIp = false;
				break;
			}
		}
		System.out.println(isIp);
	}
}
