package com.ph.grib2tools.grib2file;

import com.ph.grib2tools.grib2file.datarepresentation.DataRepresentationTemplate50;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

/**
 * A representation of a GRIB file containing all meta data and allowing access to a
 * GRIB file that is streamed. As a consequence of the streaming, it is not possible
 * to access the data randomly.
 */
public class StreamedGribFile extends GribFile {

    private static final long serialVersionUID = 100L;

    protected InputStream gribFile;

    private static final Logger log = Logger.getLogger(StreamedGribFile.class.getName());


    public StreamedGribFile(String typeid, String source) {
        super(typeid, source);
    }

    public boolean prepareImportFromStream(InputStream gribFile1, int numSkip) throws IOException {

        this.gribFile = gribFile1;

        if ( gribFile == null ) { return false; }

        // By overwriting the section variables, the first numSkip GRIB file data structures
        // within a stream or a file can be skipped
        for ( int t = 0; t < numSkip + 1; t++ ) {

            gridcnt = 0;

            // Read all meta data but not the data itself in Section 7
            importMetadatFromStream(gribFile);

            // Consider Section 7 but do not read its data into memory
            GribSection nextsection = new GribSection(gribFile).initSection();
            if ( nextsection.sectionnumber == 7 ) {
                section7[gridcnt] = (GribSection7) nextsection;
                gridcnt++;
            } else {
                log.warning("Section " + nextsection.sectionnumber + " found while Section 7 expected. aborting.");
                return false;
            }

            // End import of data if a Section 7 does not contain any data
            if ( section7[gridcnt - 1].sectionlength == 5 ) { finalizeImport(gribFile1); }
        }

        return true;
    }

    /**
     * Extracts the next data field from the streamed GRIB file and returns the value represented
     * by the data
     */
    public float nextValue() throws IOException {

        int gridIdx = 0;

        GribSection5 sec5 = getSection5(gridIdx);

        float val = 0;

        if ( sec5.dataRepresentationTemplateNumber == 0 ) {

            // Get the resolution of the data (number of bytes per value)
            DataRepresentationTemplate50 dataRepresentation = (DataRepresentationTemplate50) sec5.dataRepresentationTemplate;
            int                          bytesperval        = dataRepresentation.numberBits / 8;

            // Read data from data stream
            byte[] data = new byte[bytesperval];
            gribFile.read(data);

            // Determine the value represented by the data
            short unsigneDraw = ByteBuffer.wrap(data).getShort();
            val = sec5.calcValue(unsigneDraw);
        } else {
            log.warning("Data Representation Template Number 5." + sec5.dataRepresentationTemplateNumber + " not implemented.");
        }

        return val;
    }
}
