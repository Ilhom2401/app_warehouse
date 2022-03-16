package uz.pdp.app_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.CategoryDto;
import uz.pdp.app_warehouse.entity.Category;
import uz.pdp.app_warehouse.entity.Measurement;
import uz.pdp.app_warehouse.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody CategoryDto categoryDto){
        return categoryService.add(categoryDto);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return categoryService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return categoryService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return categoryService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
        return categoryService.edit(id, categoryDto);
    }

}
