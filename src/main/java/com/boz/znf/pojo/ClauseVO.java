package com.boz.znf.pojo;

/**
 * @author ZhangNanFu
 * @date 2021年04月22日 20:21
 */
public class ClauseVO {
    private Integer currentPage;
    private Integer currentCount;
    private Integer total;

    public ClauseVO() {
    }

    public ClauseVO(Integer currentPage, Integer currentCount, Integer total) {
        this.currentPage = currentPage;
        this.currentCount = currentCount;
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "UserClauseVO{" +
                "currentPage=" + currentPage +
                ", currentCount=" + currentCount +
                ", total=" + total +
                '}';
    }
}
