package microservicios.movimientos; 

import com.intuit.karate.junit5.Karate;

class MovimientosRunner {
    
    @Karate.Test
    Karate testMovimientos() {
        return Karate.run("movimientos").relativeTo(getClass());
    }    
}
