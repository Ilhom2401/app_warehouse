package uz.pdp.app_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.app_warehouse.constants.ResponseConstants;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.SupplierDto;
import uz.pdp.app_warehouse.entity.Supplier;
import uz.pdp.app_warehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

import static uz.pdp.app_warehouse.constants.ResponseConstants.*;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public ApiResponse add(SupplierDto supplierDto){
        Optional<Supplier> optionalSupplier = supplierRepository.findByPhoneNumber(supplierDto.getPhoneNumber());
        if (optionalSupplier.isPresent())
            return new ApiResponse(ALREADY_EXIST, false);

        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return new ApiResponse(SUCCESS, true, supplierList);
    }

    public ApiResponse get(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalSupplier.get());
    }

    public ApiResponse delete(Integer id){
        supplierRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, SupplierDto supplierDto){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Supplier supplier = optionalSupplier.get();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);
        return new ApiResponse(SUCCESS,true);
    }

}
