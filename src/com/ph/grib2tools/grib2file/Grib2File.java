package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate5x;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate3x;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate4x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Grib2File implements Serializable {

    /**
     * Conversation factor for converting GRIB file units to degree (for coordinates)
     */
    public static int GRIB2_DEG_UNIT = 1000000;

    /**
     * A string pointing to the source of the GRIB2 data. For information only,
     * so can be "" or null if not needed
     */
    private String file;

    /**
     * Sections of the GRIB file
     */
    private GribSection0       section0;
    private GribSection1       section1;
    private List<GribSection2> section2;
    private List<GribSection3> section3;
    private List<GribSection4> section4;
    private List<GribSection5> section5;
    private List<GribSection6> section6;
    private List<GribSection7> section7;

    private static final Logger log = Logger.getLogger(Grib2File.class.getName());

    public Grib2File() {
        section2 = new ArrayList<>();
        section3 = new ArrayList<>();
        section4 = new ArrayList<>();
        section5 = new ArrayList<>();
        section6 = new ArrayList<>();
        section7 = new ArrayList<>();
    }

    public Grib2File(String source) {
        this.file = source;
        section2 = new ArrayList<>();
        section3 = new ArrayList<>();
        section4 = new ArrayList<>();
        section5 = new ArrayList<>();
        section6 = new ArrayList<>();
        section7 = new ArrayList<>();
    }

    public String getFile() {
        return file;
    }

    /**
     * Returns the GridDefinitionTemplate of the GRIB file according to the Template Number
     */
    private GridDefinitionTemplate3x getGridDefinitionTemplate() {
        GridDefinitionTemplate3x gridDefinition = null;
        //        if ( section3.getGridDefinitionTemplateNumber() == 0 ) {
        //            gridDefinition = section3.getGridDefinitionTemplate();
        //        } else {
        //            log.severe("Grid Definition Template Number 3." + section3.getGridDefinitionTemplateNumber() + " not implemented.");
        //        }
        return gridDefinition;
    }

    /**
     * Returns the ProductDefinitionTemplate of the GRIB file according to the Template Number
     */
    private ProductDefinitionTemplate4x getProductDefinitionTemplate() {
        ProductDefinitionTemplate4x productDefinition = null;
        //                if ( section4.get(0).getProductDefinitionTemplateNumber() == 0 ) {
        //                    productDefinition = section4.getProductDefinitionTemplate();
        //                } else if ( section4.getProductDefinitionTemplateNumber() == 8 ) {
        //                    productDefinition = section4.getProductDefinitionTemplate();
        //                } else {
        //                    log.severe("Product Definition Template Number 4." + section4.getProductDefinitionTemplateNumber() + " not implemented.");
        //                }
        return productDefinition;
    }

    /**
     * Returns the DataRepresentationTemplate of grid gridId of the GRIB file according to the Template Number
     *
     * @param gridId
     */
    private DataRepresentationTemplate5x getDataRepresentationTemplate(int gridId) {
        DataRepresentationTemplate5x dataRepresentation = null;
        if ( this.section5.get(gridId).getDataRepresentationTemplateNumber() == 0 ) {
            dataRepresentation = this.section5.get(gridId).getDataRepresentationTemplate();
        } else {
            log.severe("Data Representation Template Number 5." + this.section5.get(gridId).getDataRepresentationTemplateNumber() + " not implemented.");
        }
        return dataRepresentation;
    }


    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in degrees and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the neighbored grid points is interpolated and used
     * to calculate the value belonging to the passed position.
     */
    public float interpolateValueAtLocation(int gridIdx, double lat, double lon) {
        return interpolateValueAt(gridIdx, Grib2File.degToUnits(lat), Grib2File.degToUnits(lon));
    }

    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in units and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the neighbored grid points is interpolated and used
     * to calculate the value belonging to the passed position.
     */
    private float interpolateValueAt(int gridIdx, int lat, int lon) {
//        GribSection5 sec5 = getSection5().get(gridIdx);
//        GribSection7 sec7 = getSection7().get(gridIdx);
//        float        val  = 0;
//        if ( sec5.getDataRepresentationTemplateNumber() == 3 ) {
//            DataRepresentationTemplate50 dataRepresentation = (DataRepresentationTemplate50) sec5.getDataRepresentationTemplate();
//            int                          bytesperval        = dataRepresentation.getNumberBits() / 8;
//            if ( getSection3().get(gridIdx).getGridDefinitionTemplateNumber() == 0 ) {
//                GridDefinitionTemplate30 gridDefinition = (GridDefinitionTemplate30) getSection3().get(gridIdx).getGridDefinitionTemplate();
//                int                      deltaj         = 0;
//                // Implementation of Scanning Modes
//                if ( (gridDefinition.getScanningMode() & 0x01) == 0x01 ) {
//                    log.warning("Scanning mode " + 0x01 + " not supported");
//                }
//                if ( (gridDefinition.getScanningMode() & 0x02) == 0x02 ) {
//                    log.warning("Scanning mode " + 0x02 + " not supported");
//                }
//                if ( (gridDefinition.getScanningMode() & 0x04) == 0x04 ) {
//                    log.warning("Scanning mode " + 0x04 + " not supported");
//                }
//                if ( (gridDefinition.getScanningMode() & 0x08) == 0x08 ) {
//                    log.warning("Scanning mode " + 0x08 + " not supported");
//                }
//                if ( (gridDefinition.getScanningMode() & 0x10) == 0x10 ) {
//                    log.warning("Scanning mode " + 0x10 + " not supported");
//                }
//                if ( (gridDefinition.getScanningMode() & 0x20) == 0x20 ) {
//                    log.warning("Scanning mode " + 0x20 + " not supported");
//                }
//                if ( (gridDefinition.getScanningMode() & 0x40) == 0x40 ) {
//                    deltaj = gridDefinition.getjDirectionIncrement() / 2;
//                }
//                if ( (gridDefinition.getScanningMode() & 0x80) == 0x80 ) {
//                    log.warning("Scanning mode " + 0x80 + " not supported");
//                }
//
//                deltaj = 0;
//                // Find j indices of the grid points of the matrix containing the data that surround the
//                // passed latitude lat
//                int deltalat = (int) (lat - (gridDefinition.getFirstPointLat() + deltaj));
//                //int jidx1 = Math.round((float)deltalat / (float)gridDefinition.jDirectionIncrement);
//                int jidx1 = (int) Math.floor((float) deltalat / (float) gridDefinition.getjDirectionIncrement());
//
//                // Correct indices when end of the array dimension is reached
//                if ( jidx1 >= gridDefinition.getNumberPointsLat() - 1 ) { jidx1--; }
//                int jidx2 = jidx1 + 1;
//
//                // Find i indices of the grid points of the matrix containing the data that surround the
//                // passed longitude lon
//                int firstPointLon = (int) gridDefinition.getFirstPointLon();
//                if ( firstPointLon >= Grib2File.degToUnits(180) ) { firstPointLon -= Grib2File.degToUnits(360); }
//                int deltalon = lon - firstPointLon;
//                //int iidx1 = Math.round((float)deltalon / (float)gridDefinition.iDirectionIncrement);
//                int iidx1 = (int) Math.floor((float) deltalon / (float) gridDefinition.getiDirectionIncrement());
//
//                // Correct indices when end of the array dimension is reached
//                if ( iidx1 >= gridDefinition.getNumberPointsLon() - 1 ) { iidx1--; }
//                int iidx2 = iidx1 + 1;
//
//
//                byte data[] = sec7.getSectionData();
//
//                // Extract data of the four grid points surrounding the passed coordinate and calculate the
//                // values represented by the data
//                float val11 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx1 * gridDefinition.getNumberPointsLon() + iidx1) * bytesperval));
//                float val12 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx1 * gridDefinition.getNumberPointsLon() + iidx2) * bytesperval));
//
//                float val21 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx2 * gridDefinition.getNumberPointsLon() + iidx1) * bytesperval));
//                float val22 = sec5.calcValue(ByteBuffer.wrap(data).getShort((jidx2 * gridDefinition.getNumberPointsLon() + iidx2) * bytesperval));
//
//
//                // Find latitudes of the grid points of the matrix containing the data that surround the
//                // passed latitude lat
//                int lat1 = (int) ((gridDefinition.getFirstPointLat() + deltaj) + jidx1 * gridDefinition.getjDirectionIncrement());
//                int lat2 = lat1 + gridDefinition.getjDirectionIncrement();
//
//                // Find longitudes of the grid points of the matrix containing the data that surround the
//                // passed longitude lon
//                int lon1 = firstPointLon + iidx1 * gridDefinition.getiDirectionIncrement();
//                int lon2 = lon1 + gridDefinition.getiDirectionIncrement();
//
//                // Interpolate value belonging to the passed location
//                float val1x = val11 + (val12 - val11) * (lon - lon1) / (lon2 - lon1);
//                float val2x = val21 + (val22 - val21) * (lon - lon1) / (lon2 - lon1);
//                val = val1x + (val2x - val1x) * (lat - lat1) / (lat2 - lat1);
//            } else {
//                log.warning("Grid Definition Template Number 3." + getSection3().get(gridIdx).getGridDefinitionTemplateNumber() + " not implemented.");
//            }
//        } else {
//            log.warning("Data Representation Template Number 5." + sec5.getDataRepresentationTemplateNumber() + " not implemented.");
//        }
//
//        return val;
        return 0;
    }

    public GribSection0 getSection0() {
        return section0;
    }

    public void setSection0(GribSection0 section0) {
        this.section0 = section0;
    }

    public GribSection1 getSection1() {
        return section1;
    }

    public void setSection1(GribSection1 section1) {
        this.section1 = section1;
    }

    public List<GribSection2> getSection2() {
        return section2;
    }

    public void setSection2(List<GribSection2> section2) {
        this.section2 = section2;
    }

    public List<GribSection3> getSection3() {
        return section3;
    }

    public void setSection3(List<GribSection3> section3) {
        this.section3 = section3;
    }

    public List<GribSection4> getSection4() {
        return section4;
    }

    public void setSection4(List<GribSection4> section4) {
        this.section4 = section4;
    }

    public List<GribSection5> getSection5() {
        return section5;
    }

    public void setSection5(List<GribSection5> section5) {
        this.section5 = section5;
    }

    public List<GribSection6> getSection6() {
        return section6;
    }

    public void setSection6(List<GribSection6> section6) {
        this.section6 = section6;
    }

    public List<GribSection7> getSection7() {
        return section7;
    }

    public void setSection7(List<GribSection7> section7) {
        this.section7 = section7;
    }

    public static float getLatIndex(GridDefinitionTemplate30 gridDefinition, double latitude) {
        return (Grib2File.degToUnits(latitude) - gridDefinition.getFirstPointLat()) / gridDefinition.getjDirectionIncrement();
    }

    public static float getLonIndex(GridDefinitionTemplate30 gridDefinition, double longitude) {
        // Make sure that longitude is in a range from -180 to 180 degrees
        float firstPointLon = gridDefinition.getFirstPointLon();
        if ( firstPointLon >= Grib2File.degToUnits(180) ) { firstPointLon -= Grib2File.degToUnits(360); }

        return (Grib2File.degToUnits(longitude) - firstPointLon) / gridDefinition.getiDirectionIncrement();
    }

    public static double unitsToDeg(int units) {
        return (double) units / GRIB2_DEG_UNIT;
    }

    public static int degToUnits(double deg) {
        return (int) (deg * GRIB2_DEG_UNIT);
    }
}
