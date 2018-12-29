package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate40;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate48;
import com.ph.grib2tools.grib2file.productdefinition.ProductDefinitionTemplate4x;

import java.io.IOException;
import java.io.RandomAccessFile;

public class GribSection4 extends GribSection {

    private static final long serialVersionUID = 100L;

    private int                         numberCoordinateValues;
    private int                         productDefinitionTemplateNumber;
    private ProductDefinitionTemplate4x productDefinitionTemplate;
    private byte[]                      optionalListOfCoordinates;


    @Override
    public void readData(RandomAccessFile gribFile) throws IOException {
        super.readData(gribFile);
        readSection(gribFile);
    }

    private void readSection(RandomAccessFile gribFile) throws IOException {
        // 6-7
        numberCoordinateValues = DataUtil.int2(gribFile);
        // 8-9
        productDefinitionTemplateNumber = DataUtil.int2(gribFile);

        if ( productDefinitionTemplateNumber == 0 ) {
            productDefinitionTemplate = new ProductDefinitionTemplate40(gribFile);
        } else if ( productDefinitionTemplateNumber == 8 ) {
            productDefinitionTemplate = new ProductDefinitionTemplate48(gribFile);
        } else {
            System.out.println("Product Definition Template Number 4." + productDefinitionTemplateNumber + " not implemented.");
        }

        optionalListOfCoordinates = new byte[numberCoordinateValues];
        gribFile.read(optionalListOfCoordinates);
    }

    public int getNumberCoordinateValues() {
        return numberCoordinateValues;
    }

    public int getProductDefinitionTemplateNumber() {
        return productDefinitionTemplateNumber;
    }

    public ProductDefinitionTemplate4x getProductDefinitionTemplate() {
        return productDefinitionTemplate;
    }

    public byte[] getOptionalListOfCoordinates() {
        return optionalListOfCoordinates;
    }
}
