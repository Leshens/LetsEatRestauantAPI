package com.leshen.LetsEatRestaurantAPI.tables;

public class Tables {

    private Integer id;
    private String Name;

    private Integer twoOs;

    private Integer fourOs;

    private Integer sixOs;
    private Integer eightOs;

    public Tables() {
    }

    public Tables(Integer id, String name, Integer twoOs, Integer fourOs, Integer sixOs, Integer eightOs) {
        this.id = id;
        Name = name;
        this.twoOs = twoOs;
        this.fourOs = fourOs;
        this.sixOs = sixOs;
        this.eightOs = eightOs;
    }

    public Tables(String name, Integer twoOs, Integer fourOs, Integer sixOs, Integer eightOs) {
        Name = name;
        this.twoOs = twoOs;
        this.fourOs = fourOs;
        this.sixOs = sixOs;
        this.eightOs = eightOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getTwoOs() {
        return twoOs;
    }

    public void setTwoOs(Integer twoOs) {
        this.twoOs = twoOs;
    }

    public Integer getFourOs() {
        return fourOs;
    }

    public void setFourOs(Integer fourOs) {
        this.fourOs = fourOs;
    }

    public Integer getSixOs() {
        return sixOs;
    }

    public void setSixOs(Integer sixOs) {
        this.sixOs = sixOs;
    }

    public Integer getEightOs() {
        return eightOs;
    }

    public void setEightOs(Integer eightOs) {
        this.eightOs = eightOs;
    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", twoOs=" + twoOs +
                ", fourOs=" + fourOs +
                ", sixOs=" + sixOs +
                ", eightOs=" + eightOs +
                '}';
    }
}
