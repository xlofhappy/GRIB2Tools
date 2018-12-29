package com.ph.grib2tools.grib2file;

import java.util.logging.Logger;

/**
 * A representation of a GRIB file containing all meta data and allowing a random
 * access to the data of the GRIB file. While the random access allows great flexibility
 * it requires that the complete data is held in memory
 **/
public class RandomAccessGribFile extends Grib2File {

    private Logger log = Logger.getLogger("Grib2File");

    //    private static final Logger log = Logger.getLogger(RandomAccessGribFile.class.getName());
    //
    //    public RandomAccessGribFile(String typeId, String source) {
    //        super(typeId, source);
    //    }
    //
    //    public List<Grib2File> importFromStream(RandomAccessFile grib2File, int numSkip) throws IOException {
    //        if ( grib2File == null ) {
    //            return null;
    //        }
    //        ArrayList<Grib2File> grib2Files = new ArrayList<>();
    //        // By overwriting the section variables, the first numSkip GRIB file data structures
    //        // within a stream or a file can be skipped
    //        for ( int t = 0; t < numSkip + 1; t++ ) {
    //            while ( true ) {
    //                //            while ( gridcnt < 1 ) {
    //                // Read all meta data but not the data itself in Section 7
    //                Grib2File grib2File1 = importMetadataFromStream(grib2File);
    //                grib2Files.add(grib2File1);
    //                // Read the data of Section 7 into memory
    ////                short sectionNumber = GribSection.sectionNumber(grib2File);
    ////                if ( sectionNumber == 7 ) {
    ////                    GribSection7 section7 = new GribSection7(grib2File);
    ////                    grib2File.seek(grib2File.getFilePointer() + section7.getSectionLength() - 5);
    ////                    this.section7.add(section7);
    ////                } else {
    ////                    log.warning("Section " + sectionNumber + " found while Section 7 expected. aborting.");
    ////                    break;
    ////                }
    //            }
    ////            finalizeImport(grib2File);
    //        }
    //        return grib2Files;
    //    }

    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in degrees and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the grid point closest to the passed position
     * is considered.
     */
    public float getValueAtLocation(int gridIdx, double lat, double lon) {
        //        return getValueAt(gridIdx, Grib2File.degToUnits(lat), Grib2File.degToUnits(lon));
        return 0;
    }

    /**
     * Extracts the data belonging to the passed coordinate (lat, lon) in units and returns the
     * value represented by this data. If the passed coordinate is not a grid point of the
     * data grid, the data belonging to the grid point closest to the passed position
     * is considered.
     */
    //    public float getValueAt(int gridIdx, int lat, int lon) {
    //        GribSection5 sec5 = getSection5(gridIdx);
    //        GribSection7 sec7 = getSection7(gridIdx);
    //        float        val  = 0;
    //        if ( sec5.getDataRepresentationTemplateNumber() == 0 ) {
    //            DataRepresentationTemplate50 dataRepresentation = (DataRepresentationTemplate50) sec5.getDataRepresentationTemplate();
    //            int                          bytesperval        = dataRepresentation.getNumberBits() / 8;
    //
    //            if ( section3.getGridDefinitionTemplateNumber() == 0 ) {
    //                GridDefinitionTemplate30 gridDefinition = (GridDefinitionTemplate30) section3.getGridDefinitionTemplate();
    //                int                      deltaj         = 0;
    //                // Implementation of Scanning Modes
    //                if ( (gridDefinition.getScanningMode() & 0x01) == 0x01 ) {
    //                    log.warning("Scanning mode " + 0x01 + " not supported");
    //                }
    //                if ( (gridDefinition.getScanningMode() & 0x02) == 0x02 ) {
    //                    log.warning("Scanning mode " + 0x02 + " not supported");
    //                }
    //                if ( (gridDefinition.getScanningMode() & 0x04) == 0x04 ) {
    //                    log.warning("Scanning mode " + 0x04 + " not supported");
    //                }
    //                if ( (gridDefinition.getScanningMode() & 0x08) == 0x08 ) {
    //                    log.warning("Scanning mode " + 0x08 + " not supported");
    //                }
    //                if ( (gridDefinition.getScanningMode() & 0x10) == 0x10 ) {
    //                    log.warning("Scanning mode " + 0x10 + " not supported");
    //                }
    //                if ( (gridDefinition.getScanningMode() & 0x20) == 0x20 ) {
    //                    log.warning("Scanning mode " + 0x20 + " not supported");
    //                }
    //                if ( (gridDefinition.getScanningMode() & 0x40) == 0x40 ) {
    //                    deltaj = gridDefinition.getjDirectionIncrement() / 2;
    //                }
    //                if ( (gridDefinition.getScanningMode() & 0x80) == 0x80 ) {
    //                    log.warning("Scanning mode " + 0x80 + " not supported");
    //                }
    //
    //                // Calculate j index of the matrix containing the data the contains the data of the
    //                // passed latitude lat
    //                int deltalat = lat - (gridDefinition.getFirstPointLat() + deltaj);
    //                int jidx     = Math.round((float) deltalat / (float) gridDefinition.getjDirectionIncrement());
    //
    //                // Calculate i index of the matrix containing the data the contains the data of the
    //                // passed longitude lon
    //                int firstPointLon = gridDefinition.getFirstPointLon() + 0;
    //                if ( firstPointLon >= Grib2File.degToUnits(180) ) { firstPointLon -= Grib2File.degToUnits(360); }
    //                int deltalon = lon - firstPointLon;
    //                int iidx     = Math.round((float) deltalon / (float) gridDefinition.getiDirectionIncrement());
    //
    //                // Extract data belonging to the referred location and calculate the value represented by the data
    //                byte  data[]      = sec7.getSectionData();
    //                short unsigneDraw = ByteBuffer.wrap(data).getShort((jidx * gridDefinition.getNumberPointsLon() + iidx) * bytesperval);
    //                val = sec5.calcValue(unsigneDraw);
    //            } else {
    //                log.warning("Grid Definition Template Number 3." + section3.getGridDefinitionTemplateNumber() + " not implemented.");
    //            }
    //        } else {
    //            log.warning("Data Representation Template Number 5." + sec5.getDataRepresentationTemplateNumber() + " not implemented.");
    //        }
    //
    //        return val;
    //    }

}
