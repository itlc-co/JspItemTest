package org.smbms.util;

/**
 * @ProjectName: JspItemTest
 * @PackageName: org.smbms.util
 * @ClassName: PagerDriver
 * @Description: 自定义的分页器
 * @Author: lc_co
 * @Contact: itlico@126.com
 * @Date: 2021/11/3 19:23
 * @Copyright: (c) 2021 Author LC_CO. All rights reserved.
 * @Company:
 * @JavaVersion: jdk1.8
 * @Version: 1.0
 */
public class PagerDriver {

    private int currentPageNo = 1;  // 当前页面默认1

    private int totalCount = 0; // 总数量默认0

    private int pageSize = 0; // 页面容量默认0

    private int totalPageCont = 1; // 页面数量默认1

    public PagerDriver() {
    }

    public PagerDriver(int currentPageNo, int totalCount, int pageSize, int totalPageCont) {
        this.currentPageNo = currentPageNo;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPageCont = totalPageCont;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalPageCont() {
        return totalPageCont;
    }

    public void setTotalPageCont() {
        int totalPageCont = getTotalPageCountByTotalCount();
        if (totalPageCont > 0) {
            this.totalPageCont = totalPageCont;
        }
    }

    /**
     * @Description: 通过页面的容量以及总数量获取页面的数量
     * @Author: lc_co
     * @Date: 2021-11-03 19:34:54
     * @Param:
     * @Return: int 页面的总数量
     * @Throws: Exception
     */
    private int getTotalPageCountByTotalCount() {
        int totalPageCont = 0;
        // 能整除则直接将其相除即可
        if (this.totalCount % this.pageSize == 0) {
            totalPageCont = this.totalCount / this.pageSize;
        } else {
            // 不能说明有多余的则在添加一个页面
            totalPageCont = this.totalCount / this.pageSize + 1;
        }
        return totalPageCont;
    }
}
