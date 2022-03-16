package uz.pdp.app_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.InputDto;
import uz.pdp.app_warehouse.service.InputService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/input")
public class InputController {

    private final InputService inputService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody InputDto inputDto) {
        return inputService.add(inputDto);
    }

    @GetMapping("/get")
    public ApiResponse get() {
        return inputService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id) {
        return inputService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return inputService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody InputDto inputDto) {
        return inputService.edit(id, inputDto);
    }

}
