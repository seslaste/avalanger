package ch.zhaw.avalanger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.avalanger.model.Avalange;
import ch.zhaw.avalanger.model.AvalangeCreateDTO;
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
                                  @RequestParam(required = false) String state) {
        if (country == null || country.isEmpty()) {
            if (state == null || state.isEmpty()) {
                return ResponseEntity.ok(avalangeRepository.findAll());
            } else {
                return ResponseEntity.ok(avalangeRepository.findByState(ch.zhaw.avalanger.model.AvalangeState.valueOf(state)));
            }
            return "No avalanges found";
        }
        return "No avalanges found for country: " + country + ", state: " + state;
    }

    @PostMapping("")
    public ResponseEntity<Avalange>postMethodName(@RequestBody AvalangeCreateDTO avalangeDto) {
        Avalange avalange = new Avalange(avalangeDto.getCountry(), avalangeDto.getDescription());
        Avalange savedAvalange = avalangeRepository.save(avalange);
        
        return ResponseEntity.status(201).body(savedAvalange);
    }
}
