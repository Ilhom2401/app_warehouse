package uz.pdp.app_warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.app_warehouse.dto.ApiResponse;
import uz.pdp.app_warehouse.entity.Measurement;
import uz.pdp.app_warehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

import static uz.pdp.app_warehouse.constants.ResponseConstants.NOT_FOUND;
import static uz.pdp.app_warehouse.constants.ResponseConstants.SUCCESS;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public ApiResponse add(Measurement measurement) {
        measurementRepository.save(measurement);
        return new ApiResponse(SUCCESS, true);
    }

    public ApiResponse getList() {
        List<Measurement> all = measurementRepository.findAll();
        return new ApiResponse(SUCCESS, true, all);
    }

    public ApiResponse get(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        return new ApiResponse(SUCCESS,true,optionalMeasurement.get());
    }

    public ApiResponse delete(Integer id){
        measurementRepository.deleteById(id);
        return new ApiResponse(SUCCESS,true);
    }

    public ApiResponse edit(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty())
            return new ApiResponse(NOT_FOUND,false);
        measurement.setId(optionalMeasurement.get().getId());
        measurementRepository.save(measurement);
        return new ApiResponse(SUCCESS,true);
    }

}
