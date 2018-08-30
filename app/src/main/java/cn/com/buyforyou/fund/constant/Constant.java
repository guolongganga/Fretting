package cn.com.buyforyou.fund.constant;

/**
 * 作者：sunnyzeng on 2017/12/5
 * 描述：静态常量
 */

public class Constant {

    public static final String BASE_CONSTANT = "fretting_";
    //token 登录标识 存本地
    public static final String TOKEN = BASE_CONSTANT + "token";

    //userId 用户编号 存本地
    public static final String USERID = BASE_CONSTANT + "userId";

    //手机号 存本地
    public static final String USER_PHONE = BASE_CONSTANT + "user_phone";

    //服务号码
    public static final String SERVICE_TEL = BASE_CONSTANT + "service_tel";

    //身份证号
    public static final String USER_CERTNO = BASE_CONSTANT + "user_certno";

    //是否开户 1未开户  0 开户 存本地
    public static final String IS_OPEN_ACCOUNT = BASE_CONSTANT + "is_open_account";

    //已开户
    public static final String ALREADY_OPEN_ACCOUNT = "0";

    //密码
    public static final String PWD = BASE_CONSTANT + "pwd";

    //是否第一次使用APP true:未使用
    public static final String FIRST_USE = BASE_CONSTANT + "first_use";

    //32位长度加密Key
    public static final String AESKEY = "0e441613d8a611e784ef6c92bf314e43";

    //WebView标题
    public static final String WEB_TITLE = BASE_CONSTANT + "web_title";

    //WebView链接
    public static final String WEB_LINK = BASE_CONSTANT + "web_link";

    //基金页面中的tab名称
    public static final String FUND_TAB_NAME = BASE_CONSTANT + "fund_tab_name";

    //页面名称，人气产品或基金
    public static final String ACTIVITY_NAME = BASE_CONSTANT + "activity_name";

    //选择的银行
    public static final String CHOOSE_BANCK = BASE_CONSTANT + "";

    //注册传递电话号码
    public static final String PHONE = BASE_CONSTANT + "phone";

    //注册成功传递姓名
    public static final String NAME = BASE_CONSTANT + "name";

    //注册成功传递身份证号
    public static final String CERT_NO = BASE_CONSTANT + "cert_no";

    //修改手机号传递数据
    public static final String CHANGE_PHONE = "change_phone";

    //修改银行卡传递数据
    public static final String CHANGE_BANK = "change_bank";

    //修改银行卡成功
    public static final String CHANGE_BANK_SUCCESS = "0";

    //跳转登录页面标识
    public static final String SKIP_SIGN = "skip_sign";

    //变更登录密码 跳转登录页面 skip_sign
    public static final String SKIP_MAIN_ACTIVITY = "300";

    //变更交易密码 跳转登录页面 skip_sign
    public static final String SKIP_INDEX_ACTIVITY = "301";

    //找回交易密码 跳转登录页面 skip_sign
//    public static final String FIND_TRADE_ACTIVITY = "302";

    //web页面 跳转登录页面 skip_sign
    public static final String WEB_ACTIVITY = "303";

    //投资页的类型，是定投，还是定投修改
    public static final String INVEST_ACTIVITY_TYPE = "invest_activity_type";

    //投资页的类型，定投
    public static final String INVEST_ACTIVITY = "invest_activity";

    //投资页的类型，定投修改
    public static final String INVEST_ACTIVITY_UPDATE = "invest_activity_update";

    //跳转到主页面，我的tab
    public static final String MAIN_MY = "main_my";

    //跳转到主页面，我的tab
    public static final String MAIN_INDEX = "main_index";

    //可以购买跳转购买页面携带参数
    public static final String BUY_FUND_OBJECT = "buy_fund_object";

    //购买成功传递数据
    public static final String BUY_SUCCESS_OBJECT = "buy_success_object";

    //基金列表-->基金详情页传递基金代码
    public static final String FUND_DETAIL_CODE = "fund_detail_code";

    //基金列表-->基金详情页传递基金名称
    public static final String FUND_DETAIL_NAME = "fund_detail_name";

    //详情页点击定投跳转定投页面携带参数
    public static final String INVEST_FUND_OBJECT = "invest_fund_object";

    //定投页 跳转定投成功 携带参数
    public static final String INVEST_SUCCESS_OBJECT = "invest_success_object";

    //定投编号
    public static final String INVEST_PROTOCOL_ID = BASE_CONSTANT + "invest_protocol_id";

    //定投状态
    public static final String INVEST_STATUS = BASE_CONSTANT + "invest_status";

    //定投记录状态
    public static final String FUND_OBJECT = "fund_object";

    //分红方式
    public static final String BONUS_TYPE = "bonus_type";

    //页面名称，定投计划
    public static final String INVEST_PLAN = BASE_CONSTANT + "invest_plan";

    //页面名称，我的定投
    public static final String MY_INVEST = BASE_CONSTANT + "my_invest";

    //页面需要传递的对象
    public static final String ACTIVITY_OBJECT = BASE_CONSTANT + "activity_object";

    //页面标题
    public static final String ACTIVITY_TITLE = BASE_CONSTANT + "activity_title";

    //交易密码
    public static final String TRADE_PASSWORD = BASE_CONSTANT + "trade_password";


    /********************************************* 固定值 ********************************************/

    //股票型 参数
    public static final String FUND_TAB_SHARES_TYPE = "B";

    //混合型 参数
    public static final String FUND_TAB_BLEND_TYPE = "A";

    //债券型 参数
    public static final String FUND_TAB_BOND_TYPE = "E";

    //指数型 参数
    public static final String FUND_TAB_FINGER_TYPE = "F";

    //货币型 名称
    public static final String FUND_TAB_CURRENCY = "货币型";

    //股票型 名称
    public static final String FUND_TAB_SHARES = "股票型";

    //混合型 名称
    public static final String FUND_TAB_BLEND = "混合型";

    //债券型 名称
    public static final String FUND_TAB_BOND = "债券型";

    //指数型 名称
    public static final String FUND_TAB_FINGER = "指数型";

    //购买验证，未开户，跳转开户页面
    public static final Object TO_OPEN_ACCOUNT = "toOpenAccount";

    //购买验证，您所绑定的银行卡暂不支持该基金定投业务,请选择申购方式购买！
    public static final Object TO_CHANGE_ACCOUNT = "toChangeType";

    //购买验证，补全个人信息，跳转个人信息
    public static final Object TO_PERSON_INFO = "toPersonInfo";

    //购买验证，风险测评，跳转风险测评
    public static final Object TO_RISK_TEST = "toRiskTest";

    //购买验证，验证风险等级，弹出风险等级弹出框
    public static final Object TO_VALIDATE = "toValidate";

    //购买验证，去购买
    public static final Object TO_BUY = "toBuy";

    //股票型 名称
    public static final String TRANSACTION_TAB_PURCHASE = "买入";

    //混合型 名称
    public static final String TRANSACTION_TAB_SELLOUT = "赎回";

    //债券型 名称
    public static final String TRANSACTION_TAB_ONPASSAGE = "定投";

    //指数型 名称
    public static final String TRANSACTION_TAB_BONUS = "分红";

    //定投详情状态 终止
    public static final String INVEST_STATE_END = "H";

    //定投详情状态 暂停
    public static final String INVEST_STATE_STOP = "P";

    //定投详情状态 启动
    public static final String INVEST_STATE_ING = "A";

    //定投计划状态 启用
    public static final String INVEST_PLAN_ING = "启用";

    //定投计划状态 暂停
    public static final String INVEST_PLAN_STOP = "暂停";

    //定投计划状态 终止
    public static final String INVEST_PLAN_END = "终止";

    //我的持仓
    public static final String MY_HOLD = "我的持仓";

    //待确认基金
    public static final String MY_WAIT = "待确认基金";


    /********************************************* int ********************************************/

    //人气产品
    public static final int POPULARITY = 1;
    //基金主页
    public static final int FUND_INDEX = 2;

    //跳转银行列表 requestcode
    public static final int BANKLIST_ACTIVITY = 100;

    //银行卡列表选择之后回到之前页面 resultcode
    public static final int BANKLIST_RESULT_CODE = 200;

    //去修改手机页面 requestcode
    public static final int CHANGE_PHONE_ACTIVITY = 101;

    //修改手机号码成功后返回我的手机号码页面 resultcode
    public static final int SUCCESS_BACK_PHONE = 201;

    //定投购买 到 修改银行卡 requestcode
    public static final int INVEST_BANK_ACTIVITY = 102;

    //我的银行卡后 返回我的手机号码页面 resultcode
    public static final int SUCCESS_BACK_BANK = 202;

    //定投计划 跳转 定投详情页 requestcode
    public static final int INVEST_PLAN_ACTIVITY = 103;

    //定投详情 返回 定投计划页 resultcode
    public static final int INVEST_DETAIL_BACK = 203;

    //详情页 到 分红方式 requestcode
    public static final int WEB_BONUS_ACTIVITY = 104;

    //分红方式修改成功后返回 resultcode
    public static final int BONUS_BACK_ACTIVITY = 204;

    //去风险测评后返回 requestcode
    public static final int WEB_RISK_ACTIVITY = 105;

    //分红方式修改成功后返回 resultcode
    public static final int RISK_BACK_ACTIVITY = 205;

    //接口状态值  未登录
    public static final int NO_LOGIN_STATUS = 520;

    //接口状态值  未登录
    public static final int PASSWORD_ERROR_STATUS = 526;
}
