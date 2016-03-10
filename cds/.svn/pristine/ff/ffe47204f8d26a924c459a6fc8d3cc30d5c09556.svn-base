package com.hixapi.web.framework.common;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hixapi.model.common.BaseException;

public class EncryptionUtil {

	private final static String IV = "hixservices-pwd#"; //16 characters for AES - This same value should be used at the decryption
	private final static String AES_CBC = "AES/CBC/PKCS5Padding";
	static SecretKeySpec key = new SecretKeySpec(IV.getBytes(), "AES");
	private static final Logger log = LogManager.getLogger(EncryptionUtil.class);

	
	
	public static String decrypt(final String cipherText, boolean utf8Decode) throws BaseException {
		log.debug("Decrypting:  " + cipherText);
		if (StringUtils.isBlank(cipherText)) {
			return "";
		}
		
		String encrypted = cipherText;
		try {
		if(utf8Decode){
			encrypted = URLDecoder.decode(encrypted,"utf-8");
		}

		AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());

		Cipher cipher = null;
		byte[] output = null;
		byte[] decrypted = null;
	
			cipher = Cipher.getInstance(AES_CBC);
			cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			output = Base64.decodeBase64(encrypted);
			decrypted = cipher.doFinal(output);
			return new String(decrypted);
		} catch (Exception e) {
			BaseException ex = new BaseException("Decryption failure for " + cipherText, e);
			ex.setErrorCode(APIConstants.ERRORCODE_GENERAL_ERROR);
			throw ex;
		}

	}

	public static String encrypt(final String plainText, boolean utf8Encode) throws BaseException {

		if (StringUtils.isBlank(plainText)) {
			return "";
		}
		
		AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());

		Cipher cipher = null;
		byte[] ecrypted = null;
		String output = null;

		try {
			cipher = Cipher.getInstance(AES_CBC);
			cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			ecrypted = cipher.doFinal(plainText.getBytes());
			output = Base64.encodeBase64String(ecrypted);
			if(utf8Encode){
				output = URLEncoder.encode(output, "UTF-8");
			}
			return output;

		} catch (Exception e) {
			BaseException ex = new BaseException("Decryption failure for " + plainText, e);
			ex.setErrorCode(APIConstants.ERRORCODE_GENERAL_ERROR);
			throw ex;
		}

	}
}
