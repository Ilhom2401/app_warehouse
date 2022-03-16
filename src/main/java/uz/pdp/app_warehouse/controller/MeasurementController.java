package uz.pdp.app_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.entity.Measurement;
import uz.pdp.app_warehouse.service.MeasurementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Measurement measurement){
        return measurementService.add(measurement);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return measurementService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return measurementService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return measurementService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.edit(id, measurement);
    }
}
