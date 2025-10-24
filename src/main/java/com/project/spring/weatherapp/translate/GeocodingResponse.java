package com.project.spring.weatherapp.translate;

import com.google.gson.annotations.SerializedName;
import com.project.spring.weatherapp.coordinationDetales.CoordinationDetales;

import java.util.List;

public class GeocodingResponse {

    @SerializedName("results")
    private List<CoordinationDetales> coordinationDetales;

    public List<CoordinationDetales> getCoordinationDetales() {
        return coordinationDetales;
    }

    public void setCoordinationDetales(List<CoordinationDetales> coordinationDetales) {
        this.coordinationDetales = coordinationDetales;
    }
}
