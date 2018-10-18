package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate50;
import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate52;
import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate53;
import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate5x;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection5 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 5
     */
    private int                          numberDataPoints;
    private int                          dataRepresentationTemplateNumber;
    private DataRepresentationTemplate5x dataRepresentationTemplate;


    @Override
    public void readData(RandomAccessFile grib2File) throws IOException {
        super.readData(grib2File);
        readSection();
    }

    private void readSection() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());
        // 6-9 Number of data points where one or more values are specified in Section 7 when a bit map is present,
        //     total number of data points when a bit map is absent.
        numberDataPoints = byteBuffer.getInt();
        // 10-11
        dataRepresentationTemplateNumber = Short.toUnsignedInt(byteBuffer.getShort());

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
        int raw = adjustUnsignedShort(unsignedraw);
        return (dataRepresentationTemplate.getReferenceValueR() + (float) (0 + raw) * (float) Math.pow(2, dataRepresentationTemplate.getBinaryScaleFactorE()) / (float) Math.pow(10, dataRepresentationTemplate.getDecimalScaleFactorD()));
    }

    public int getNumberDataPoints() {
        return numberDataPoints;
    }

    public void setNumberDataPoints(int numberDataPoints) {
        this.numberDataPoints = numberDataPoints;
    }

    public int getDataRepresentationTemplateNumber() {
        return dataRepresentationTemplateNumber;
    }

    public void setDataRepresentationTemplateNumber(int dataRepresentationTemplateNumber) {
        this.dataRepresentationTemplateNumber = dataRepresentationTemplateNumber;
    }

    public DataRepresentationTemplate5x getDataRepresentationTemplate() {
        return dataRepresentationTemplate;
    }

    public void setDataRepresentationTemplate(DataRepresentationTemplate5x dataRepresentationTemplate) {
        this.dataRepresentationTemplate = dataRepresentationTemplate;
    }
}
