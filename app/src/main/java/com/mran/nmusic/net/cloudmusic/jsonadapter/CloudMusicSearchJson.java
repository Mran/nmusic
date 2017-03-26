package com.mran.nmusic.net.cloudmusic.jsonadapter;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/8/13.
 */
public class CloudMusicSearchJson {


    private ResultBean result;


    private int code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class ResultBean {
        private int songCount;


        private List<SongsBean> songs;

        public int getSongCount() {
            return songCount;
        }

        public void setSongCount(int songCount) {
            this.songCount = songCount;
        }

        public List<SongsBean> getSongs() {
            return songs;
        }

        public void setSongs(List<SongsBean> songs) {
            this.songs = songs;
        }

        public static class SongsBean {
            private boolean starred;
            private double popularity;
            private int starredNum;
            private int playedNum;
            private int dayPlays;
            private int hearTime;
            private String mp3Url;
            private Object audition;
            private Object ringtone;
            private String disc;
            private int no;
            private String commentThreadId;


            private AlbumBean album;
            private int mvid;
            private int fee;
            private int ftype;
            private int rtype;
            private Object rurl;
            private String copyFrom;
            private Object rtUrl;
            /**
             * playTime : 298000
             * dfsId : 1373290036678160
             * sr : 44100
             * bitrate : 96000
             * volumeDelta : -2.6
             * name : null
             * id : 1222186575
             * size : 3576730
             * extension : mp3
             */

            private BMusicBean bMusic;
            private Object crbt;
            private int status;
            private int copyrightId;
            private int score;


            private HMusicBean hMusic;


            private MMusicBean mMusic;


            private LMusicBean lMusic;
            private int position;
            private int duration;
            private String name;
            private int id;
            private List<?> rtUrls;
            /**
             * img1v1Id : 0
             * briefDesc :
             * albumSize : 0
             * img1v1Url : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
             * trans :
             * picUrl : http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
             * picId : 0
             * musicSize : 0
             * alias : []
             * name : 洛天依
             * id : 906118
             */

            private List<ArtistsBean> artists;
            private List<?> alias;

            public boolean isStarred() {
                return starred;
            }

            public void setStarred(boolean starred) {
                this.starred = starred;
            }

            public double getPopularity() {
                return popularity;
            }

            public void setPopularity(double popularity) {
                this.popularity = popularity;
            }

            public int getStarredNum() {
                return starredNum;
            }

            public void setStarredNum(int starredNum) {
                this.starredNum = starredNum;
            }

            public int getPlayedNum() {
                return playedNum;
            }

            public void setPlayedNum(int playedNum) {
                this.playedNum = playedNum;
            }

            public int getDayPlays() {
                return dayPlays;
            }

            public void setDayPlays(int dayPlays) {
                this.dayPlays = dayPlays;
            }

            public int getHearTime() {
                return hearTime;
            }

            public void setHearTime(int hearTime) {
                this.hearTime = hearTime;
            }

            public String getMp3Url() {
                return mp3Url;
            }

            public void setMp3Url(String mp3Url) {
                this.mp3Url = mp3Url;
            }

            public Object getAudition() {
                return audition;
            }

            public void setAudition(Object audition) {
                this.audition = audition;
            }

            public Object getRingtone() {
                return ringtone;
            }

            public void setRingtone(Object ringtone) {
                this.ringtone = ringtone;
            }

            public String getDisc() {
                return disc;
            }

            public void setDisc(String disc) {
                this.disc = disc;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public String getCommentThreadId() {
                return commentThreadId;
            }

            public void setCommentThreadId(String commentThreadId) {
                this.commentThreadId = commentThreadId;
            }

            public AlbumBean getAlbum() {
                return album;
            }

            public void setAlbum(AlbumBean album) {
                this.album = album;
            }

            public int getMvid() {
                return mvid;
            }

            public void setMvid(int mvid) {
                this.mvid = mvid;
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

            public int getRtype() {
                return rtype;
            }

            public void setRtype(int rtype) {
                this.rtype = rtype;
            }

            public Object getRurl() {
                return rurl;
            }

            public void setRurl(Object rurl) {
                this.rurl = rurl;
            }

            public String getCopyFrom() {
                return copyFrom;
            }

            public void setCopyFrom(String copyFrom) {
                this.copyFrom = copyFrom;
            }

            public Object getRtUrl() {
                return rtUrl;
            }

            public void setRtUrl(Object rtUrl) {
                this.rtUrl = rtUrl;
            }

            public BMusicBean getBMusic() {
                return bMusic;
            }

            public void setBMusic(BMusicBean bMusic) {
                this.bMusic = bMusic;
            }

            public Object getCrbt() {
                return crbt;
            }

            public void setCrbt(Object crbt) {
                this.crbt = crbt;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCopyrightId() {
                return copyrightId;
            }

            public void setCopyrightId(int copyrightId) {
                this.copyrightId = copyrightId;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public HMusicBean getHMusic() {
                return hMusic;
            }

            public void setHMusic(HMusicBean hMusic) {
                this.hMusic = hMusic;
            }

            public MMusicBean getMMusic() {
                return mMusic;
            }

            public void setMMusic(MMusicBean mMusic) {
                this.mMusic = mMusic;
            }

            public LMusicBean getLMusic() {
                return lMusic;
            }

            public void setLMusic(LMusicBean lMusic) {
                this.lMusic = lMusic;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
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

            public List<?> getRtUrls() {
                return rtUrls;
            }

            public void setRtUrls(List<?> rtUrls) {
                this.rtUrls = rtUrls;
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
                private boolean paid;
                private boolean onSale;
                private String commentThreadId;
                /**
                 * img1v1Id : 0
                 * briefDesc :
                 * albumSize : 0
                 * img1v1Url : http://p4.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * trans :
                 * picUrl : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * picId : 0
                 * musicSize : 0
                 * alias : []
                 * name :
                 * id : 0
                 */

                private ArtistBean artist;
                private String briefDesc;
                private long publishTime;
                private int companyId;
                private String company;
                private long pic;
                private String picUrl;
                private String blurPicUrl;
                private String tags;
                private int status;
                private int copyrightId;
                private long picId;
                private String description;
                private Object subType;
                private String name;
                private int id;
                private Object type;
                private int size;
                private List<?> songs;
                /**
                 * img1v1Id : 0
                 * briefDesc :
                 * albumSize : 0
                 * img1v1Url : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * trans :
                 * picUrl : http://p3.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg
                 * picId : 0
                 * musicSize : 0
                 * alias : []
                 * name : 洛天依
                 * id : 906118
                 */

                private List<ArtistsBean> artists;
                private List<?> alias;

                public boolean isPaid() {
                    return paid;
                }

                public void setPaid(boolean paid) {
                    this.paid = paid;
                }

                public boolean isOnSale() {
                    return onSale;
                }

                public void setOnSale(boolean onSale) {
                    this.onSale = onSale;
                }

                public String getCommentThreadId() {
                    return commentThreadId;
                }

                public void setCommentThreadId(String commentThreadId) {
                    this.commentThreadId = commentThreadId;
                }

                public ArtistBean getArtist() {
                    return artist;
                }

                public void setArtist(ArtistBean artist) {
                    this.artist = artist;
                }

                public String getBriefDesc() {
                    return briefDesc;
                }

                public void setBriefDesc(String briefDesc) {
                    this.briefDesc = briefDesc;
                }

                public long getPublishTime() {
                    return publishTime;
                }

                public void setPublishTime(long publishTime) {
                    this.publishTime = publishTime;
                }

                public int getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(int companyId) {
                    this.companyId = companyId;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public long getPic() {
                    return pic;
                }

                public void setPic(long pic) {
                    this.pic = pic;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public String getBlurPicUrl() {
                    return blurPicUrl;
                }

                public void setBlurPicUrl(String blurPicUrl) {
                    this.blurPicUrl = blurPicUrl;
                }

                public String getTags() {
                    return tags;
                }

                public void setTags(String tags) {
                    this.tags = tags;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getCopyrightId() {
                    return copyrightId;
                }

                public void setCopyrightId(int copyrightId) {
                    this.copyrightId = copyrightId;
                }

                public long getPicId() {
                    return picId;
                }

                public void setPicId(long picId) {
                    this.picId = picId;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public Object getSubType() {
                    return subType;
                }

                public void setSubType(Object subType) {
                    this.subType = subType;
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

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public List<?> getSongs() {
                    return songs;
                }

                public void setSongs(List<?> songs) {
                    this.songs = songs;
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

                public static class ArtistBean {
                    private String img1v1Id;
                    private String briefDesc;
                    private int albumSize;
                    private String img1v1Url;
                    private String trans;
                    private String picUrl;
                    private int picId;
                    private int musicSize;
                    private String name;
                    private int id;
                    private List<?> alias;

                    public String getImg1v1Id() {
                        return img1v1Id;
                    }

                    public void setImg1v1Id(String img1v1Id) {
                        this.img1v1Id = img1v1Id;
                    }

                    public String getBriefDesc() {
                        return briefDesc;
                    }

                    public void setBriefDesc(String briefDesc) {
                        this.briefDesc = briefDesc;
                    }

                    public int getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(int albumSize) {
                        this.albumSize = albumSize;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public String getTrans() {
                        return trans;
                    }

                    public void setTrans(String trans) {
                        this.trans = trans;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public int getPicId() {
                        return picId;
                    }

                    public void setPicId(int picId) {
                        this.picId = picId;
                    }

                    public int getMusicSize() {
                        return musicSize;
                    }

                    public void setMusicSize(int musicSize) {
                        this.musicSize = musicSize;
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

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }
                }

                public static class ArtistsBean {
                    private String img1v1Id;
                    private String briefDesc;
                    private int albumSize;
                    private String img1v1Url;
                    private String trans;
                    private String picUrl;
                    private int picId;
                    private int musicSize;
                    private String name;
                    private int id;
                    private List<?> alias;

                    public String getImg1v1Id() {
                        return img1v1Id;
                    }

                    public void setImg1v1Id(String img1v1Id) {
                        this.img1v1Id = img1v1Id;
                    }

                    public String getBriefDesc() {
                        return briefDesc;
                    }

                    public void setBriefDesc(String briefDesc) {
                        this.briefDesc = briefDesc;
                    }

                    public int getAlbumSize() {
                        return albumSize;
                    }

                    public void setAlbumSize(int albumSize) {
                        this.albumSize = albumSize;
                    }

                    public String getImg1v1Url() {
                        return img1v1Url;
                    }

                    public void setImg1v1Url(String img1v1Url) {
                        this.img1v1Url = img1v1Url;
                    }

                    public String getTrans() {
                        return trans;
                    }

                    public void setTrans(String trans) {
                        this.trans = trans;
                    }

                    public String getPicUrl() {
                        return picUrl;
                    }

                    public void setPicUrl(String picUrl) {
                        this.picUrl = picUrl;
                    }

                    public int getPicId() {
                        return picId;
                    }

                    public void setPicId(int picId) {
                        this.picId = picId;
                    }

                    public int getMusicSize() {
                        return musicSize;
                    }

                    public void setMusicSize(int musicSize) {
                        this.musicSize = musicSize;
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

                    public List<?> getAlias() {
                        return alias;
                    }

                    public void setAlias(List<?> alias) {
                        this.alias = alias;
                    }
                }
            }

            public static class BMusicBean {
                private int playTime;
                private long dfsId;
                private int sr;
                private int bitrate;
                private double volumeDelta;
                private Object name;
                private int id;
                private int size;
                private String extension;

                public int getPlayTime() {
                    return playTime;
                }

                public void setPlayTime(int playTime) {
                    this.playTime = playTime;
                }

                public long getDfsId() {
                    return dfsId;
                }

                public void setDfsId(long dfsId) {
                    this.dfsId = dfsId;
                }

                public int getSr() {
                    return sr;
                }

                public void setSr(int sr) {
                    this.sr = sr;
                }

                public int getBitrate() {
                    return bitrate;
                }

                public void setBitrate(int bitrate) {
                    this.bitrate = bitrate;
                }

                public double getVolumeDelta() {
                    return volumeDelta;
                }

                public void setVolumeDelta(double volumeDelta) {
                    this.volumeDelta = volumeDelta;
                }

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getExtension() {
                    return extension;
                }

                public void setExtension(String extension) {
                    this.extension = extension;
                }
            }

            public static class HMusicBean {
                private int playTime;
                private long dfsId;
                private int sr;
                private int bitrate;
                private double volumeDelta;
                private Object name;
                private int id;
                private int size;
                private String extension;

                public int getPlayTime() {
                    return playTime;
                }

                public void setPlayTime(int playTime) {
                    this.playTime = playTime;
                }

                public long getDfsId() {
                    return dfsId;
                }

                public void setDfsId(long dfsId) {
                    this.dfsId = dfsId;
                }

                public int getSr() {
                    return sr;
                }

                public void setSr(int sr) {
                    this.sr = sr;
                }

                public int getBitrate() {
                    return bitrate;
                }

                public void setBitrate(int bitrate) {
                    this.bitrate = bitrate;
                }

                public double getVolumeDelta() {
                    return volumeDelta;
                }

                public void setVolumeDelta(double volumeDelta) {
                    this.volumeDelta = volumeDelta;
                }

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getExtension() {
                    return extension;
                }

                public void setExtension(String extension) {
                    this.extension = extension;
                }
            }

            public static class MMusicBean {
                private int playTime;
                private long dfsId;
                private int sr;
                private int bitrate;
                private double volumeDelta;
                private Object name;
                private int id;
                private int size;
                private String extension;

                public int getPlayTime() {
                    return playTime;
                }

                public void setPlayTime(int playTime) {
                    this.playTime = playTime;
                }

                public long getDfsId() {
                    return dfsId;
                }

                public void setDfsId(long dfsId) {
                    this.dfsId = dfsId;
                }

                public int getSr() {
                    return sr;
                }

                public void setSr(int sr) {
                    this.sr = sr;
                }

                public int getBitrate() {
                    return bitrate;
                }

                public void setBitrate(int bitrate) {
                    this.bitrate = bitrate;
                }

                public double getVolumeDelta() {
                    return volumeDelta;
                }

                public void setVolumeDelta(double volumeDelta) {
                    this.volumeDelta = volumeDelta;
                }

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getExtension() {
                    return extension;
                }

                public void setExtension(String extension) {
                    this.extension = extension;
                }
            }

            public static class LMusicBean {
                private int playTime;
                private long dfsId;
                private int sr;
                private int bitrate;
                private double volumeDelta;
                private Object name;
                private int id;
                private int size;
                private String extension;

                public int getPlayTime() {
                    return playTime;
                }

                public void setPlayTime(int playTime) {
                    this.playTime = playTime;
                }

                public long getDfsId() {
                    return dfsId;
                }

                public void setDfsId(long dfsId) {
                    this.dfsId = dfsId;
                }

                public int getSr() {
                    return sr;
                }

                public void setSr(int sr) {
                    this.sr = sr;
                }

                public int getBitrate() {
                    return bitrate;
                }

                public void setBitrate(int bitrate) {
                    this.bitrate = bitrate;
                }

                public double getVolumeDelta() {
                    return volumeDelta;
                }

                public void setVolumeDelta(double volumeDelta) {
                    this.volumeDelta = volumeDelta;
                }

                public Object getName() {
                    return name;
                }

                public void setName(Object name) {
                    this.name = name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getExtension() {
                    return extension;
                }

                public void setExtension(String extension) {
                    this.extension = extension;
                }
            }

            public static class ArtistsBean {
                private String img1v1Id;
                private String briefDesc;
                private int albumSize;
                private String img1v1Url;
                private String trans;
                private String picUrl;
                private int picId;
                private int musicSize;
                private String name;
                private int id;
                private List<?> alias;

                public String getImg1v1Id() {
                    return img1v1Id;
                }

                public void setImg1v1Id(String img1v1Id) {
                    this.img1v1Id = img1v1Id;
                }

                public String getBriefDesc() {
                    return briefDesc;
                }

                public void setBriefDesc(String briefDesc) {
                    this.briefDesc = briefDesc;
                }

                public int getAlbumSize() {
                    return albumSize;
                }

                public void setAlbumSize(int albumSize) {
                    this.albumSize = albumSize;
                }

                public String getImg1v1Url() {
                    return img1v1Url;
                }

                public void setImg1v1Url(String img1v1Url) {
                    this.img1v1Url = img1v1Url;
                }

                public String getTrans() {
                    return trans;
                }

                public void setTrans(String trans) {
                    this.trans = trans;
                }

                public String getPicUrl() {
                    return picUrl;
                }

                public void setPicUrl(String picUrl) {
                    this.picUrl = picUrl;
                }

                public int getPicId() {
                    return picId;
                }

                public void setPicId(int picId) {
                    this.picId = picId;
                }

                public int getMusicSize() {
                    return musicSize;
                }

                public void setMusicSize(int musicSize) {
                    this.musicSize = musicSize;
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

                public List<?> getAlias() {
                    return alias;
                }

                public void setAlias(List<?> alias) {
                    this.alias = alias;
                }
            }
        }
    }
}
