package com.mran.nmusic.net.xiamimusic.jsonadapter;

/**
 * Created by 张孟尧 on 2016/8/22.
 */
public class XiamiArtistDetailJson {
    /**
     * state : 0
     * message :
     * request_id : 0ab51f3b14718695208696156e
     * data : {"artist_id":665494514,"company":"","area":"China 中国大陆","gender":"B","category":3,"logo":"http://img.xiami.net/images/artistlogo/34/13654945138934_1.jpg","english_name":"","albums_count":11,"recommends":5,"artist_name":"黄昌平2洛天依","play_count":113637}
     */

    private int state;
    private String message;
    private String request_id;
    /**
     * artist_id : 665494514
     * company :
     * area : China 中国大陆
     * gender : B
     * category : 3
     * logo : http://img.xiami.net/images/artistlogo/34/13654945138934_1.jpg
     * english_name :
     * albums_count : 11
     * recommends : 5
     * artist_name : 黄昌平2洛天依
     * play_count : 113637
     */

    private DataBean data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int artist_id;
        private String company;
        private String area;
        private String gender;
        private int category;
        private String logo;
        private String english_name;
        private int albums_count;
        private int recommends;
        private String artist_name;
        private int play_count;

        public int getArtist_id() {
            return artist_id;
        }

        public void setArtist_id(int artist_id) {
            this.artist_id = artist_id;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getEnglish_name() {
            return english_name;
        }

        public void setEnglish_name(String english_name) {
            this.english_name = english_name;
        }

        public int getAlbums_count() {
            return albums_count;
        }

        public void setAlbums_count(int albums_count) {
            this.albums_count = albums_count;
        }

        public int getRecommends() {
            return recommends;
        }

        public void setRecommends(int recommends) {
            this.recommends = recommends;
        }

        public String getArtist_name() {
            return artist_name;
        }

        public void setArtist_name(String artist_name) {
            this.artist_name = artist_name;
        }

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }
    }
}
