package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/25 14:37
 * 描述：
 */

public class SelfChooseParams extends CommonReqData<SelfChooseParams> {
    private String sortJz;
    private String sortZdf;

    public String getSortJz() {
        return sortJz;
    }

    public void setSortJz(String sortJz) {
        this.sortJz = sortJz;
    }

    public String getSortZdf() {
        return sortZdf;
    }

    public void setSortZdf(String sortZdf) {
        this.sortZdf = sortZdf;
    }
}
