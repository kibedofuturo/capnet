package com.unicap.capnet.controllers;

import com.unicap.capnet.domain.publication.ListPublicationDTO;
import com.unicap.capnet.domain.publication.Publication;
import com.unicap.capnet.domain.publication.PublicationDTO;
import com.unicap.capnet.domain.publication.UpdatePublicationDTO;
import com.unicap.capnet.services.publication.PublicationService;
import com.unicap.capnet.services.user.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publications")
public class PublicationController {
    @Autowired
    private PublicationService publicationService;

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}")
    @Transactional
    public void register(@RequestBody @Valid PublicationDTO data, @PathVariable long userId) {
        var user = userService.findById(userId);

        publicationService.savePublication(data, user);
    }

    @GetMapping("{publicationId}")
    public ResponseEntity<ListPublicationDTO> getPublicationById(@PathVariable long publicationId) {
        Publication publication = publicationService.findById(publicationId);

        if (publication != null) {
            ListPublicationDTO publicationDTO = new ListPublicationDTO(publication);

            return ResponseEntity.ok(publicationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ListPublicationDTO>> getAllPublications(Pageable pagination) {
        Page<ListPublicationDTO> publicationPage = publicationService.findAllPublications(pagination);

        return ResponseEntity.ok(publicationPage);
    }

    @PutMapping("{userId}")
    @Transactional
    public void update(@RequestBody @Valid UpdatePublicationDTO data, @PathVariable Long userId) {
        publicationService.updatePublication(data, userId);
    }

    @DeleteMapping("{publicationId}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable long publicationId) {
        Publication publication = publicationService.findById(publicationId);

        if (publication != null) {
            publicationService.deletePublication(publicationId);
            return ResponseEntity.ok("Publicação excluida com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicação não encontrada.");
        }
    }
}
