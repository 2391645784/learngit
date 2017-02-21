package com.shixin.finalfigher.bean;

/**
 * Created by mio on 2017/2/20.
 */

public class V2exHot {

    /**
     * id : 341583
     * title : 有哪些神级性价比路由器?
     * url : http://www.v2ex.com/t/341583
     * content : 1. 配置足, 能折腾, 起码能不拆机刷 openwrt
     2. 支持 5G
     3. 其它亮点?
     * content_rendered : <ol>
     <li>配置足, 能折腾, 起码能不拆机刷 openwrt</li>
     <li>支持 5G</li>
     <li>其它亮点?</li>
     </ol>

     * replies : 103
     * member : {"id":158627,"username":"freestyle","tagline":"","avatar_mini":"//v2ex.assets.uxengine.net/avatar/81bd/6208/158627_mini.png?m=1480850690","avatar_normal":"//v2ex.assets.uxengine.net/avatar/81bd/6208/158627_normal.png?m=1480850690","avatar_large":"//v2ex.assets.uxengine.net/avatar/81bd/6208/158627_large.png?m=1480850690"}
     * node : {"id":12,"name":"qna","title":"问与答","title_alternative":"Questions and Answers","url":"http://www.v2ex.com/go/qna","topics":83386,"avatar_mini":"//v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_mini.png?m=1484191251","avatar_normal":"//v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_normal.png?m=1484191251","avatar_large":"//v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_large.png?m=1484191251"}
     * created : 1487497557
     * last_modified : 1487497634
     * last_touched : 1487573353
     */

    private int id;
    private String title;
    private String url;
    private String content;
    private String content_rendered;
    private int replies;
    private MemberBean member;
    private NodeBean node;
    private int created;
    private int last_modified;
    private int last_touched;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent_rendered() {
        return content_rendered;
    }

    public void setContent_rendered(String content_rendered) {
        this.content_rendered = content_rendered;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
    }

    public NodeBean getNode() {
        return node;
    }

    public void setNode(NodeBean node) {
        this.node = node;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(int last_modified) {
        this.last_modified = last_modified;
    }

    public int getLast_touched() {
        return last_touched;
    }

    public void setLast_touched(int last_touched) {
        this.last_touched = last_touched;
    }

    public static class MemberBean {
        /**
         * id : 158627
         * username : freestyle
         * tagline :
         * avatar_mini : //v2ex.assets.uxengine.net/avatar/81bd/6208/158627_mini.png?m=1480850690
         * avatar_normal : //v2ex.assets.uxengine.net/avatar/81bd/6208/158627_normal.png?m=1480850690
         * avatar_large : //v2ex.assets.uxengine.net/avatar/81bd/6208/158627_large.png?m=1480850690
         */

        private int id;
        private String username;
        private String tagline;
        private String avatar_mini;
        private String avatar_normal;
        private String avatar_large;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }
    }

    public static class NodeBean {
        /**
         * id : 12
         * name : qna
         * title : 问与答
         * title_alternative : Questions and Answers
         * url : http://www.v2ex.com/go/qna
         * topics : 83386
         * avatar_mini : //v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_mini.png?m=1484191251
         * avatar_normal : //v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_normal.png?m=1484191251
         * avatar_large : //v2ex.assets.uxengine.net/navatar/c20a/d4d7/12_large.png?m=1484191251
         */

        private int id;
        private String name;
        private String title;
        private String title_alternative;
        private String url;
        private int topics;
        private String avatar_mini;
        private String avatar_normal;
        private String avatar_large;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle_alternative() {
            return title_alternative;
        }

        public void setTitle_alternative(String title_alternative) {
            this.title_alternative = title_alternative;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getTopics() {
            return topics;
        }

        public void setTopics(int topics) {
            this.topics = topics;
        }

        public String getAvatar_mini() {
            return avatar_mini;
        }

        public void setAvatar_mini(String avatar_mini) {
            this.avatar_mini = avatar_mini;
        }

        public String getAvatar_normal() {
            return avatar_normal;
        }

        public void setAvatar_normal(String avatar_normal) {
            this.avatar_normal = avatar_normal;
        }

        public String getAvatar_large() {
            return avatar_large;
        }

        public void setAvatar_large(String avatar_large) {
            this.avatar_large = avatar_large;
        }
    }
}
