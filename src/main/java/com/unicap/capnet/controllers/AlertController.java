package com.unicap.capnet.controllers;

import com.unicap.capnet.domain.alert.AlertDTO;
import com.unicap.capnet.domain.alert.ListAlertDTO;
import com.unicap.capnet.domain.alert.Alert;
import com.unicap.capnet.domain.alert.UpdateAlertDTO;
import com.unicap.capnet.services.alert.AlertService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("alerts")
public class AlertController {
    @Autowired
    private AlertService alertService;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid AlertDTO data) {
        alertService.saveAlert(data);
    }

    @GetMapping("/{alertId}")
    public ResponseEntity<ListAlertDTO> getAlertById(@PathVariable long alertId){
        Alert alert = alertService.findById(alertId);

        if (alert != null) {
            ListAlertDTO alertDTO = new ListAlertDTO(alert);

            return ResponseEntity.ok(alertDTO);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ListAlertDTO>> getAllAlerts(Pageable pagination) {
        Page<ListAlertDTO> alertPage = alertService.findAllAlerts(pagination);

        return ResponseEntity.ok(alertPage);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateAlertDTO data) {
        alertService.updateAlert(data);
    }

    @DeleteMapping("/{alertId}")
    @Transactional
    public ResponseEntity<String> delete(@PathVariable long alertId) {
        Alert alert = alertService.findById(alertId);

        if (alert != null) {
            alertService.deleteAlert(alertId);
            return ResponseEntity.ok("Alerta excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alerta não encontrado.");
        }
    }
}
