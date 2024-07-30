package com.veganplanet.common.core.request.model;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 请求参数分页和时间范围
 *
 */
@Data
public class BasePageParam {
    /**
     * 页数
     */
    private Long pageNum;
    /**
     * 每页显示条数
     */
    private Long pageSize;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 获得某天最小时间 2022-07-07 00:00:00
     */
    public Date getStartTime() {
        if (startTime != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime.getTime()), ZoneId.systemDefault());
            LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
            return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }

    /**
     * 获得某天最大时间 2022-07-07 23:59:59
     */
    public Date getEndTime() {
        if (endTime != null) {
            LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime.getTime()), ZoneId.systemDefault());
            ;
            LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
            return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        }
        return null;
    }
}
