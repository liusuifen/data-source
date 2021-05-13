package com.example.mysqloracle.param;

/**
 * 1年，2岁
 */
public enum ProgressEnum {
    PROCESS_录单(0, 11),
    PROCESS_预收( 1, 12),
    PROCESS_承保( 2, 15),
    PROCESS_回执( 3, 16),
    PROCESS_复核( 4, 17),
    PROCESS_回访( 5, 18),
    PROCESS_对账( 6, 19),
    PROCESS_发首佣( 7, 20),
    PROCESS_发积分( 8, 21);



    private Integer code;
    private Integer newCode;

    ProgressEnum(Integer code, Integer newCode) {
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
        for (ProgressEnum ele : values()) {
            if(ele.getCode().equals(code))
                return ele.getNewCode();
        }
        return 1;//默认为待承保
    }

}
