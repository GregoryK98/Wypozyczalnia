package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.IProvider.IEquipmentProvider;
import com.mycompany.wypozyczalnia.IRepositories.IEquipmentRepository;
import com.mycompany.wypozyczalnia.Models.Equipment;
import com.mycompany.wypozyczalnia.Validators.EquipmentValidator;

public class EquipmentService {
    private IEquipmentProvider _equipmentProvider;
    private IEquipmentRepository _equipmentRepository;

    public EquipmentService(IEquipmentProvider equipmentProvider, IEquipmentRepository equipmentRepository) {
        _equipmentProvider = equipmentProvider;
        _equipmentRepository = equipmentRepository;
    }

    public String addEquipment() {
        Equipment equipment = _equipmentProvider.readEquipment(false);
        EquipmentValidator equipmentValidator = new EquipmentValidator();
        if (!equipmentValidator.validate(equipment))
            return "Equipment cannot be added because is not valid.\n";
        try {
            _equipmentRepository.create(equipment);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Equipment added.\n";
    }

    public String updateEquipment() {
        Equipment equipment = _equipmentProvider.readEquipment(true);
        EquipmentValidator equipmentValidator = new EquipmentValidator();
        if (!equipmentValidator.validate(equipment))
            return "Equipment cannot be updated because new equipmnets data are not not valid.\n";
        try {
            _equipmentRepository.update(equipment);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Equipment updated.\n";
    }

    public String showAllEq() {
        try {
            _EquipmentProvider.writeEquipments(_equipmentRepository.getAllEq());
            return "";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
