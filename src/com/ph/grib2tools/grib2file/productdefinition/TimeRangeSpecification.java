package com.ph.grib2tools.grib2file.productdefinition;

import com.ph.grib2tools.grib2file.DataUtil;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class TimeRangeSpecification implements Serializable {

    private static final long serialVersionUID = 100L;

    private int statisticalProcess;
    private int typeOfTimeIncrement;
    private int unitTimeRange;
    private int lengthTimeRange;
    private int unitTimeIncrement;
    private int lengthTimeIncrement;


    public TimeRangeSpecification(RandomAccessFile gribFile) throws IOException {
        statisticalProcess = gribFile.read();
        typeOfTimeIncrement = gribFile.read();
        unitTimeRange = gribFile.read();
        lengthTimeRange = DataUtil.int4(gribFile);
        unitTimeIncrement = gribFile.read();
        lengthTimeIncrement = DataUtil.int4(gribFile);
    }

    public int getStatisticalProcess() {
        return statisticalProcess;
    }

    public int getTypeOfTimeIncrement() {
        return typeOfTimeIncrement;
    }

    public int getUnitTimeRange() {
        return unitTimeRange;
    }

    public int getLengthTimeRange() {
        return lengthTimeRange;
    }

    public int getUnitTimeIncrement() {
        return unitTimeIncrement;
    }

    public int getLengthTimeIncrement() {
        return lengthTimeIncrement;
    }
}
