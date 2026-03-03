package ch.zhaw.avalanger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.avalanger.model.Avalange;
import ch.zhaw.avalanger.model.AvalangeCreateDTO;
import ch.zhaw.avalanger.model.AvalangeState;
import ch.zhaw.avalanger.repository.AvalangeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/avalange")
public class AvalangeController {

    @Autowired
    private AvalangeRepository avalangeRepository;

    @GetMapping(value = {"", "/{country}"})
    public ResponseEntity<List<Avalange>> getAllAvalanges(@PathVariable(required = false) String country, 
                                  @RequestParam(required = false) AvalangeState state) {
      // Handle all combinations of country and state filters
        if (country == null || country.isEmpty()) {
            if (state == null) {
                List<Avalange> avalanges = avalangeRepository.findAll();
                return ResponseEntity.ok(avalanges);
            } else {
                List<Avalange> avalanges = avalangeRepository.findByState(state);
                return ResponseEntity.ok(avalanges);
            }
        } else {
            if (state == null) {
                List<Avalange> avalanges = avalangeRepository.findByCountry(country);
                return ResponseEntity.ok(avalanges);
            } else {
                List<Avalange> avalanges = avalangeRepository.findByCountryAndState(country, state);
                return ResponseEntity.ok(avalanges);
            }
        }
    }

    @PostMapping("")
    public ResponseEntity<Avalange> postMethodName(@RequestBody AvalangeCreateDTO avalangeDTO) {
        Avalange avalange = new Avalange(avalangeDTO.getCountry(), avalangeDTO.getDescription());
        Avalange savedAvalange = avalangeRepository.save(avalange);
        
        return ResponseEntity.status(201).body(savedAvalange);
    }
}
