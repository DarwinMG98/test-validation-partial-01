package ec.edu.epn.skyroute.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BaggageFeeCalculatorTest {

    @Mock
    private PassengerService passengerService;

    @InjectMocks
    private BaggageFeeCalculator baggageFeeCalculator;

    @Test
    @DisplayName("Caso 1: Debería cobrar tarifa base cuando el equipaje es estándar y el pasajero es regular")
    public void shouldChargeBaseFeeWhenBagIsStandardAndPassengerIsRegular() {
        Mockito.when(passengerService.isPassengerVip(1L)).thenReturn(false);
        double fee = baggageFeeCalculator.calculateFee(20.0, 1, 1L);
        Assertions.assertEquals(30.0, fee);
    }

    @Test
    @DisplayName("Caso 2: Debería cobrar recargo por sobrepeso cuando la maleta supera los 23 kg")
    public void shouldChargeOverweightFeeWhenBagExceeds23Kg() {
        Mockito.when(passengerService.isPassengerVip(1L)).thenReturn(false);
        double fee = baggageFeeCalculator.calculateFee(25.0, 1, 1L);
        Assertions.assertEquals(80.0, fee);
    }

    @Test
    @DisplayName("Caso 3: Debería condonar la tarifa de la primera maleta cuando el pasajero es VIP")
    public void shouldNotChargeBaseFeeWhenPassengerIsVipAndBagIsStandard() {
        Mockito.when(passengerService.isPassengerVip(2L)).thenReturn(true);
        double fee = baggageFeeCalculator.calculateFee(15.0, 1, 2L);
        Assertions.assertEquals(0.0, fee);
    }

    @Test
    @DisplayName("Caso 4: Debería cobrar tarifa normal a partir de la segunda maleta para pasajeros VIP")
    public void shouldChargeNormalFeeForAdditionalBagsWhenPassengerIsVip() {
        Mockito.when(passengerService.isPassengerVip(2L)).thenReturn(true);
        double fee = baggageFeeCalculator.calculateFee(15.0, 2, 2L);
        Assertions.assertEquals(30.0, fee);
    }

    @test
    @DisplayName("Caso 5: Debería lanzar IllegalArgumentException cuando los parámetros de entrada son inválidos")
    public void shouldThrowExceptionWhenParametersAreInvalid() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            baggageFeeCalculator.calculateFee(-5.0, 1, 1L);
        });
        Assertions.assertEquals("Parámetros de equipaje invalidos", exception.getMessage());
    }
}