package com.mran.nmusic.net.qqmusic.jsonadapter;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/10/11.
 */
public class QQmusicUrlPreJson {
    /**
     * code : 0
     * sip : ["http://cc.stream.qqmusic.qq.com/","http://ws.stream.qqmusic.qq.com/","http://119.84.85.22/streamoc.music.tc.qq.com/","http://dl.stream.qqmusic.qq.com/"]
     * thirdip : ["http://thirdparty.gtimg.com/abcd1234/","http://thirdparty.gtimg.com/abcd1234/"]
     * key : B6491C9C4926134F4DE6B3CB406149FB81BBA45483D946CC39744C2323E8037988AE413D3312F9775D5483F0EF7833BD734841A66D3B9252
     */

    private int code;
    private String key;
    private List<String> sip;
    private List<String> thirdip;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getSip() {
        return sip;
    }

    public void setSip(List<String> sip) {
        this.sip = sip;
    }

    public List<String> getThirdip() {
        return thirdip;
    }

    public void setThirdip(List<String> thirdip) {
        this.thirdip = thirdip;
    }
}
