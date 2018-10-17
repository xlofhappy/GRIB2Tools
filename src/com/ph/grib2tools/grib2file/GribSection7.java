package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.datarepresentation.*;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection7 extends GribSection {

    private static final long serialVersionUID = 200L;

    private int                          numberDataPoints;
    private DataRepresentationTemplate5x dataRepresentation;
    private DataSection7x                data;

    public GribSection7(int len, byte num, byte[] data) {
        super(len, num, data);
    }

    public GribSection7(RandomAccessFile grib2File) throws IOException {
        super(grib2File);
    }

    public GribSection7(GribSection gribSection) {
        super(gribSection.getSectionLength(), gribSection.getSectionNumber(), gribSection.getSectionData());
    }

    @Override
    public void readData(RandomAccessFile grib2File) throws IOException {
        super.readData(grib2File);
        readSection();
    }

    public void readSection() {
        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());
        if ( dataRepresentation.getClass().equals(DataRepresentationTemplate50.class) ) {
            /* Nothing to do */
        } else if ( dataRepresentation.getClass().equals(DataRepresentationTemplate52.class) ) {
            data = new DataSection72(numberDataPoints, (DataRepresentationTemplate52) dataRepresentation, byteBuffer);
        } else if ( dataRepresentation.getClass().equals(DataRepresentationTemplate53.class) ) {
            data = new DataSection73(numberDataPoints, (DataRepresentationTemplate53) dataRepresentation, byteBuffer);
        } else {
            System.out.println("Data Representation Template Number 7." + dataRepresentation + " not implemented.");
        }
    }

    public void setDataRepresentation(int numberDataPoints, DataRepresentationTemplate5x dataRepresentation) {
        this.numberDataPoints = numberDataPoints;
        this.dataRepresentation = dataRepresentation;
    }

    public int getNumberDataPoints() {
        return numberDataPoints;
    }

    public void setNumberDataPoints(int numberDataPoints) {
        this.numberDataPoints = numberDataPoints;
    }

    public DataRepresentationTemplate5x getDataRepresentation() {
        return dataRepresentation;
    }

    public void setDataRepresentation(DataRepresentationTemplate5x dataRepresentation) {
        this.dataRepresentation = dataRepresentation;
    }

    public DataSection7x getData() {
        return data;
    }

    public void setData(DataSection7x data) {
        this.data = data;
    }
}
