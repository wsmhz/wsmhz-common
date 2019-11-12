package com.wsmhz.common.business.service;

/**
 * Created By TangBiJing On 2019/5/15
 * Description: 雪花算法
 * SnowFlake的结构如下(每部分用-分开):
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)得到的值
 * 10位的数据机器位，可以部署在1024个节点，包括5位dataCenterId和5位workerId
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号，加起来刚好64位，为一个Long型。
 * 优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，每秒能产生26万ID
 */
public class SnowflakeIdWorkerService {
    // 起始时间戳
    private final static long START_STAMP = 1545185577000L;
    // 机器id所占的位数
    private static long workerIdBits = 5L;
    // 数据中心标识id所占的位数
    private static long dataCenterIdBits = 5L;
    // 支持的最大机器id
    private static long maxWorkerId = ~(-1L << workerIdBits);
    // 支持的最大数据标识id
    private static long maxDataCenterId = ~(-1L << dataCenterIdBits);
    // 序列在id中占的位数
    private long sequenceBits = 12L;
    // 机器id向左移的位数
    private long workerIdLeftShift = sequenceBits;
    // 数据标识id向左移的位数
    private long dataCenterIdLeftShift = workerIdBits + workerIdLeftShift;
    // 时间截向左移的位置
    private long timestampLeftShift = dataCenterIdBits + dataCenterIdLeftShift;
    // 生成序列的掩码
    private long sequenceMask = ~(-1 << sequenceBits);
    // 工作机器id
    private long workerId;
    // 数据中心标识id部分-序列号
    private long dataCenterId;
    // 同一个时间截内生成的序列数，初始值是0，从0开始
    private long sequence = 0L;
    // 上次生成id的时间截
    private long lastTimestamp = -1L;

    public SnowflakeIdWorkerService(long workerId, long dataCenterId) {
        if (workerId < 0 || workerId > maxWorkerId) {
            throw new IllegalArgumentException(String.format("workerId[%d] is less than 0 or greater than maxWorkerId[%d].", workerId, maxWorkerId));
        }
        if (dataCenterId < 0 || dataCenterId > maxDataCenterId) {
            throw new IllegalArgumentException(
                    String.format("dataCenterId[%d] is less than 0 or greater than maxDataCenterId[%d].", dataCenterId, maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    // 生成id
    public synchronized long getId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 表示是同一时间截内生成的id
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 说明当前时间生成的序列数已达到最大
            if (sequence == 0) {
                // 生成下一个毫秒级的序列
                timestamp = tilNextMillis();
                // 序列从0开始
                sequence = 0L;
            }
        } else {
            // 新的时间截，则序列从0开始
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - START_STAMP) << timestampLeftShift) // 时间截部分
               | (dataCenterId << dataCenterIdLeftShift) // 数据标识id部分
               | (workerId << workerIdLeftShift) // 机器id部分
               | sequence; // 序列部分
    }

    private long tilNextMillis() {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}