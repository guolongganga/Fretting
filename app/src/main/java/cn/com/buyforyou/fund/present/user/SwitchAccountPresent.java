package cn.com.buyforyou.fund.present.user;

import cn.com.buyforyou.fund.ui.activity.user.SwitchAccountActivity;

import java.util.ArrayList;
import java.util.List;

import cn.droidlover.xdroidmvp.mvp.XPresent;

/**
 * 切换账户
 * Created by ${Yis}
 * data: 2017/12/14
 */

public class SwitchAccountPresent extends XPresent<SwitchAccountActivity> {

    public void loadData() {
        List<String> list = new ArrayList<>();
        list.add("周三");
        list.add("李四");
        list.add("王麻子");
        getV().showData(list);
    }
}
