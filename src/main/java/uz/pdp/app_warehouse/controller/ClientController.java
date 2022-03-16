package uz.pdp.app_warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.entity.Client;
import uz.pdp.app_warehouse.entity.Currency;
import uz.pdp.app_warehouse.service.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/add")
    public ApiResponse add(@RequestBody Client client){
        return clientService.add(client);
    }

    @GetMapping("/get")
    public ApiResponse get(){
        return clientService.getList();
    }

    @GetMapping("/get/{id}")
    public ApiResponse get(@PathVariable Integer id){
        return clientService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        return clientService.delete(id);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edit(@PathVariable Integer id, @RequestBody Client client){
        return clientService.edit(id, client);
    }

}
