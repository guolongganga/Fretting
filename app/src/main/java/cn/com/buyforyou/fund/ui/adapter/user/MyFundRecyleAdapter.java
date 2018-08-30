package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.user.HoldFundResp;
import cn.com.buyforyou.fund.model.user.MyHoldFundResp;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RateStyleUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：sunnyzeng on 2017/12/18 14:07
 * 描述：我的持仓基金
 */

public class MyFundRecyleAdapter extends SimpleRecAdapter<MyHoldFundResp, MyFundRecyleAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识

    public MyFundRecyleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_user_my_fund_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MyHoldFundResp resp = data.get(position);
        holder.tvName.setText(resp.getFundName()+"("+resp.getFundCode()+")");
        holder.tvMoney.setText(BigDecimalUtil.bigdecimalToString(resp.getHoldAmount()));

        RateStyleUtil.amountStyle(context, holder.tvYesterday, resp.getEarningsLastDay());
        RateStyleUtil.amountStyle(context, holder.tvHold, resp.getTotalEarn());
//        holder.tvYesterday.setText("+" + BigDecimalUtil.bigdecimalToString(resp.getEarningsLastDay()));
//        holder.tvHold.setText("-" + BigDecimalUtil.bigdecimalToString(resp.getHoldAmount()));
        if (resp.getSureNumber() == null || "0".equals(resp.getSureNumber())) {
            holder.llNumberIng.setVisibility(View.GONE);
        } else {
            holder.llNumberIng.setVisibility(View.VISIBLE);
            holder.tvNumberIng.setText(resp.getSureNumber());
        }
        if (data.size() - 1 == position) {
            holder.viewLine.setVisibility(View.GONE);
        } else {
            holder.viewLine.setVisibility(View.VISIBLE);
        }
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, resp, ITEM_CLICK, holder);
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** 内容区域 */
        @BindView(R.id.ll_content) LinearLayout llContent;
        /** 基金名称 */
        @BindView(R.id.tv_name) TextView tvName;
        /** 余额 */
        @BindView(R.id.tv_money) TextView tvMoney;
        /** 昨日收益 */
        @BindView(R.id.tv_yesterday) TextView tvYesterday;
        /** 持有收益 */
        @BindView(R.id.tv_hold) TextView tvHold;
        /** 分割线 */
        @BindView(R.id.view_line) View viewLine;
        /** X笔交易确认中 */
        @BindView(R.id.ll_number_ing) LinearLayout llNumberIng;
        /** X笔交易确认中 */
        @BindView(R.id.tv_number_ing) TextView tvNumberIng;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
