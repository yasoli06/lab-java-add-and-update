package com.springBoot.service;

import com.springBoot.DTO.PatienteUpdateDTO;
import com.springBoot.model.Patient;
import com.springBoot.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() { return patientRepository.findAll();}

    public Optional<Patient> getPatientById(Long id) { return patientRepository.findById(id); }

    public List<Patient> getPatientsByDateOfBirth(LocalDate start, LocalDate end){
        return patientRepository.findByDateOfBirthBetween(start, end);
    }

    public List<Patient> getPatientsAdmittedBy(Integer employeeId, String department){
        return patientRepository.admittedBy(employeeId, department);
    }

    public List<Patient> getPatientsWithOffDoctor(){
        return patientRepository.withOffDoctor();
    }

    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Optional<Patient> updatePatientPartially(Long id, PatienteUpdateDTO patientUpdateDTO){

        Optional<Patient> patientOptional = patientRepository.findById(id);

        if(patientOptional.isPresent() && patientUpdateDTO.getName() != null && patientUpdateDTO.getDateOfBirth() != null && patientUpdateDTO.getEmployeeId() != null){
            Patient patient = patientOptional.get();
            patient.setName(patientUpdateDTO.getName());
            patient.setDateOfBirth(patientUpdateDTO.getDateOfBirth());
            patient.setEmployeeId(patientUpdateDTO.getEmployeeId());
            return Optional.of(patientRepository.save(patient));
        }
        else if(patientOptional.isPresent() && patientUpdateDTO.getName() != null){
            Patient patient = patientOptional.get();
            patient.setName(patientUpdateDTO.getName());
            return Optional.of(patientRepository.save(patient));
        }
        else if(patientOptional.isPresent() && patientUpdateDTO.getDateOfBirth() != null){
            Patient patient = patientOptional.get();
            patient.setDateOfBirth(patientUpdateDTO.getDateOfBirth());
            return Optional.of(patientRepository.save(patient));
        }
        else if(patientOptional.isPresent() && patientUpdateDTO.getEmployeeId() != null){
            Patient patient = patientOptional.get();
            patient.setEmployeeId(patientUpdateDTO.getEmployeeId());
            return Optional.of(patientRepository.save(patient));
        }

        return Optional.empty();
    }
}
