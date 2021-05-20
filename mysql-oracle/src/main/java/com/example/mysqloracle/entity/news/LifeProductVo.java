package com.example.mysqloracle.entity.news;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author blueSky
 * @Description:寿险产品库
 * @since 2021-05-06
 */
@NoArgsConstructor
@ApiModel(value = "寿险产品库")
@Data
@EqualsAndHashCode(callSuper = false)

public class LifeProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("first_pay_count")
    private Integer first_pay_count;
    @JsonProperty("sale_way")
    private String sale_way;
    @JsonProperty("proposal_url")
    private String proposal_url;
    @JsonProperty("sale_online_url")
    private String sale_online_url;
    @JsonProperty("qrcode_url")
    private String qrcode_url;
    @JsonProperty("image_url")
    private String image_url;
    @JsonProperty("list_image_url")
    private String list_image_url;
    @JsonProperty("is_long_term")
    private String is_long_term;
    @JsonProperty("status")
    private String status;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("commission_type")
    private String commission_type;
    @JsonProperty("allow_age")
    private String allow_age;
    @JsonProperty("is_sale_online")
    private String is_sale_online;
    @JsonProperty("is_proposal")
    private String is_proposal;
    @JsonProperty("is_green_service")
    private String is_green_service;
    @JsonProperty("is_community")
    private String is_community;
    @JsonProperty("is_order_online")
    private String is_order_online;
    @JsonProperty("category")
    private String category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("name_short")
    private String name_short;
    @JsonProperty("code")
    private String code;
    @JsonProperty("range")
    private String range;
    @JsonProperty("source")
    private String source;
    @JsonProperty("company_id")
    private String company_id;
    @JsonProperty("hesitate_time")
    private String hesitate_time;
    @JsonProperty("hesitate_unit")
    private String hesitate_unit;
    @JsonProperty("start_time")
    private String start_time;
    @JsonProperty("end_time")
    private String end_time;
    @JsonProperty("contents")
    private List<ContentsDTO> contents;
    @JsonProperty("images")
    private List<ImagesDTO> images;
    @JsonProperty("additions")
    private List<AdditionsDTO> additions;
    @JsonProperty("tags")
    private List<TagsDTO> tags;
    @JsonProperty("documents")
    private List<DocumentsDTO> documents;

    @NoArgsConstructor
    @Data
    public static class ContentsDTO {
        @JsonProperty("type")
        private String type;
        @JsonProperty("content")
        private String content;
    }

    @NoArgsConstructor
    @Data
    public static class ImagesDTO {
        @JsonProperty("url")
        private String url;
    }

    @NoArgsConstructor
    @Data
    public static class AdditionsDTO {
        @JsonProperty("product_id")
        private String productId;
        @JsonProperty("code")
        private String code;
        @JsonProperty("status")
        private String status;
    }

    @NoArgsConstructor
    @Data
    public static class TagsDTO {
        @JsonProperty("type")
        private String type;
        @JsonProperty("tag_id")
        private String tagId;
    }

    @NoArgsConstructor
    @Data
    public static class DocumentsDTO {
        @JsonProperty("url")
        private String url;
        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private String type;
        @JsonProperty("status")
        private String status;
        @JsonProperty("created_at")
        private String createdAt;
    }
}