package com.example.asus.quartertou.bean;

import java.util.List;

public class UserVideosBean {


    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126083846931511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":2,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608384693041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":121,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126083994901511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":1,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608399490041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":122,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084000211511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":1,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608400021041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":123,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084005531511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":0,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608400553041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":124,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084007251511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":0,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608400725041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":125,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084009591511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":0,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608400959041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":126,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084011001511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":0,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608401100041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":127,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084036781511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":0,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608403678041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":128,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084040681511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":0,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608404068041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":129,"workDesc":"视频里面能发图片么？"},{"commentNum":0,"cover":"https://www.zhaoapi.cn/images/quarter/15126084042561511879908298.jpg, 1511879908298.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"50","localUri":null,"longitude":"50","playNum":0,"praiseNum":0,"uid":77,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512608404256041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg","wid":130,"workDesc":"视频里面能发图片么？"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * commentNum : 0
         * cover : https://www.zhaoapi.cn/images/quarter/15126083846931511879908298.jpg, 1511879908298.jpg
         * createTime : 2017-12-17T19:20:44
         * favoriteNum : 0
         * latitude : 50
         * localUri : null
         * longitude : 50
         * playNum : 2
         * praiseNum : 0
         * uid : 77
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512608384693041635_3419280694_4_713_844.jpg, 041635_3419280694_4_713_844.jpg
         * wid : 121
         * workDesc : 视频里面能发图片么？
         */

        private int commentNum;
        private String cover;
        private String createTime;
        private int favoriteNum;
        private String latitude;
        private Object localUri;
        private String longitude;
        private int playNum;
        private int praiseNum;
        private int uid;
        private String videoUrl;
        private int wid;
        private String workDesc;
        private boolean flag;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getFavoriteNum() {
            return favoriteNum;
        }

        public void setFavoriteNum(int favoriteNum) {
            this.favoriteNum = favoriteNum;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Object getLocalUri() {
            return localUri;
        }

        public void setLocalUri(Object localUri) {
            this.localUri = localUri;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getPlayNum() {
            return playNum;
        }

        public void setPlayNum(int playNum) {
            this.playNum = playNum;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public int getWid() {
            return wid;
        }

        public void setWid(int wid) {
            this.wid = wid;
        }

        public String getWorkDesc() {
            return workDesc;
        }

        public void setWorkDesc(String workDesc) {
            this.workDesc = workDesc;
        }
    }
}
