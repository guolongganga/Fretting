package cn.com.buyforyou.fund.event;

/**
 * 作者：sunnyzeng on 2018/3/20 13:58
 * 描述：刷新分红方式
 */

public class RefreshBonusEvent {
    private String bonusType;
    public RefreshBonusEvent(String bonusType) {
        this.bonusType = bonusType;
    }

    public String getBonusType() {
        return bonusType;
    }
}
