package dev.daly.issatsoevents.controller;

import dev.daly.issatsoevents.entity.Event;
import dev.daly.issatsoevents.entity.ImageData;
import dev.daly.issatsoevents.service.EventService;
import dev.daly.issatsoevents.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("location") String location,
                                             @RequestParam("date") LocalDate date,
                                             @RequestParam("time") LocalTime time,
                                             @RequestParam("organizer") String organizer,
                                             @RequestParam("image") MultipartFile image) throws IOException {
        ImageData imageData = imageService.uploadImage(image);
        return ResponseEntity.ok(eventService.createEvent(name, description, location, date, time, organizer, imageData));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }


}
