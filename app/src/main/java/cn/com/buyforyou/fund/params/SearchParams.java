package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/15 13:19
 * 描述：
 */

public class SearchParams extends CommonReqData<SearchParams> {
    private int pageNum;
    private int pageSize;
    private String fund_name;
    private String fund_code;

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

    public String getFund_name() {
        return fund_name;
    }

    public void setFund_name(String fund_name) {
        this.fund_name = fund_name;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }
}
