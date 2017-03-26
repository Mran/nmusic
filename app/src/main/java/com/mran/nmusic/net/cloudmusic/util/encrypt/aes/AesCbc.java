package com.mran.nmusic.net.cloudmusic.util.encrypt.aes;

/**
 * Created by 张孟尧 on 2016/8/17.
 */
public class AesCbc {
    private int[] lastCipherblock;
    private AES aes;

    public AesCbc(int[] key, int[] iv) {
        this.lastCipherblock = iv;
        this.aes = new AES(key);
    }

    public int[] encrypt(int[] text) throws Exception {
        if (text.length % 16 != 0)
            throw new Exception("invalid plaintext size (must be multiple of 16 bytes)");
        int[] ciphertext = new int[text.length];
        int[] block = new int[16];
        for (int i = 0; i < text.length; i += 16) {
            copy(text, block, 0, i, i + 16);
            for (int j = 0; j < 16; j++) {
                block[j] ^= lastCipherblock[j];
            }
            lastCipherblock = aes.encrypt(block);
            int[] lastCipherblocks = new int[lastCipherblock.length];
//            for (int k = 0; k < lastCipherblock.length; k++) {
//                lastCipherblocks[k] = (byte) lastCipherblock[k];
//            }
            copy(lastCipherblock, ciphertext, i, 0, 16);
        }
        return ciphertext;
    }

    private void copy(int[] source, int[] taget, int tagstart, int sourcestart, int sourceend) {
        for (int i = sourcestart; i < sourceend; i++) {
            taget[tagstart++] = source[i];
        }
    }

}
