package com.example.mysqloracle.enums;

/**
 * 国籍枚举
 */
public enum NationEnum {
    YEAR(1, "1-年"),
    ANNUM( 2, "2-岁");

    private Integer old;
    private String news;

    NationEnum(Integer old, String news) {
        this.old = old;
        this.news = news;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public static String getNewByOld(Integer old){
        for (NationEnum ele : values()) {
            if(ele.getOld().equals(old))
                return ele.getNews();
        }
        return null;
    }

}
