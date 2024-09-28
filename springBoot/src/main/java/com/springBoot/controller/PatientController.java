package com.springBoot.controller;

import com.springBoot.DTO.PatienteUpdateDTO;
import com.springBoot.model.Patient;
import com.springBoot.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() { return patientService.getAllPatients(); }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/birthdate")
    public List<Patient> getPatientsByDateOfBirth
            (@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){

        return patientService.getPatientsByDateOfBirth(startDate, endDate);
    }

    @GetMapping("/admittedin")
    public List<Patient> getPatientsByTheirDepartmentsDoctor
            (@RequestParam Integer employeeId,
             @RequestParam String department){

        return patientService.getPatientsAdmittedBy(employeeId, department);
    }

    @GetMapping("/offdoctor")
    public List<Patient> getPatientsWithOffDoctor(){return patientService.getPatientsWithOffDoctor();}

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> updatePatientStatus(@PathVariable Long id, @RequestBody PatienteUpdateDTO patientUpdateDTO){
        Optional<Patient> updatePatient = patientService.updatePatientPartially(id, patientUpdateDTO);
        return updatePatient.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

}
