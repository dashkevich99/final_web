package by.gsu.epamlab.enums;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum ChoiceDate {
    TODAY {
        @Override
        public LocalDate getDate() {
            return LocalDate.now();
        }
    },
    TOMORROW {
        @Override
        public LocalDate getDate() {
            return LocalDate.now().plus(1, ChronoUnit.DAYS);
        }
    },
    SOMEDAY {
        @Override
        public LocalDate getDate() {
            return null;
        }
    };

    private static final String SECTION = "section";

    public abstract LocalDate getDate();

    public static ChoiceDate getValueByParam(HttpServletRequest request) {
        return ChoiceDate.valueOf(request.getParameter(SECTION));
    }
}