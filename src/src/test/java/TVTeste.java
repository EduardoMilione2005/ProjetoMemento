import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TVTeste {

    @Test
    void deveRestaurarEstadoAnteriorDaTV() {

        TV tv = new TV(1, 10);
        Historico historico = new Historico();

        historico.adicionarMemento(tv.salvar());

        tv.setCanal(20);
        tv.setVolume(50);

        tv.restaurar(historico.getMemento(0));

        assertEquals(1, tv.getCanal());
        assertEquals(10, tv.getVolume());
    }

    @Test
    void deveSalvarMultiplosEstados() {

        TV tv = new TV(2, 15);
        Historico historico = new Historico();

        historico.adicionarMemento(tv.salvar());

        tv.setCanal(8);
        tv.setVolume(25);

        historico.adicionarMemento(tv.salvar());

        tv.setCanal(100);
        tv.setVolume(60);

        tv.restaurar(historico.getMemento(1));

        assertEquals(8, tv.getCanal());
        assertEquals(25, tv.getVolume());
    }
}