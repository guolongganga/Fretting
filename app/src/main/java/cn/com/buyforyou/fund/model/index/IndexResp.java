package cn.com.buyforyou.fund.model.index;

import cn.com.buyforyou.fund.model.BaseResp;

import java.util.ArrayList;

/**
 * 作者：sunnyzeng on 2018/1/2 10:49
 * 描述：
 */

public class IndexResp extends BaseResp<IndexResp> {
    /**
     * banner集合
     */
    private ArrayList<BannerModel> bannerList;
    /**
     * 微动利专题
     */
    private ArrayList<BannerModel> themeList;
    /**
     * 明星基金
     */
    private ProductModel starFund;
    /**
     * 人气产品
     */
    private ArrayList<ProductModel> hotFunds;
    /**
     * 指数基金
     */
    private ArrayList<ProductModel> indexFunds;

    /**
     * 优选定投
     */
    private ArrayList<ProductModel> fixedFunds;

    public ArrayList<BannerModel> getBannerList() {
        return bannerList;
    }

    public void setBannerList(ArrayList<BannerModel> bannerList) {
        this.bannerList = bannerList;
    }

    public ArrayList<BannerModel> getThemeList() {
        return themeList;
    }

    public void setThemeList(ArrayList<BannerModel> themeList) {
        this.themeList = themeList;
    }

    public ProductModel getStarFund() {
        return starFund;
    }

    public void setStarFund(ProductModel starFund) {
        this.starFund = starFund;
    }

    public ArrayList<ProductModel> getHotFunds() {
        return hotFunds;
    }

    public void setHotFunds(ArrayList<ProductModel> hotFunds) {
        this.hotFunds = hotFunds;
    }

    public ArrayList<ProductModel> getIndexFunds() {
        return indexFunds;
    }

    public void setIndexFunds(ArrayList<ProductModel> indexFunds) {
        this.indexFunds = indexFunds;
    }

    public ArrayList<ProductModel> getFixedFunds() {
        return fixedFunds;
    }

    public void setFixedFunds(ArrayList<ProductModel> fixedFunds) {
        this.fixedFunds = fixedFunds;
    }
}
