package com.particle.global.openapi.enums;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.apache.commons.lang3.function.TriFunction;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <p>
 * 开放接口签名算法
 * </p>
 *
 * @author yangwei
 * @since 2023-08-03 09:58:38
 */
public enum OpenapiSignatureAlgorithm {


	/**
	 * 支持 hmac简单数字签名 {@link HmacAlgorithm}
	 */
	HmacMD5("HmacMD5",(data,secret)->{
		byte[] key = StrUtil.bytes(secret);
		HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);
		return mac.digestHex(data);
	},(data,secret,signatureStr)->{
		String sign = sign(data, secret,"HmacMD5");
		return Objects.equals(sign, signatureStr);
	},OpenapiDigestAlgorithm.MD5),
	HmacSHA1("HmacSHA1",(data,secret)->{
		byte[] key = StrUtil.bytes(secret);
		HMac mac = new HMac(HmacAlgorithm.HmacSHA1, key);
		return mac.digestHex(data);
	},(data,secret,signatureStr)->{
		String sign = sign(data, secret,"HmacSHA1");
		return Objects.equals(sign, signatureStr);
	},OpenapiDigestAlgorithm.SHA_1),
	HmacSHA256("HmacSHA256",(data,secret)->{
		byte[] key = StrUtil.bytes(secret);
		HMac mac = new HMac(HmacAlgorithm.HmacSHA256, key);
		return mac.digestHex(data);
	},(data,secret,signatureStr)->{
		String sign = sign(data, secret,"HmacSHA256");
		return Objects.equals(sign, signatureStr);
	},OpenapiDigestAlgorithm.SHA_256),
	HmacSHA384("HmacSHA384",(data,secret)->{
		byte[] key = StrUtil.bytes(secret);
		HMac mac = new HMac(HmacAlgorithm.HmacSHA384, key);
		return mac.digestHex(data);
	},(data,secret,signatureStr)->{
		String sign = sign(data, secret,"HmacSHA384");
		return Objects.equals(sign, signatureStr);
	},OpenapiDigestAlgorithm.SHA_384),
	HmacSHA512("HmacSHA512",(data,secret)->{
		byte[] key = StrUtil.bytes(secret);
		HMac mac = new HMac(HmacAlgorithm.HmacSHA512, key);
		return mac.digestHex(data);
	},(data,secret,signatureStr)->{
		String sign = sign(data, secret,"HmacSHA512");
		return Objects.equals(sign, signatureStr);
	},OpenapiDigestAlgorithm.SHA_512),
	/** HmacSM3算法实现，需要BouncyCastle库支持 */
	HmacSM3("HmacSM3",(data,secret)->{
		byte[] key = StrUtil.bytes(secret);
		HMac mac = new HMac(HmacAlgorithm.HmacSM3, key);
		return mac.digestHex(data);
	},(data,secret,signatureStr)->{
		String sign = sign(data, secret,"HmacSM3");
		return Objects.equals(sign, signatureStr);
	},OpenapiDigestAlgorithm.SM3),
	/** SM4 CMAC模式实现，需要BouncyCastle库支持 */
	SM4CMAC("SM4CMAC",(data,secret)->{
		byte[] key = StrUtil.bytes(secret);
		HMac mac = new HMac(HmacAlgorithm.SM4CMAC, key);
		return mac.digestHex(data);
	},(data,secret,signatureStr)->{
		String sign = sign(data, secret,"SM4CMAC");
		return Objects.equals(sign, signatureStr);
	},null),


	/**
	 * 支持其它复杂签名如：rsa {@link SignAlgorithm}
	 */
	// The RSA signature algorithm
	NONEwithRSA("NONEwithRSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.NONEwithRSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.NONEwithRSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},null),

	// The MD2/MD5 with RSA Encryption signature algorithm
	MD2withRSA("MD2withRSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.MD2withRSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.MD2withRSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.MD2),
	MD5withRSA("MD5withRSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.MD5withRSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.MD5),

	// The signature algorithm with SHA-* and the RSA
	SHA1withRSA("SHA1withRSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withRSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_1),
	SHA256withRSA("SHA256withRSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_256),
	SHA384withRSA("SHA384withRSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA384withRSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA384withRSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_384),
	SHA512withRSA("SHA512withRSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withRSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withRSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_512),

	// The Digital Signature Algorithm
	NONEwithDSA("NONEwithDSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.NONEwithDSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.NONEwithDSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},null),
	// The DSA with SHA-1 signature algorithm
	SHA1withDSA("SHA1withDSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withDSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withDSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_1),

	// The ECDSA signature algorithms
	NONEwithECDSA("NONEwithECDSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.NONEwithECDSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.NONEwithECDSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},null),
	SHA1withECDSA("SHA1withECDSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withECDSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA1withECDSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_1),
	SHA256withECDSA("SHA256withECDSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withECDSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withECDSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_256),
	SHA384withECDSA("SHA384withECDSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA384withECDSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA384withECDSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_384),
	SHA512withECDSA("SHA512withECDSA",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withECDSA,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withECDSA,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_512),

	// 需要BC库加入支持
	SHA256withRSA_PSS("SHA256WithRSA/PSS",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA_PSS,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA256withRSA_PSS,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_256),
	SHA384withRSA_PSS("SHA384WithRSA/PSS",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA384withRSA_PSS,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA384withRSA_PSS,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_384),
	SHA512withRSA_PSS("SHA512WithRSA/PSS",(data,secret)->{
		// 签名使用私钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withRSA_PSS,secret,null);
		return sign.signHex(data);
	},(data,secret,signatureStr)->{
		// 验证签名使用公钥签名
		Sign sign = SecureUtil.sign(SignAlgorithm.SHA512withRSA_PSS,null,secret);
		return sign.verify(StrUtil.bytes(data),StrUtil.bytes(signatureStr));
	},OpenapiDigestAlgorithm.SHA_512)
	// todo 国密支持
	;

	/**
	 * 名称，仅展示
	 */
	private String name;
	/**
	 * 签名算法
	 */
	private BiFunction<String, String, String> signAlgorithm;
	/**
	 * 签名验证算法
	 */
	private TriFunction<String,String,String, Boolean> signVerifyAlgorithm;

	/**
	 * 对应的摘要算法
	 */
	private OpenapiDigestAlgorithm openapiDigestAlgorithm;

	OpenapiSignatureAlgorithm(String name,
							  BiFunction<String, String, String> signAlgorithm,
							  TriFunction<String,String,String, Boolean> signVerifyAlgorithm,
							  OpenapiDigestAlgorithm openapiDigestAlgorithm) {
		this.name = name;
		this.signAlgorithm = signAlgorithm;
		this.signVerifyAlgorithm = signVerifyAlgorithm;
		this.openapiDigestAlgorithm = openapiDigestAlgorithm;
	}

	public String getName() {
		return name;
	}

	public BiFunction<String, String, String> getSignAlgorithm() {
		return signAlgorithm;
	}

	public TriFunction<String, String, String, Boolean> getSignVerifyAlgorithm() {
		return signVerifyAlgorithm;
	}

	public OpenapiDigestAlgorithm getOpenapiDigestAlgorithm() {
		return openapiDigestAlgorithm;
	}

	/**
	 * 签名
	 * @param data
	 * @param secret
	 * @return
	 */
	public String sign(String data, String secret) {
		return this.signAlgorithm.apply(data, secret);
	}

	/**
	 * 签名校验
	 * @param data
	 * @param secret
	 * @param signatureStr
	 * @return
	 */
	public Boolean verify(String data, String secret, String signatureStr) {
		return this.signVerifyAlgorithm.apply(data, secret,signatureStr);
	}
	/**
	 * 在验证签名 function 传入构造函数数不能引用枚举自己，这里提供一个静态方式
	 * @param data
	 * @param secret
	 * @param name {@link OpenapiSignatureAlgorithm#getName()}
	 * @return
	 */
	private static String sign(String data, String secret,String name) {
		OpenapiSignatureAlgorithm openapiSignatureAlgorithm = Arrays.stream(OpenapiSignatureAlgorithm.values()).filter(item -> Objects.equals(name, item.getName())).findFirst().get();
		return openapiSignatureAlgorithm.sign(data,secret);
	}

}
