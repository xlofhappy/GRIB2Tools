package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate40;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate48;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate4x;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class GribSection4 extends GribSection {

    private static final long serialVersionUID = 100L;

    /**
     * Content and structure of a Section 4
     */
    private short                       numberCoordinateValues;
    private short                       productDefinitionTemplateNumber;
    private ProductDefinitionTemplate4x productDefinitionTemplate;
    private byte[]                      optionalListOfCoordinates;


    public GribSection4(RandomAccessFile gribFile) throws IOException {
        super(gribFile);
    }

    public GribSection4(GribSection gribSection) {
        super(gribSection.getSectionLength(), gribSection.getSectionNumber(), gribSection.getSectionData());
    }

    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection();
    }

    private void readSection() {

        ByteBuffer byteBuffer = ByteBuffer.wrap(getSectionData());

        // Parse section and extract data
        numberCoordinateValues = byteBuffer.getShort();
        productDefinitionTemplateNumber = byteBuffer.getShort();

        if ( productDefinitionTemplateNumber == 0 ) {
            productDefinitionTemplate = new ProductDefinitionTemplate40(byteBuffer);
        } else if ( productDefinitionTemplateNumber == 8 ) {
            productDefinitionTemplate = new ProductDefinitionTemplate48(byteBuffer);
        } else {
            System.out.println("Product Definition Template Number 4." + productDefinitionTemplateNumber + " not implemented.");
        }

        optionalListOfCoordinates = new byte[numberCoordinateValues];
        byteBuffer.get(optionalListOfCoordinates);
    }

    public short getNumberCoordinateValues() {
        return numberCoordinateValues;
    }

    public void setNumberCoordinateValues(short numberCoordinateValues) {
        this.numberCoordinateValues = numberCoordinateValues;
    }

    public short getProductDefinitionTemplateNumber() {
        return productDefinitionTemplateNumber;
    }

    public void setProductDefinitionTemplateNumber(short productDefinitionTemplateNumber) {
        this.productDefinitionTemplateNumber = productDefinitionTemplateNumber;
    }

    public ProductDefinitionTemplate4x getProductDefinitionTemplate() {
        return productDefinitionTemplate;
    }

    public void setProductDefinitionTemplate(ProductDefinitionTemplate4x productDefinitionTemplate) {
        this.productDefinitionTemplate = productDefinitionTemplate;
    }

    public byte[] getOptionalListOfCoordinates() {
        return optionalListOfCoordinates;
    }

    public void setOptionalListOfCoordinates(byte[] optionalListOfCoordinates) {
        this.optionalListOfCoordinates = optionalListOfCoordinates;
    }
}
