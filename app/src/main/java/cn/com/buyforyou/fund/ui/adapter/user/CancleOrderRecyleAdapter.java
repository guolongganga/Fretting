package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.CancleOrderResp;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;
import cn.com.buyforyou.fund.model.user.TransactionResp;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：sunnyzeng on 2017/12/18 14:07
 * 描述：定投计划
 */

public class CancleOrderRecyleAdapter extends SimpleRecAdapter<CancleOrderResp, CancleOrderRecyleAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识

    public CancleOrderRecyleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_user_cancle_order_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        CancleOrderResp resp = data.get(position);
        holder.tvFundName.setText(resp.getFund_name());
        holder.tvFundCode.setText(resp.getFund_code());
        holder.tvTime.setText(resp.getApply_time());
        //（1、买入 0、卖出 修改分红方式）
        if ("1".equals(resp.getBuyType())) {
            holder.tvType.setText("买入");
        } else {
            holder.tvType.setText("卖出");
        }
        holder.tvmount.setText(BigDecimalUtil.bigdecimalToString(resp.getApply_share()) + "份");

        holder.btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, data.get(position), ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** 内容区域 */
        @BindView(R.id.ll_content) LinearLayout llContent;
        /** 基金名称 */
        @BindView(R.id.tv_fund_name) TextView tvFundName;
        /** 基金代码 */
        @BindView(R.id.tv_fund_code) TextView tvFundCode;
        /** 方式 */
        @BindView(R.id.tv_type) TextView tvType;
        /** 交易时间 */
        @BindView(R.id.tv_time) TextView tvTime;
        /** 份额 */
        @BindView(R.id.tv_amount) TextView tvmount;
        /** 份额 */
        @BindView(R.id.btn_cancle) Button btnCancle;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
