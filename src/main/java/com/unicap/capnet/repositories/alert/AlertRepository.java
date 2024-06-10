package com.unicap.capnet.repositories.alert;

import com.unicap.capnet.domain.alert.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    Page<Alert> findAllByActiveTrue(Pageable pagination);
    Alert findByIdAndActiveTrue(long alertId);
}
