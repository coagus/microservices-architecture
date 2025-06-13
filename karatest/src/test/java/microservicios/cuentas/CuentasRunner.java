package microservicios.cuentas; 

import com.intuit.karate.junit5.Karate;

class CuentasRunner {
    
    @Karate.Test
    Karate testCuentas() {
        return Karate.run("cuentas").relativeTo(getClass());
    }    
}
