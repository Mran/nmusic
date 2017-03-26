package com.mran.nmusic.net.cloudmusic.util.encrypt.rsa;


import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by 张孟尧 on 2016/8/18.
 */
public class RsaEncrypt {
    public static String encrypt(String text, String pubkey, String modulus) {
        String texts = new StringBuilder(text).reverse().toString();
        texts=hexify(texts);
        BigInteger bigInteger = new BigInteger(texts, 16);
        BigInteger bigInteger1 = bigInteger.modPow(BigInteger.valueOf(Integer.valueOf(pubkey, 16)),new BigInteger(modulus, 16));
        String result = bigInteger1.toString(16);
        return zfill(result);
    }

    private static String zfill(String a) {
        int padLegth = 256 - a.length();
        StringBuilder builder = new StringBuilder();
        char[] ss = new char[padLegth];
        Arrays.fill(ss, '0');
        return builder.append(String.valueOf(ss)).append(a).toString();
    }

    private static String hexify(String text)
    {
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int c=text.codePointAt(i);
            builder.append(Integer.toHexString(c).toString());
        }
        return builder.toString();

    }
}
