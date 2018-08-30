package cn.com.buyforyou.fund.net;

/**
 * Created by ${sunnyzeng}
 * data: 2017/12/04
 */

public class HttpContent {

    //    Base地址
//    public static String baseUrl = "http://jdw.twszbhwl.com/";

    /** 登录 */
    public static final String user_login = "noPermission/login";
    /** 注册 */
    public static final String user_register = "noPermission/regist";
    /** 银行列表 */
    public static final String common_bank_list = "noPermission/c401SelectBank";
    /** 图片验证码 */
    public static final String image_code = "noPermission/imageBase64";
    /** 短信验证码 */
    public static final String phone_code = "noPermission/phoneCode";
    /** 开户绑卡 */
    public static final String open_account = "permissionCheck/openAccount";
    /** 我的资产 */
    public static final String fund_home = "permissionCheck/fundHome";
    /** 基金页 */
//    public static final String newest_fund = "noPermission/s428NewestFund";
    public static final String newest_fund = "noPermission/fundTypeListOrderBy";
    /** 主页 */
    public static final String get_home = "noPermission/getHome";
    /** 我的银行卡 */
    public static final String my_bankcard = "permissionCheck/fundHome/turnToMyBankCard";
    /** 检查是否可以更换银行卡 */
    public static final String change_bankcard_check = "permissionCheck/fundHome/changeBankCardCheck";
    /** 更换银行卡操作 */
    public static final String change_bankcard = "permissionCheck/fundHome/changeBankCard";
    /** 发送短信验证码 不需要图片验证码 更换银行卡 */
    public static final String send_phone_code = "permissionCheck/fundHome/sendPhoneCode";
    /** 我的手机号码 */
    public static final String change_phone_index = "permissionCheck/change/phone/index";
    /** 发送短信验证码 不需要图片验证码 更换手机号码 */
    public static final String change_phone_sendcode = "permissionCheck/change/phone/sendCode";
    /** 更换手机号 */
    public static final String change_phone_save = "permissionCheck/change/phone/save";
    /** 变更登录密码 */
    public static final String password_change_login = "permissionCheck/password/change/login";
    /** 变更交易密码 */
    public static final String password_change_trade = "permissionCheck/password/change/trade";
    /** 获取职业信息 */
    public static final String get_occupation = "permissionCheck/fundHome/getOccupation";
    /** 我的个人信息 */
    public static final String my_informationpage = "permissionCheck/fundHome/MyInformationPage";
    /** 个人涉税信息 */
    public static final String my_crsinfoquery= "permissionCheck/fundHome/crsinfoquery";
    /** 保存个人涉税信息 */
    public static final String save_crsinfoquery = "permissionCheck/fundHome/crsinfooperate";
    /** 修改个人信息 */
    public static final String change_my_information = "permissionCheck/fundHome/changeMyInformation";
    /** 找回登录密码 的 短信验证码 */
    public static final String password_phonecode = "noPermission/password/phoneCode";
    /** 找回登录密码 第一步 */
    public static final String password_check = "noPermission/password/check";
    /** 找回登录密码 第二步 */
    public static final String password_reset = "noPermission/password/reset";
    /** 找回交易密码 的 短信验证码 */
    public static final String trade_password_phonecode = "noPermission/tradePassword/phoneCode";
    /** 找回交易密码 第一步 */
    public static final String trade_password_check = "noPermission/tradePassword/check";
    /** 找回交易密码 第二步 */
    public static final String trade_password_reset = "noPermission/tradepassword/reset";
    /** 基金立即购买验证 */
//    public static final String buy_fund = "permissionCheck/sellFundPre";
    public static final String buy_fund = "permissionCheck/buyFundNew";
    /** 基金立即购买 */
//    public static final String buy_now = "permissionCheck/purchase";
    public static final String purchase = "permissionCheck/purchase";
    /** 基金搜索 */
    public static final String find_fund_like = "noPermission/findFundLike";
    /** 定投搜索 */
    public static final String find_fixed_fund_like = "noPermission/findFixedFundLike";
    /** 基金定投 */
    public static final String fund_invest_time = "noPermission/fund/times/getData";
    /** 定投 根据选择时间 显示下次扣款日 */
    public static final String get_next_time = "noPermission/fund/times/getnextTime";
    /** 定投购买 */
    public static final String fund_times_save = "noPermission/fund/times/save";
    /** 定投成功页 */
    public static final String fund_times_succdetails = "noPermission/fund/times/succDetails";
    /** 优选定投 */
    public static final String fund_type_fixed = "noPermission/fundTypeFixedOrderBy";
    /** 自选基金 */
    public static final String user_follow_data = "noPermission/user/follow/data";
    /** 我的定投 */
    public static final String my_times_buy_index = "noPermission/fund/times/myTimesBuyIndex";
    /** 定投详情 */
    public static final String my_times_buy_detail = "noPermission/fund/times/myTimesBuyDetail";
    /** 定投变更状态 */
    public static final String my_times_buy_state_change = "noPermission/fund/times/myTimesBuyStateChange";
    /** 交易查询 */
    public static final String trade_query = "permissionCheck/myFound/tradeQuery";
    /** 分红 */
    public static final String share_out_bonus_trade = "permissionCheck/myFound/shareOutBonusTradeQuery";
    /** 我的分红 */
    public static final String bonus_his_page = "permissionCheck/bonus/his/page";
    /** 修改分红方式列表 */
    public static final String bonus_xg_page = "permissionCheck/bonus/xg/page";
    /** 修改分红方式详情 */
    public static final String bonus_xg_details = "permissionCheck/bonus/xg/details";
    /** 修改分红方式 */
    public static final String bonus_xg_modifybonus = "permissionCheck/bonus/xg/modifybonus";
    /** 单只基金交易查询 */
    public static final String single_trade_query = "permissionCheck/myFound/singleFundTradeQuery";
    /** 修改定投 */
    public static final String buy_updata_data = "noPermission/fund/times/getMyTimesBuyUpdataData";
    /** 定投计划 */
    public static final String buy_on_fund = "noPermission/fund/times/myTimesBuyOnFundData";
    /** 撤单列表页 */
    public static final String withdraw_apply_list = "noPermission/withdrawapply/list";
    /** 撤单交易详情页 */
    public static final String withdraw_apply_detail = "noPermission/withdrawapply/detail";
    /** 撤单操作 */
    public static final String withdraw_apply_operate = "noPermission/withdrawapply/operate";
    /** 撤单成功 */
    public static final String withdraw_apply_succDetail = "noPermission/withdrawapply/succDetail";
    /** 设置页面 显示风险等级 */
    public static final String setup_index = "permissionCheck/setup/index";
    /** 赎回准备 */
    public static final String redeem_init = "permissionCheck/redeem/init";
    /** 确认赎回 */
    public static final String open_remitbranch_bank = "permissionCheck/redeem/openremitbranchbank";
    /** 计算这个手续费 */
    public static final String buy_fund_calculation = "permissionCheck/buyFundNew/calculation";
   /**开户检测手机号是否存在*/
    public static final String open_check_phone_exist = "noPermission/registCheckPhoneNo";

//    检测是否已存在账号
    public static final String open_check_id_exist = "/permissionCheck/openAccount/checkAccount";
//    检测版本更新
    public static final String check_version = "/noPermission/versionCheck";



    /************************************************ html页面 **************************************************/

    /** 基金详情页 */
    public static final String fund_detail = "htmlNoPermission/fundDetail";
    /** 风险测评 */
    public static final String risk_question = "htmlNoPermission/risk/getQuestions";
    /** 风险等级 */
    public static final String risk_dengji = "htmlNoPermission/risk/dengji";
    /** 持有基金详情页 */
    public static final String hold_fund_detail = "htmlNoPermission/myFound/holdFundDetail";
    /** 权益须知 */
    public static final String openaccount_agreement = "htmlNoPermission/openAccount/agreement";
    /** 服务协议 */
    public static final String openaccount_instructions = "htmlNoPermission/openAccount/instructions";
    /** 定投的协议 */
    public static final String agreement_fdtimesbuy = "htmlNoPermission/agreement/fdTimesBuy";
    /** 关于我们 */
    public static final String about_us = "htmlNoPermission/aboutUs";


}
