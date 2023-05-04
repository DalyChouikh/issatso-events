package dev.daly.issatsoevents.repository;

import dev.daly.issatsoevents.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(String name);
    List<Event> findByLocation(String location);
    List<Event> findByDate(LocalDate date);
    List<Event> findByTime(LocalTime time);
    List<Event> findByOrganizer(String organizer);


    List<Event> findEventsByNameContainsIgnoreCase(String name);
}
