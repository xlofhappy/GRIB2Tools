package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate50;
import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate52;
import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate53;
import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate5x;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class GribSection5 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 5
     */
    private int                          numberDataPoints;
    private short                        dataRepresentationTemplateNumber;
    private DataRepresentationTemplate5x dataRepresentationTemplate;


    public GribSection5(InputStream gribfile) throws IOException {
        super(gribfile);
    }

    public GribSection5(GribSection gribSection) {
        super(gribSection.getSectionLength(), gribSection.getSectionNumber(), gribSection.getSectionData());
    }

    @Override
    public void readData(InputStream gribfile) throws IOException {
        super.readData(gribfile);
        readSection();
    }

    private void readSection() {

        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());

        // Parse section and extract data
        numberDataPoints = byteBuffer.getInt();
        dataRepresentationTemplateNumber = byteBuffer.getShort();

        if ( dataRepresentationTemplateNumber == 0 ) {
            dataRepresentationTemplate = new DataRepresentationTemplate50(byteBuffer);
        } else if ( dataRepresentationTemplateNumber == 2 ) {
            dataRepresentationTemplate = new DataRepresentationTemplate52(byteBuffer);
        } else if ( dataRepresentationTemplateNumber == 3 ) {
            dataRepresentationTemplate = new DataRepresentationTemplate53(byteBuffer);
        } else {
            System.out.println("Data Representation Template Number 5." + dataRepresentationTemplateNumber + " not implemented.");
        }
    }

    public int getNumDataPoints() {
        return numberDataPoints;
    }

    public float calcValue(short unsignedraw) {
        // Calculate value according to the GRIB specification
        int   raw = adjustUnsignedShort(unsignedraw);
        return (dataRepresentationTemplate.getReferenceValueR() + (float) (0 + raw) * (float) Math.pow(2, dataRepresentationTemplate.getBinaryScaleFactorE()) / (float) Math.pow(10, dataRepresentationTemplate.getDecimalScaleFactorD()));
    }

    public int getNumberDataPoints() {
        return numberDataPoints;
    }

    public void setNumberDataPoints(int numberDataPoints) {
        this.numberDataPoints = numberDataPoints;
    }

    public short getDataRepresentationTemplateNumber() {
        return dataRepresentationTemplateNumber;
    }

    public void setDataRepresentationTemplateNumber(short dataRepresentationTemplateNumber) {
        this.dataRepresentationTemplateNumber = dataRepresentationTemplateNumber;
    }

    public DataRepresentationTemplate5x getDataRepresentationTemplate() {
        return dataRepresentationTemplate;
    }

    public void setDataRepresentationTemplate(DataRepresentationTemplate5x dataRepresentationTemplate) {
        this.dataRepresentationTemplate = dataRepresentationTemplate;
    }
}
