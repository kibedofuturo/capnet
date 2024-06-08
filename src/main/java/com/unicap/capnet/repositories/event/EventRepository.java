package com.unicap.capnet.repositories.event;

import com.unicap.capnet.domain.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findAllByActiveTrue(Pageable pagination);
}
