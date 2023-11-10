package com.inova.project_manager_api.controllers;


import com.inova.project_manager_api.dto.request.ResponsiblePersonInovaRequestDto;
import com.inova.project_manager_api.services.ResponsiblePersonInovaService;
import com.inova.project_manager_api.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/responsible-person")
@CrossOrigin(origins = "http://localhost:3000")
public class ResponsiblePersonInovaController {
    @Autowired
    private ResponsiblePersonInovaService responsiblePersonInovaService;

    @GetMapping("/list")
    public ResponseEntity<StandardResponse> findAllResponsiblePersons() {
        return new ResponseEntity<>(
                responsiblePersonInovaService.findAllResponsiblePersons()
                , HttpStatus.OK
        );

    }

    @GetMapping(value = "/search",params = {"page", "size","searchtext"})
    public ResponseEntity<StandardResponse> searchAllPerson(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String searchtext
    ){
        return new ResponseEntity<>(
                responsiblePersonInovaService.searchAllPersons(page,size,searchtext),
                HttpStatus.OK
        );
    }

    @PostMapping("/create")
    public ResponseEntity<StandardResponse> saveResponsiblePerson(@RequestBody ResponsiblePersonInovaRequestDto requestDto) {
        return new ResponseEntity<>(
                responsiblePersonInovaService.saveResponsiblePerson(requestDto)
                , HttpStatus.OK
        );
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<StandardResponse> updatePerson(@PathVariable int id, @RequestBody ResponsiblePersonInovaRequestDto updatedPerson) {
//        try {
//
//
//        } catch (PersonNotFoundException e) {
//            return ResponseEntity.notFound().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(new ResponsiblePersonInova()); // Provide a valid default value or response
//        }
        return new ResponseEntity<>(
                responsiblePersonInovaService.updatePerson(id, updatedPerson)
                , HttpStatus.OK
        );
    }



}
