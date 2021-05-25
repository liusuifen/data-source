package com.example.mysqloracle.param;

/**
 * 1年，2岁
 */
public enum StatusEnum {
    STATUS_承保中(0, 1),
    STATUS_审核( 1, 3),
    STATUS_撤单( 2, 8),
    STATUS_退保( 3, 6),
    STATUS_拒保( 4, 8),
    STATUS_人工核保( 5, 1),
    STATUS_犹退( 6, 5),
    STATUS_有效( 7, 3),
    STATUS_失效( 8, 7),
    STATUS_终止( 9, 8),
    STATUS_承保( 10, 3);



    private Integer code;
    private Integer newCode;

    StatusEnum(Integer code, Integer newCode) {
        this.code = code;
        this.newCode = newCode;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getNewCode() {
        return newCode;
    }

    public void setNewCode(Integer newCode) {
        this.newCode = newCode;
    }


    public static Integer getNewByCode(Integer code){
        for (StatusEnum ele : values()) {
            if(ele.getCode().equals(code))
                return ele.getNewCode();
        }
        return 1;//默认为待承保
    }

}
