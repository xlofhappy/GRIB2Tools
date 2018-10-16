package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate5x;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate30;
import com.ph.grib2tools.grib2file.griddefinition.GridDefinitionTemplate3x;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate4x;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

public abstract class GribFile implements Serializable {

    private static final long serialVersionUID = 100L;

    /**
     * Maximum number of Sections 5, 6, 7 supported
     */
    protected final int MAX_NUM_SECTIONS_5_6_7 = 10;

    /**
     * Conversation factor for converting GRIB file units to degree (for coordinates)
     */
    public static int GRIB2_DEG_UNIT = 1000000;

    /**
     * A string for identifying the data set. For information only, so can
     * be "" or null if not needed.
     */
    protected String typeId;

    /**
     * A string pointing to the source of the GRIB2 data. For information only,
     * so can be "" or null if not needed
     */
    protected String source;

    /**
     * Sections of the GRIB file
     */
    protected GribSection0   section0;
    protected GribSection1   section1;
    protected GribSection2   section2;
    protected GribSection3   section3;
    protected GribSection4   section4;
    protected GribSection5[] section5;
    protected GribSection6[] section6;
    protected GribSection7[] section7;
    protected GribSection8   section8;

    /**
     * Counter for multiple Sections 5, 6, 7
     */
    protected int gridcnt;

    private static final Logger log = Logger.getLogger(GribFile.class.getName());


    public GribFile(String typeId, String source) {

        this.typeId = typeId;
        this.source = source;

        this.gridcnt = 0;

        section5 = new GribSection5[MAX_NUM_SECTIONS_5_6_7];
        section6 = new GribSection6[MAX_NUM_SECTIONS_5_6_7];
        section7 = new GribSection7[MAX_NUM_SECTIONS_5_6_7];
    }

    /**
     * Reads all data describing structure and type of the product, the data etc. The
     * data itself is not read, also the end section (Section 8) is not considered.
     * This offers the opportunity to process the data separately and independently.
     * //public void importMetadatFromStream(InputStream gribFile) {
     */
    public void importMetadatFromStream(InputStream gribFile) throws IOException {
        if ( gribFile == null ) {
            return;
        }
        // Read Section 0 and verify if the data is valid GRIB/GRIB2 data
        section0 = new GribSection0(gribFile);
        if ( !section0.verifyMagicNumber() ) {
            System.out.println("This is not a GRIB file");
        }
        if ( !section0.verifyGribVersion() ) {
            System.out.println("This is not a GRIB2 file");
        }
        // Process the GRIB file
        while ( true ) {
            // Read the next Section of the GRIB file
            GribSection nextsection = new GribSection(gribFile).initSection();
            //if (nextsection == null) continue;
            if ( nextsection == null ) { break; }
            if ( nextsection.getSectionNumber() == 1 ) {
                section1 = (GribSection1) nextsection;
                section1.readData(gribFile);
            } else if ( nextsection.getSectionNumber() == 2 ) {
                section2 = (GribSection2) nextsection;
                section2.readData(gribFile);
            } else if ( nextsection.getSectionNumber() == 3 ) {
                section3 = (GribSection3) nextsection;
                section3.readData(gribFile);
            } else if ( nextsection.getSectionNumber() == 4 ) {
                section4 = (GribSection4) nextsection;
                section4.readData(gribFile);
            } else if ( nextsection.getSectionNumber() == 5 ) {
                section5[gridcnt] = (GribSection5) nextsection;
                section5[gridcnt].readData(gribFile);
            } else if ( nextsection.getSectionNumber() == 6 ) {
                section6[gridcnt] = (GribSection6) nextsection;
                section6[gridcnt].readData(gribFile);
                break;
            } else {
                // Stop processing the GRIB file if no known section is found
                break;
            }
        }
    }

    /**
     * Handle Section 8 of a GRIB file
     *
     * @param gribFile
     */
    public void finalizeImport(InputStream gribFile) {
        // Read Section 8 and verify if the GRIB data is terminated correctly
        section8 = new GribSection8(gribFile);
        if ( !section8.verifyEndIdentifier() ) {
            System.out.println("End of GRIB file not reached.");
        }
    }

    /**
     * Write the complete GRIB file data to an OutputStream
     * //public void writeToStream(OutputStream gribFile) {
     */
    public void writeToStream(OutputStream gribFile) throws IOException {
        section0.writeToStream(gribFile);
        section1.writeToStream(gribFile);
        section2.writeToStream(gribFile);
        section3.writeToStream(gribFile);
        section4.writeToStream(gribFile);

        for ( int cnt = 0; cnt < gridcnt; cnt++ ) {
            section5[cnt].writeToStream(gribFile);
            section6[cnt].writeToStream(gribFile);
            section7[cnt].writeToStream(gribFile);
        }

        section8.writeToStream(gribFile);
    }

    public String getType() {
        return typeId;
    }

    public String getSource() {
        return source;
    }

    public int getGridCount() {
        return gridcnt;
    }

    /**
     * Returns the GridDefinitionTemplate of the GRIB file according to the Template Number
     */
    public GridDefinitionTemplate3x getGridDefinitionTemplate() {
        GridDefinitionTemplate3x gridDefinition = null;
        if ( section3.getGridDefinitionTemplateNumber() == 0 ) {
            gridDefinition = section3.getGridDefinitionTemplate();
        } else {
            log.severe("Grid Definition Template Number 3." + section3.getGridDefinitionTemplateNumber() + " not implemented.");
        }
        return gridDefinition;
    }

    /**
     * Returns the ProductDefinitionTemplate of the GRIB file according to the Template Number
     */
    public ProductDefinitionTemplate4x getProductDefinitionTemplate() {
        ProductDefinitionTemplate4x productDefinition = null;
        if ( section4.getProductDefinitionTemplateNumber() == 0 ) {
            productDefinition = section4.getProductDefinitionTemplate();
        } else if ( section4.getProductDefinitionTemplateNumber() == 8 ) {
            productDefinition = section4.getProductDefinitionTemplate();
        } else {
            log.severe("Product Definition Template Number 4." + section4.getProductDefinitionTemplateNumber() + " not implemented.");
        }
        return productDefinition;
    }

    /**
     * Returns the DataRepresentationTemplate of grid gridId of the GRIB file according to the Template Number
     *
     * @param gridId
     */
    public DataRepresentationTemplate5x getDataRepresentationTemplate(int gridId) {
        DataRepresentationTemplate5x dataRepresentation = null;
        if ( section5[gridId].getDataRepresentationTemplateNumber() == 0 ) {
            dataRepresentation = section5[gridId].getDataRepresentationTemplate();
        } else {
            log.severe("Data Representation Template Number 5." + section5[gridId].getDataRepresentationTemplateNumber() + " not implemented.");
        }
        return dataRepresentation;
    }

    public GribSection0 getSection0() {
        return section0;
    }

    public GribSection1 getSection1() {
        return section1;
    }

    public GribSection2 getSection2() {
        return section2;
    }

    public GribSection3 getSection3() {
        return section3;
    }

    public GribSection4 getSection4() {
        return section4;
    }

    public GribSection5 getSection5(int gridId) {
        return section5[gridId];
    }

    public GribSection6 getSection6(int gridId) {
        return section6[gridId];
    }

    public GribSection7 getSection7(int gridId) {
        return section7[gridId];
    }

    public GribSection8 getSection8() {
        return section8;
    }

    public void setSection0(GribSection0 section0) {
        this.section0 = section0;
    }

    public void setSection1(GribSection1 section1) {
        this.section1 = section1;
    }

    public void setSection2(GribSection2 section2) {
        this.section2 = section2;
    }

    public void setSection3(GribSection3 section3) {
        this.section3 = section3;
    }

    public void setSection4(GribSection4 section4) {
        this.section4 = section4;
    }

    public void setSection5(int idx, GribSection5 section5) {
        this.section5[idx] = section5;
    }

    public void setSection6(int idx, GribSection6 section6) {
        this.section6[idx] = section6;
    }

    public void setSection7(int idx, GribSection7 section7) {
        this.section7[idx] = section7;
    }

    public void setSection8(GribSection8 section8) {
        this.section8 = section8;
    }

    public static int getLatIndex(GridDefinitionTemplate30 gridDefinition, double latitude) {
        return (GribFile.degToUnits(latitude) - gridDefinition.getFirstPointLat()) / gridDefinition.getjDirectionIncrement();
    }

    public static int getLonIndex(GridDefinitionTemplate30 gridDefinition, double longitude) {
        // Make sure that longitude is in a range from -180 to 180 degrees
        int firstPointLon = gridDefinition.getFirstPointLon();
        if ( firstPointLon >= GribFile.degToUnits(180) ) { firstPointLon -= GribFile.degToUnits(360); }

        return (GribFile.degToUnits(longitude) - firstPointLon) / gridDefinition.getiDirectionIncrement();
    }

    public static long getVersion() {
        return serialVersionUID;
    }

    public static String getVersionString() {
        return Long.toString(serialVersionUID);
    }

    public static double unitsToDeg(int units) {
        return (double) units / GRIB2_DEG_UNIT;
    }

    public static int degToUnits(double deg) {
        return (int) (deg * GRIB2_DEG_UNIT);
    }
}
