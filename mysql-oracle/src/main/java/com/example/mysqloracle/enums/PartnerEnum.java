package com.example.mysqloracle.enums;

/**
 * 渠道对应的合作方关系
 */
public enum PartnerEnum {
    /**
     * 佳兆业22:  25884107053465600
     * 众康27:  25895773933666304
     * 汇盟 2: 25670487065493504
     * 大同25: 25891271281213440
     * 产商31: 25899747575332864
     *
     */
    //佳兆业
    channelId_佳兆业(22, 25884107053465600L),
    channelId_众康(27, 25895773933666304L),
    channelId_大同(25, 25891271281213440L),
    channelId_汇盟(2, 25670487065493504L),
    channelId_广商(31, 25899747575332864L),
    channelId_华润(33, 25895035346092032L),
    channelId_南粤(34, 25897342687248384L);

    private Integer channelId;
    private Long partnerId;

    PartnerEnum(Integer channelId, Long partnerId) {
        this.channelId = channelId;
        this.partnerId = partnerId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public static Long getPartnerIdByChannelId(Integer channelId){
        for (PartnerEnum ele : values()) {
            if(ele.getChannelId().equals(channelId))
                return ele.getPartnerId();
        }
        return null;
    }
}
