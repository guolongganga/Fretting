package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.user.BankResp;
import cn.com.buyforyou.fund.utils.Base64ImageUtil;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.kit.KnifeKit;

/**
 * 作者：sunnyzeng on 2017/12/18 14:07
 * 描述：银行列表adapter
 */

public class BankListAdapter extends SimpleRecAdapter<BankResp, BankListAdapter.ViewHolder> {
    public static final int ITEM_CLICK = 0;    //点击标识
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public BankListAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_user_bank_list_rv_item;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BankResp resp = data.get(position);
        //获取 图片Base64 字符串
        String strimage = resp.getBankLogo();
        if (!TextUtils.isEmpty(strimage)) {
            //将Base64图片串转换成Bitmap
            Bitmap bitmap = Base64ImageUtil.base64ToBitmap(strimage);
            holder.bankImage.setImageBitmap(bitmap);
        }
        holder.bankName.setText(resp.getBank_name());
        holder.bankLimit.setText("单笔限额" + resp.getLimit_per_payment() + "万，单日限额" + resp.getLimit_per_day() + "万，单月限额" + resp.getLimit_per_month() + "万");
        if (data.size() - 1 == position) {
            holder.viewLine.setVisibility(View.GONE);
        } else {
            holder.viewLine.setVisibility(View.VISIBLE);
        }
        holder.rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRecItemClick().onItemClick(position, resp, ITEM_CLICK, holder);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** 银行图标 */
        @BindView(R.id.bank_image) ImageView bankImage;
        /** 银行名称 */
        @BindView(R.id.bank_name) TextView bankName;
        /** 银行限额 */
        @BindView(R.id.bank_limit) TextView bankLimit;
        /** 内容 */
        @BindView(R.id.rl_content) RelativeLayout rlContent;
        /** 分割线 */
        @BindView(R.id.view_line) View viewLine;

        public ViewHolder(View itemView) {
            super(itemView);
            KnifeKit.bind(this, itemView);
        }
    }
}
