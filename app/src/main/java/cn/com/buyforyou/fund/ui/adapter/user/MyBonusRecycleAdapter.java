package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.MyBonusResp;
import cn.com.buyforyou.fund.model.user.TransactionResp;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by ${sunny}
 * data: 2017/12/19
 */

public class MyBonusRecycleAdapter extends SimpleRecAdapter<MyBonusResp, MyBonusRecycleAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识

    public MyBonusRecycleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_user_transaction_content_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MyBonusResp resp = data.get(position);

        holder.tvAmount.setText(resp.getBonusTotal());

            holder.tvStatus.setVisibility(View.GONE);

        holder.tvType.setText(resp.getAutoBuyVal());
        holder.tvFundName.setText(resp.getFundName());
        holder.tvFundCode.setText(resp.getFundcode());
        holder.tvTime.setText(resp.getDividenddate());
        holder.llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, data.get(position), ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**
         * 点击区域
         */
        @BindView(R.id.ll_content)
        LinearLayout llContent;
        /**
         * 类型
         */
        @BindView(R.id.tv_type)
        TextView tvType;
        /**
         * 基金名称
         */
        @BindView(R.id.tv_fund_name)
        TextView tvFundName;
        /**
         * 基金代码
         */
        @BindView(R.id.tv_fund_code)
        TextView tvFundCode;
        /**
         * 完成时间
         */
        @BindView(R.id.tv_time)
        TextView tvTime;
        /**
         * 金额
         */
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        /**
         * 状态
         */
        @BindView(R.id.tv_status)
        TextView tvStatus;


        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
