package com.zhsoft.fretting.ui.widget.wheel.model;

import java.util.List;

public class CityModel {
    private int id;
    private String name;
    private List<DistrictModel> districtList;

    public CityModel() {
    }


    public CityModel(int id, String name, List<DistrictModel> districtList) {
        this.id = id;
        this.name = name;
        this.districtList = districtList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictModel> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictModel> districtList) {
        this.districtList = districtList;
    }
}
