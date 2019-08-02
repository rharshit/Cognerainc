package com.rharshit.cognerainc;

public class Callbacks {
    public static processCallback processCallback;
    public static setCallback setCallback;

    interface processCallback {
        void process(String s);
    }

    interface setCallback {
        void set(String s);
    }
}
