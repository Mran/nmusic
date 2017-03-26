package com.mran.nmusic.net.cloudmusic.jsonadapter;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/8/21.
 */
public class MusicUrlJson {
    /**
     * data : [{"id":108597,"url":"http://m10.music.126.net/20160821232336/dab5430d3fd84c467599a74d862ef487/ymusic/6920/dc64/1812/d6ef0ceb224a4a322b111d8720b8c454.mp3","br":128000,"size":3761303,"md5":"d6ef0ceb224a4a322b111d8720b8c454","code":200,"expi":1200,"type":"mp3","gain":0.2032,"fee":8,"uf":null,"payed":0,"flag":0,"canExtend":false}]
     * code : 200
     */

    private int code;
    /**
     * id : 108597
     * url : http://m10.music.126.net/20160821232336/dab5430d3fd84c467599a74d862ef487/ymusic/6920/dc64/1812/d6ef0ceb224a4a322b111d8720b8c454.mp3
     * br : 128000
     * size : 3761303
     * md5 : d6ef0ceb224a4a322b111d8720b8c454
     * code : 200
     * expi : 1200
     * type : mp3
     * gain : 0.2032
     * fee : 8
     * uf : null
     * payed : 0
     * flag : 0
     * canExtend : false
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String url;
        private int br;
        private int size;
        private String md5;
        private int code;
        private int expi;
        private String type;
        private double gain;
        private int fee;
        private Object uf;
        private int payed;
        private int flag;
        private boolean canExtend;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getBr() {
            return br;
        }

        public void setBr(int br) {
            this.br = br;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getExpi() {
            return expi;
        }

        public void setExpi(int expi) {
            this.expi = expi;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getGain() {
            return gain;
        }

        public void setGain(double gain) {
            this.gain = gain;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public Object getUf() {
            return uf;
        }

        public void setUf(Object uf) {
            this.uf = uf;
        }

        public int getPayed() {
            return payed;
        }

        public void setPayed(int payed) {
            this.payed = payed;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public boolean isCanExtend() {
            return canExtend;
        }

        public void setCanExtend(boolean canExtend) {
            this.canExtend = canExtend;
        }
    }
}
