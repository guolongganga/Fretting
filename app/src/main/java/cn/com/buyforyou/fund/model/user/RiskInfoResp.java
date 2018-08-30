package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

/**
 * 作者：sunnyzeng on 2018/2/2 15:49
 * 描述：{"data":{"riskEvaluteStatus":"1","riskEvaluteVal":"稳健型"},"message":"成功","status":200}
 */

public class RiskInfoResp extends BaseResp<RiskInfoResp> {
    //1 表示已测， 0 表示 未测
    private String riskEvaluteStatus;
    private String riskEvaluteVal;
    private String serviceTel;
    private String invite_code;

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getRiskEvaluteStatus() {
        return riskEvaluteStatus;
    }

    public void setRiskEvaluteStatus(String riskEvaluteStatus) {
        this.riskEvaluteStatus = riskEvaluteStatus;
    }

    public String getRiskEvaluteVal() {
        return riskEvaluteVal;
    }

    public void setRiskEvaluteVal(String riskEvaluteVal) {
        this.riskEvaluteVal = riskEvaluteVal;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }
}
