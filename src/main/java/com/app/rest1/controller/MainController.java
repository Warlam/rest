package com.app.rest1.controller;

import com.app.rest1.entity.Cat;
import com.app.rest1.repository.CatRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {


    private final CatRepo catRepo;

    private final ObjectMapper objectMapper;

    @PostMapping("/api/add")
    public void addCat(@RequestBody Cat cat){
        log.info("New row"+catRepo.save(cat).toString());
    }
    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll(){
        return catRepo.findAll();
    }

    @GetMapping("/api/cat")
    public Cat getCat(@RequestParam int id){
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id){
        catRepo.deleteById(id);
    }

    @PutMapping("/api")
    public String updateCat(@RequestBody Cat cat){
        if(!catRepo.existsById(cat.getId())){
            return "no such row";
        }
        return catRepo.save(cat).toString();
    }
}
