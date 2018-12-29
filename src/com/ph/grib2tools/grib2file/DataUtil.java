package com.ph.grib2tools.grib2file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class DataUtil {

    private static final int UNDEFINED = -9999;

    public static int int2(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();

        return int2(a, b);
    }

    private static int int2(int a, int b) {
        if ( ((a == 0xff) && (b == 0xff)) ) {
            return UNDEFINED;
        }
        return (1 - ((a & 128) >> 6)) * ((a & 127) << 8 | b);
    }

    public static int int3(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();
        int c = raf.read();

        return int3(a, b, c);
    }

    private static int int3(int a, int b, int c) {
        return (1 - ((a & 128) >> 6)) * ((a & 127) << 16 | b << 8 | c);
    }

    public static int int4(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();
        int c = raf.read();
        int d = raf.read();

        return int4(a, b, c, d);
    }

    private static int int4(int a, int b, int c, int d) {
        // all bits set to ones
        if ( (a == 0xff) && (b == 0xff) && (c == 0xff) && (d == 0xff) ) {
            return UNDEFINED;
        }

        return (1 - ((a & 128) >> 6)) * ((a & 127) << 24 | b << 16 | c << 8 | d);
    }

    public static int uint2(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();

        return uint2(a, b);
    }

    private static int uint2(int a, int b) {
        return a << 8 | b;
    }

    public static int uint3(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();
        int c = raf.read();

        return uint3(a, b, c);
    }

    private static int uint3(int a, int b, int c) {
        return a << 16 | b << 8 | c;
    }

    public static float float4(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();
        int c = raf.read();
        int d = raf.read();

        return float4(a, b, c, d);
    }

    private static float float4(int a, int b, int c, int d) {
        int sgn, mant, exp;

        mant = b << 16 | c << 8 | d;
        if ( mant == 0 ) {
            return 0.0f;
        }

        sgn = -(((a & 128) >> 6) - 1);
        exp = (a & 127) - 64;

        return (float) (sgn * Math.pow(16.0, exp - 6) * mant);
    }

    public static long long8(RandomAccessFile raf) throws IOException {
        int a = raf.read();
        int b = raf.read();
        int c = raf.read();
        int d = raf.read();
        int e = raf.read();
        int f = raf.read();
        int g = raf.read();
        int h = raf.read();

        return (1 - ((a & 128) >> 6)) * ((a & 127) << 56 | b << 48 | c << 40 | d << 32 | e << 24 | f << 16 | g << 8 | h);

    }
}
