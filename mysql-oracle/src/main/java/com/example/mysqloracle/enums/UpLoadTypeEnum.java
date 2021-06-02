package com.example.mysqloracle.enums;

/**
 * 国籍枚举
 */
public enum UpLoadTypeEnum {
    UP_LOAD_TYPE_无媒体(0, 3),
    UP_LOAD_TYPE_视频( 1, 1),
    UP_LOAD_TYPE_音频( 2, 2);

    private Integer old;
    private Integer news;

    UpLoadTypeEnum(Integer old, Integer news) {
        this.old = old;
        this.news = news;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public Integer getNews() {
        return news;
    }

    public void setNews(Integer news) {
        this.news = news;
    }

    public static Integer getNewByOld(Integer old){
        for (UpLoadTypeEnum ele : values()) {
            if(ele.getOld().equals(old))
                return ele.getNews();
        }
        return null;
    }

}
