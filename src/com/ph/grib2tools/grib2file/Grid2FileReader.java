package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Grid2FileReader {

    public static Grib2File read(RandomAccessFile sourceGrib2File) throws IOException {
        if ( sourceGrib2File == null ) {
            return null;
        }
        Grib2File grib2File = new Grib2File();
        // Read Section 0 and verify if the data is valid GRIB/GRIB2 data
        grib2File.setSection0(new GribSection0(sourceGrib2File));
        if ( !grib2File.getSection0().isGrib2File() ) {
            System.out.println("This is not a GRIB2 file");
            throw new IOException("This is not a GRIB2 file");
        }
        short sectionNumber = GribSection.sectionNumber(sourceGrib2File);
        if ( sectionNumber == 1 ) {
            grib2File.setSection1(new GribSection1());
            grib2File.getSection1().readData(sourceGrib2File);
        }

        // Process the GRIB2 file
        while ( sourceGrib2File.getFilePointer() < sourceGrib2File.length() ) {
            // Read the next Section of the GRIB2 file
            sectionNumber = GribSection.sectionNumber(sourceGrib2File);
            if ( sectionNumber == 2 ) {
                GribSection2 section2 = new GribSection2();
                section2.readData(sourceGrib2File);
                grib2File.getSection2().add(section2);
            } else if ( sectionNumber == 3 ) {
                GribSection3 section3 = new GribSection3();
                section3.readData(sourceGrib2File);
                grib2File.getSection3().add(section3);
            } else if ( sectionNumber == 4 ) {
                GribSection4 section4 = new GribSection4();
                section4.readData(sourceGrib2File);
                grib2File.getSection4().add(section4);
            } else if ( sectionNumber == 5 ) {
                GribSection5 section5 = new GribSection5();
                section5.readData(sourceGrib2File);
                grib2File.getSection5().add(section5);
            } else if ( sectionNumber == 6 ) {
                GribSection6 section6 = new GribSection6();
                section6.readData(sourceGrib2File);
                grib2File.getSection6().add(section6);
            } else if ( sectionNumber == 7 ) {
                GribSection7 section7 = new GribSection7();
                section7.readData(sourceGrib2File);
                grib2File.getSection7().add(section7);
                GribSection8 gribSection8 = new GribSection8(sourceGrib2File);
                if ( gribSection8.isEnd() ) {
                    break;
                }
            } else {
                // Stop processing the GRIB file if no known section is found
                break;
            }
        }
        return grib2File;
    }
}
