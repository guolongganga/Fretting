package com.zhsoft.fretting.ui.widget.wheel.model;

import java.util.List;

public class ProvinceModel {
    private int id;
    private String name;
    private List<CityModel> cityList;

    public ProvinceModel() {
    }

    public ProvinceModel(int id, String name, List<CityModel> cityList) {
        this.id = id;
        this.name = name;
        this.cityList = cityList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityModel> cityList) {
        this.cityList = cityList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
