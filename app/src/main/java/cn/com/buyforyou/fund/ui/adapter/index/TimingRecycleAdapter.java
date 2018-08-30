package cn.com.buyforyou.fund.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.fund.FundResp;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RateStyleUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by ${sunny}
 * data: 2017/12/19
 * 定投列表
 */

public class TimingRecycleAdapter extends SimpleRecAdapter<NewestFundResp, TimingRecycleAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识

    public TimingRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_index_timing_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final NewestFundResp fundResp = data.get(position);
        holder.tvName.setText(fundResp.getFund_name());
        holder.tvCode.setText(fundResp.getFund_code());
//        holder.tvRange.setText("+" + BigDecimalUtil.bigdecimalToString(fundResp.getFund_rose()) + "%");
        RateStyleUtil.rateStyle(context, holder.tvRange, fundResp.getFund_rose());
        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, fundResp, ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_content)
        RelativeLayout rlContent;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_code)
        TextView tvCode;
        @BindView(R.id.tv_range)
        TextView tvRange;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
