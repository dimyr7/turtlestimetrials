package com.ironhawk.dimyr7.turtlestimetrials;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by dimyr7 on 6/2/15.
 */
public class SwimSet {
    private static String[][] swimmers = new String[3][5];
    private static int repeatTimes;
    private static int[] numSwimmersInLane = new int[3];

    private static ArrayList<Long> intervals = new ArrayList<Long>();
    private static long startTime;
    private static long pauseTime;


    private static ArrayList<Integer> laneLog = new ArrayList<Integer>();
    private static ArrayList<Integer> heatLog = new ArrayList<Integer>();
    private static ArrayList<Long> timeLog = new ArrayList<Long>();

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

    public static void addInterval(long time){
        intervals.add(time);
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

    public static void newLogEntry(int lane, int heat, long time){
        laneLog.add((Integer)lane);
        heatLog.add((Integer)heat);
        timeLog.add((Long)time);
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

    public static void setNumSwimmersInLane(int lane, int amt){
        numSwimmersInLane[lane]=amt;
    }
    public static int getNumSwimmersInLane(int lane){
        return numSwimmersInLane[lane];
    }



}
