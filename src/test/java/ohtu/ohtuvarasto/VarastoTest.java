package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriToimiiKelvollisillaSyotteilla() {
        int alkusaldo = 2;
        int tilavuus = 10;
        varasto2 = new Varasto(tilavuus, alkusaldo);
        assertEquals(alkusaldo, (long) varasto2.getSaldo());
        assertEquals(tilavuus, (long) varasto2.getTilavuus());
    }

    @Test
    public void konstruktoriNollataanEpakelvollisellaAlkusaldolla() {
        int alkusaldo = -2;
        int tilavuus = 10;
        varasto2 = new Varasto(tilavuus, alkusaldo);
        assertEquals(0, (long) varasto2.getSaldo());
        assertEquals(tilavuus, (long) varasto2.getTilavuus());
    }

    @Test
    public void konstruktoriNollataanEpakelvollisellaTilavuudella() {
        int alkusaldo = 2;
        int tilavuus = -10;
        varasto2 = new Varasto(tilavuus, alkusaldo);
        assertEquals(tilavuus, (long) varasto2.getSaldo());
        assertEquals(0, (long) varasto2.getTilavuus());
    }

    @Test
    public void toinenKonstruktoriNollataanEpakelvollisellaTilavuudella2() {
        int tilavuus = -10;
        varasto = new Varasto(tilavuus);
        assertEquals(0, (long) varasto.getTilavuus());
    }

    @Test
    public void lisaaVarastoonMetodiEiLisaaNegatiivisillaSyotteilla() {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, (long) varasto.getSaldo());
    }

    @Test
    public void lisaaVarastoonMetodiEiLisaaTilavuuttaEnempaa() {
        varasto.lisaaVarastoon(200);
        assertEquals((long) varasto.getTilavuus(), (long) varasto.getSaldo());
    }

    @Test
    public void otaVarastostaEiToimiNegatiivisillaSyotteilla() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, (long) varasto.getSaldo());
        varasto.otaVarastosta(-20);
        assertEquals(5, (long) varasto.getSaldo());
    }

    @Test
    public void otaVarastostaEiOtaSaldoaEnempaa() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, (long) varasto.getSaldo());
        varasto.otaVarastosta(20);
        assertEquals(0, (long) varasto.getSaldo());
    }

    @Test
    public void varastoToStringToimiiOikein() {
        int saldo = 5;
        int tilavuus = 10;
        varasto2 = new Varasto(tilavuus, saldo);
        assertEquals("saldo = " + varasto2.getSaldo() + ", vielä tilaa " + varasto2.paljonkoMahtuu(), varasto2.toString());
    }
}