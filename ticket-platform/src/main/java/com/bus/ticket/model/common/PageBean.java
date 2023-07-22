package com.bus.ticket.model.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页数据模型。
 * 
 * @param <T>
 *            分页中包含的数据类型
 * 
 * @author Wu.Zheng
 */
public class PageBean<T> extends PageInfo {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页数据集
     */
    @Getter
    private List<T> pageDatas = Collections.emptyList();

    /**
     * 查询参数
     */
    @Getter
    @Setter
    private Map<String, Object> queryParameters;

    /**
     * 附加结果数据
     */
    @Getter
    @Setter
    private Map<String, Object> extraDatas;

    public PageBean() {
        super();
    }

    public PageBean(String orderBy, String orderType) {
        super(orderBy, orderType);
    }

    public PageBean(int pageIndex, int pageSize) {
        super(pageIndex, pageSize, null, null);
    }

    public PageBean(int pageIndex, int pageSize, String orderBy, String orderType) {
        super(pageIndex, pageSize, orderBy, orderType);
    }

    public PageBean(PageInfo pageInfo) {
        super(pageInfo.getPageIndex(), pageInfo.getPageSize(), pageInfo.getOrderBy(), pageInfo.getOrderType());
    }

    public PageBean(PageInfo pageInfo, List<T> pageDatas, int totalRecords) {
        super(pageInfo.getPageIndex(), pageInfo.getPageSize(), pageInfo.getOrderBy(), pageInfo.getOrderType());
        setTotalRecords(totalRecords);
        setPageDatas(pageDatas);
    }

    public void setPageDatas(List<T> pageDatas) {
        this.pageDatas = pageDatas == null ? new ArrayList<T>() : pageDatas;
        if (pageDatas != null) {
            setEndIndex(getStartIndex() + pageDatas.size());
        }
    }

    public void setIncludeProbesPageDatas(List<T> pageDatas) {
        int dataSize = pageDatas != null ? pageDatas.size() : 0;
        boolean hasNextPage = dataSize > getPageSize();
        if (hasNextPage) {
            setPageDatas(pageDatas.subList(0, getPageSize()));
            setTotalRecords(getEndIndex() + 1);
        } else {
            setPageDatas(pageDatas);
            setTotalRecords(getStartIndex() + dataSize);
        }
    }

    /**
     * 获取查询参数。
     * 
     * @param name
     *            参数名
     * @return 参数值。
     */
    public Object getQueryParameter(String name) {
        return queryParameters != null ? queryParameters.get(name) : null;
    }

    /**
     * 设置查询参数。
     * 
     * @param name
     *            参数名
     * @param value
     *            参数值
     */
    public void setQueryParameter(String name, Object value) {
        if (queryParameters == null) {
            initDefaultQueryParameters();
        }
        queryParameters.put(name, value);
    }

    /**
     * 移除查询参数。
     * 
     * @param name
     *            参数名
     */
    public void removeQueryParameter(String name) {
        if (queryParameters != null) {
            queryParameters.remove(name);
        }
    }

    /**
     * 
     */
    private void initDefaultQueryParameters() {
        queryParameters = new HashMap<>(16);
    }

    /**
     * 获取附加数据。
     * 
     * @param name
     *            数据名
     * @return 数据值。
     */
    public Object getExtraData(String name) {
        return extraDatas != null ? extraDatas.get(name) : null;
    }

    /**
     * 设置附加数据。
     * 
     * @param name
     *            数据名
     * @param value
     *            数据值
     */
    public void setExtraData(String name, Object value) {
        if (extraDatas == null) {
            initDefaultExtraDatas();
        }
        extraDatas.put(name, value);
    }

    /**
     * 移除附加数据。
     * 
     * @param name
     *            数据名
     */
    public void removeExtraData(String name) {
        if (extraDatas != null) {
            extraDatas.remove(name);
        }
    }

    /**
     * 
     */
    private void initDefaultExtraDatas() {
        extraDatas = new HashMap<>(16);
    }

}
