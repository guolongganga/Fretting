package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/29 10:28
 * 描述：
 * "data":{
 * "ofund_type":"0",
 * "performance_term":"3",
 * "pageNum":2,
 * "pageSize":3
 * }
 */

public class NewestFundParams extends CommonReqData<NewestFundParams> {
    private int pageNum;
    private int pageSize;
    private String ofund_type;
    private String performance_term;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOfund_type() {
        return ofund_type;
    }

    public void setOfund_type(String ofund_type) {
        this.ofund_type = ofund_type;
    }

    public String getPerformance_term() {
        return performance_term;
    }

    public void setPerformance_term(String performance_term) {
        this.performance_term = performance_term;
    }
}
