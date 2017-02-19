package com.shixin.finalfigher.bean;

import java.util.List;

/**
 * Created by mio on 2017/2/19.
 */

public class GanhuoBean {


    /**
     * error : false
     * results : [{"_id":"58a64202421aa9662dc7409e","createdAt":"2017-02-17T08:21:22.893Z","desc":"Android Dropdown 风格的 Alert 窗体。","images":["http://img.gank.io/61492bf0-03ad-42a2-accb-21abf47e840f","http://img.gank.io/008a38fc-139d-484b-909a-354298366d8b"],"publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"Android","url":"https://github.com/Tapadoo/Alerter","used":true,"who":"代码家"},{"_id":"58a65ad0421aa9662dc740a3","createdAt":"2017-02-17T10:07:12.739Z","desc":"5分钟完全理解android handler","publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"Android","url":"https://android-notes.github.io/2016/12/03/5%E5%88%86%E9%92%9F%E5%AE%8C%E5%85%A8%E7%90%86%E8%A7%A3android-handler/","used":true,"who":"LHF"},{"_id":"58a66205421aa9662dc740a4","createdAt":"2017-02-17T10:37:57.553Z","desc":"OkHttp Mock 数据调用，在做 Api 测试，写测试用例的时候会很有用哦。","publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"Android","url":"https://github.com/mirrajabi/okhttp-json-mock","used":true,"who":"代码家"},{"_id":"58a662a8421aa96631f16631","createdAt":"2017-02-17T10:40:40.669Z","desc":"一款漂亮的 Android 视差效果。","images":["http://img.gank.io/d63f2314-8aac-4023-92c8-9319acf47afb"],"publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"Android","url":"https://github.com/developer-shivam/ChanelView","used":true,"who":"nds"},{"_id":"58a662e2421aa966366d05e5","createdAt":"2017-02-17T10:41:38.997Z","desc":"实现类似锤子和 Google Photo 的滑动选择功能。","images":["http://img.gank.io/9843391b-480d-475d-b3b0-51802eefd957"],"publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"Android","url":"https://github.com/MFlisar/DragSelectRecyclerView","used":true,"who":"代码家"},{"_id":"58a6659d421aa96631f16632","createdAt":"2017-02-17T10:53:17.138Z","desc":"一个实用的联系人选择工具。","images":["http://img.gank.io/830f325c-9f1d-46f1-94e5-5b521f9b2dcd"],"publishedAt":"2017-02-17T11:31:19.996Z","source":"chrome","type":"Android","url":"https://github.com/quiin/UnifiedContactPicker","used":true,"who":"代码家"},{"_id":"58a50780421aa9662dc74098","createdAt":"2017-02-16T09:59:28.885Z","desc":"Android 上个性自定义 Loader。","images":["http://img.gank.io/5deef32c-5273-4ea6-8d75-9cbc44560725"],"publishedAt":"2017-02-16T10:07:37.13Z","source":"chrome","type":"Android","url":"https://github.com/nntuyen/mkloader","used":true,"who":"dmj"},{"_id":"58a507da421aa966366d05d4","createdAt":"2017-02-16T10:00:58.274Z","desc":"扩展 Android Tab Layout，实现动画指示器效果，很漂亮。","images":["http://img.gank.io/5a750803-2e97-40e9-8f9f-d14131dc6c5c","http://img.gank.io/c17ab302-2f2e-4ae1-9c7e-55e3e50223e1"],"publishedAt":"2017-02-16T10:07:37.13Z","source":"chrome","type":"Android","url":"https://github.com/Andy671/Dachshund-Tab-Layout","used":true,"who":"代码家"},{"_id":"58a5083a421aa9662f42972d","createdAt":"2017-02-16T10:02:34.823Z","desc":"Android 双层级 Dialog 选择框。","publishedAt":"2017-02-16T10:07:37.13Z","source":"chrome","type":"Android","url":"https://github.com/aliab/Two-Step-Picker-Dialog","used":true,"who":"代码家"},{"_id":"58a3b7c6421aa96631f16619","createdAt":"2017-02-15T10:07:02.301Z","desc":"图解 Android 事件分发机制","images":["http://img.gank.io/1c9e0326-7d44-4b54-a523-b34fc939777a","http://img.gank.io/1ebed9ec-6b82-450a-a7e6-248091d15388","http://img.gank.io/2b83339d-5173-465c-bdb3-5526fcfc1947"],"publishedAt":"2017-02-15T11:24:04.127Z","source":"chrome","type":"Android","url":"http://www.jianshu.com/p/e99b5e8bd67b?utm_campaign=haruki&utm_content=note&utm_medium=reader_share&utm_source=weixin","used":true,"who":"LHF"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 58a64202421aa9662dc7409e
         * createdAt : 2017-02-17T08:21:22.893Z
         * desc : Android Dropdown 风格的 Alert 窗体。
         * images : ["http://img.gank.io/61492bf0-03ad-42a2-accb-21abf47e840f","http://img.gank.io/008a38fc-139d-484b-909a-354298366d8b"]
         * publishedAt : 2017-02-17T11:31:19.996Z
         * source : chrome
         * type : Android
         * url : https://github.com/Tapadoo/Alerter
         * used : true
         * who : 代码家
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
