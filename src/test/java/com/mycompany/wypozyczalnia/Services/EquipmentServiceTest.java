package com.mycompany.wypozyczalnia.Services;

import com.mycompany.wypozyczalnia.IProvider.IEquipmentProvider;
import com.mycompany.wypozyczalnia.IRepositories.IEquipmentRepository;
import com.mycompany.wypozyczalnia.Models.Equipment;
import com.mycompany.wypozyczalnia.Repositories.EquipmentRepository;
import com.mycompany.wypozyczalnia.Validators.EquipmentValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        equipment.Nazwa = "Narty versja 2000";
        equipment.Typ = "Narty";
        equipment.Dostepnosc = true;

        assertNotNull(equipmentRepositoryMock);
        assertNotNull(equipmentProviderMock);
        when(equipmentProviderMock.readEquipment(false)).thenReturn(equipment);

        EquipmentService equipmentService = new EquipmentService(equipmentProviderMock, (IEquipmentRepository) equipmentRepositoryMock);
        assertTrue(equipmentService.addEquipment().equals("Equipmnet added.\n"));
    }

    @Test
    void add_return_equipment_invalid_if_invalid_equipment_ok() throws SQLException {
        Equipment equipment = new Equipment();
        equipment.Nazwa = "Narty versja 2000";
        equipment.Typ = "";
        equipment.Dostepnosc = true;

        assertNotNull(equipmentRepositoryMock);
        assertNotNull(equipmentProviderMock);
        when(equipmentProviderMock.readEquipment(false)).thenReturn(equipment);

        EquipmentService equipmentService = new EquipmentService(equipmentProviderMock, (IEquipmentRepository) equipmentRepositoryMock);
        assertTrue(equipmentService.addEquipment().equals("Equipmnet  cannot be added because is not valid.\n"));
    }
}
