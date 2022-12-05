package com.moviesapp.entity;

import java.util.Date;
import java.util.Objects;

public class Studio {
    private String studioName;
    private String industry;
    private Date foundation;
    private String founder;
    private String headquarters;
    private Integer studioID;

    public Studio(String studioName, String industry, Date foundation, String founder, String headquarters, Integer studioID) {
        this.studioName = studioName;
        this.industry = industry;
        this.foundation = foundation;
        this.founder = founder;
        this.headquarters = headquarters;
        this.studioID = studioID;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Date getFoundation() {
        return foundation;
    }

    public void setFoundation(Date foundation) {
        this.foundation = foundation;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public Integer getStudioID() {
        return studioID;
    }

    public void setStudioID(Integer studioID) {
        this.studioID = studioID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studio)) return false;
        Studio studio = (Studio) o;
        return studioName.equals(studio.studioName) && Objects.equals(industry, studio.industry) && Objects.equals(foundation, studio.foundation) && Objects.equals(founder, studio.founder) && Objects.equals(headquarters, studio.headquarters) && studioID.equals(studio.studioID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studioName, industry, foundation, founder, headquarters, studioID);
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioName='" + studioName + '\'' +
                ", industry='" + industry + '\'' +
                ", foundation=" + foundation +
                ", founder='" + founder + '\'' +
                ", headquarters='" + headquarters + '\'' +
                ", studioID=" + studioID +
                '}';
    }
}
