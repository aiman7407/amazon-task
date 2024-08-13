package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {

     public  static String  getTimeNow()
    {

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy@HH-mm");

        return localDateTime.format(formatter);
    }


}
