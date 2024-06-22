package utils;
import java.util.ArrayList;

public class Time {
	 public static ArrayList times;

	    public static long timeStart;
	    public static long timeEnd;
	    public static long RunTime;

	    public static void timeCountStart(){timeStart = System.nanoTime();}
	    public static void timeCountEnd(){timeEnd = System.nanoTime();}
	    public static void timeCountRun(){RunTime = timeEnd-timeStart;}

	    public static void addRuntime(long RunTime){ times.add(RunTime);}


}
