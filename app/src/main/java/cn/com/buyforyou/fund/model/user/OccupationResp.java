package cn.com.buyforyou.fund.model.user;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.ArrayList;

/**
 * 作者：sunnyzeng on 2018/1/4 17:47
 * 描述：
 */

public class OccupationResp extends BaseResp<ArrayList<OccupationResp>> {
    /**
     * 中文描述 S 50 0 Y v4.0.1.0
     */
    private String caption;
    /**
     * 词条键值 S 150 0 Y v4.0.1.0
     */
    private String keyvalue;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    @Override
    public String toString() {
        return "OccupationResp{" +
                "caption='" + caption + '\'' +
                ", keyvalue='" + keyvalue + '\'' +
                '}';
    }
}
