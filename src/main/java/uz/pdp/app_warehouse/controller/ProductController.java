package uz.pdp.app_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.ProductDto;
import uz.pdp.app_warehouse.entity.Product;
import uz.pdp.app_warehouse.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody ProductDto productDto){
        return productService.add(productDto);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return productService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return productService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return productService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.edit(id, productDto);
    }

}
