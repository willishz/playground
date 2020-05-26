package org.willishz.playground.algorithm;

/**
 * 计算日期差
 */
public class DaysBetween {

    public static void main(String[] args) {
        System.out.println(DaysBetween.getDaysBetween(1999, 2, 28, 2020, 5, 26));
    }

    public static int getDaysBetween(int y1, int m1, int d1, int y2, int m2, int d2) {
        int[] daysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int runnian = 0;
        for (int i = y1; i <= y2; i++) {
            if (isLeapYear(i)) {
                runnian++;
            }
        }

        int day1 = 0; // 和1.1日比
        for (int i = 0; i < m1; i++) {
            day1 += daysInMonth[i];
            if (i == 2 && isLeapYear(y1)) {
                day1++;
            }
        }
        day1 += d1;

        int day2 = 0; // 和1.1日比
        for (int j = 0; j < m2; j++) {
            day2 += daysInMonth[j];
            if (j == 2 && isLeapYear(y2)) {
                day2++;
            }
        }
        day2 += d2;

        int diff = (y2 - y1) * 365 + runnian + day2 - day1;
        return diff;
    }

    static boolean isLeapYear(int i) {
        return i % 4 == 0 || i % 100 == 0 || i % 400 == 0;
    }
}