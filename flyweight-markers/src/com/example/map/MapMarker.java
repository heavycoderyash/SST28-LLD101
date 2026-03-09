package com.example.map;

public class MapMarker {
    private final double lat;
    private final double lng;
    private final String label;
    private final MarkerStyle style;

    // I changed the constructor to accept a MarkerStyle object directly.
    // I removed the logic that used new MarkerStyle() inside this class because the marker should no longer own the creation of its style.
    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = style;
    }

    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getLabel() { return label; }
    public MarkerStyle getStyle() { return style; }
}

/* Initial code
public class MapMarker {
    private final double lat;
    private final double lng;
    private final String label;
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label,
                     String shape, String color, int size, boolean filled) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = new MarkerStyle(shape, color, size, filled);
    }

    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getLabel() { return label; }
    public MarkerStyle getStyle() { return style; }
}
*/