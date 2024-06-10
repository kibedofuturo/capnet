package com.unicap.capnet.services.alert;

import com.unicap.capnet.domain.alert.Alert;
import com.unicap.capnet.domain.alert.AlertDTO;
import com.unicap.capnet.domain.alert.ListAlertDTO;
import com.unicap.capnet.domain.alert.UpdateAlertDTO;
import com.unicap.capnet.repositories.alert.AlertRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertService implements IAlertService {
    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public Alert findById(long alertId) {
        return alertRepository.findByIdAndActiveTrue(alertId);
    }

    @Override
    public Page<ListAlertDTO> findAllAlerts(Pageable pagination) {
        return alertRepository.findAllByActiveTrue(pagination).map(ListAlertDTO::new);
    }

    @Override
    public void saveAlert(AlertDTO data) {
        alertRepository.save(new Alert(data));
    }

    @Override
    public void updateAlert(UpdateAlertDTO data) {
       var alert = alertRepository.getReferenceById(data.id());

       alert.updateInfo(data);
    }

    @Override
    public void deleteAlert(long alertId) {
        var alert = alertRepository.getReferenceById(alertId);

        alert.delete();
    }
}
