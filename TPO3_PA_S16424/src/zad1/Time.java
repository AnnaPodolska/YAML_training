/**
 *
 *  @author Podolska Anna S16424
 *
 */

package zad1;



import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Time {
    public Time(){
    }
    public static String passed(String from, String to){
        String dpatt = "d MMMM yyyy (EEEE)";
        String tpatt = "d MMMM yyyy (EEEE) 'godz.' HH:mm";
        Locale pl = new Locale("pl");
        Long days;
        Long hours;
        Long minutes;
        String result = "";
        try {

            if (from.contains("T") && to.contains("T")) {
                LocalDateTime ld1 = LocalDateTime.parse(from);
                LocalDateTime ld2 = LocalDateTime.parse(to);
                ZonedDateTime zdt1 = ZonedDateTime.of(ld1, ZoneId.of("Europe/Warsaw"));
                ZonedDateTime zdt2 = ZonedDateTime.of(ld2, ZoneId.of("Europe/Warsaw"));
                days = ChronoUnit.DAYS.between( LocalDate.parse(from.split("T")[0]), LocalDate.parse(to.split("T")[0]));
                hours = ChronoUnit.HOURS.between(zdt1, zdt2);
                minutes = ChronoUnit.MINUTES.between(zdt1, zdt2);
                Period p = Period.between(LocalDate.parse(from.substring(0, 10)), LocalDate.parse(to.substring(0, 10)));
                result = "Od " + ld1.format(DateTimeFormatter.ofPattern(tpatt)) +
                        " do " + ld2.format(DateTimeFormatter.ofPattern(tpatt)) + "\n" +
                        "- mija: " + days;
                if(days ==1)
                    result += " dzień, ";
                else
                    result += " dni, ";
                result += "tygodni " + (String.format("%.2f", days / 7.0).replaceAll(",", "."));
                if (days >= 1) {
                    result += "\n- godzin: " + hours + ", minut: " + minutes;
                }
                result += getByPeriod(p);
            } else {
                LocalDate ld1 = LocalDate.parse(from);
                LocalDate ld2 = LocalDate.parse(to);
                Period p = Period.between(ld1, ld2);
                days = ChronoUnit.DAYS.between(ld1, ld2);
                result = "Od " + ld1.format(DateTimeFormatter.ofPattern(dpatt)) +
                        " do " + ld2.format(DateTimeFormatter.ofPattern(dpatt)) + "\n" +
                        "- mija: " + days;
                if(days ==1)
                    result += " dzień, ";
                else
                    result += " dni, ";
                result += "tygodni " + (String.format("%.2f", days / 7.0).replaceAll(",", "."));
                result += getByPeriod(p);
            }
        }
        catch (DateTimeParseException ex) {
            System.out.print("*** " + ex.toString());
        }
        return result;
    }

    public static String getByPeriod(Period p){
        int days = p.getDays();
        int months = p.getMonths();
        int years = p.getYears();
        String str = "\n- kalendarzowo: ";
        if(years != 0){
            str += years;
            if (years == 1)
                    str += " rok";
            else if (years % 2 ==0)
                str += " lata";
            else
                str += " lat";
            }
         if(months != 0){
             if(years != 0)
                 str += ", ";
            str += months;
             if (months == 1)
             str += " miesiąc";
            else if (months % 2 ==0)
                 str += " miesiące";
             else
                 str += " miesięcy";
         }
         if(days != 0) {
            if(months != 0)
                str += ", ";
             str += days;
             if (days == 1)
                 str += " dzień";
             else
                 str += " dni";
         }
            return str;
        }
    }
