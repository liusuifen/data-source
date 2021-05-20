package com.example.mysqloracle.enums;

/**
 * 渠道对应的合作方关系
 */
public enum PartnerEnum {

    channelId_佳兆业(22, 25670487065493504L);

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
