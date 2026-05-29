package ec.edu.epn.skyroute.service;

public class BaggageFeeCalculator {

    private final PassengerService passengerService;

    public BaggageFeeCalculator(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public double calculateFee(double weight, int bagCount, Long passengerId) {
        if (weight <= 0 || bagCount < 1 || passengerId == null) {
            throw new IllegalArgumentException("Parametros de equipaje invalidos");
        }

        double totalFee = 0.0;
        boolean isVip = passengerService.isPassengerVip(passengerId);

        for (int i = 1; i <= bagCount; i++) {
            double currentBagFee = 30.0; 

            if (weight > 23.0) {
                currentBagFee += 50.0;
            }
            if (isVip && i == 1 && weight <= 23.0) {
                currentBagFee = 0.0;
            }

            totalFee += currentBagFee;
        }

        return totalFee;
    }
}