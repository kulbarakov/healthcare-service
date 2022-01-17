import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.medical.MedicalService;
import ru.netology.patient.service.medical.MedicalServiceImpl;

import java.math.BigDecimal;

public class MedicalServiceImplTest {
    @Test
    void checkBloodPressureTest() {
        HealthInfo healthInfo = new HealthInfo(new BigDecimal("36.4"), new BloodPressure(120, 80));
        PatientInfo patientInfo = new PatientInfo(null, null, null, healthInfo);
        PatientInfoFileRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("test id")).thenReturn(patientInfo);

        SendAlertService sendAlertService = Mockito.mock(SendAlertService.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkBloodPressure("test_id", new BloodPressure(110, 70));
    }
}
