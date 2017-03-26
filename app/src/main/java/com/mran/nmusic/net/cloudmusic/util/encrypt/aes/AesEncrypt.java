package com.mran.nmusic.net.cloudmusic.util.encrypt.aes;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Created by 张孟尧 on 2016/8/17.
 */
public class AesEncrypt {
    public static int[] convertStringToBytes(String text) {

        String tmp = null;
        try {
            tmp = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int[] result = new int[text.length()];
        int i = 0;
        for (int j = 0; j < text.length(); ) {
            int c = Character.codePointAt(tmp, i++);
            if (c == 37) {
                result[j] = (byte) Integer.parseInt(tmp.substring(i, i + 2), 16);
                j++;
                i += 2;
            } else {
                result[j] = (byte) c;
                j++;
            }
        }
        return result;
    }

    public static String encrypt(String text, char[] secKey) {
        int pad = 16 - text.length() % 16;
        StringBuilder builder = new StringBuilder(pad);
        builder.append(text);
        for (int i = 0; i < pad; i++) {
            builder.append(Character.toChars(pad));
        }
        String texto = builder.toString();
        int[] key = AesEncrypt.convertStringToBytes(String.valueOf(secKey));
        int[] iv = AesEncrypt.convertStringToBytes("0102030405060708");
        int[] textBytes = AesEncrypt.convertStringToBytes(texto);
        int[] ivs = new int[iv.length];
        for (int i = 0; i < iv.length; i++) {
            ivs[i] = iv[i];
        }
        int[] block=new int[16];
        int[] cipherArry = new int[0];
        AesCbc aesCbc = new AesCbc(key, ivs);
        while (textBytes.length != 0) {
            try {
                block = aesCbc.encrypt(slice(textBytes,0,16));
            } catch (Exception e) {
                e.printStackTrace();
            }
            cipherArry=byteMerger(cipherArry,block);
            textBytes=slice(textBytes,16,textBytes.length);
        }

        char[] ciphertext=new char[cipherArry.length];
        for (int i = 0; i < cipherArry.length; i++) {
            ciphertext[i]=(char)cipherArry[i];
        }
return Base64.encodeToString(String.valueOf(ciphertext).getBytes(Charset.forName("ISO-8859-1")),Base64.DEFAULT);
//        return Base64.getEncoder().encodeToString(String.valueOf(ciphertext).getBytes(Charset.forName("ISO-8859-1")));


    }

    public static int[] byteMerger(int[] byte_1, int[] byte_2){
        int[] byte_3 = new int[byte_1.length+byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }
    public static int[] slice(int[] text,int start,int end)
    {
        int k=end;
        if (end>text.length)
        {
            k=text.length;
        }
        int[] result=new int[k-start];

        for (int i = 0; i < k-start; i++) {
            result[i]=text[start+i];
        }
        return result;
    }
}
