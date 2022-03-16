package uz.pdp.app_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.entity.Warehouse;
import uz.pdp.app_warehouse.service.WarehouseService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Warehouse warehouse){
        return warehouseService.add(warehouse);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return warehouseService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return warehouseService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return warehouseService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.edit(id, warehouse);
    }

}
