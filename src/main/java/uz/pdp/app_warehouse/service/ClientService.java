package uz.pdp.app_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.entity.Client;
import uz.pdp.app_warehouse.entity.Currency;
import uz.pdp.app_warehouse.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import static uz.pdp.app_warehouse.constants.ResponseConstants.NOT_FOUND;
import static uz.pdp.app_warehouse.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ApiResponse add(Client client) {
        Optional<Client> optionalClient = clientRepository.findByName(client.getName());
        if (optionalClient.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        clientRepository.save(client);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<Client> all = clientRepository.findAll();
        return new ApiResponse(SUCCESS, true, all);
    }

    public ApiResponse get(Integer id){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        return new ApiResponse(SUCCESS,true,optionalClient.get());
    }

    public ApiResponse delete(Integer id){
        clientRepository.deleteById(id);
        return new ApiResponse(SUCCESS,true);
    }

    public ApiResponse edit(Integer id, Client client){
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        client.setId(optionalClient.get().getId());
        clientRepository.save(client);
        return new ApiResponse(SUCCESS,true);
    }

}
