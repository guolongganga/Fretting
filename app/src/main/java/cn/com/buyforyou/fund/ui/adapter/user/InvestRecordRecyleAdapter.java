package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;
import cn.com.buyforyou.fund.model.user.InvestRecordResp;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：sunnyzeng on 2017/12/18 14:07
 * 描述：定投记录
 */

public class InvestRecordRecyleAdapter extends SimpleRecAdapter<InvestRecordResp, InvestRecordRecyleAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识

    public InvestRecordRecyleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_user_invest_record_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        InvestRecordResp recordResp = data.get(position);
        holder.tvDay.setText(recordResp.getDay());
        holder.tvDayWeek.setText(recordResp.getDayWeek());
        holder.tvMoney.setText(recordResp.getAmount());
        holder.tvInvestStatus.setText(recordResp.getStatus());

        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, data.get(position), ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** 内容区域 */
        @BindView(R.id.rl_content) RelativeLayout rlContent;
        /** 定投日期 */
        @BindView(R.id.tv_day) TextView tvDay;
        /** 定投周 */
        @BindView(R.id.tv_day_week) TextView tvDayWeek;
        /** 定投金额 */
        @BindView(R.id.tv_money) TextView tvMoney;
        /** 定投状态 */
        @BindView(R.id.tv_invest_status) TextView tvInvestStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
