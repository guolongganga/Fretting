package cn.com.buyforyou.fund.ui.adapter.boot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 下拉选择
 * Created by ${Yis}
 * data: 2017/12/14
 */

public class PopDropSelectorRecycleAdapter extends SimpleRecAdapter<ApplyBaseInfo, PopDropSelectorRecycleAdapter.ViewHolder> {

    public static final int ITEM_CLICK = 0;    //点击标识

    private int isSelector;//当前选中的账号


    public void setIsSelector(int isSelector) {
        this.isSelector = isSelector;
    }

    public PopDropSelectorRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_pop_drop_selector_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(data.get(position).getContent());

        if (isSelector == position) {
            holder.ivSelector.setVisibility(View.VISIBLE);
        } else {
            holder.ivSelector.setVisibility(View.INVISIBLE);
        }

        if (data.size() - 1 == position) {
            holder.viewLine.setVisibility(View.GONE);
        } else {
            holder.viewLine.setVisibility(View.VISIBLE);
        }

        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, data.get(position), ITEM_CLICK, holder);
            }
        });


    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_content)
        RelativeLayout rlContent;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.view_line)
        View viewLine;
        @BindView(R.id.iv_selector)
        ImageView ivSelector;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
