package pl.com.softproject.diabetyk.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Piotr Szwed on 20.08.15.
 */
public class DateTimeValidator {

    private Pattern pattern;
    private Matcher matcher;

    // yyyy-MM-dd-HH-mm-ss
    private static final String DATE_PATTERN =
            "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-([01]?[0-9]|2[0-3])-([0-5][0-9])-([0-5][0-9])";

    public DateTimeValidator(){
        pattern = Pattern.compile(DATE_PATTERN);
    }

    public boolean validate(final String date){

        matcher = pattern.matcher(date);

        if(matcher.matches()){

            matcher.reset();

            if(matcher.find()){

                int year = Integer.parseInt(matcher.group(1));
                String month = matcher.group(3);
                String day = matcher.group(2);


                if (day.equals("31") &&
                    (month.equals("4") || month .equals("6") || month.equals("9") ||
                     month.equals("11") || month.equals("04") || month .equals("06") ||
                     month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
