package com.moviesapp.entity;

import java.util.Date;
import java.util.Objects;

public class Studio {
    private String studioName;
    private Date foundation;
    private String founder;
    private String headquarters;

    public Studio(String studioName, Date foundation, String founder, String headquarters) {
        this.studioName = studioName;
        this.foundation = foundation;
        this.founder = founder;
        this.headquarters = headquarters;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studio)) return false;
        Studio studio = (Studio) o;
        return studioName.equals(studio.studioName) && foundation.equals(studio.foundation) && Objects.equals(founder, studio.founder) && Objects.equals(headquarters, studio.headquarters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studioName, foundation, founder, headquarters);
    }

    @Override
    public String toString() {
        return "Studio{" +
                "studioName='" + studioName + '\'' +
                ", foundation=" + foundation +
                ", founder='" + founder + '\'' +
                ", headquarters='" + headquarters + '\'' +
                '}';
    }
}
