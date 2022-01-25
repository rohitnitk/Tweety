package com.twitter.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils {

    public static String generateNonce() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String percentEncode(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
    }

    public static String getParameterString(Map<String, String> requestParams) throws UnsupportedEncodingException {
        Map<String, String> requestParamsSorted = new TreeMap<>(requestParams);
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry : requestParamsSorted.entrySet()) {
            sb.append(percentEncode(entry.getKey())).append("=").append(percentEncode(entry.getValue())).append("&");
        }

        return sb.substring(0, sb.length() - 1).toString();
    }

    public static String getSignatureBaseString(String httpMethod, String baseURL, String parameterString) throws UnsupportedEncodingException {
        return httpMethod.toUpperCase() + "&" + percentEncode(baseURL) + "&" + percentEncode(parameterString);
    }

    public static String getSigningKey(String consumerSecret, String tokenSecret) throws UnsupportedEncodingException {

        if (tokenSecret == null) {
            tokenSecret = "";
        }
        return percentEncode(consumerSecret) + "&" + percentEncode(tokenSecret);

    }

    public static String getHmacSHA1Signature(String signatureBaseString, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return Base64.getEncoder().encodeToString(mac.doFinal(signatureBaseString.getBytes()));
    }

    public static String getUnixTimeStamp() {
        Long timeLong = System.currentTimeMillis() / 1000;
        return timeLong.toString();
    }

    public static String formatKeyValue(String key, String value) {
        return " " + key + "=" + "\"" + value + "\"" + ",";
    }
}
