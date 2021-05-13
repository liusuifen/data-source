package com.example.mysqloracle.param;

/**
 * 1年，2岁
 */
public enum RelationEnum {
    RelationEnum_1(1, "本人"),
    RelationEnum_2( 2, "配偶"),
    RelationEnum_3( 3, "子女"),
    RelationEnum_4( 4, "父母"),
    RelationEnum_5( 5, "其他"),
    RelationEnum_6( 6, "祖孙");


    private Integer code;
    private String desc;

    RelationEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static Integer getCodeByDesc(String desc){
        for (RelationEnum ele : values()) {
            if(ele.getDesc().equals(desc))
                return ele.getCode();
        }
        return 0;
    }
    public static String getNameByCode(Integer code){
        for (RelationEnum ele : values()) {
            if(ele.getCode().equals(code))
                return ele.getDesc();
        }
        return "";
    }

}
