package com.mran.nmusic.net.cloudmusic.jsonadapter;

import java.util.List;

/**
 * Created by 张孟尧 on 2016/8/13.
 */
public class CloudMusicUserSearchJson {
    /**
     * userprofiles : [{"mutual":false,"remarkName":null,"vipType":0,"nickname":"安小尧","avatarUrl":"http://p4.music.126.net/hw1NskbvWJkj9LH-QQHHcg==/3427177763926533.jpg","userId":31712644,"expertTags":null,"avatarImgId":3427177763926533,"backgroundImgId":2002210674180201,"province":410000,"city":410100,"birthday":631191529198,"gender":1,"defaultAvatar":false,"backgroundUrl":"http://p1.music.126.net/o3G7lWrGBQAvSRt3UuApTw==/2002210674180201.jpg","accountStatus":0,"userType":0,"authStatus":0,"followed":false,"djStatus":0,"detailDescription":"","signature":"","authority":0,"description":"","followeds":2,"follows":3,"eventCount":1,"playlistCount":12,"playlistBeSubscribedCount":0},{"mutual":false,"remarkName":null,"vipType":0,"nickname":"安小尧尧","avatarUrl":"http://p4.music.126.net/bTIoFKFaE1-JQpgN3OynGg==/1986817511391283.jpg","userId":35613125,"expertTags":null,"avatarImgId":1986817511391283,"backgroundImgId":2002210674180203,"province":140000,"city":140700,"birthday":-2209017600000,"gender":0,"defaultAvatar":true,"backgroundUrl":"http://p1.music.126.net/bmA_ablsXpq3Tk9HlEg9sA==/2002210674180203.jpg","accountStatus":0,"userType":0,"authStatus":0,"followed":false,"djStatus":0,"detailDescription":"","signature":"","authority":0,"description":"","followeds":0,"follows":7,"eventCount":0,"playlistCount":1,"playlistBeSubscribedCount":0},{"mutual":false,"remarkName":null,"vipType":0,"nickname":"安安静静的小小尧","avatarUrl":"http://p4.music.126.net/0Tu5kSdkGlTGe5sO0Vtjtw==/3289738790480030.jpg","userId":84107912,"expertTags":null,"avatarImgId":3289738790480030,"backgroundImgId":2002210674180203,"province":220000,"city":220200,"birthday":-2209017600000,"gender":1,"defaultAvatar":false,"backgroundUrl":"http://p1.music.126.net/bmA_ablsXpq3Tk9HlEg9sA==/2002210674180203.jpg","accountStatus":0,"userType":0,"authStatus":0,"followed":false,"djStatus":0,"detailDescription":"","signature":"\u2014\u2014 做好自己。。","authority":0,"description":"","followeds":1,"follows":0,"eventCount":0,"playlistCount":1,"playlistBeSubscribedCount":0}]
     * userprofileCount : 3
     */

    private ResultBean result;
    /**
     * result : {"userprofiles":[{"mutual":false,"remarkName":null,"vipType":0,"nickname":"安小尧","avatarUrl":"http://p4.music.126.net/hw1NskbvWJkj9LH-QQHHcg==/3427177763926533.jpg","userId":31712644,"expertTags":null,"avatarImgId":3427177763926533,"backgroundImgId":2002210674180201,"province":410000,"city":410100,"birthday":631191529198,"gender":1,"defaultAvatar":false,"backgroundUrl":"http://p1.music.126.net/o3G7lWrGBQAvSRt3UuApTw==/2002210674180201.jpg","accountStatus":0,"userType":0,"authStatus":0,"followed":false,"djStatus":0,"detailDescription":"","signature":"","authority":0,"description":"","followeds":2,"follows":3,"eventCount":1,"playlistCount":12,"playlistBeSubscribedCount":0},{"mutual":false,"remarkName":null,"vipType":0,"nickname":"安小尧尧","avatarUrl":"http://p4.music.126.net/bTIoFKFaE1-JQpgN3OynGg==/1986817511391283.jpg","userId":35613125,"expertTags":null,"avatarImgId":1986817511391283,"backgroundImgId":2002210674180203,"province":140000,"city":140700,"birthday":-2209017600000,"gender":0,"defaultAvatar":true,"backgroundUrl":"http://p1.music.126.net/bmA_ablsXpq3Tk9HlEg9sA==/2002210674180203.jpg","accountStatus":0,"userType":0,"authStatus":0,"followed":false,"djStatus":0,"detailDescription":"","signature":"","authority":0,"description":"","followeds":0,"follows":7,"eventCount":0,"playlistCount":1,"playlistBeSubscribedCount":0},{"mutual":false,"remarkName":null,"vipType":0,"nickname":"安安静静的小小尧","avatarUrl":"http://p4.music.126.net/0Tu5kSdkGlTGe5sO0Vtjtw==/3289738790480030.jpg","userId":84107912,"expertTags":null,"avatarImgId":3289738790480030,"backgroundImgId":2002210674180203,"province":220000,"city":220200,"birthday":-2209017600000,"gender":1,"defaultAvatar":false,"backgroundUrl":"http://p1.music.126.net/bmA_ablsXpq3Tk9HlEg9sA==/2002210674180203.jpg","accountStatus":0,"userType":0,"authStatus":0,"followed":false,"djStatus":0,"detailDescription":"","signature":"\u2014\u2014 做好自己。。","authority":0,"description":"","followeds":1,"follows":0,"eventCount":0,"playlistCount":1,"playlistBeSubscribedCount":0}],"userprofileCount":3}
     * code : 200
     */

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
        private int userprofileCount;
        /**
         * mutual : false
         * remarkName : null
         * vipType : 0
         * nickname : 安小尧
         * avatarUrl : http://p4.music.126.net/hw1NskbvWJkj9LH-QQHHcg==/3427177763926533.jpg
         * userId : 31712644
         * expertTags : null
         * avatarImgId : 3427177763926533
         * backgroundImgId : 2002210674180201
         * province : 410000
         * city : 410100
         * birthday : 631191529198
         * gender : 1
         * defaultAvatar : false
         * backgroundUrl : http://p1.music.126.net/o3G7lWrGBQAvSRt3UuApTw==/2002210674180201.jpg
         * accountStatus : 0
         * userType : 0
         * authStatus : 0
         * followed : false
         * djStatus : 0
         * detailDescription :
         * signature :
         * authority : 0
         * description :
         * followeds : 2
         * follows : 3
         * eventCount : 1
         * playlistCount : 12
         * playlistBeSubscribedCount : 0
         */

        private List<UserprofilesBean> userprofiles;

        public int getUserprofileCount() {
            return userprofileCount;
        }

        public void setUserprofileCount(int userprofileCount) {
            this.userprofileCount = userprofileCount;
        }

        public List<UserprofilesBean> getUserprofiles() {
            return userprofiles;
        }

        public void setUserprofiles(List<UserprofilesBean> userprofiles) {
            this.userprofiles = userprofiles;
        }

        public static class UserprofilesBean {
            private boolean mutual;
            private Object remarkName;
            private int vipType;
            private String nickname;
            private String avatarUrl;
            private int userId;
            private Object expertTags;
            private long avatarImgId;
            private long backgroundImgId;
            private int province;
            private int city;
            private long birthday;
            private int gender;
            private boolean defaultAvatar;
            private String backgroundUrl;
            private int accountStatus;
            private int userType;
            private int authStatus;
            private boolean followed;
            private int djStatus;
            private String detailDescription;
            private String signature;
            private int authority;
            private String description;
            private int followeds;
            private int follows;
            private int eventCount;
            private int playlistCount;
            private int playlistBeSubscribedCount;

            public boolean isMutual() {
                return mutual;
            }

            public void setMutual(boolean mutual) {
                this.mutual = mutual;
            }

            public Object getRemarkName() {
                return remarkName;
            }

            public void setRemarkName(Object remarkName) {
                this.remarkName = remarkName;
            }

            public int getVipType() {
                return vipType;
            }

            public void setVipType(int vipType) {
                this.vipType = vipType;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatarUrl() {
                return avatarUrl;
            }

            public void setAvatarUrl(String avatarUrl) {
                this.avatarUrl = avatarUrl;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getExpertTags() {
                return expertTags;
            }

            public void setExpertTags(Object expertTags) {
                this.expertTags = expertTags;
            }

            public long getAvatarImgId() {
                return avatarImgId;
            }

            public void setAvatarImgId(long avatarImgId) {
                this.avatarImgId = avatarImgId;
            }

            public long getBackgroundImgId() {
                return backgroundImgId;
            }

            public void setBackgroundImgId(long backgroundImgId) {
                this.backgroundImgId = backgroundImgId;
            }

            public int getProvince() {
                return province;
            }

            public void setProvince(int province) {
                this.province = province;
            }

            public int getCity() {
                return city;
            }

            public void setCity(int city) {
                this.city = city;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public boolean isDefaultAvatar() {
                return defaultAvatar;
            }

            public void setDefaultAvatar(boolean defaultAvatar) {
                this.defaultAvatar = defaultAvatar;
            }

            public String getBackgroundUrl() {
                return backgroundUrl;
            }

            public void setBackgroundUrl(String backgroundUrl) {
                this.backgroundUrl = backgroundUrl;
            }

            public int getAccountStatus() {
                return accountStatus;
            }

            public void setAccountStatus(int accountStatus) {
                this.accountStatus = accountStatus;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public int getAuthStatus() {
                return authStatus;
            }

            public void setAuthStatus(int authStatus) {
                this.authStatus = authStatus;
            }

            public boolean isFollowed() {
                return followed;
            }

            public void setFollowed(boolean followed) {
                this.followed = followed;
            }

            public int getDjStatus() {
                return djStatus;
            }

            public void setDjStatus(int djStatus) {
                this.djStatus = djStatus;
            }

            public String getDetailDescription() {
                return detailDescription;
            }

            public void setDetailDescription(String detailDescription) {
                this.detailDescription = detailDescription;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public int getAuthority() {
                return authority;
            }

            public void setAuthority(int authority) {
                this.authority = authority;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getFolloweds() {
                return followeds;
            }

            public void setFolloweds(int followeds) {
                this.followeds = followeds;
            }

            public int getFollows() {
                return follows;
            }

            public void setFollows(int follows) {
                this.follows = follows;
            }

            public int getEventCount() {
                return eventCount;
            }

            public void setEventCount(int eventCount) {
                this.eventCount = eventCount;
            }

            public int getPlaylistCount() {
                return playlistCount;
            }

            public void setPlaylistCount(int playlistCount) {
                this.playlistCount = playlistCount;
            }

            public int getPlaylistBeSubscribedCount() {
                return playlistBeSubscribedCount;
            }

            public void setPlaylistBeSubscribedCount(int playlistBeSubscribedCount) {
                this.playlistBeSubscribedCount = playlistBeSubscribedCount;
            }
        }
    }
}
