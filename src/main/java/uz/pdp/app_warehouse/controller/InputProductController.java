package uz.pdp.app_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.InputProductDto;
import uz.pdp.app_warehouse.service.InputProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/input_product")
public class InputProductController {

    private final InputProductService inputProductService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody InputProductDto inputProductDto) {
        return inputProductService.add(inputProductDto);
    }

    @GetMapping("/get")
    public ApiResponse get() {
        return inputProductService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id) {
        return inputProductService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return inputProductService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto) {
        return inputProductService.edit(id, inputProductDto);
    }

}
