package jni;

public class CpplibJNI {
    static {
        System.loadLibrary("cpplib");
    }

    public native String cpplib(int number);
}
