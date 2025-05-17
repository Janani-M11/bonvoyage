package com.example.bonvoyagehotel.util;

import java.time.LocalDate;

public class DateUtils {

    public static long calculateDays(LocalDate start, LocalDate end) {
        return end.toEpochDay() - start.toEpochDay();
    }

    public static boolean isInvalidBookingDate(LocalDate checkIn, LocalDate checkOut) {
        return checkOut.isBefore(checkIn) || checkIn.isBefore(LocalDate.now());
    }
}
