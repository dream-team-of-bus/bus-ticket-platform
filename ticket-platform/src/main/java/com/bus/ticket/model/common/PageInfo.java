package com.bus.ticket.model.common;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 分页模型。
 * 
 * @author Wu.Zheng
 */
@ToString
@EqualsAndHashCode
public class PageInfo implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认的当前页数。
     */
    public static final int DEFAULT_PAGE_IDNEX = 1;

    /**
     * 默认的页面记录数。
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页码。
     */
    private int pageIndex;

    /**
     * 总页数。
     */
    private int totalPages;

    /**
     * 每页记录数。
     */
    private int pageSize;

    /**
     * 总记录数。
     */
    private int totalRecords;

    /**
     * 是否有上一页。
     */
    private boolean hasPrePage;

    /**
     * 是否有下一页。
     */
    private boolean hasNextPage;

    /**
     * 排序标识。
     */
    private String orderBy = null;

    /**
     * 排序标识。
     */
    private String orderType = null;

    /**
     * 页面数据起始索引。
     */
    private Integer startIndex;

    /**
     * 页面数据结束索引。
     */
    private Integer endIndex;

    public PageInfo() {
        this(DEFAULT_PAGE_IDNEX, DEFAULT_PAGE_SIZE, null, null);
    }

    public PageInfo(String orderBy, String orderType) {
        this(DEFAULT_PAGE_IDNEX, DEFAULT_PAGE_SIZE, orderBy, orderType);
    }

    public PageInfo(int pageIndex, int pageSize) {
        this(pageIndex, pageSize, null, null);
    }

    public PageInfo(int pageIndex, int pageSize, String orderBy, String orderType) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    public void setTotalRecords(int totalRecords) {
        if (totalRecords < 0) {
            throw new IllegalArgumentException("total records must greater than or equals 0");
        }
        // 设置总记录数
        this.totalRecords = totalRecords;
        // 计算总页数
        this.totalPages = this.totalRecords / this.pageSize;
        if (this.totalRecords % this.pageSize != 0) {
            this.totalPages++;
        }
        // 计算是否有上一页
        if (this.pageIndex > 1) {
            this.hasPrePage = true;
        } else {
            this.hasPrePage = false;
        }
        // 计算是否有下一页
        if (this.pageIndex < this.totalPages) {
            this.hasNextPage = true;
        } else {
            this.hasNextPage = false;
        }
        // 计算开始索引和结束索引
        getStartIndex();
        getEndIndex();
        if (endIndex > totalRecords) {
            setEndIndex(totalRecords);
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasPrePage() {
        return hasPrePage;
    }

    public boolean getHasPrePage() {
        return hasPrePage;
    }

    public void setHasPrePage(boolean isHasPrePage) {
        this.hasPrePage = isHasPrePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public boolean getHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean isHasNextPage) {
        this.hasNextPage = isHasNextPage;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy
     *            the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        startIndex = (pageIndex - 1) * pageSize;
        return startIndex;
    }

    /**
     * @param startIndex
     *            the startIndex to set
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return the endIndex
     */
    public int getEndIndex() {
        endIndex = pageIndex * pageSize;
        return endIndex;
    }

    /**
     * @param endIndex
     *            the endIndex to set
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

}
