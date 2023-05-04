package dev.daly.issatsoevents.controller;

import dev.daly.issatsoevents.entity.Event;
import dev.daly.issatsoevents.entity.ImageData;
import dev.daly.issatsoevents.service.EventService;
import dev.daly.issatsoevents.service.ImageService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @GetMapping("{id}/image")
    public ResponseEntity<?> getEventImage(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(eventService.getEventImage(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id){
        if (eventService.getEvent(id) == null)
            return ResponseEntity.badRequest().body("Event with id " + id + " not found");
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event with id " + id + " deleted successfully");
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllEvent(){
        eventService.deleteAllEvent();
        return ResponseEntity.ok("Events deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id,
                                             @RequestParam("name") String name,
                                             @RequestParam("description") String description,
                                             @RequestParam("location") String location,
                                             @RequestParam("date") LocalDate date,
                                             @RequestParam("time") LocalTime time,
                                             @RequestParam("organizer") String organizer,
                                             @RequestParam("image") MultipartFile image) throws IOException {
        Event event = eventService.getEvent(id);
        ImageData imageData =  imageService.uploadImage(image);
        if (event == null)
            return ResponseEntity.badRequest().body(null);


        return ResponseEntity.ok(eventService.updateEvent(event, name, description, location, date, time, organizer, imageData));
    }

    //Search events by organizer or name or location or date or time
    @GetMapping("/search")
    public ResponseEntity<List<Event>> searchEvents(@RequestParam String name){
        return ResponseEntity.ok(eventService.searchEvents(name));
    }


}
