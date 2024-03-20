package com.yl.spring.simpleBeanContainer;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yl
 */
public class RSAUtil {

    //公钥，可以写前端
    public static String public_key="";

    //私钥，只能放后端
    public static String private_key="MIICXQIBAAKBgQDClYvmnVePg1TbftTk4g6nVANFZRwojHkJX3TCi3n3IoMxujKO\n" +
            "oklnJqUZ29ypKpkGpR2vqWQzoAEC8QnF2ejoOeB1bpLC+BVKNqvyKLWbRhL8jL8+\n" +
            "hdo2AwJDEFfLYEA0qAh2VNtdFOGK84f/w1gxeC4c6ATtUdlsTVt2/FFsxQIDAQAB\n" +
            "AoGBAKFUnn7hW5S4neWy3c67gszHQkMKFvOjrELSKxuVytTVf5L+Pya1dUiZ6O9s\n" +
            "tCJqGCBDTD61okePVOdcc+7fwlM+Uj6frZyVGRG7yQ5d7L8KqZZP9Naem4pF2i0j\n" +
            "mNjC3bF+xWZgqw2Wn0/rbO19wrdI09xhppj5OF730s68RFEtAkEA5YzGr/+IFjYu\n" +
            "9fyPLj6TT/Wq8cGGn3i28R3rA7CaA4eraoo4jsqQbpI4Y0mMI/vbiJ4uBk9+WFNE\n" +
            "/ety8rh1VwJBANkBW195w5VKU/CwBGmYca6Qr3i5jU/hj38ZQwFED1L5xIB/j02X\n" +
            "B2k2tAX/9y7yefX5Q3d1boX1Pvveb/9ZoUMCQB7z1ZLDzRDZq4llzdS5gVml0bAZ\n" +
            "nVKBj4GBvD/aH1pcf+O00Z2HeOQ/+TSCJVBw1JBea+xT6cB1MfdfOTe7Ya8CQAVb\n" +
            "hTf8N5nt9diRX+qDevsGU261lgmM1hAbPY5AcehohjNt8snFmsc8NIGZai7ciapK\n" +
            "9yFx4sKn5WAug7FYMyUCQQCyZcSv3Z+4FA4L9VRGNYWfz79NLQ0GrrERkCgFUkcC\n" +
            "aKgWgALdONJiAu4KLq3MwsrNE0pqpTBm23jfZ5DhNIRT";


    public static void main(String[] args) {
        //解密数据
        try {
            //生成公钥和私钥
            genKeyPair();
            String publicKey = keyMap.get(0);
            //打印出来自己记录下
            System.out.println("公钥:" + publicKey);
            String privateKey = keyMap.get(1);
            //打印出来自己记录下
            System.out.println("私钥:" + privateKey);



            //获取到后，可以放这里，测试下能不能正确加解密
//            publicKey = public_key;
//            privateKey = private_key;

            // '{"userId":1,"userName":"管理员","orgId":"0","staffCode":"admin","roleCode":"system","areaId":"0"}'

            String orgData = "{\"staff_name\":\"袁磊\",\"org_id\":\"1\",\"staff_id\":98,\"staff_code\":\"yuanlei\",\"role_code\":\"system\",\"area_id\":\"0\"}";
            //String orgData="{\"userId\":1,\"userName\":\"管理员\",\"orgId\":\"0\",\"staffCode\":\"admin\",\"roleCode\":\"system\",\"areaId\":\"0\"}";
            System.out.println("原数据：" + orgData);

            //加密
            String encryptStr =encrypt(orgData,publicKey);
            System.out.println("加密结果：" + encryptStr);

            //解密
            String decryptStr = decrypt(encryptStr,privateKey);
            System.out.println("解密结果：" + decryptStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserInfo getUserInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Info");
        if (!header.isEmpty()) {
            try {
                String json = decrypt(header, private_key);
                UserInfo userInfo = JSONObject.parseObject(json, UserInfo.class);
                return userInfo;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new UserInfo();
    }

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str,String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = decryptBASE64(publicKey);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = encryptBASE64(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = decryptBASE64(str);
        //base64编码的私钥
        byte[] decoded = decryptBASE64(privateKey);
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    //编码返回字符串
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    //解码返回byte
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * 密钥长度 于原文长度对应 以及越长速度越慢
     */
    private final static int KEY_SIZE = 1024;
    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();

    /**
     * 随机生成密钥对
     * @throws Exception
     */
    public static void genKeyPair() throws Exception {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器
        keyPairGen.initialize(KEY_SIZE, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = encryptBASE64(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = encryptBASE64(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        //0表示公钥
        keyMap.put(0, publicKeyString);
        //1表示私钥
        keyMap.put(1, privateKeyString);
    }
}
