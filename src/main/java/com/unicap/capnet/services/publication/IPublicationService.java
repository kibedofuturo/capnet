package com.unicap.capnet.services.publication;

import com.unicap.capnet.domain.publication.ListPublicationDTO;
import com.unicap.capnet.domain.publication.Publication;
import com.unicap.capnet.domain.publication.PublicationDTO;
import com.unicap.capnet.domain.publication.UpdatePublicationDTO;
import com.unicap.capnet.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPublicationService {
    Publication findById(long publicationId);

    Page<ListPublicationDTO> findAllPublications(Pageable pagination);

    void savePublication(PublicationDTO data, User user);

    Optional<Publication> updatePublication(UpdatePublicationDTO data, Long userId);

    void deletePublication(long publicationId);
}
