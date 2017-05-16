package com.heren.his.commons.util;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SecurityUtils {

    public static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static String md5(String str) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return "";
        }
        byte[] hash = digest.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xff;
            if (v < 16) {
                sb.append(0);
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static RSAPrivateKey getRSAPrivateKeyFromString(String base64rsaKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decodeBase64(base64rsaKey)));
        return rSAPrivateKey;
    }

    public static RSAPublicKey getRSAPublicKeyFromString(String base64pub) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey RSAPublicKey = (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decodeBase64(base64pub)));
        return RSAPublicKey;
    }

    public static KeyPairString getRSAKeyPairString() {
        KeyPairString keyPairString = new KeyPairString(
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDkW2ePEH7tA6qW/w78FEyaoQZ0qqO16uf87qKIXfsfh36IFqRUJk2sig0LF6EFWVBx5gvxO7cwrfZPfZCpQXU1ce6rODU70UbFYZHxlfTNxWg+7xE+FTLgrJ8rvsg7We796wXDFruf1tu/Tzhu6+1thENe2WYmMI21xPBy1xBqfQIDAQAB", "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAORbZ48Qfu0Dqpb/DvwUTJqhBnSqo7Xq5/zuoohd+x+HfogWpFQmTayKDQsXoQVZUHHmC/E7tzCt9k99kKlBdTVx7qs4NTvRRsVhkfGV9M3FaD7vET4VMuCsnyu+yDtZ7v3rBcMWu5/W279POG7r7W2EQ17ZZiYwjbXE8HLXEGp9AgMBAAECgYA18SWIVPQ2lqwgWQW4JC9oQ2tb+ccMPP4p8FDyd4jPjNkl2ntc6WNlw4KLQTynH87x6M6fYGTImf9aDaYzZSQDVriE15nc/nfRas5ray2xWZG7c/fVABgxp/tZ3NDGYAvIDD48HyCqAVwM9S1n+7KxMGDzAxvRSnbX7TDoG81kYQJBAPLoRm5ZGXdd4J7Ab8xBrWs6YgEEYLj+QUhtrBeEDM5CmfB5GhzVuzRdcY3Ta1LBApPv0u3cCeAjtnyHxkVKbcUCQQDwqlv9OhFQMH+DzQWbLlSR45CJY3Ml6dDqPL7379fQrV1Y4otJjrHI8Igsviz4fqN5vdYJBtaK2fmo70JHPE1ZAkAv4STw31Mrui9qW1guFMwQFMP4m1pNXKCVnDiNshOsHGing9dn06FA4yJc6E2BVFYjvuP+yp6PtbpktYaZZeBZAkBCYAns5kX+ItIYbMQ+D2IdtnCXo8kPTNRktgpJYWnZUOcs65ZU3DkmXUiBZpYKlwrJOdU9OhiTxRh5Qju6X+RJAkEAqROeAcjiQaIBlWd3iHMPT+CaUN25SFrXkAmcx1Oo5I6HUgo38X8gj6ngu42oBzs1WOezEJtxW6IWM5tYiODA3w=="
        );
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            keyPairString = new KeyPairString(Base64.encodeBase64String(publicKey.getEncoded()), Base64.encodeBase64String(privateKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }
        return keyPairString;
    }


    public static byte[] decrypt(RSAPrivateKey RSAPrivateKey, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance(RSAPrivateKey.getAlgorithm());
        c.init(Cipher.DECRYPT_MODE, RSAPrivateKey);
        byte[] alldata = null;
        for (int i = 0; i < data.length; i += 128) {
            byte[] doFinal = c.doFinal(ArrayUtils.subarray(data, i,
                    i + 128));
            alldata = ArrayUtils.addAll(alldata, doFinal);
        }
        return alldata;
    }

    public static byte[] encrypt(RSAPublicKey RSAPublicKey, byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance(RSAPublicKey.getAlgorithm());
        c.init(Cipher.ENCRYPT_MODE, RSAPublicKey);
        byte[] alldata = null;
        for (int i = 0; i < data.length; i += 117) {
            byte[] doFinal = c.doFinal(ArrayUtils.subarray(data, i,
                    i + 117));
            alldata = ArrayUtils.addAll(alldata, doFinal);
        }
        return alldata;
    }

    public static String encrypt(String base64pub, String msg) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        return Base64.encodeBase64String(encrypt(getRSAPublicKeyFromString(base64pub), msg.getBytes(Charsets.UTF_8)));
    }

    public static String decrypt(String base64pri, String code) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        return new String(decrypt(getRSAPrivateKeyFromString(base64pri), Base64.decodeBase64(code)), Charsets.UTF_8);
    }

    public static class KeyPairString {
        private String publicKey;
        private String privateKey;

        private KeyPairString(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }
    }
}
