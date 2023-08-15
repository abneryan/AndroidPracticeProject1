package com.example.rxjava.use.bean;

import java.util.List;

/**
 * @Auther: yanguoqing
 * @Date: 2023/8/12 00:53
 * @Description:
 */
public class ProjectBean {

    /**
     * Auto-generated: 2023-08-12 0:2:10
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */

    private List<Data> data;
    private int errorCode;
    private String errorMsg;

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Auto-generated: 2023-08-12 0:55:19
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private List<String> articleList;
        private String author;
        private List<String> children;
        private int courseId;
        private String cover;
        private String desc;
        private int id;
        private String lisense;
        private String lisenseLink;
        private String name;
        private long order;
        private int parentChapterId;
        private int type;
        private boolean userControlSetTop;
        private int visible;

        public void setArticleList(List<String> articleList) {
            this.articleList = articleList;
        }

        public List<String> getArticleList() {
            return articleList;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        public void setChildren(List<String> children) {
            this.children = children;
        }

        public List<String> getChildren() {
            return children;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCover() {
            return cover;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setLisense(String lisense) {
            this.lisense = lisense;
        }

        public String getLisense() {
            return lisense;
        }

        public void setLisenseLink(String lisenseLink) {
            this.lisenseLink = lisenseLink;
        }

        public String getLisenseLink() {
            return lisenseLink;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setOrder(long order) {
            this.order = order;
        }

        public long getOrder() {
            return order;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public boolean getUserControlSetTop() {
            return userControlSetTop;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getVisible() {
            return visible;
        }

    }
}
