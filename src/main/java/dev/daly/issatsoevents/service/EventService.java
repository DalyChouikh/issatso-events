package dev.daly.issatsoevents.service;

import dev.daly.issatsoevents.entity.Event;
import dev.daly.issatsoevents.entity.ImageData;
import dev.daly.issatsoevents.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final ImageService imageService;
    private final EventRepository eventRepository;


    public Event createEvent(String name,
                            String description,
                            String location,
                            LocalDate date,
                            LocalTime time,
                            String organizer,
                            ImageData imageData) throws IOException {
        Event event = Event.builder()
                .name(name)
                .description(description)
                .location(location)
                .date(date)
                .time(time)
                .organizer(organizer)
                .imageData(imageData)
                .build();
        eventRepository.save(event);
        return event;
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).get();
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}