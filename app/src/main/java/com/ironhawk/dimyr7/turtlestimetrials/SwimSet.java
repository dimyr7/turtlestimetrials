package com.ironhawk.dimyr7.turtlestimetrials;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by dimyr7 on 6/2/15.
 */
public class SwimSet {
    private static String[][] swimmers = new String[3][5];
    private static int repeatTimes;
    private static int maxSwimmerPerLane;

    private static ArrayList<Long> intervals = new ArrayList<>();
    private static long startTime;
    private static long pauseTime;


    private static ArrayList<Integer> laneLog = new ArrayList<>();
    private static ArrayList<Integer> heatLog = new ArrayList<>();
    private static ArrayList<Long> timeLog = new ArrayList<>();

    public static void setSwimmer(int lane, int swimmer, String name) {
        swimmers[lane][swimmer] = name;
    }
    public static String getSwimmer(int lane, int swimmer){
        return swimmers[lane][swimmer];
    }

    public static void setRepeatTimes(int repeat){
        repeatTimes = repeat;
    }
    public static int getRepeatTimes(){
        return repeatTimes;
    }

    public static void pushInterval(int time){
        intervals.add((long)time);
    }
    public static long getInterval(int i){
        return intervals.get(i);
    }

    public static void setStartTime(){
        startTime = System.currentTimeMillis();
    }
    public static long getStartTime(){
        return startTime;
    }

    public static void setMaxSwimmerPerLane(int num){
        maxSwimmerPerLane = num;
    }
    public static int getMaxSwimmerPerLane(){
        return maxSwimmerPerLane;
    }

    public static void newLogEntry(int lane, int heat){
        laneLog.add((Integer)lane);
        heatLog.add((Integer)heat);
        timeLog.add((Long)System.currentTimeMillis());
    }
    public static ArrayList<Integer> getLaneLog(){
        return laneLog;
    }
    public static ArrayList<Integer> getHeatLog(){
        return heatLog;
    }
    public static ArrayList<Long> getTimeLog(){
        return timeLog;
    }

    public static void setPauseTime(long time){
        pauseTime = time;
    }
    public static long getPauseTime(){
        return pauseTime;
    }

}
