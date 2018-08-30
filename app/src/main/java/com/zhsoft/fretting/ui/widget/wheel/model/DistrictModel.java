package com.zhsoft.fretting.ui.widget.wheel.model;

public class DistrictModel {
    private int id;
    private String name;


    public DistrictModel() {
    }

    public DistrictModel(int id, String name) {
        this.id = id;
        this.name = name;
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


}
