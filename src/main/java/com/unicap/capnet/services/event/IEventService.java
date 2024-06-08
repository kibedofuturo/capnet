package com.unicap.capnet.services.event;

import com.unicap.capnet.domain.event.Event;
import com.unicap.capnet.domain.event.EventDTO;
import com.unicap.capnet.domain.event.ListEventDTO;
import com.unicap.capnet.domain.event.UpdateEventDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventService {
    Event findById(long eventId);

    Page<ListEventDTO> findAllEvents(Pageable pagination);

    void saveEvent(EventDTO data);

    void updateEvent(UpdateEventDTO data);

    void deleteEvent(long eventId);
}
