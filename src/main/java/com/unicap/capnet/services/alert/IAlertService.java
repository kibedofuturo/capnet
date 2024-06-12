package com.unicap.capnet.services.alert;

import com.unicap.capnet.domain.alert.Alert;
import com.unicap.capnet.domain.alert.AlertDTO;
import com.unicap.capnet.domain.alert.ListAlertDTO;
import com.unicap.capnet.domain.alert.UpdateAlertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAlertService {
    Alert findById(long alertId);

    Page<ListAlertDTO> findAllAlerts(Pageable pagination);

    void saveAlert(AlertDTO data);

    Optional<Alert> updateAlert(UpdateAlertDTO data, Long alertId);

    void deleteAlert(long alertId);
}
