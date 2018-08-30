package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.ArrayList;

/**
 * 作者：sunnyzeng on 2018/1/31 14:37
 * 描述：
 */

public class ResultDetailResp extends BaseResp<ResultDetailResp> {
    private RecordResp record;
    private ArrayList<StepResp> stepList;

    public RecordResp getRecord() {
        return record;
    }

    public void setRecord(RecordResp record) {
        this.record = record;
    }

    public ArrayList<StepResp> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<StepResp> stepList) {
        this.stepList = stepList;
    }
}
