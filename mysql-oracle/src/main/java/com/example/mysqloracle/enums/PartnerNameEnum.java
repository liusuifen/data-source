package com.example.mysqloracle.enums;

/**
 * 渠道对应的合作方关系
 */
public enum PartnerNameEnum {
    /**
     * 佳兆业22:  25884107091214336
     * 众康27:  25895773975609344
     * 汇盟 2: 25670487107436544
     * 大同25: 25891271281213440
     * 产商31: 25899747575332864
     *
     */
    //佳兆业
    channelId_佳兆业(22, "佳兆业建科保险经纪有限公司"),
    channelId_众康(27, "湖北众康在线保险销售有限公司"),
    channelId_大同(25, "25884107053465600L"),
    channelId_汇盟(2, "深圳保联科技有限公司"),
    channelId_广商(31, "广东广商保险销售股份有限公司"),
    channelId_华润(33, "华润保险经纪有限公司"),
    channelId_南粤(34, "南粤保险代理有限公司");

    private Integer channelId;
    private String partnerName;

    PartnerNameEnum(Integer channelId, String partnerId) {
        this.channelId = channelId;
        this.partnerName = partnerId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerId(String partnerId) {
        this.partnerName = partnerId;
    }

    public static String getPartnerNameByChannelId(Integer channelId){
        for (PartnerNameEnum ele : values()) {
            if(ele.getChannelId().equals(channelId))
                return ele.getPartnerName();
        }
        return null;
    }
}
