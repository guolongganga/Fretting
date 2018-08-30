package cn.com.buyforyou.fund.params;

/**
 * 作者：sunnyzeng on 2018/1/31 11:06
 * 描述： 撤单参数
 */

public class ResultParams extends CommonReqData<ResultParams> {
    private String allot_no;
    private String password;
    private String busin_board_type;
    private String comb_requestno;
    private String fund_code;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAllot_no() {
        return allot_no;
    }

    public void setAllot_no(String allot_no) {
        this.allot_no = allot_no;
    }

    public String getBusin_board_type() {
        return busin_board_type;
    }

    public void setBusin_board_type(String busin_board_type) {
        this.busin_board_type = busin_board_type;
    }

    public String getComb_requestno() {
        return comb_requestno;
    }

    public void setComb_requestno(String comb_requestno) {
        this.comb_requestno = comb_requestno;
    }

    public String getFund_code() {
        return fund_code;
    }

    public void setFund_code(String fund_code) {
        this.fund_code = fund_code;
    }
}
