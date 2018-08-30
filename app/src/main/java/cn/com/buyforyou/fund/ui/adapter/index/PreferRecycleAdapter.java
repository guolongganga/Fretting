package cn.com.buyforyou.fund.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.index.ProductModel;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RateStyleUtil;

import java.math.BigDecimal;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 优选定投
 * Created by ${zengsuwa}
 * data: 2017/12/14
 */

public class PreferRecycleAdapter extends SimpleRecAdapter<ProductModel, PreferRecycleAdapter.ViewHolder> {

    public static final int ITEM_CLICK = 0;    //点击标识

    public PreferRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_index_prefer_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ProductModel model = data.get(position);
        holder.preferredName.setText(model.getFund_name());
        RateStyleUtil.rateStyleNoPer(context, holder.preferredRate, model.getFund_rose());
        if (model.getFund_rose() != null && model.getFund_rose().compareTo(BigDecimal.ZERO) > 0) {
            holder.tvPercent.setTextColor(context.getResources().getColor(R.color.color_main));
        } else {
            holder.tvPercent.setTextColor(context.getResources().getColor(R.color.x_green));
        }
//        holder.preferredRate.setText(BigDecimalUtil.bigdecimalToString(model.getFund_rose()) + "%");
        holder.tvRateDesc.setText(model.getRiseTermDesc());

        holder.btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, model, ITEM_CLICK, holder);
            }
        });
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, model, ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_content)
        LinearLayout llContent;
        @BindView(R.id.preferred_name)
        TextView preferredName;
        @BindView(R.id.preferred_rate)
        TextView preferredRate;
        @BindView(R.id.tv_rate_desc)
        TextView tvRateDesc;
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        @BindView(R.id.btn_invest)
        Button btnInvest;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
