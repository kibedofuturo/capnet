package com.unicap.capnet.services.alert;

import com.unicap.capnet.domain.alert.Alert;
import com.unicap.capnet.domain.alert.AlertDTO;
import com.unicap.capnet.domain.alert.ListAlertDTO;
import com.unicap.capnet.domain.alert.UpdateAlertDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAlertService {
    Alert findById(long alertId);

    Page<ListAlertDTO> findAllAlerts(Pageable pagination);

    void saveAlert(AlertDTO data);

    void updateAlert(UpdateAlertDTO data);

    void deleteAlert(long alertId);
}
