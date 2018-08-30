package cn.com.buyforyou.fund.ui.adapter.user;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhsoft.fretting.ui.widget.SelectPopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.model.ApplyBaseInfo;
import cn.com.buyforyou.fund.model.user.OccupationResp;
import cn.com.buyforyou.fund.model.user.TaxInfoEResp;

public class TaxInfoAdapter extends BaseAdapter {

    private Context context;
    //集合
    private List<TaxInfoEResp> taxInfoEResps;
    //集合保存输入状态
    private List<TaxInfoEResp> taxInfoEResps_e = new ArrayList<>();

    // 现居住国家 中文
    private List<OccupationResp> nationList;

    // 无居民国家地区纳税人识别号原因
    private List<OccupationResp> noTaxReasonList;

    public TaxInfoAdapter(Context context, List<TaxInfoEResp> taxInfoEResps, List<OccupationResp> nationList, List<OccupationResp> noTaxReasonList) {
        this.taxInfoEResps = taxInfoEResps;
        this.context = context;
        this.nationList = nationList;
        this.noTaxReasonList = noTaxReasonList;

        for (TaxInfoEResp taxInfoEResp : taxInfoEResps) {
            TaxInfoEResp taxInfoEResp1 = new TaxInfoEResp();
            taxInfoEResp1.setTax_no(taxInfoEResp.getTax_no());
            taxInfoEResp1.setTax_explain(taxInfoEResp.getTax_explain());
            taxInfoEResps_e.add(taxInfoEResp1);
        }
    }


    public List<TaxInfoEResp> getTaxInfoEResps_e() {
        return taxInfoEResps_e;
    }

    @Override
    public int getCount() {
        return taxInfoEResps.size();
    }

    @Override
    public Object getItem(int i) {
        return taxInfoEResps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TaxInfoEResp taxInfoEResp = (TaxInfoEResp) getItem(position);


        final ViewHolder viewHolder;

        convertView = LayoutInflater.from(context).inflate(R.layout.activity_tax_list, parent, false);
        viewHolder = new ViewHolder();
        viewHolder.tax_resident_countries = convertView.findViewById(R.id.tax_resident_countries);
        viewHolder.title_delete = convertView.findViewById(R.id.title_delete);
        viewHolder.tax_noid_why_ll = convertView.findViewById(R.id.tax_noid_why_ll);
        viewHolder.title_top = convertView.findViewById(R.id.title_top);
        viewHolder.tax_noid = convertView.findViewById(R.id.tax_noid);
        viewHolder.tax_id = convertView.findViewById(R.id.tax_id);
        viewHolder.tax_noid_why = convertView.findViewById(R.id.tax_noid_why);


        if (taxInfoEResp.getIs_main_tax().equals("1")) {
            viewHolder.title_top.setText("主税收居民国信息：");
            viewHolder.title_delete.setVisibility(View.GONE);
        } else {
            viewHolder.title_top.setText("其他税收居民国信息：");
            viewHolder.title_delete.setVisibility(View.VISIBLE);
        }

        //税收居民国地区
        for (OccupationResp occupationResp : nationList) {
            if (occupationResp.getKeyvalue().equals(taxInfoEResp.getTax_nation())) {
                viewHolder.tax_resident_countries.setText(occupationResp.getCaption());
            }
        }

        ArrayList<ApplyBaseInfo> cycleList = new ArrayList<>();
        for (OccupationResp occupationResp : nationList) {
            cycleList.add(new ApplyBaseInfo(occupationResp.getKeyvalue(), occupationResp.getCaption()));
        }

        //现居住国家弹框
        final SelectPopupWindow cyclePopupWindow1 = new SelectPopupWindow(context, cycleList);
        cyclePopupWindow1.setCallBackCode(new SelectPopupWindow.CallBackCode() {
            @Override
            public void onSelectChange(int currentItem, String name, String code) {
                taxInfoEResp.setTax_nation(code);
                viewHolder.tax_resident_countries.setText(name);
                notifyDataSetChanged();
            }
        });
        viewHolder.tax_resident_countries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cyclePopupWindow1.showAtLocation(viewHolder.tax_resident_countries, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });


        // 无居民国家地区纳税人识别号原因
        for (OccupationResp occupationResp : noTaxReasonList) {
            if (occupationResp.getKeyvalue().equals(taxInfoEResp.getNo_tax_no_reason())) {
                viewHolder.tax_noid.setText(occupationResp.getCaption());
            }
        }


        ArrayList<ApplyBaseInfo> cycleList2 = new ArrayList<>();
        for (OccupationResp occupationResp : noTaxReasonList) {
            cycleList2.add(new ApplyBaseInfo(occupationResp.getKeyvalue(), occupationResp.getCaption()));
        }

        // 无居民国家地区纳税人识别号原因弹框
        final SelectPopupWindow cyclePopupWindow2 = new SelectPopupWindow(context, cycleList2);
        cyclePopupWindow2.setCallBackCode(new SelectPopupWindow.CallBackCode() {
            @Override
            public void onSelectChange(int currentItem, String name, String code) {
                taxInfoEResp.setNo_tax_no_reason(code);
                viewHolder.tax_noid.setText(name);
                notifyDataSetChanged();
            }
        });

        viewHolder.tax_noid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cyclePopupWindow2.showAtLocation(viewHolder.tax_noid, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        //可用的纳税识别号码
        viewHolder.tax_id.setText(taxInfoEResp.getTax_no());

        if (taxInfoEResp.getNo_tax_no_reason().equals("B")) {
            viewHolder.tax_noid_why_ll.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tax_noid_why_ll.setVisibility(View.GONE);
        }


        //删除
        viewHolder.title_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taxInfoEResps.remove(taxInfoEResp);
                taxInfoEResps_e.remove(position);
                notifyDataSetChanged();
            }
        });

        viewHolder.tax_noid_why.setText(taxInfoEResp.getTax_explain());


        viewHolder.tax_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals("")) {
                    taxInfoEResps_e.get(position).setTax_no(editable.toString());
                    taxInfoEResp.setTax_no(editable.toString());
                }
            }
        });
        viewHolder.tax_noid_why.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals("")) {
                    taxInfoEResps_e.get(position).setTax_explain(editable.toString());
                    taxInfoEResp.setTax_explain(editable.toString());
                }

            }
        });


        return convertView;
    }


    class ViewHolder {
        // 是否主税收国
        TextView title_top;

        // 删除
        TextView title_delete;

        // 税收居民国（地区）
        TextView tax_resident_countries;
        //可用的纳税识别号码
        EditText tax_id;
        //无居民国家/地区纳税人识别号的原因
        TextView tax_noid;
        //未能取得纳税人识别号的具体原因
        EditText tax_noid_why;

        //未能取得纳税人识别号的具体原因layout
        LinearLayout tax_noid_why_ll;
    }
}