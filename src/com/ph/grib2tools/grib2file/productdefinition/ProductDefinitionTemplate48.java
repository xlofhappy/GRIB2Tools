package com.ph.grib2tools.grib2file.productdefinition;

import com.ph.grib2tools.grib2file.DataUtil;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ProductDefinitionTemplate48 extends ProductDefinitionTemplate4x {

    private static final long serialVersionUID = 100L;

    private ProductDefinitionTemplate40 productDefinitionTemplate40;

    private int                      yearEnd;
    private int                      monthEnd;
    private int                      dayEnd;
    private int                      hourEnd;
    private int                      minuteEnd;
    private int                      secondEnd;
    private int                      numberTimeRangeSpecifications;
    private int                      totalNumberDataValuesMissing;
    private TimeRangeSpecification[] timeRangeSpecification;


    public ProductDefinitionTemplate48(RandomAccessFile gribFile) throws IOException {
        productDefinitionTemplate40 = new ProductDefinitionTemplate40(gribFile);
        yearEnd = gribFile.read();
        monthEnd = gribFile.read();
        dayEnd = gribFile.read();
        hourEnd = gribFile.read();
        minuteEnd = gribFile.read();
        secondEnd = gribFile.read();
        numberTimeRangeSpecifications = gribFile.read();
        totalNumberDataValuesMissing = DataUtil.int4(gribFile);
        timeRangeSpecification = new TimeRangeSpecification[numberTimeRangeSpecifications];
        for ( int i = 0; i < numberTimeRangeSpecifications; i++ ) {
            timeRangeSpecification[i] = new TimeRangeSpecification(gribFile);
        }
    }

    public ProductDefinitionTemplate40 getProductDefinitionTemplate40() {
        return productDefinitionTemplate40;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public int getDayEnd() {
        return dayEnd;
    }

    public int getHourEnd() {
        return hourEnd;
    }

    public int getMinuteEnd() {
        return minuteEnd;
    }

    public int getSecondEnd() {
        return secondEnd;
    }

    public int getNumberTimeRangeSpecifications() {
        return numberTimeRangeSpecifications;
    }

    public int getTotalNumberDataValuesMissing() {
        return totalNumberDataValuesMissing;
    }

    public TimeRangeSpecification[] getTimeRangeSpecification() {
        return timeRangeSpecification;
    }
}
