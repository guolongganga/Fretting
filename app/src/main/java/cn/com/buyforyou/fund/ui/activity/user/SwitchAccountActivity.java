package cn.com.buyforyou.fund.ui.activity.user;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.com.buyforyou.fund.R;
import cn.com.buyforyou.fund.present.user.SwitchAccountPresent;
import cn.com.buyforyou.fund.ui.adapter.user.SwitchAccountRecycleAdapter;

import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.base.SimpleRecAdapter;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * 作者：sunnyzeng on 2017/12/14 15:51
 * 描述：切换账户页面
 */

public class SwitchAccountActivity extends XActivity<SwitchAccountPresent> {

    /** 返回 */
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    /** 编辑 */
    @BindView(R.id.tv_compile)
    TextView tvCompile;
    /** 展示所有账户 */
    @BindView(R.id.xrv_account)
    XRecyclerView xRecyclerView;
    /** 添加账户 */
    @BindView(R.id.tv_add_account)
    TextView tvAddAccount;

    private SwitchAccountRecycleAdapter adapter;
    /** 数据源 */
    private List<String> datas;
    /** 可得到当前选中的账号 */
    private int isSelector;

    @Override
    public int getLayoutId() {
        return R.layout.activity_switch_account;
    }

    @Override
    public SwitchAccountPresent newP() {
        return new SwitchAccountPresent();
    }

    @Override
    public void initData(Bundle bundle) {

        xRecyclerView.horizontalDivider(R.color.color_F9F9F9, R.dimen.dimen_01);//设置divider
        xRecyclerView.verticalLayoutManager(context);

        getAdapter();

        getP().loadData();
    }


    @Override
    public void initEvents() {
        //返回
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //编辑
        tvCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getText(tvCompile).equals("编辑")) {
                    adapter.setShowDelete(true);
                    tvCompile.setText("完成");
                } else {
                    adapter.setShowDelete(false);
                    tvCompile.setText("编辑");
                }
                adapter.notifyDataSetChanged();
            }
        });
        //添加账户
        tvAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("添加账户");
            }
        });
    }

    /**
     * 初始化adapter
     *
     * @return
     */
    public SimpleRecAdapter getAdapter() {
        if (adapter == null) {
            adapter = new SwitchAccountRecycleAdapter(context);
            xRecyclerView.setAdapter(adapter);
            adapter.setRecItemClick(new RecyclerItemCallback<String, SwitchAccountRecycleAdapter.ViewHolder>() {
                @Override
                public void onItemClick(int position, String model, int tag, SwitchAccountRecycleAdapter.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                    switch (tag) {
                        //点击item切换账号
                        case SwitchAccountRecycleAdapter.ITEM_CLICK:
                            isSelector = position;
                            adapter.setIsSelector(isSelector);
                            adapter.notifyDataSetChanged();
                            break;
                        //删除账号
                        case SwitchAccountRecycleAdapter.DELETE_CLICK:
                            datas.remove(position);
                            if (datas.size() <= 1) {
                                adapter.setShowDelete(false);
                                tvCompile.setVisibility(View.GONE);
                            }
                            adapter.setIsSelector(0);//重置选中的账户
                            getAdapter().setData(datas);
                            break;
                    }
                }
            });
        }
        return adapter;
    }

    /**
     * 数据展示
     *
     * @param item
     */
    public void showData(List<String> item) {
        if (item != null && item.size() > 0) {
            datas = item;
            //多个账号才显示编辑
            if (item.size() > 1) {
                tvCompile.setVisibility(View.VISIBLE);
            }

            getAdapter().addData(datas);
        }
    }
}
