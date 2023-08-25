package com.bus.ticket.util;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/4
 */
public class PageUtils {

    public static <T> Page<T> convert(Page<?> page, List<T> list) {
        Page<T> result = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        result.setRecords(list);
        return result;
    }
}
