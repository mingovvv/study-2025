package backend.backendweb.week_03.yh.jung.mapper;

import backend.backendweb.week_03._problem.enums.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public interface CommonMapper {

    @Named("cmm_dateTimeToDateTimeString")
    static String dateTimeToDateTimeString(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Named("cmm_stringToDateTime")
    static LocalDateTime stringToDateTime(String dateTime) {
        if (dateTime == null) return null;
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Named("cmm_dateTimeToDateString")
    static String dateTimeToDateString(LocalDateTime date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Named("cmm_stringToDate")
    static LocalDate stringToDate(String date) {
        if (date == null) return null;
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Named("cmm_dateToDateString")
    static String dateToDateString(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    @Named("cmm_userRoleToEnum")
    static UserRole userRoleToEnum(String role) {
        switch (role.toUpperCase()) {
            case "ROLE_USER":
                return UserRole.ROLE_USER;
            case "ROLE_ADMIN":
                return UserRole.ROLE_ADMIN;
            case "ROLE_GUEST":
                return UserRole.ROLE_GUEST;
            default:
                return UserRole.ROLE_GUEST;
        }
    }
}
