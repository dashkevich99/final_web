package by.gsu.epamlab.enums;

import by.gsu.epamlab.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum ChoiceTask {
    TODAY {
        @Override
        public String getQuery(User user) {
            System.out.println(user.getUserId());
            return "SELECT * FROM jee.tasks WHERE idUser = '" + user.getUserId() +
                    "' AND CreateDate<= '" +
                    LocalDate.now() + "' AND RecycleBin = 0";
        }
    },
    TOMORROW {
        @Override
        public String getQuery(User user) {
            return "SELECT * FROM jee.tasks WHERE idUser = '" + user.getUserId() +
                    "' AND CreateDate= '" + getDatePlusOneDay() + "' AND RecycleBin = 0";
        }
    },
    SOMEDAY {
        @Override
        public String getQuery(User user) {
            return "SELECT * FROM jee.tasks WHERE idUser = '" + user.getUserId() +
                    "' AND CreateDate > '" + getDatePlusOneDay() + "' AND RecycleBin = 0";
        }
    },
    FIXED {
        @Override
        public String getQuery(User user) {
            return "SELECT * FROM jee.tasks WHERE idUser = '" + user.getUserId() + "' AND Fixed = 1";
        }
    },
    RECYCLE_BIN {
        @Override
        public String getQuery(User user) {
            return "SELECT * FROM jee.tasks WHERE idUser = '" + user.getUserId() + "' AND RecycleBin = 1";
        }
    };
    private static final String SECTION = "section";

    public abstract String getQuery(User user);

    public static ChoiceTask getValueByParam(HttpServletRequest request) {
        return ChoiceTask.valueOf(request.getParameter(SECTION).toUpperCase());
    }

    public static LocalDate getDatePlusOneDay() {
        return LocalDate.now().plus(1, ChronoUnit.DAYS);
    }
}