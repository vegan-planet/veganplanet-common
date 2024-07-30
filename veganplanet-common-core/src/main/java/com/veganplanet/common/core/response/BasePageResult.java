package com.veganplanet.common.core.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.util.List;

/**
 * 返回参数分页
 *
 */
@Data
public class BasePageResult<T> {
    private List<T> result;
    private long totalElements;
    private long pageSize;
    private long pageNum;
    private long totalPages;
    private boolean optimizeCountSql;
    private List<OrderItem> orders;

    public BasePageResult(List<T> result, long totalElements, long pageSize, long pageNum, long totalPages,
                          boolean optimizeCountSql, List<OrderItem> orders) {
        this.result = result;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totalPages = totalPages;
        this.optimizeCountSql = optimizeCountSql;
        this.orders = orders;
    }

    public static <E> BasePageResult<E> newInstance(IPage<E> page) {
        return new BasePageResult<>(page.getRecords(), page.getTotal(), page.getSize(), page.getCurrent(), page.getPages(),
                 page.optimizeCountSql(), page.orders());
    }

    public static <T,E> BasePageResult<E> newInstance(IPage<T> page,final List<E> content) {
        return new BasePageResult<>(content, page.getTotal(), page.getSize(), page.getCurrent(),page.getPages(),
                 page.optimizeCountSql(), page.orders());
    }
}
