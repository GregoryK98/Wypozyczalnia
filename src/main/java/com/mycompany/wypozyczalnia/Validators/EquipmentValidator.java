package com.mycompany.wypozyczalnia.Validators;

import com.mycompany.wypozyczalnia.Models.Equipment;
import org.apache.commons.lang3.ObjectUtils;

public class EquipmentValidator {

    public boolean validateEq(Equipment equipment) {
        if (equipment.Nazwa.isEmpty()
                || equipment.Typ.isEmpty()
                || !equipment.Dostepnosc)
            return false;
        return true;
    }
}

