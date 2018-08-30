package cn.com.buyforyou.fund.ui.adapter.boot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RateStyleUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 搜索下拉选择
 * Created by ${Yis}
 * data: 2017/12/14
 */

public class SearchRecycleAdapter extends SimpleRecAdapter<NewestFundResp, SearchRecycleAdapter.ViewHolder> {

    public static final int ITEM_CLICK = 0;    //点击标识

    public SearchRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_boot_search_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final NewestFundResp fundResp = data.get(position);
        holder.tvFundName.setText(fundResp.getFund_name());
        RateStyleUtil.rateStyle(context, holder.tvFundRate, fundResp.getFund_rose());
//        holder.tvFundRate.setText(BigDecimalUtil.bigdecimalToString(fundResp.getFund_rose()) + "%");
        holder.tvFundCode.setText(fundResp.getFund_code());
        holder.tvFundPerTime.setText("近一年");

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
        @BindView(R.id.tv_fund_name)
        TextView tvFundName;
        @BindView(R.id.tv_fund_rate)
        TextView tvFundRate;
        @BindView(R.id.tv_fund_code)
        TextView tvFundCode;
        @BindView(R.id.tv_fund_per_time)
        TextView tvFundPerTime;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
