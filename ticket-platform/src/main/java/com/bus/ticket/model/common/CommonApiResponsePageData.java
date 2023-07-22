package com.bus.ticket.model.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通用的API分页响应数据模型。
 * 
 * @author wuzheng@tiduyun.com
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonApiResponsePageData implements Serializable {

    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Pager {
        @JsonProperty("page")
        private Integer page;
        @JsonProperty("size")
        private Integer size;
        @JsonProperty("totalPages")
        private Integer totalPages;
        @JsonProperty("total")
        private Integer total;
    }

    private static final long serialVersionUID = 1L;

    @JsonProperty("pager")
    private Pager pager;

    @JsonProperty("list")
    private List<?> list;

    @JsonProperty("meta")
    private Map<String, Object> meta;

    public CommonApiResponsePageData() {
        this(null, null, null);
    }

    public CommonApiResponsePageData(Pager pager, List<?> list) {
        this(pager, list, null);
    }

    public CommonApiResponsePageData(Pager pager, List<?> list, Map<String, Object> meta) {
        super();
        this.pager = pager;
        this.list = list;
        this.meta = meta;
    }

}
