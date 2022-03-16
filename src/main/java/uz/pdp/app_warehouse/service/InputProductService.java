package uz.pdp.app_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.InputProductDto;
import uz.pdp.app_warehouse.entity.Input;
import uz.pdp.app_warehouse.entity.InputProduct;
import uz.pdp.app_warehouse.entity.Product;
import uz.pdp.app_warehouse.repository.InputProductRepository;
import uz.pdp.app_warehouse.repository.InputRepository;
import uz.pdp.app_warehouse.repository.ProductRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static uz.pdp.app_warehouse.constants.ResponseConstants.NOT_FOUND;
import static uz.pdp.app_warehouse.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class InputProductService {

    private final InputProductRepository inputProductRepository;
    private final ProductRepository productRepository;
    private final InputRepository inputRepository;
    private final ModelMapper modelMapper;

    public ApiResponse add(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (optionalProduct.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (optionalInput.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(Date.valueOf(formatter.format(inputProductDto.getExpireDate())));
        inputProductRepository.save(inputProduct);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<InputProduct> productList = inputProductRepository.findAll();
        return new ApiResponse(SUCCESS, true, productList);
    }

    public ApiResponse get(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalInputProduct.get());
    }

    public ApiResponse delete(Integer id){
        inputProductRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, InputProductDto inputProductDto){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (optionalInputProduct.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        InputProduct inputProduct = modelMapper.map(inputProductDto, InputProduct.class);
        inputProduct.setId(optionalInputProduct.get().getId());
        inputProductRepository.save(inputProduct);
        return new ApiResponse(SUCCESS,true);
    }
}
