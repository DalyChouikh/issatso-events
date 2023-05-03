package dev.daly.issatsoevents.repository;

import dev.daly.issatsoevents.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

    //find event by different criteria
    Event findByName(String name);
    Event findByLocation(String location);
    Event findByDate(String date);
    Event findByTime(String time);
    Event findByOrganizer(String organizer);
    Event findByDescription(String description);



}
