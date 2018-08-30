package cn.com.buyforyou.fund.model.user;


import java.util.List;

import cn.com.buyforyou.fund.model.BaseResp;

public class ResidentsTaxInfoResp extends BaseResp<ResidentsTaxInfoResp> {
    //税收信息
    private CrsmainResp crsmain;


    // 现居住国家 中文
    private List<OccupationResp> nationList;
    // 现居住国家 英文
    private List<OccupationResp> nationList_en;
    // 无居民国家地区纳税人识别号原因
    private List<OccupationResp> noTaxReasonList;


    public CrsmainResp getCrsmain() {
        return crsmain;
    }

    public void setCrsmain(CrsmainResp crsmain) {
        this.crsmain = crsmain;
    }

    public List<OccupationResp> getNationList() {
        return nationList;
    }

    public void setNationList(List<OccupationResp> nationList) {
        this.nationList = nationList;
    }


    public List<OccupationResp> getNationList_en() {
        return nationList_en;
    }

    public void setNationList_en(List<OccupationResp> nationList_en) {
        this.nationList_en = nationList_en;
    }

    public List<OccupationResp> getNoTaxReasonList() {
        return noTaxReasonList;
    }

    public void setNoTaxReasonList(List<OccupationResp> noTaxReasonList) {
        this.noTaxReasonList = noTaxReasonList;
    }

    @Override
    public String toString() {
        return "ResidentsTaxInfoResp{" +
                "crsmain=" + crsmain +
                ", nationList=" + nationList +
                ", nationList_en=" + nationList_en +
                ", noTaxReasonList=" + noTaxReasonList +
                '}';
    }
}
