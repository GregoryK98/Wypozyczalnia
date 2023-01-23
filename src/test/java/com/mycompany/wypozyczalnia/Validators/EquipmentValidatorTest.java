package com.mycompany.wypozyczalnia.Validators;

import com.mycompany.wypozyczalnia.Models.Equipment;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EquipmentValidatorTest {

    @Test
    void equipment_valid_if_everything_is_ok() {
        Equipment equipment = new Equipment();
        equipment.Nazwa = "Narty versja 2000";
        equipment.Typ = "Narty";
        equipment.Dostepnosc = true;

        EquipmentValidator equipmentlidator = new EquipmentValidator();
        assertTrue(equipmentlidator.validateEq(equipment));
    }

    @Test
    void equipment_invalid_if_nazwa_is_empty() {
        Equipment equipment = new Equipment();
        equipment.Nazwa = "";
        equipment.Typ = "Narty";
        equipment.Dostepnosc = true;

        EquipmentValidator equipmentlidator = new EquipmentValidator();
        assertTrue(!equipmentlidator.validateEq(equipment));
    }

    @Test
    void equipment_invalid_if_typ_is_empty() {
        Equipment equipment = new Equipment();
        equipment.Nazwa = "Narty versja 2000";
        equipment.Typ = "";
        equipment.Dostepnosc = true;

        EquipmentValidator equipmentlidator = new EquipmentValidator();
        assertTrue(!equipmentlidator.validateEq(equipment));
    }

    @Test
    void equipment_invalid_if_dostepnosc_is_false() {
        Equipment equipment = new Equipment();
        equipment.Nazwa = "Narty versja 2000";
        equipment.Typ = "Narty";
        equipment.Dostepnosc = false;

        EquipmentValidator equipmentlidator = new EquipmentValidator();
        assertTrue(!equipmentlidator.validateEq(equipment));
        // Assert.assertFalse("Pole powinno być puste", equipment.Dostepnosc.getMyBooleanField());
    }
}

