package util;

import java.util.*;

public class AppConfig {

    public static int roomAndUtilityBilIssueDay = 30;
    public static int roomAndUtilityPaymentDay = 30;

    public static List<Integer> roomPaymentMonths = new ArrayList<>(Arrays.asList(4, 8, 12));

    public static int notificationDay = 30;

    public static boolean bookRoom = true;

    public static double electricityPrice = 3000;
    public static double waterPrice = 10000;
}
