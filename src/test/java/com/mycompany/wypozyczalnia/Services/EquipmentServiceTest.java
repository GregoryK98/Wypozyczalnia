package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.IProvider.IEquipmentProvider;
import com.mycompany.wypozyczalnia.Models.Equipment;
import com.mycompany.wypozyczalnia.Repositories.EquipmentRepository;
import com.mycompany.wypozyczalnia.Validators.EquipmentValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {
    @Mock
    private IEquipmentProvider equipmentProviderMock;
    @Mock
    private EquipmentRepository equipmentRepositoryMock;

    @Test
    void add_return_equipment_added_if_everything_ok() throws SQLException {
        Equipment equipment = new Equipment();
        equipment.Nazwa = "versja";
        equipment.Typ = "Narty";
        equipment.Dostepnosc = true;

        assertNotNull(equipmentRepositoryMock);
        assertNotNull(equipmentProviderMock);
        when(equipmentProviderMock.readEquipment(false)).thenReturn(equipment);

        EquipmentService equipmentService = new EquipmentService(equipmentProviderMock,equipmentRepositoryMock);
        assertTrue(equipmentService.addEquipment().equals("Equipment added.\n"));
    }

    @Test
    void add_return_equipment_invalid_if_invalid_equipment() throws SQLException {
        Equipment equipment = new Equipment();
        equipment.Nazwa = "Narty versja 2000";
        equipment.Typ = "";
        equipment.Dostepnosc = true;

        assertNotNull(equipmentRepositoryMock);
        assertNotNull(equipmentProviderMock);
        when(equipmentProviderMock.readEquipment(false)).thenReturn(equipment);

        EquipmentService equipmentService = new EquipmentService(equipmentProviderMock,equipmentRepositoryMock);
        assertFalse(equipmentService.addEquipment().equals("Equipment cannot be updated because new equipmnets data are not not valid.\n"));
    }
}
