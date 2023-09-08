package com.example.employees.service;

import com.example.employees.dto.ProgrammerDTO;
import com.example.employees.entity.Programmer;
import com.example.employees.handler.ApiRequestException;
import com.example.employees.repository.ProgrammerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgrammerService {

    private final ProgrammerRepository programmerRepository;

    private ProgrammerDTO convertEntityToDto(Programmer programmer) {
        ProgrammerDTO programmerDto = new ProgrammerDTO();
        programmerDto.setId(programmer.getId());
        programmerDto.setName(programmer.getName());
        programmerDto.setAddress(programmer.getAddress());
        programmerDto.setBirth(programmer.getBirth());
        programmerDto.setPhone(programmer.getPhone());
        programmerDto.setEmail(programmer.getEmail());
        programmerDto.setRole(programmer.getRole());
        programmerDto.setIntern(programmer.isIntern());
        programmerDto.setDeleted(programmer.isDeleted());
        return programmerDto;
    }

    public List<ProgrammerDTO> getAllProgrammers() {
        return programmerRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public ProgrammerDTO getProgrammerById(Long id) {
        try{
            Programmer programmer = programmerRepository.findById(id).get();
            ProgrammerDTO programmerDTO = new ProgrammerDTO();
            BeanUtils.copyProperties(programmer, programmerDTO);
            return programmerDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("Programmer doesn't exist with this id");
        }
    }

    public ProgrammerDTO saveProgrammers(ProgrammerDTO programmerDTO) {
        try {
            Programmer newProgrammer = new Programmer();
            newProgrammer.setId(programmerDTO.getId());
            newProgrammer.setName(programmerDTO.getName());
            newProgrammer.setAddress(programmerDTO.getAddress());
            newProgrammer.setBirth(programmerDTO.getBirth());
            newProgrammer.setPhone(programmerDTO.getPhone());
            newProgrammer.setEmail(programmerDTO.getEmail());
            newProgrammer.setRole(programmerDTO.getRole());
            newProgrammer.setIntern(programmerDTO.isIntern());
            programmerRepository.save(newProgrammer);
            return programmerDTO;
        } catch (Exception exception) {
            throw new ApiRequestException("There is a problem. Please check the filled forms are valid!");
        }
    }

    public ProgrammerDTO updateProgrammer(Long id, ProgrammerDTO programmerDTO) {
        try {
            Programmer updatedProgrammer = new Programmer();
            updatedProgrammer.setId(programmerDTO.getId());
            updatedProgrammer.setName(programmerDTO.getName());
            updatedProgrammer.setAddress(programmerDTO.getAddress());
            updatedProgrammer.setBirth(programmerDTO.getBirth());
            updatedProgrammer.setPhone(programmerDTO.getPhone());
            updatedProgrammer.setEmail(programmerDTO.getEmail());
            updatedProgrammer.setRole(programmerDTO.getRole());
            updatedProgrammer.setIntern(programmerDTO.isIntern());
            programmerRepository.save(updatedProgrammer.toBuilder().id(id).build());
            return programmerDTO;
        } catch(Exception exception) {
            throw new ApiRequestException("There is a problem with edit function. Please check the filled forms are valid!");
        }
    }

    public void deleteProgrammer(Long id) {
        try {
            Programmer programmer = programmerRepository.findById(id).get();
            programmerRepository.delete(programmer);
        } catch (Exception exception) {
            throw new ApiRequestException("Programmer doesn't exist with this id");
        }
    }

    public List<Programmer> findProgrammersByDeleted(boolean deleted) {
        return programmerRepository.findAllByDeleted(deleted);
    }
}
