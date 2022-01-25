package com.twitter.api.utils;

import static com.twitter.api.constants.EngineConstants.HMAC_SHA1;
import static com.twitter.api.constants.EngineConstants.include_entities;
import static com.twitter.api.constants.EngineConstants.oauth_consumer_key;
import static com.twitter.api.constants.EngineConstants.oauth_nonce;
import static com.twitter.api.constants.EngineConstants.oauth_signature_method;
import static com.twitter.api.constants.EngineConstants.oauth_timestamp;
import static com.twitter.api.constants.EngineConstants.oauth_token;
import static com.twitter.api.constants.EngineConstants.oauth_version;
import static com.twitter.api.constants.EngineConstants.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SecurityUtilsTest {
    String parameterString = null;
    String signatureBaseString = null;
    String signingKey = null;

    @Test
    public void generatNonce() {
        assertNotNull(SecurityUtils.generateNonce());
    }
    
    @Test
    public void percentEncode() throws UnsupportedEncodingException {
        String testString1 = "Ladies + Gentlemen";
        String expectedString1 = "Ladies%20%2B%20Gentlemen";
        String testString2 = "Dogs, Cats & Mice";
        String expectedString2 = "Dogs%2C%20Cats%20%26%20Mice";
        String testString3 = "20%";
        String expectedString3 = "20%25";
        assertEquals(SecurityUtils.percentEncode(testString1), expectedString1);
        assertEquals(SecurityUtils.percentEncode(testString2), expectedString2);
        assertEquals(SecurityUtils.percentEncode(testString3), expectedString3);
    }

    @Test
    public void getParameterString() throws UnsupportedEncodingException {
        Map<String, String> requestParams = getRequestMap();
        String expectedString = getExpectedParameterString();
        assertEquals(parameterString = SecurityUtils.getParameterString(requestParams), expectedString);

    }

    @Test
    public void getSignatureBaseString() throws UnsupportedEncodingException {
        String httpMethod = "post";
        String baseURL = "https://api.twitter.com/1.1/statuses/update.json";
        getParameterString();
        assertEquals(signatureBaseString = SecurityUtils.getSignatureBaseString(httpMethod, baseURL, parameterString), getExpectedSignatureBaseString());
    }

    @Test
    public void getSigningKey() throws UnsupportedEncodingException {
        String consumerSecret = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw";
        String tokenSecret = "LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE";
        String expectedSigingKey = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&LswwdoUaIvS8ltyTt5jkRh4J50vUPVVHtR2YPi5kE";
        assertEquals(signingKey = SecurityUtils.getSigningKey(consumerSecret, tokenSecret), expectedSigingKey);
        expectedSigingKey = "kAcSOqF21Fu85e7zjz7ZN2U4ZRhfV3WpwPAoE3Z7kBw&";
        assertEquals(SecurityUtils.getSigningKey(consumerSecret, null), expectedSigingKey);
    }

    @Test
    public void getHmacSHA1Signature() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String expectedSignature = "hCtSmYh+iHYCEqBWrE7C7hYmtUk=";
        if (parameterString == null) {
            getSignatureBaseString();
        }
        if (signingKey == null) {
            getSigningKey();
        }
        assertEquals(SecurityUtils.getHmacSHA1Signature(signatureBaseString, signingKey), expectedSignature);
    }

    @Test
    public void getUnixTimeStamp() {
        assertNotNull(SecurityUtils.getUnixTimeStamp());
    }

    @Test
    public void formatKeyValue() {
        String key = "key";
        String value = "value";
        String expectedString = " " + key + "=" + "\"" + value + "\"" + ",";
        assertEquals(SecurityUtils.formatKeyValue(key, value), expectedString);
    }

    private Map<String, String> getRequestMap() {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(status, "Hello Ladies + Gentlemen, a signed OAuth request!");
        requestParams.put(include_entities, "true");
        requestParams.put(oauth_consumer_key, "xvz1evFS4wEEPTGEFPHBog");
        requestParams.put(oauth_nonce, "kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg");
        requestParams.put(oauth_signature_method, HMAC_SHA1);
        requestParams.put(oauth_timestamp, "1318622958");
        requestParams.put(oauth_token, "370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb");
        requestParams.put(oauth_version, "1.0");

        return requestParams;
    }

    private String getExpectedParameterString() {
        String expectedString = "include_entities=true&oauth_consumer_key=xvz1evFS4wEEPTGEFPHBog&oauth_nonce=kYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg"
                + "&oauth_signature_method=HMAC-SHA1&oauth_timestamp=1318622958&oauth_token=370773112-GmHxMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb&oauth_version=1.0"
                + "&status=Hello%20Ladies%20%2B%20Gentlemen%2C%20a%20signed%20OAuth%20request%21";
        return expectedString;
    }

    private String getExpectedSignatureBaseString() {
        String expectedString = "POST&https%3A%2F%2Fapi.twitter.com%2F1.1%2Fstatuses%2Fupdate.json&include_entities%3Dtrue%26oauth_consumer_key%3Dxvz1evFS4wEEPTGEFPHBog%26"
                + "oauth_nonce%3DkYjzVBB8Y0ZFabxSWbWovY3uYSQ2pTgmZeNu2VS4cg%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1318622958%26oauth_token%3D370773112-GmH"
                + "xMAgYyLbNEtIKZeRNFsMKPR9EyMZeS9weJAEb%26oauth_version%3D1.0%26status%3DHello%2520Ladies%2520%252B%2520Gentlemen%252C%2520a%2520signed%2520OAuth%2520request%2521";
        return expectedString;
    }
}
