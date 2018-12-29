package com.ph.grib2tools.grib2file.griddefinition;

import com.ph.grib2tools.grib2file.DataUtil;
import com.ph.grib2tools.grib2file.GribSection;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GridDefinitionTemplate30 extends GridDefinitionTemplate3x {

    private int    shapeOfEarth;
    private String shapeOfEarthName;
    private int    scaleFactorRadiusSphericalEarth;
    private int    scaledValueRadiusSphericalEarth;
    private int    scaleFactorMajorAxisOblateSpheroidEarth;
    private int    scaledValueMajorAxisOblateSpheroidEarth;
    private int    scaleFactorMinorAxisOblateSpheroidEarth;
    private int    scaledValueMinorAxisOblateSpheroidEarth;
    private int    numberPointsLon;
    private int    numberPointsLat;
    private int    basicAngle;
    private int    subdivisionsBasicAngle;
    private float  firstPointLat;
    private float  firstPointLon;
    private int    resolutionAndComponentFlags;
    private float  lastPointLat;
    private float  lastPointLon;
    private int    iDirectionIncrement;
    private int    jDirectionIncrement;
    private int    scanningMode;


    public GridDefinitionTemplate30(RandomAccessFile gribFile) throws IOException {
        // 15
        shapeOfEarth = gribFile.read();
        shapeOfEarthName = chooseShapeOfEarthName(shapeOfEarth);
        // 16
        scaleFactorRadiusSphericalEarth = gribFile.read();
        // 17-20
        scaledValueRadiusSphericalEarth = DataUtil.int4(gribFile);
        // 21
        scaleFactorMajorAxisOblateSpheroidEarth = gribFile.read();
        // 22-25
        scaledValueMajorAxisOblateSpheroidEarth = DataUtil.int4(gribFile);
        // 26
        scaleFactorMinorAxisOblateSpheroidEarth = gribFile.read();
        // 27-30
        scaledValueMinorAxisOblateSpheroidEarth = DataUtil.int4(gribFile);
        // 31-34
        numberPointsLon = DataUtil.int4(gribFile);
        // 35-38
        numberPointsLat = DataUtil.int4(gribFile);
        // 39-42
        basicAngle = DataUtil.int4(gribFile);
        // 43-46
        subdivisionsBasicAngle = DataUtil.int4(gribFile);
        float rate;
        if ( basicAngle == 0 ) {
            rate = 1 / 1000000F;
        } else {
            rate = basicAngle * 1F / subdivisionsBasicAngle;
        }
        // 47-50
        firstPointLat = DataUtil.int4(gribFile) * rate;
        // 51-54
        firstPointLon = DataUtil.int4(gribFile) * rate;
        // 55
        resolutionAndComponentFlags = gribFile.read();
        // 56-59
        lastPointLat = DataUtil.int4(gribFile) * rate;
        // 60-63
        lastPointLon = DataUtil.int4(gribFile) * rate;
        // 64-67
        iDirectionIncrement = GribSection.correctNegativeInt(DataUtil.int4(gribFile));
        // 68-71
        jDirectionIncrement = GribSection.correctNegativeInt(DataUtil.int4(gribFile));
        // 72
        scanningMode = gribFile.read();

    }

    private String chooseShapeOfEarthName(int shapeOfEarth) {
        String name;
        switch ( shapeOfEarth ) {
            case 0:
                name = "Earth assumed spherical with radius = 6,367,470.0 m";
                break;
            case 1:
                name = "Earth assumed spherical with radius specified (in m) by data producer";
                break;
            case 2:
                name = "Earth assumed oblate spheriod with size as determined by IAU in 1965 (major axis = 6,378,160.0 m, minor axis = 6,356,775.0 m, f = 1/297.0)";
                break;
            case 3:
                name = "Earth assumed oblate spheriod with major and minor axes specified (in km) by data producer";
                break;
            case 4:
                name = "Earth assumed oblate spheriod as defined in IAG-GRS80 model (major axis = 6,378,137.0 m, minor axis = 6,356,752.314 m, f = 1/298.257222101)";
                break;
            case 5:
                name = "Earth assumed represented by WGS84 (as used by ICAO since 1998) (Uses IAG-GRS80 as a basis)";
                break;
            case 6:
                name = "Earth assumed spherical with radius = 6,371,229.0 m";
                break;
            case 7:
                name = "Earth assumed oblate spheroid with major and minor axes specified (in m) by data producer";
                break;
            case 8:
                name = "Earth model assumed spherical with radius 6,371,200 m, but the horizontal datum of the resulting Latitude/Longitude field is the WGS84 reference frame";
                break;
            case 9:
                name = "Earth represented by the OSGB 1936 Datum, using the Airy_1830 Spheroid, the Greenwich meridian as 0 Longitude, the Newlyn datum as mean sea level, 0 height.";
                break;
            case 255:
                name = "Missing";
                break;
            default:
                name = "Reserved or Reserved for Local Use";
                break;
        }
        return name;
    }

    public void setLastPointLat(float lastPointLat) {
        this.lastPointLat = lastPointLat;
    }

    public void setLastPointLon(float lastPointLon) {
        this.lastPointLon = lastPointLon;
    }

    public int getShapeOfEarth() {
        return shapeOfEarth;
    }

    public void setShapeOfEarth(short shapeOfEarth) {
        this.shapeOfEarth = shapeOfEarth;
    }

    public String getShapeOfEarthName() {
        return shapeOfEarthName;
    }

    public void setShapeOfEarthName(String shapeOfEarthName) {
        this.shapeOfEarthName = shapeOfEarthName;
    }

    public int getScaleFactorRadiusSphericalEarth() {
        return scaleFactorRadiusSphericalEarth;
    }

    public void setScaleFactorRadiusSphericalEarth(int scaleFactorRadiusSphericalEarth) {
        this.scaleFactorRadiusSphericalEarth = scaleFactorRadiusSphericalEarth;
    }

    public int getScaledValueRadiusSphericalEarth() {
        return scaledValueRadiusSphericalEarth;
    }

    public void setScaledValueRadiusSphericalEarth(int scaledValueRadiusSphericalEarth) {
        this.scaledValueRadiusSphericalEarth = scaledValueRadiusSphericalEarth;
    }

    public int getScaleFactorMajorAxisOblateSpheroidEarth() {
        return scaleFactorMajorAxisOblateSpheroidEarth;
    }

    public void setScaleFactorMajorAxisOblateSpheroidEarth(int scaleFactorMajorAxisOblateSpheroidEarth) {
        this.scaleFactorMajorAxisOblateSpheroidEarth = scaleFactorMajorAxisOblateSpheroidEarth;
    }

    public int getScaledValueMajorAxisOblateSpheroidEarth() {
        return scaledValueMajorAxisOblateSpheroidEarth;
    }

    public void setScaledValueMajorAxisOblateSpheroidEarth(int scaledValueMajorAxisOblateSpheroidEarth) {
        this.scaledValueMajorAxisOblateSpheroidEarth = scaledValueMajorAxisOblateSpheroidEarth;
    }

    public int getScaleFactorMinorAxisOblateSpheroidEarth() {
        return scaleFactorMinorAxisOblateSpheroidEarth;
    }

    public void setScaleFactorMinorAxisOblateSpheroidEarth(int scaleFactorMinorAxisOblateSpheroidEarth) {
        this.scaleFactorMinorAxisOblateSpheroidEarth = scaleFactorMinorAxisOblateSpheroidEarth;
    }

    public int getScaledValueMinorAxisOblateSpheroidEarth() {
        return scaledValueMinorAxisOblateSpheroidEarth;
    }

    public void setScaledValueMinorAxisOblateSpheroidEarth(int scaledValueMinorAxisOblateSpheroidEarth) {
        this.scaledValueMinorAxisOblateSpheroidEarth = scaledValueMinorAxisOblateSpheroidEarth;
    }

    public int getNumberPointsLon() {
        return numberPointsLon;
    }

    public void setNumberPointsLon(int numberPointsLon) {
        this.numberPointsLon = numberPointsLon;
    }

    public int getNumberPointsLat() {
        return numberPointsLat;
    }

    public void setNumberPointsLat(int numberPointsLat) {
        this.numberPointsLat = numberPointsLat;
    }

    public int getBasicAngle() {
        return basicAngle;
    }

    public void setBasicAngle(int basicAngle) {
        this.basicAngle = basicAngle;
    }

    public int getSubdivisionsBasicAngle() {
        return subdivisionsBasicAngle;
    }

    public void setSubdivisionsBasicAngle(int subdivisionsBasicAngle) {
        this.subdivisionsBasicAngle = subdivisionsBasicAngle;
    }

    public float getFirstPointLat() {
        return firstPointLat;
    }

    public void setFirstPointLat(float firstPointLat) {
        this.firstPointLat = firstPointLat;
    }

    public float getFirstPointLon() {
        return firstPointLon;
    }

    public void setFirstPointLon(float firstPointLon) {
        this.firstPointLon = firstPointLon;
    }

    public int getResolutionAndComponentFlags() {
        return resolutionAndComponentFlags;
    }

    public void setResolutionAndComponentFlags(int resolutionAndComponentFlags) {
        this.resolutionAndComponentFlags = resolutionAndComponentFlags;
    }

    public float getLastPointLat() {
        return lastPointLat;
    }

    public void setLastPointLat(int lastPointLat) {
        this.lastPointLat = lastPointLat;
    }

    public float getLastPointLon() {
        return lastPointLon;
    }

    public void setLastPointLon(int lastPointLon) {
        this.lastPointLon = lastPointLon;
    }

    public int getiDirectionIncrement() {
        return iDirectionIncrement;
    }

    public void setiDirectionIncrement(int iDirectionIncrement) {
        this.iDirectionIncrement = iDirectionIncrement;
    }

    public int getjDirectionIncrement() {
        return jDirectionIncrement;
    }

    public void setjDirectionIncrement(int jDirectionIncrement) {
        this.jDirectionIncrement = jDirectionIncrement;
    }

    public int getScanningMode() {
        return scanningMode;
    }

    public void setScanningMode(int scanningMode) {
        this.scanningMode = scanningMode;
    }
}
