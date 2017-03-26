package com.mran.nmusic.net.cloudmusic.jsonadapter;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/8/13.
 */
public class CloudMusicListDetailJson {
    /**
     * status : 0
     * album : {"id":3286141,"name":"半壶纱","picUrl":"http://p4.music.126.net/SKujq5vqqv4KYOVFk7SiRA==/3407386538630284.jpg","pic_str":"3407386538630284","pic":3407386538630284}
     * artists : [{"id":918054,"name":"刘珂矣"}]
     * privilege : {"id":28793140,"fee":0,"payed":0,"st":0,"pl":320000,"dl":320000,"sp":7,"cp":1,"subp":1,"cs":false,"maxbr":999000,"fl":320000,"toast":false,"flag":0}
     * djid : 0
     * alias : []
     * transNames : null
     * commentThreadId : R_SO_4_28793140
     * score : 100
     * copyrightId : 30021
     * mvid : 5269013
     * type : 0
     * duration : 221994
     * fee : 0
     * ftype : 0
     * no : 2
     * name : 半壶纱
     * id : 28793140
     */

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private int status;
        /**
         * id : 3286141
         * name : 半壶纱
         * picUrl : http://p4.music.126.net/SKujq5vqqv4KYOVFk7SiRA==/3407386538630284.jpg
         * pic_str : 3407386538630284
         * pic : 3407386538630284
         */

        private AlbumBean album;
        /**
         * id : 28793140
         * fee : 0
         * payed : 0
         * st : 0
         * pl : 320000
         * dl : 320000
         * sp : 7
         * cp : 1
         * subp : 1
         * cs : false
         * maxbr : 999000
         * fl : 320000
         * toast : false
         * flag : 0
         */

        private PrivilegeBean privilege;
        private int djid;
        private Object transNames;
        private String commentThreadId;
        private int score;
        private int copyrightId;
        private int mvid;
        private int type;
        private int duration;
        private int fee;
        private int ftype;
        private int no;
        private String name;
        private int id;
        /**
         * id : 918054
         * name : 刘珂矣
         */

        private List<ArtistsBean> artists;
        private List<?> alias;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public PrivilegeBean getPrivilege() {
            return privilege;
        }

        public void setPrivilege(PrivilegeBean privilege) {
            this.privilege = privilege;
        }

        public int getDjid() {
            return djid;
        }

        public void setDjid(int djid) {
            this.djid = djid;
        }

        public Object getTransNames() {
            return transNames;
        }

        public void setTransNames(Object transNames) {
            this.transNames = transNames;
        }

        public String getCommentThreadId() {
            return commentThreadId;
        }

        public void setCommentThreadId(String commentThreadId) {
            this.commentThreadId = commentThreadId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getCopyrightId() {
            return copyrightId;
        }

        public void setCopyrightId(int copyrightId) {
            this.copyrightId = copyrightId;
        }

        public int getMvid() {
            return mvid;
        }

        public void setMvid(int mvid) {
            this.mvid = mvid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getFtype() {
            return ftype;
        }

        public void setFtype(int ftype) {
            this.ftype = ftype;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<ArtistsBean> getArtists() {
            return artists;
        }

        public void setArtists(List<ArtistsBean> artists) {
            this.artists = artists;
        }

        public List<?> getAlias() {
            return alias;
        }

        public void setAlias(List<?> alias) {
            this.alias = alias;
        }

        public static class AlbumBean {
            private int id;
            private String name;
            private String picUrl;
            private String pic_str;
            private long pic;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }

            public String getPic_str() {
                return pic_str;
            }

            public void setPic_str(String pic_str) {
                this.pic_str = pic_str;
            }

            public long getPic() {
                return pic;
            }

            public void setPic(long pic) {
                this.pic = pic;
            }
        }

        public static class PrivilegeBean {
            private int id;
            private int fee;
            private int payed;
            private int st;
            private int pl;
            private int dl;
            private int sp;
            private int cp;
            private int subp;
            private boolean cs;
            private int maxbr;
            private int fl;
            private boolean toast;
            private int flag;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }

            public int getPayed() {
                return payed;
            }

            public void setPayed(int payed) {
                this.payed = payed;
            }

            public int getSt() {
                return st;
            }

            public void setSt(int st) {
                this.st = st;
            }

            public int getPl() {
                return pl;
            }

            public void setPl(int pl) {
                this.pl = pl;
            }

            public int getDl() {
                return dl;
            }

            public void setDl(int dl) {
                this.dl = dl;
            }

            public int getSp() {
                return sp;
            }

            public void setSp(int sp) {
                this.sp = sp;
            }

            public int getCp() {
                return cp;
            }

            public void setCp(int cp) {
                this.cp = cp;
            }

            public int getSubp() {
                return subp;
            }

            public void setSubp(int subp) {
                this.subp = subp;
            }

            public boolean isCs() {
                return cs;
            }

            public void setCs(boolean cs) {
                this.cs = cs;
            }

            public int getMaxbr() {
                return maxbr;
            }

            public void setMaxbr(int maxbr) {
                this.maxbr = maxbr;
            }

            public int getFl() {
                return fl;
            }

            public void setFl(int fl) {
                this.fl = fl;
            }

            public boolean isToast() {
                return toast;
            }

            public void setToast(boolean toast) {
                this.toast = toast;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }
        }

        public static class ArtistsBean {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}