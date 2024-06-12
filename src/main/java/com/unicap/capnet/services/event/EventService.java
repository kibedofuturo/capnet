package com.unicap.capnet.services.event;

import com.unicap.capnet.domain.event.Event;
import com.unicap.capnet.domain.event.EventDTO;
import com.unicap.capnet.domain.event.ListEventDTO;
import com.unicap.capnet.domain.event.UpdateEventDTO;
import com.unicap.capnet.repositories.event.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService implements IEventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event findById(long eventId) {
        return eventRepository.findByIdAndActiveTrue(eventId);
    }

    @Override
    public Page<ListEventDTO> findAllEvents(Pageable pagination) {
        return eventRepository.findAllByActiveTrue(pagination).map(ListEventDTO::new);
    }

    @Override
    public void saveEvent(EventDTO data) {
        eventRepository.save(new Event(data));
    }

    @Override
    public Optional<Event> updateEvent(UpdateEventDTO data, Long eventId) {
        return eventRepository.findById(eventId)
                .map(event -> {
                    event.setEventDate((data.eventDate() == null) ? event.getEventDate() : data.eventDate());
                    event.setDescription((data.description() == null) ? event.getDescription() : data.description());
                    return  eventRepository.save(event);
                });
    }

    @Override
    public void deleteEvent(long eventId) {
        var event = eventRepository.getReferenceById(eventId);
        event.delete();
    }
}