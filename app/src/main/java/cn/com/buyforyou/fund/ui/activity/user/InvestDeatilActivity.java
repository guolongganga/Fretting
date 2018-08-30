package cn.com.buyforyou.fund.ui.activity.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.com.buyforyou.fund.App;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.event.InvalidTokenEvent;
import cn.com.buyforyou.fund.model.fund.InvestResp;
import cn.com.buyforyou.fund.model.user.InvestRecordResp;
import cn.com.buyforyou.fund.present.user.InvestDetailPresent;
import cn.com.buyforyou.fund.ui.activity.fund.InvestActivity;
import cn.com.buyforyou.fund.ui.adapter.user.InvestRecordRecyleAdapter;
import cn.com.buyforyou.fund.ui.adapter.user.MyFundRecyleAdapter;
import com.zhsoft.fretting.ui.widget.CustomDialog;
import com.zhsoft.fretting.ui.widget.FundBuyDialog;
import cn.com.buyforyou.fund.utils.RuntimeHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.dialog.httploadingdialog.HttpLoadingDialog;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2018/1/22 17:03
 * 描述： 定投详情页
 * <p>
 * 状态： A：启用 P：暂停 H：终止
 */

public class InvestDeatilActivity extends XActivity<InvestDetailPresent> {
    @BindView(R.id.head_back) ImageButton headBack;
    @BindView(R.id.head_title) TextView headTitle;
    @BindView(R.id.tv_fund_name) TextView tvFundName;
    @BindView(R.id.tv_fund_code) TextView tvFundCode;
    @BindView(R.id.tv_invest_status) TextView tvInvestStatus;
    @BindView(R.id.tv_invest_day) TextView tvInvestDay;
    @BindView(R.id.tv_sum) TextView tvSum;
    @BindView(R.id.tv_total) TextView tvTotal;
    @BindView(R.id.tv_stage) TextView tvStage;
    @BindView(R.id.tv_bank_name) TextView tvBankName;
    @BindView(R.id.tv_bank_tail) TextView tvBankTail;
    @BindView(R.id.tv_protocol_number) TextView tvProtocolNumber;
    @BindView(R.id.tv_day_week) TextView tvDayWeek;
    @BindView(R.id.tv_next_time) TextView tvNextTime;
    @BindView(R.id.tv_next_time_hint) TextView tvNextTimeHint;
    @BindView(R.id.xrv_invest_record) XRecyclerView xrvInvestRecord;
    @BindView(R.id.btn_end) Button btnEnd;
    @BindView(R.id.btn_stop) Button btnStop;
    @BindView(R.id.btn_update) Button btnUpdate;
    @BindView(R.id.btn_recovery) Button btnRecovery;

    /** 登录标识 */
    private String token;
    /** 用户编号 */
    private String userId;
    /** 加载圈 */
    private HttpLoadingDialog httpLoadingDialog;
    /** 协议编号 */
    private String protocol_id;
    /** 定投状态 */
    private String investStatus;
    /** 终止弹框 */
    private CustomDialog endDialog;
    /** 输入密码弹框 */
    private FundBuyDialog endPasswordDialog;
    /** 暂停弹框 */
    private CustomDialog stopDialog;
    /** 输入密码弹框 */
    private FundBuyDialog stopPasswordDialog;
    /** 恢复弹框 */
    private CustomDialog recoveryDialog;
    /** 输入密码弹框 */
    private FundBuyDialog recoveryPasswordDialog;
    /** 密码错误弹框 */
    private CustomDialog customDialog;
    /** 基金代码 */
    private String fundCode;
    /** 基金名称 */
    private String fundName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_invest_detail;
    }

    @Override
    public InvestDetailPresent newP() {
        return new InvestDetailPresent();
    }

    @Override
    public void initData(Bundle bundle) {
        headTitle.setText("定投详情");
        httpLoadingDialog = new HttpLoadingDialog(context);
        token = App.getSharedPref().getString(Constant.TOKEN, "");
        userId = App.getSharedPref().getString(Constant.USERID, "");
        //防止一进页面显示在最底部
        xrvInvestRecord.setFocusable(false);
        xrvInvestRecord.verticalLayoutManager(context);//设置RecycleView类型 - 不设置RecycleView不显示

        if (bundle != null) {
            protocol_id = bundle.getString(Constant.INVEST_PROTOCOL_ID);
            investStatus = bundle.getString(Constant.INVEST_STATUS);
        }

        httpLoadingDialog.visible();
        getP().investDetailData(token, userId, protocol_id);

        if (Constant.INVEST_PLAN_STOP.equals(investStatus)) {
            //如果是暂停状态，显示 暂停
            tvInvestStatus.setVisibility(View.VISIBLE);
            //下次扣款时间：--，
            tvDayWeek.setVisibility(View.GONE);
            tvNextTime.setVisibility(View.VISIBLE);
            //请保持账户资金充足
            tvNextTimeHint.setText("请保持账户资金充足");
            //暂停按钮消失
            btnStop.setVisibility(View.GONE);
            //修改按钮消失
            btnUpdate.setVisibility(View.GONE);
            //恢复按钮显示
            btnRecovery.setVisibility(View.VISIBLE);
        } else {
            tvInvestStatus.setVisibility(View.GONE);
            tvDayWeek.setVisibility(View.VISIBLE);
            tvNextTime.setVisibility(View.GONE);
            tvNextTimeHint.setText("将进行新一期定投扣款，请保持账户资金充足");
            btnStop.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);
            btnRecovery.setVisibility(View.GONE);
        }

    }

    @Override
    public void initEvents() {
        //返回
        headBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //终止
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (endDialog == null) {
                    endDialog = new CustomDialog.Builder(context)
                            .setMessage("终止后将不再执行定期扣款协议且无法恢复，确认要终止定投吗？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    endDialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    endDialog.dismiss();
                                    //弹出框
                                    if (endPasswordDialog == null) {
                                        endPasswordDialog = new FundBuyDialog
                                                .Builder(context)
                                                .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                                                    @Override
                                                    public void onFinish(String str) {
                                                        endPasswordDialog.dismiss();
                                                        //请求终止接口
                                                        httpLoadingDialog.visible();
                                                        getP().changeState(token, userId, protocol_id, Constant.INVEST_STATE_END, str);

                                                    }
                                                }).create();
                                    }
                                    endPasswordDialog.show();

                                }
                            }).create();
                }
                endDialog.show();
            }
        });
        //暂停
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stopDialog == null) {
                    stopDialog = new CustomDialog.Builder(context)
                            .setMessage("暂停后将不再执行定期扣款，确定要暂停定投吗？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    stopDialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    stopDialog.dismiss();
                                    //弹出框
                                    if (stopPasswordDialog == null) {
                                        stopPasswordDialog = new FundBuyDialog
                                                .Builder(context)
                                                .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                                                    @Override
                                                    public void onFinish(String str) {
                                                        stopPasswordDialog.dismiss();
                                                        //请求暂停接口
                                                        httpLoadingDialog.visible();
                                                        getP().changeState(token, userId, protocol_id, Constant.INVEST_STATE_STOP, str);

                                                    }
                                                }).create();
                                    }
                                    stopPasswordDialog.show();

                                }
                            }).create();
                }
                stopDialog.show();
            }
        });
        //恢复
        btnRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recoveryDialog == null) {
                    recoveryDialog = new CustomDialog.Builder(context)
                            .setMessage("恢复后将执行定期扣款，请确保支付银行卡资金充足")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    recoveryDialog.dismiss();
                                }
                            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    recoveryDialog.dismiss();
                                    // 弹出框
                                    if (recoveryPasswordDialog == null) {
                                        recoveryPasswordDialog = new FundBuyDialog
                                                .Builder(context)
                                                .setOnTextFinishListener(new FundBuyDialog.OnTextFinishListener() {
                                                    @Override
                                                    public void onFinish(String str) {
                                                        recoveryPasswordDialog.dismiss();
                                                        // 请求恢复接口
                                                        httpLoadingDialog.visible();
                                                        getP().changeState(token, userId, protocol_id, Constant.INVEST_STATE_ING, str);

                                                    }
                                                }).create();
                                    }
                                    recoveryPasswordDialog.show();

                                }
                            }).create();
                }
                recoveryDialog.show();
            }
        });
        //修改定投
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //修改定投
                httpLoadingDialog.visible();
                getP().investTimeUpdata(token, userId, protocol_id);
            }
        });
    }

    /**
     * 初始化定投计划adapter
     *
     * @return
     */
    public SimpleRecAdapter getInvestRecordAdapter() {
        InvestRecordRecyleAdapter adapter = new InvestRecordRecyleAdapter(context);
        xrvInvestRecord.setAdapter(adapter);
        adapter.setRecItemClick(new RecyclerItemCallback<InvestRecordResp, InvestRecordRecyleAdapter.ViewHolder>() {
            @Override
            public void onItemClick(int position, InvestRecordResp model, int tag, InvestRecordRecyleAdapter.ViewHolder holder) {
                super.onItemClick(position, model, tag, holder);
                switch (tag) {
                    //点击
                    case MyFundRecyleAdapter.ITEM_CLICK:

                        Bundle bundle = new Bundle();
                        bundle.putString(Constant.ACTIVITY_TITLE, getString(R.string.result_title_buy));
                        //交易流水号
                        bundle.putString(Constant.INVEST_PROTOCOL_ID, model.getAllot_no());

                        if ("11".equals(model.getStatus())) {
                            //TODO 交易成功，待确认份额
                            startActivity(ResultDetailOneActivity.class, bundle);

                        } else if ("22".equals(model.getStatus())) {
                            //TODO 买入确认成功
                            startActivity(ResultDetailThreeActivity.class, bundle);

                        } else {
                            //TODO 撤单成功 支付失败 确认失败
                            startActivity(ResultDetailTwoActivity.class, bundle);
                        }

                        break;
                }
            }
        });
        return adapter;
    }

    /**
     * 请求定投详情接口数据成功
     */
    public void requestInvestDetailSuccess(InvestResp resp) {
        httpLoadingDialog.dismiss();
        if (resp != null) {
            fundName = resp.getFund_name();
            fundCode = resp.getFundCode();
            tvFundName.setText(fundName);
            tvFundCode.setText("(" + fundCode + ")");
            tvInvestDay.setText(resp.getFixDateDetails());
            tvSum.setText(resp.getApply_sum() + "");
            tvTotal.setText(resp.getTotal_succ_sum());
            tvStage.setText(resp.getTotal_succ_time());
            tvBankName.setText(resp.getBankCardPageEntity().getBankName());
            tvBankTail.setText("(" + resp.getBankCardPageEntity().getBankNoTail() + ")");
            tvProtocolNumber.setText(resp.getScheduled_protocol_id());
            tvDayWeek.setText(resp.getNext_fixrequest_date() + "  " + resp.getExchWeek());
        }

    }

    /**
     * 请求定投详情接口数据失败
     */
    public void requestInvestDetailFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 请求修改定投接口失败
     */
    public void requestInvestFail() {
        httpLoadingDialog.dismiss();

    }

    /**
     * 请求修改定投接口成功
     *
     * @param resp
     */
    public void requestInvestSuccess(final InvestResp resp) {
        httpLoadingDialog.dismiss();
        //去修改定投
        Bundle bundle = new Bundle();
        bundle.putString(Constant.INVEST_ACTIVITY_TYPE, Constant.INVEST_ACTIVITY_UPDATE);
        bundle.putString(Constant.FUND_DETAIL_CODE, fundCode);
        bundle.putString(Constant.FUND_DETAIL_NAME, fundName);
        bundle.putString(Constant.INVEST_PROTOCOL_ID, protocol_id);
        bundle.putParcelable(Constant.INVEST_FUND_OBJECT, resp);
        startActivity(InvestActivity.class, bundle);

    }

    /**
     * 修改状态成功
     */
    public void requestChangeStateSuccess() {
        httpLoadingDialog.dismiss();
        setResult(Constant.INVEST_DETAIL_BACK);
        finish();

    }

    /**
     * 修改状态失败
     */
    public void requestChangeStateFail() {
        httpLoadingDialog.dismiss();
    }

    /**
     * 立即购买 密码错误
     */
    public void passwordError(final String investState,String message) {
        httpLoadingDialog.dismiss();
        if (customDialog == null) {
            customDialog = new CustomDialog.Builder(context)
                    .setMessage(message)
                    .setNegativeButton("忘记密码", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customDialog.dismiss();
                            startActivity(FindPwdTradeFirstActivity.class);
                        }
                    }).setPositiveButton("再试一次", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customDialog.dismiss();
                            if (Constant.INVEST_STATE_END.equals(investState)) {
                                endPasswordDialog.show();
                            } else if (Constant.INVEST_STATE_STOP.equals(investState)) {
                                stopPasswordDialog.show();
                            } else if (Constant.INVEST_STATE_ING.equals(investState)) {
                                recoveryPasswordDialog.show();
                            }
                        }
                    }).create();
        }
        customDialog.show();
    }

    @Override
    protected void onDestroy() {
        if (endDialog != null) {
            endDialog.dismiss();
            endDialog = null;
        }
        if (endPasswordDialog != null) {
            endPasswordDialog.dismiss();
            endPasswordDialog = null;
        }
        if (stopDialog != null) {
            stopDialog.dismiss();
            stopDialog = null;
        }
        if (stopPasswordDialog != null) {
            stopPasswordDialog.dismiss();
            stopPasswordDialog = null;
        }
        if (recoveryDialog != null) {
            recoveryDialog.dismiss();
            recoveryDialog = null;
        }
        if (recoveryPasswordDialog != null) {
            recoveryPasswordDialog.dismiss();
            recoveryPasswordDialog = null;
        }

        if (customDialog != null) {
            customDialog.dismiss();
            customDialog = null;
        }
        super.onDestroy();
    }

    /**
     * 已经登出系统，请重新登录
     */
    public void areadyLogout() {
        httpLoadingDialog.dismiss();
//        EventBus.getDefault().post(new InvalidTokenEvent());
        //清除本地缓存，设置成未登录
        RuntimeHelper.getInstance().isInvalidToken();
        //跳转登录界面
        Bundle bundle = new Bundle();
        bundle.putString(Constant.SKIP_SIGN, Constant.SKIP_INDEX_ACTIVITY);
        startActivity(LoginActivity.class, bundle);
    }
}
