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

import java.util.Optional;

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
    public Optional<Publication> updatePublication(UpdatePublicationDTO data, Long userId) {

        return publicationRepository.findById(userId)
                .map(publication -> {
                    publication.setText((data.text() == null) ? publication.getText() : data.text());
                    return publicationRepository.save(publication);
                });
    }

    @Override
    public void deletePublication(long publicationId) {
        var publication = publicationRepository.getReferenceById(publicationId);
        publication.delete();
    }
}
