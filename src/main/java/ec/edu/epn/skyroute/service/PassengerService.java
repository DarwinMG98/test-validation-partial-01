package ec.edu.epn.skyroute.service;

/**
 * Servicio externo que verifica el estatus del pasajero en el sistema.
 * <p>
 * Esta interfaz representa una dependencia externa que debe ser
 * simulada (mock) durante las pruebas unitarias.
 */
public interface PassengerService {

    /**
     * 
     * Determina si un pasajero tiene estatus VIP activo.
     *
     * @param passengerId identificador único del pasajero
     * @return true si el pasajero es VIP, false en caso contrario
     */


    boolean isVip(Long passengerId);

}



