package com.example.mysqloracle.entity.news;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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

public class LifeProductResult implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long id;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("company_id")
    private Integer companyId;
    @JsonProperty("code")
    private String code;
    @JsonProperty("code_api")
    private String codeApi;
    @JsonProperty("name")
    private String name;
    @JsonProperty("name_short")
    private String nameShort;
    @JsonProperty("cycle")
    private String cycle;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("range")
    private String range;
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("is_api")
    private Integer isApi;
    @JsonProperty("is_compare")
    private Integer isCompare;
    @JsonProperty("is_sale_online")
    private Integer isSaleOnline;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("start_time")
    private Object startTime;
    @JsonProperty("end_time")
    private Object endTime;
    @JsonProperty("hesitate_time")
    private Integer hesitateTime;
    @JsonProperty("hesitate_unit")
    private Integer hesitateUnit;
    @JsonProperty("system_user_id")
    private Long systemUserId;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("is_deleted")
    private Integer isDeleted;
    @JsonProperty("first_pay_count")
    private Integer firstPayCount;
    @JsonProperty("list_image_url")
    private String listImageUrl;
    @JsonProperty("is_long_term")
    private Integer isLongTerm;
    @JsonProperty("buy_link")
    private String buyLink;
    @JsonProperty("source")
    private String source;
    @JsonProperty("images")
    private List<ImagesDTO> images;
    @JsonProperty("additions")
    private List<AdditionsDTO> additions;
    @JsonProperty("contents")
    private List<ContentsDTO> contents;
    @JsonProperty("category")
    private CategoryDTO category;
    @JsonProperty("documents")
    private List<DocumentsDTO> documents;
    @JsonProperty("company")
    private CompanyDTO company;

    @NoArgsConstructor
    @Data
    public static class ImagesDTO {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("url")
        private String url;
        @JsonProperty("sort")
        private Integer sort;
    }

    @NoArgsConstructor
    @Data
    public static class CategoryDTO {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("status")
        private Integer status;
    }

    @NoArgsConstructor
    @Data
    public static class CompanyDTO {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("full_name")
        private String fullName;
    }

    @NoArgsConstructor
    @Data
    public static class AdditionsDTO {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("sort")
        private Integer sort;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("product_id")
        private Long productId;
        @JsonProperty("product_name")
        private String productName;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("code")
        private String code;
    }

    @NoArgsConstructor
    @Data
    public static class ContentsDTO {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("content")
        private String content;
    }

    @NoArgsConstructor
    @Data
    public static class DocumentsDTO {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("name")
        private String name;
        @JsonProperty("url")
        private String url;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("created_at")
        private String createdAt;
    }
}