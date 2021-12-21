package formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Component
public class TimeFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        LocalDate localDate1 = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return localDate1;
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return null;
    }
}
