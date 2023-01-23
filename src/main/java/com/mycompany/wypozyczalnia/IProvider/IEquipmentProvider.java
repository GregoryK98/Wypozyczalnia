package com.mycompany.wypozyczalnia.IProvider;

import com.mycompany.wypozyczalnia.Models.Equipment;

import java.util.List;

public interface IEquipmentProvider {
    public Equipment readEquipment(boolean readID);
    public void writeEquipment(List<Equipment> equipments);
}
