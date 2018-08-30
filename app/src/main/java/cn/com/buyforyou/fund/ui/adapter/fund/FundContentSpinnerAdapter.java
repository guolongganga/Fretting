package cn.com.buyforyou.fund.ui.adapter.fund;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;

import java.util.List;

/**
 * 下拉框选择
 * Created by ${sunny}
 * data: 2017/12/19
 */

public class FundContentSpinnerAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    public FundContentSpinnerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_fund_content_spinner_item, viewGroup, false);
            holder = new ViewHolder();
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.ivSelector = convertView.findViewById(R.id.iv_selector);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(list.get(position));
        return convertView;
    }
}

class ViewHolder {
    TextView tvName;
    ImageView ivSelector;
}
