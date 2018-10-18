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
        //        if ( section4.getProductDefinitionTemplateNumber() == 0 ) {
        //            productDefinition = section4.getProductDefinitionTemplate();
        //        } else if ( section4.getProductDefinitionTemplateNumber() == 8 ) {
        //            productDefinition = section4.getProductDefinitionTemplate();
        //        } else {
        //            log.severe("Product Definition Template Number 4." + section4.getProductDefinitionTemplateNumber() + " not implemented.");
        //        }
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

    public static int getLatIndex(GridDefinitionTemplate30 gridDefinition, double latitude) {
        return (Grib2File.degToUnits(latitude) - gridDefinition.getFirstPointLat()) / gridDefinition.getjDirectionIncrement();
    }

    public static int getLonIndex(GridDefinitionTemplate30 gridDefinition, double longitude) {
        // Make sure that longitude is in a range from -180 to 180 degrees
        int firstPointLon = gridDefinition.getFirstPointLon();
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
