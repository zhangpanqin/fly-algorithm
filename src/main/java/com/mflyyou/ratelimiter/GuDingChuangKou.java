package com.mflyyou.ratelimiter;

public class GuDingChuangKou {

    private final long windowSize;

    private int totalRequestCount;

    private final int maxRequestCount;

    private long rightLimit;

    public GuDingChuangKou(int maxRequestCount, long windowSize) {
        this.maxRequestCount = maxRequestCount;
        this.rightLimit = System.currentTimeMillis();
        this.windowSize = windowSize;
    }

    public synchronized boolean tryAcquire() {
        long currentTimeMillis = System.currentTimeMillis();

        if (currentTimeMillis > rightLimit) {
            totalRequestCount = 0;

            while (rightLimit < currentTimeMillis) {
                rightLimit += windowSize;
            }
        }


        if (totalRequestCount < maxRequestCount) {
            totalRequestCount++;
            return true;
        }
        return false;
    }
}
