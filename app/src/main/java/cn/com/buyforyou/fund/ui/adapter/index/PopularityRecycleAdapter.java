package cn.com.buyforyou.fund.ui.adapter.index;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.index.PopularityResp;
import cn.com.buyforyou.fund.model.index.ProductModel;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;
import cn.com.buyforyou.fund.utils.RateStyleUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 人气产品
 * Created by ${zengsuwa}
 * data: 2017/12/14
 */

public class PopularityRecycleAdapter extends SimpleRecAdapter<ProductModel, PopularityRecycleAdapter.ViewHolder> {

    public static final int ITEM_CLICK = 0;    //点击标识

    public PopularityRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_index_popularity_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ProductModel model = data.get(position);
//        holder.tvValue.setText(BigDecimalUtil.bigdecimalToString(model.getFund_rose()) + "%");
        RateStyleUtil.rateStyle(context, holder.tvValue, model.getFund_rose());
        holder.tvDescribe.setText(model.getRiseTermDesc());
        holder.tvTitle.setText(model.getFund_name());
        holder.tvLocationOne.setText(model.getFeatureone());
        holder.tvLocationTwo.setText(model.getFeaturetwo());

//        if (data.size() - 1 == position) { 
//            holder.viewLine.setVisibility(View.GONE);
//        } else {
//            holder.viewLine.setVisibility(View.VISIBLE);
//        }

        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, model, ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_content)
        RelativeLayout rlContent;
        @BindView(R.id.tv_value)
        TextView tvValue;
        @BindView(R.id.tv_describe)
        TextView tvDescribe;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_location_one)
        TextView tvLocationOne;
        @BindView(R.id.tv_location_two)
        TextView tvLocationTwo;
        @BindView(R.id.view_line)
        View viewLine;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
