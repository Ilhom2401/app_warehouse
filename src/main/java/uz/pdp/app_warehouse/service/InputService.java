package uz.pdp.app_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.InputDto;
import uz.pdp.app_warehouse.entity.Currency;
import uz.pdp.app_warehouse.entity.Input;
import uz.pdp.app_warehouse.entity.Supplier;
import uz.pdp.app_warehouse.entity.Warehouse;
import uz.pdp.app_warehouse.repository.CurrencyRepository;
import uz.pdp.app_warehouse.repository.InputRepository;
import uz.pdp.app_warehouse.repository.SupplierRepository;
import uz.pdp.app_warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.pdp.app_warehouse.constants.ResponseConstants.NOT_FOUND;
import static uz.pdp.app_warehouse.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class InputService {

    private final InputRepository inputRepository;
    private final WarehouseRepository warehouseRepository;
    private final SupplierRepository supplierRepository;
    private final CurrencyRepository currencyRepository;
    private final ModelMapper modelMapper;

    public ApiResponse add(InputDto inputDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Input input = new Input();
        input.setWarehouse(optionalWarehouse.get());
        input.setSupplier(optionalSupplier.get());
        input.setCurrency(optionalCurrency.get());
        input.setCode(UUID.randomUUID().toString());
        input.setFactureNumber(UUID.randomUUID().toString());
        inputRepository.save(input);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList(){
        List<Input> inputList = inputRepository.findAll();
        return new ApiResponse(SUCCESS, true, inputList);
    }

    public ApiResponse get(Integer id){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalInput.get());
    }

    public ApiResponse delete(Integer id){
        inputRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, InputDto inputDto){
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new ApiResponse(NOT_FOUND, false);

        Input input = modelMapper.map(inputDto, Input.class);
        input.setId(optionalInput.get().getId());
        input.setCode(UUID.randomUUID().toString());
        input.setFactureNumber(UUID.randomUUID().toString());
        inputRepository.save(input);
        return new ApiResponse(SUCCESS, true);
    }

}
