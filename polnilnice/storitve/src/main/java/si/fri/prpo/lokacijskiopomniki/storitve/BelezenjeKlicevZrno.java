package si.fri.prpo.lokacijskiopomniki.storitve;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BelezenjeKlicevZrno {

    private int counter=0;

    private Logger log = Logger.getLogger(BelezenjeKlicevZrno.class.getName());

    public void povecajSteviloKlicev() {
        counter++;
        log.info("Belezenje klicev : "+counter);

    }
}