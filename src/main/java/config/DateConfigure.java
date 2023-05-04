package config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConfigure {
    private   static DateConfigure dateConfigure =new DateConfigure();

    public static DateConfigure getDateConfigure(){
        return  dateConfigure;
    }
    public String nowDate() {
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String dat = simpl.format(date);
        return dat;
    }
}
