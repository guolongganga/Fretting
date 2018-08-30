package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.BaseResp;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：sunnyzeng on 2018/1/22 13:17
 * 描述：
 */

public class InvestPlanResp extends BaseResp<InvestPlanResp>{
    private String hasDt;
    private List<ApplyBaseInfo> allFunds;
    private List<ApplyBaseInfo> allStatus;
    private ArrayList<InvestInfoResp> resResult;

    public String getHasDt() {
        return hasDt;
    }

    public void setHasDt(String hasDt) {
        this.hasDt = hasDt;
    }

    public List<ApplyBaseInfo> getAllFunds() {
        return allFunds;
    }

    public void setAllFunds(List<ApplyBaseInfo> allFunds) {
        this.allFunds = allFunds;
    }

    public List<ApplyBaseInfo> getAllStatus() {
        return allStatus;
    }

    public void setAllStatus(List<ApplyBaseInfo> allStatus) {
        this.allStatus = allStatus;
    }

    public ArrayList<InvestInfoResp> getResResult() {
        return resResult;
    }

    public void setResResult(ArrayList<InvestInfoResp> resResult) {
        this.resResult = resResult;
    }
}
