package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 切换账户
 * Created by ${Yis}
 * data: 2017/12/14
 */

public class SwitchAccountRecycleAdapter extends SimpleRecAdapter<String, SwitchAccountRecycleAdapter.ViewHolder> {

    public static final int ITEM_CLICK = 0;    //点击标识
    public static final int DELETE_CLICK = 1;    //点击标识

    private int isSelector;//当前选中的账号

    private boolean showDelete;//true：编辑状态

    public void setIsSelector(int isSelector) {
        this.isSelector = isSelector;
    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }

    public SwitchAccountRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_user_switch_account_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(data.get(position));

        if (isSelector == position) {
            holder.ivSelector.setBackgroundResource(R.mipmap.selector_true);
        } else {
            holder.ivSelector.setBackgroundResource(R.mipmap.selector_false);
        }

        if (showDelete) {
            holder.tvDelete.setVisibility(View.VISIBLE);
            holder.ivSelector.setVisibility(View.GONE);
        } else {
            holder.tvDelete.setVisibility(View.GONE);
            holder.ivSelector.setVisibility(View.VISIBLE);
        }

        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, data.get(position), ITEM_CLICK, holder);
            }
        });

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, data.get(position), DELETE_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_content)
        LinearLayout llContent;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv_selector)
        ImageView ivSelector;
        @BindView(R.id.tv_delete)
        TextView tvDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
