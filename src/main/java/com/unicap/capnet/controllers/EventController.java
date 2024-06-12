package com.unicap.capnet.controllers;

import com.unicap.capnet.domain.event.EventDTO;
import com.unicap.capnet.domain.event.ListEventDTO;
import com.unicap.capnet.domain.event.Event;
import com.unicap.capnet.domain.event.UpdateEventDTO;
import com.unicap.capnet.services.event.EventService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid EventDTO data) {
        eventService.saveEvent(data);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ListEventDTO> getEventById(@PathVariable long eventId){
        Event event = eventService.findById(eventId);

        if (event != null) {
            ListEventDTO eventDTO = new ListEventDTO(event);

            return ResponseEntity.ok(eventDTO);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ListEventDTO>> getAllEvents(Pageable pagination) {
        Page<ListEventDTO> eventPage = eventService.findAllEvents(pagination);

        return ResponseEntity.ok(eventPage);
    }

    @PutMapping("/{eventId}")
    @Transactional
    public void update(@RequestBody @Valid UpdateEventDTO data, @PathVariable Long eventId) {
        eventService.updateEvent(data, eventId);
    }

    @DeleteMapping("/{eventId}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable long eventId) {
        Event event = eventService.findById(eventId);

        if (event != null) {
            eventService.deleteEvent(eventId);
            return ResponseEntity.ok("Evento excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evento não encontrado.");
        }
    }
}
