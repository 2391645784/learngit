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

}
