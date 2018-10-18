package com.ph.grib2tools.grib2file.griddefinition;

import com.ph.grib2tools.grib2file.GribSection;

import java.nio.ByteBuffer;

public class GridDefinitionTemplate30 extends GridDefinitionTemplate3x {

    private short  shapeOfEarth;
    private String shapeOfEarthName;
    private byte   scaleFactorRadiusSphericalEarth;
    private int    scaledValueRadiusSphericalEarth;
    private byte   scaleFactorMajorAxisOblateSpheroidEarth;
    private int    scaledValueMajorAxisOblateSpheroidEarth;
    private byte   scaleFactorMinorAxisOblateSpheroidEarth;
    private int    scaledValueMinorAxisOblateSpheroidEarth;
    private int    numberPointsLon;
    private int    numberPointsLat;
    private int    basicAngle;
    private int    subdivisionsBasicAngle;
    private float  firstPointLat;
    private float  firstPointLon;
    private byte   resolutionAndComponentFlags;
    private float  lastPointLat;
    private float  lastPointLon;
    private int    iDirectionIncrement;
    private int    jDirectionIncrement;
    private byte   scanningMode;


    public GridDefinitionTemplate30(ByteBuffer byteBuffer) {
        // 15
        shapeOfEarth = (short) Byte.toUnsignedInt(byteBuffer.get());
        shapeOfEarthName = chooseShapeOfEarthName(shapeOfEarth);
        // 16
        scaleFactorRadiusSphericalEarth = byteBuffer.get();
        // 17-20
        scaledValueRadiusSphericalEarth = byteBuffer.getInt();
        // 21
        scaleFactorMajorAxisOblateSpheroidEarth = byteBuffer.get();
        // 22-25
        scaledValueMajorAxisOblateSpheroidEarth = byteBuffer.getInt();
        // 26
        scaleFactorMinorAxisOblateSpheroidEarth = byteBuffer.get();
        // 27-30
        scaledValueMinorAxisOblateSpheroidEarth = byteBuffer.getInt();
        // 31-34
        numberPointsLon = byteBuffer.getInt();
        // 35-38
        numberPointsLat = byteBuffer.getInt();
        // 39-42
        basicAngle = byteBuffer.getInt();
        // 43-46
        subdivisionsBasicAngle = byteBuffer.getInt();
        float rate;
        if ( basicAngle == 0 ) {
            rate = 1 / 1000000F;
        } else {
            rate = basicAngle * 1F / subdivisionsBasicAngle;
        }
        // 47-50
        firstPointLat = byteBuffer.getInt() * rate;
        // 51-54
        firstPointLon = byteBuffer.getInt() * rate;
        // 55
        resolutionAndComponentFlags = byteBuffer.get();
        // 56-59
        lastPointLat = byteBuffer.getInt() * rate;
        // 60-63
        lastPointLon = byteBuffer.getInt() * rate;
        // 64-67
        iDirectionIncrement = GribSection.correctNegativeInt(byteBuffer.getInt());
        // 68-71
        jDirectionIncrement = GribSection.correctNegativeInt(byteBuffer.getInt());
        // 72
        scanningMode = byteBuffer.get();

    }

    private String chooseShapeOfEarthName(short shapeOfEarth) {
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

    public short getShapeOfEarth() {
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

    public byte getScaleFactorRadiusSphericalEarth() {
        return scaleFactorRadiusSphericalEarth;
    }

    public void setScaleFactorRadiusSphericalEarth(byte scaleFactorRadiusSphericalEarth) {
        this.scaleFactorRadiusSphericalEarth = scaleFactorRadiusSphericalEarth;
    }

    public int getScaledValueRadiusSphericalEarth() {
        return scaledValueRadiusSphericalEarth;
    }

    public void setScaledValueRadiusSphericalEarth(int scaledValueRadiusSphericalEarth) {
        this.scaledValueRadiusSphericalEarth = scaledValueRadiusSphericalEarth;
    }

    public byte getScaleFactorMajorAxisOblateSpheroidEarth() {
        return scaleFactorMajorAxisOblateSpheroidEarth;
    }

    public void setScaleFactorMajorAxisOblateSpheroidEarth(byte scaleFactorMajorAxisOblateSpheroidEarth) {
        this.scaleFactorMajorAxisOblateSpheroidEarth = scaleFactorMajorAxisOblateSpheroidEarth;
    }

    public int getScaledValueMajorAxisOblateSpheroidEarth() {
        return scaledValueMajorAxisOblateSpheroidEarth;
    }

    public void setScaledValueMajorAxisOblateSpheroidEarth(int scaledValueMajorAxisOblateSpheroidEarth) {
        this.scaledValueMajorAxisOblateSpheroidEarth = scaledValueMajorAxisOblateSpheroidEarth;
    }

    public byte getScaleFactorMinorAxisOblateSpheroidEarth() {
        return scaleFactorMinorAxisOblateSpheroidEarth;
    }

    public void setScaleFactorMinorAxisOblateSpheroidEarth(byte scaleFactorMinorAxisOblateSpheroidEarth) {
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

    public byte getResolutionAndComponentFlags() {
        return resolutionAndComponentFlags;
    }

    public void setResolutionAndComponentFlags(byte resolutionAndComponentFlags) {
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

    public byte getScanningMode() {
        return scanningMode;
    }

    public void setScanningMode(byte scanningMode) {
        this.scanningMode = scanningMode;
    }
}
