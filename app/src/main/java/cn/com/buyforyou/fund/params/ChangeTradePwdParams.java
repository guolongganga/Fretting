package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2017/12/27 16:49
 * 描述：修改交易密码参数
 */

public class ChangeTradePwdParams extends CommonReqData<ChangeTradePwdParams> {
    private String tradePassword;
    private String repetTradePassword;
    private String oldTradePassword;

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword;
    }

    public String getRepetTradePassword() {
        return repetTradePassword;
    }

    public void setRepetTradePassword(String repetTradePassword) {
        this.repetTradePassword = repetTradePassword;
    }

    public String getOldTradePassword() {
        return oldTradePassword;
    }

    public void setOldTradePassword(String oldTradePassword) {
        this.oldTradePassword = oldTradePassword;
    }
}
