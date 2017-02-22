package com.shixin.finalfigher.net;

/**
 * Created by mio on 2017/2/19.
 */

public class ApiClient {
        // 阿凡达数据
        public static String apiMain = "http://api.avatardata.cn/WxNews/Query?key=56f4824fc6ff49b2abac3b98b8074e67&";
        public static String getUrl(int pageCount, int rows, String keyword) {
            String url = apiMain + "page=" + pageCount + "&rows=" + rows + "&keyword=" + keyword;
            return url;
        }
    // v2ex今日热门
    public static String v2exHot = "https://www.v2ex.com/api/topics/hot.json";
    // v2ex 技术
    public static String v2exTech = "https://www.v2ex.com/api/topics/show.json?node_name=programmer";
    // v2ex 创意
    public static String v2exIdeas = "https://www.v2ex.com/api/topics/show.json?node_name=ideas";

    // 每日必应图片
    public static String bingPicUrl="http://guolin.tech/api/bing_pic";
}
