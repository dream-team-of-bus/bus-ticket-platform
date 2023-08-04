package com.bus.ticket.util;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bus.ticket.model.common.CommonApiResponse;
import com.bus.ticket.model.common.CommonApiResponsePageData;
import com.bus.ticket.model.common.PageBean;

/**
 * @author wuzheng
 */
public class HttpResponseBodyUtil {

    public static final String DEFAULT_CODE = "0";

    public static final String DEFAULT_MESSAGE = null;

    public static CommonApiResponse toJson() {
        return toJson(DEFAULT_CODE, DEFAULT_MESSAGE, null);
    }

    public static CommonApiResponse toJson(Object data) {
        if (data == null) {
            return toJson();
        } else if (data instanceof CommonApiResponse) {
            return (CommonApiResponse)data;
        } else if (data instanceof PageBean<?>) {
            return HttpResponseBodyUtil.toJson((PageBean<?>)data);
        } else if (data instanceof List<?>) {
            return HttpResponseBodyUtil.toJson((List<?>)data);
        } else if (data instanceof Page<?>) {
            return HttpResponseBodyUtil.toJson((Page<?>)data);
        } else {
            return toJson(DEFAULT_CODE, DEFAULT_MESSAGE, data);
        }
    }

    public static CommonApiResponse toJson(String code, String message) {
        return toJson(code, message, null);
    }

    public static CommonApiResponse toJson(String code, String message, Object data) {
        CommonApiResponse body = new CommonApiResponse();
        body.setCode(code).setMessage(message).setData(data);
        return body;
    }

    public static CommonApiResponse toJson(List<?> list) {
        CommonApiResponsePageData pageData = new CommonApiResponsePageData();
        pageData.setList(list);
        return toJson(DEFAULT_CODE, DEFAULT_MESSAGE, pageData);
    }

    public static CommonApiResponse toJson(PageBean<?> pageBean) {
        CommonApiResponsePageData.Pager pager = new CommonApiResponsePageData.Pager();
        pager.setPage(pageBean.getPageIndex()).setSize(pageBean.getPageSize()).setTotalPages(pageBean.getTotalPages())
            .setTotal(pageBean.getTotalRecords());

        CommonApiResponsePageData pageData = new CommonApiResponsePageData();
        pageData.setPager(pager).setList(pageBean.getPageDatas()).setMeta(pageBean.getExtraDatas());
        return toJson(DEFAULT_CODE, DEFAULT_MESSAGE, pageData);
    }

    public static CommonApiResponse toJson(Page<?> page) {
        CommonApiResponsePageData.Pager pager = new CommonApiResponsePageData.Pager();
        pager.setPage((int)page.getCurrent()).setSize((int)page.getSize()).setTotalPages((int)page.getPages())
            .setTotal((int)page.getTotal());
        CommonApiResponsePageData pageData = new CommonApiResponsePageData();
        pageData.setPager(pager).setList(page.getRecords());
        return toJson(DEFAULT_CODE, DEFAULT_MESSAGE, pageData);
    }
}
