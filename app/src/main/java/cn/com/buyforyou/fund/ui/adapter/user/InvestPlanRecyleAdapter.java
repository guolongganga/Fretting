package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.constant.Constant;
import cn.com.buyforyou.fund.model.user.InvestInfoResp;
import cn.com.buyforyou.fund.model.user.InvestPlanResp;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：sunnyzeng on 2017/12/18 14:07
 * 描述：定投计划
 */

public class InvestPlanRecyleAdapter extends SimpleRecAdapter<InvestInfoResp, InvestPlanRecyleAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识

    public InvestPlanRecyleAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_user_invest_plan_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        InvestInfoResp planResp = data.get(position);
        holder.tvFundName.setText(planResp.getFund_name());
        holder.tvFundCode.setText("(" + planResp.getFund_code() + ")");
        holder.tvInvesType.setText(planResp.getDt_way());
        holder.tvBankName.setText(planResp.getBank_name());
        holder.tvBankTail.setText("(" + planResp.getBank_account() + ")");
        holder.tvNextTime.setText(planResp.getNext_fixrequest_date());
        holder.tvInvestStatus.setText("【" + planResp.getScheduled_protocol_state() + "】");

        if (Constant.INVEST_PLAN_ING.equals(planResp.getScheduled_protocol_state())) {
            //启用 蓝色
            holder.ivArrow.setVisibility(View.VISIBLE);
            holder.tvInvestStatus.setTextColor(getColor(R.color.color_1a7bcd));
        } else if (Constant.INVEST_PLAN_STOP.equals(planResp.getScheduled_protocol_state())) {
            //暂停 红色
            holder.ivArrow.setVisibility(View.VISIBLE);
            holder.tvInvestStatus.setTextColor(getColor(R.color.color_f7182d));
        } else if (Constant.INVEST_PLAN_END.equals(planResp.getScheduled_protocol_state())) {
            //终止 灰色
            holder.ivArrow.setVisibility(View.GONE);
            holder.tvInvestStatus.setTextColor(getColor(R.color.color_696969));
        }

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
        /** 基金名称 */
        @BindView(R.id.tv_fund_name) TextView tvFundName;
        /** 基金代码 */
        @BindView(R.id.tv_fund_code) TextView tvFundCode;
        /** 定投方式 */
        @BindView(R.id.tv_invest_type) TextView tvInvesType;
        /** 银行名称 */
        @BindView(R.id.tv_bank_name) TextView tvBankName;
        /** 银行卡尾号 */
        @BindView(R.id.tv_bank_tail) TextView tvBankTail;
        /** 下次扣款时间 */
        @BindView(R.id.tv_next_time) TextView tvNextTime;
        /** 定投计划状态 */
        @BindView(R.id.tv_invest_status) TextView tvInvestStatus;
        /** 箭头 */
        @BindView(R.id.iv_arrow) ImageView ivArrow;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
