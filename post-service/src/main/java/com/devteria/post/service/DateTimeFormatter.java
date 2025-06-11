package com.devteria.post.service;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class DateTimeFormatter {
    Map<Long, Function<Instant, String>> strateryMap = new LinkedHashMap<>();

    public DateTimeFormatter(Map<Long, Function<Instant, String>> strateryMap) {
        strateryMap.put(60L, this::formatInSeonds);
        strateryMap.put(3600L, this::formatInMinutes);
        strateryMap.put(8640L, this::formatInHours);
        strateryMap.put(Long.MAX_VALUE, this::formatInDate);

    }

    private String formatInSeonds(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());
        return elapseSeconds + " seconds";
    }
    private String formatInMinutes(Instant instant) {
        long elapseMinutes = ChronoUnit.MINUTES.between(instant, Instant.now());
        return elapseMinutes + " minutes";
    }
    private String formatInHours(Instant instant) {
        long elapseHours = ChronoUnit.HOURS.between(instant, Instant.now());
        return elapseHours + " hours";
    }
    private String formatInDate(Instant instant) {
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ISO_DATE;
        return localDateTime.format(dateTimeFormatter);
    }

    public String format(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

         var stratery = strateryMap.entrySet()
                 .stream()
                 .filter(longFunctionEntry -> elapseSeconds < longFunctionEntry.getKey())
                 .findFirst().get();

         return  stratery.getValue().apply(instant);
    }
}
