package com.example.employees.controller;

import com.example.employees.dto.ProgrammerDTO;
import com.example.employees.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProgrammerController {

    @Autowired
    ProgrammerService programmerService;

    @GetMapping("/programmers")
    public List<ProgrammerDTO> getProgrammers() {
        return programmerService.getAllProgrammers();
    }

    @GetMapping("/programmers/{id}")
    public ProgrammerDTO getProgrammerById(@PathVariable Long id) {
        return programmerService.getProgrammerById(id);
    }

    @PostMapping("/programmers")
    public ProgrammerDTO addProgrammer(@RequestBody ProgrammerDTO programmer) {
        return programmerService.saveProgrammers(programmer);
    }

    @PutMapping("/programmers/{id}")
    public ProgrammerDTO editProgrammer(@PathVariable Long id, @RequestBody ProgrammerDTO programmer) {
        return programmerService.updateProgrammer(id, programmer);
    }

    @DeleteMapping("/programmers/{id}")
    public void deleteProgrammer(@PathVariable Long id) {
            programmerService.deleteProgrammer(id);
    }
}
