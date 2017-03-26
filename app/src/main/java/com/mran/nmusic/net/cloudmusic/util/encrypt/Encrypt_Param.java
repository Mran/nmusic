package com.mran.nmusic.net.cloudmusic.util.encrypt;

import android.support.v4.util.ArrayMap;

import com.google.gson.JsonObject;
import com.mran.nmusic.net.cloudmusic.util.encrypt.aes.AesEncrypt;
import com.mran.nmusic.net.cloudmusic.util.encrypt.rsa.RsaEncrypt;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张孟尧 on 2016/8/17.
 */
public class Encrypt_Param {
    private static String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
    private static String nonce = "0CoJUm6Qyw8W8jud";
    private static String pubKey = "010001";

    public static Map<String, String> encrypt(JsonObject jsonObject) {
        String text=jsonObject.toString();
        text=text.replace("s\":","s\":[").replace(",\"br","],\"br");
//        System.out.println(text);
//        String text="{\"ids\":[\"108597\"],\"br\":320000,\"csrf_token\":\"\"}";
        char[] sec_key = Encrypt_Param.createSecretKey(16);
        String encText = AesEncrypt.encrypt(AesEncrypt.encrypt(text, nonce.toCharArray()), sec_key);
        String encSecKey= RsaEncrypt.encrypt(String.valueOf(sec_key),pubKey,modulus);
        Map<String,String> rdata=new ArrayMap<>();
        rdata.put("params",encText);
        rdata.put("encSecKey",encSecKey);
        return rdata;
    }


    public static char[] createSecretKey(int size) {
        char[] choice = {'0', '1', '2', '3', '4', '5', '6', '7', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] result = new char[16];
        for (int i = 0; i < size; i++) {
            double index = Math.floor(Math.random() * choice.length);
            result[i] = choice[((int) index)];
        }
        return result;
    }
}
