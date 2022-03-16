package uz.pdp.app_warehouse.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.dto.UserDto;
import uz.pdp.app_warehouse.entity.User;
import uz.pdp.app_warehouse.entity.Warehouse;
import uz.pdp.app_warehouse.repository.UserRepository;
import uz.pdp.app_warehouse.repository.WarehouseRepository;

import java.util.*;

import static uz.pdp.app_warehouse.constants.ResponseConstants.NOT_FOUND;
import static uz.pdp.app_warehouse.constants.ResponseConstants.SUCCESS;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;
    private final ModelMapper modelMapper;

    public ApiResponse add(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (optionalUser.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        Set<Warehouse> warehouses = new HashSet<>();
        for (Integer id : userDto.getWarehousesId()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
            optionalWarehouse.ifPresent(warehouses::add);
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setCode(UUID.randomUUID().toString());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(warehouses);
        userRepository.save(user);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<User> userList = userRepository.findAll();
        return new ApiResponse(SUCCESS, true, userList);
    }

    public ApiResponse get(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(NOT_FOUND, false);
        return new ApiResponse(SUCCESS, true, optionalUser.get());
    }

    public ApiResponse delete(Integer id) {
        userRepository.deleteById(id);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse edit(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse(NOT_FOUND, false);

        User user = modelMapper.map(userDto, User.class);
        user.setId(optionalUser.get().getId());
        user.setCode(UUID.randomUUID().toString());
        userRepository.save(user);
        return new ApiResponse(SUCCESS, true);
    }

}
