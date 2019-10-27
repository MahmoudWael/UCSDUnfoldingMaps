package mydemos;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import parsing.ParseFeed;

public class LifeExpectancy extends PApplet {
    UnfoldingMap map;
    List<Feature> countries;
    List<Marker> countryMarkers;
    Map<String,Float> lifeExp;
    @Override
    public void setup() {
        //canvas size and background color
        size(1024,600);
        background(20);
        //creating the interactive map
        map = new UnfoldingMap(this,100,100,width,height, new Google.GoogleMapProvider());
        MapUtils.createDefaultEventDispatcher(this,map);
        //load data from csv file in a hashMap (key is countryID and value is life expectancy)
        lifeExp = new HashMap<String, Float>();
        lifeExp = ParseFeed.loadLifeExpectancyFromCSV(this,"LifeExpectancyWorldBank.csv");
        countries = GeoJSONReader.loadData(this,"countries.geo.json");
        countryMarkers = MapUtils.createSimpleMarkers(countries);
        map.addMarkers(countryMarkers);
        shadeCountries();
    }

    @Override
    public void draw() {
        map.draw();
    }

    private void shadeCountries(){
        for (Marker marker: countryMarkers){
            String countryId = marker.getId();
            if(lifeExp.containsKey(countryId)){
                float lifeExpVal = lifeExp.get(countryId);
                int colorLevel = (int) map(lifeExpVal,40,90,10,255);
                marker.setColor(color(255-colorLevel, 100,colorLevel));
            }else{
                marker.setColor(color(150,150,150));
            }
        }
    }
}
