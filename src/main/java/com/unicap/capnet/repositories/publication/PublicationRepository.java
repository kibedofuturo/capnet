package com.unicap.capnet.repositories.publication;

import com.unicap.capnet.domain.publication.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    Page<Publication> findAllByActiveTrue(Pageable pagination);
}
