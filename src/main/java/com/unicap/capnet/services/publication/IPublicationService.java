package com.unicap.capnet.services.publication;

import com.unicap.capnet.domain.publication.ListPublicationDTO;
import com.unicap.capnet.domain.publication.Publication;
import com.unicap.capnet.domain.publication.PublicationDTO;
import com.unicap.capnet.domain.publication.UpdatePublicationDTO;
import com.unicap.capnet.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPublicationService {
    Publication findById(long publicationId);

    Page<ListPublicationDTO> findAllPublications(Pageable pagination);

    void savePublication(PublicationDTO data, User user);

    void updatePublication(UpdatePublicationDTO data);

    void deletePublication(long publicationId);
}
