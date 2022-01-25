package com.twitter.api.oauth;

import static com.twitter.api.constants.EngineConstants.HMAC_SHA1;
import static com.twitter.api.constants.EngineConstants.oauth_consumer_key;
import static com.twitter.api.constants.EngineConstants.oauth_nonce;
import static com.twitter.api.constants.EngineConstants.oauth_signature;
import static com.twitter.api.constants.EngineConstants.oauth_signature_method;
import static com.twitter.api.constants.EngineConstants.oauth_timestamp;
import static com.twitter.api.constants.EngineConstants.oauth_token;
import static com.twitter.api.constants.EngineConstants.oauth_version;
import static com.twitter.api.utils.SecurityUtils.formatKeyValue;
import static com.twitter.api.utils.SecurityUtils.generateNonce;
import static com.twitter.api.utils.SecurityUtils.getHmacSHA1Signature;
import static com.twitter.api.utils.SecurityUtils.getParameterString;
import static com.twitter.api.utils.SecurityUtils.getSignatureBaseString;
import static com.twitter.api.utils.SecurityUtils.getSigningKey;
import static com.twitter.api.utils.SecurityUtils.getUnixTimeStamp;
import static com.twitter.api.utils.SecurityUtils.percentEncode;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;;

public class Authorization {
    private String consumerKey;
    private String consumerSecret;
    private String token;
    private String tokenSecret;
    private String version;
    private String oauthNonce;
    private String oauthTimestamp;

    public Authorization(String consumerKey, String token, String tokenSecret, String consumerSecret) {
        super();
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.token = token;
        this.tokenSecret = tokenSecret;
        this.version = "1.0";
        this.oauthNonce = generateNonce();
        this.oauthTimestamp = getUnixTimeStamp();
    }

    public String generateOauthHeader(String httpMethod, String baseURL)
            throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        Map<String, String> oauthParams = getOauthParamsMap();
        String parameterString = getParameterString(oauthParams);
        String signatureBaseString = getSignatureBaseString(httpMethod, baseURL, parameterString);
        String signingKey = getSigningKey(consumerSecret, tokenSecret);
        String HMAC_SHA1_Sign = getHmacSHA1Signature(signatureBaseString, signingKey);

        StringBuilder sb = new StringBuilder();
        sb.append("OAuth");
        sb.append(formatKeyValue(percentEncode(oauth_consumer_key), percentEncode(this.consumerKey)));
        sb.append(formatKeyValue(percentEncode(oauth_nonce), percentEncode(this.oauthNonce)));
        sb.append(formatKeyValue(percentEncode(oauth_signature), percentEncode(HMAC_SHA1_Sign)));
        sb.append(formatKeyValue(percentEncode(oauth_signature_method), percentEncode(HMAC_SHA1)));
        sb.append(formatKeyValue(percentEncode(oauth_timestamp), percentEncode(this.oauthTimestamp)));
        sb.append(formatKeyValue(percentEncode(oauth_token), percentEncode(this.token)));
        sb.append(formatKeyValue(percentEncode(oauth_version), percentEncode(this.version)));

        return sb.substring(0, sb.length() - 1).toString();

    }

    public Map<String, String> getOauthParamsMap() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(oauth_consumer_key, this.consumerKey);
        requestParams.put(oauth_nonce, this.oauthNonce);
        requestParams.put(oauth_signature_method, HMAC_SHA1);
        requestParams.put(oauth_timestamp, this.oauthTimestamp);
        requestParams.put(oauth_token, this.token);
        requestParams.put(oauth_version, this.version);

        return requestParams;
    }
}
