package com.unicap.capnet.services.publication;

import com.unicap.capnet.domain.publication.ListPublicationDTO;
import com.unicap.capnet.domain.publication.Publication;
import com.unicap.capnet.domain.publication.PublicationDTO;
import com.unicap.capnet.domain.publication.UpdatePublicationDTO;
import com.unicap.capnet.domain.user.User;
import com.unicap.capnet.repositories.publication.PublicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublicationService implements IPublicationService {

    private final PublicationRepository publicationRepository;

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Publication findById(long publicationId) {
        return publicationRepository.findById(publicationId).orElse(null);
    }

    @Override
    public Page<ListPublicationDTO> findAllPublications(Pageable pagination) {
        return publicationRepository.findAllByActiveTrue(pagination).map(ListPublicationDTO::new);
    }

    @Override
    public void savePublication(PublicationDTO data, User user) {
        publicationRepository.save(new Publication(data, user));
    }

    @Override
    public void updatePublication(UpdatePublicationDTO data) {
        var publication = publicationRepository.getReferenceById(data.id());
        publication.updateInfo(data);
    }

    @Override
    public void deletePublication(long publicationId) {
        var publication = publicationRepository.getReferenceById(publicationId);
        publication.delete();
    }
}
