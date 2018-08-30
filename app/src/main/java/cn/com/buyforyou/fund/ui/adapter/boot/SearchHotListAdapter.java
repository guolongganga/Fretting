package cn.com.buyforyou.fund.ui.adapter.boot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：sunnyzeng on 2018/1/10 17:21
 * 描述：
 */

public class SearchHotListAdapter extends SimpleRecAdapter<NewestFundResp, SearchHotListAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识

    public SearchHotListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_boot_search_hot_rv_item;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvFundName.setText(data.get(position).getFund_name());
        holder.tvFundCode.setText(data.get(position).getFund_code());
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, data.get(position), ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** 基金名称 */
        @BindView(R.id.tv_fund_name) TextView tvFundName;
        /** 基金代码 */
        @BindView(R.id.tv_fund_code) TextView tvFundCode;
        /** 内容 */
        @BindView(R.id.ll_content) LinearLayout llContent;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
