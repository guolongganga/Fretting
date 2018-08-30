package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.fund.NewestFundResp;
import cn.com.buyforyou.fund.model.user.TransactionResp;
import cn.com.buyforyou.fund.utils.BigDecimalUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * Created by ${sunny}
 * data: 2017/12/19
 */

public class TransactionContentRecycleAdapter extends SimpleRecAdapter<TransactionResp, TransactionContentRecycleAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识
    private String tabType;

    public TransactionContentRecycleAdapter(Context context, String tabType) {
        super(context);
        this.tabType = tabType;
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
        TransactionResp resp = data.get(position);

        if (Constant.TRANSACTION_TAB_ONPASSAGE.equals(tabType)) {
            //交易在途
            holder.tvStatus.setTextColor(getColor(R.color.color_f7182d));
        } else {
            holder.tvStatus.setTextColor(getColor(R.color.color_696969));
        }

//        if ("我的分红".equals(tabType)) {
//            //我的分红
//            holder.ivArrow.setVisibility(View.VISIBLE);
//        } else {
//            holder.ivArrow.setVisibility(View.GONE);
//        }
        holder.tvAmount.setText(resp.getAmount());

        if (Constant.TRANSACTION_TAB_BONUS.equals(tabType)) {
            holder.tvStatus.setVisibility(View.GONE);
        } else {
            holder.tvStatus.setVisibility(View.VISIBLE);
            holder.tvStatus.setText(resp.getTans_status());
        }

//        if (Constant.TRANSACTION_TAB_BONUS.equals(tabType)) {
//            //申购
//            holder.tvAmount.setText(resp.getAmount() + "元");
//            holder.tvStatus.setVisibility(View.VISIBLE);
//            holder.tvStatus.setText(resp.getTans_status());
//
//        } else if ("定投".equals(resp.getIncomeType())) {
//            //定投
//            holder.tvAmount.setText(resp.getAmount() + "元");
//            holder.tvStatus.setVisibility(View.VISIBLE);
//            holder.tvStatus.setText(resp.getTans_status());
//
//        } else if ("赎回".equals(resp.getIncomeType())) {
//            //卖出
//            holder.tvAmount.setText(resp.getAmount() + "份");
//            holder.tvStatus.setVisibility(View.VISIBLE);
//            holder.tvStatus.setText(resp.getTans_status());
//
//        } else if ("分红再投资".equals(resp.getIncomeType())) {
//            //分红再投资
//            holder.tvAmount.setText(resp.getAmount() + "份额");
//            holder.tvStatus.setVisibility(View.GONE);
//
//        } else if ("现金分红".equals(resp.getIncomeType())) {
//            //现金分红
//            holder.tvAmount.setText(resp.getAmount() + "元");
//            holder.tvStatus.setVisibility(View.GONE);
//        } else {
//            holder.tvStatus.setVisibility(View.VISIBLE);
//            holder.tvStatus.setText(resp.getTans_status());
//        }


        holder.tvType.setText(resp.getIncomeType());
        holder.tvFundName.setText(resp.getFundName());
        holder.tvFundCode.setText(resp.getFundCode());
        holder.tvTime.setText(resp.getTime());
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

//        /**
//         * 箭头
//         */
//        @BindView(R.id.iv_arrow)
//        ImageView ivArrow;


        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
