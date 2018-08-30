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

public class MyInvestParams extends CommonReqData<MyInvestParams> {
    private int pageNum;
    private int pageSize;
    private String fundCode;
    private String dtStatus;
    private String isFirst;

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

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getDtStatus() {
        return dtStatus;
    }

    public void setDtStatus(String dtStatus) {
        this.dtStatus = dtStatus;
    }

    public String getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(String isFirst) {
        this.isFirst = isFirst;
    }
}
