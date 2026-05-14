import java.util.ArrayList;
import java.util.List;

public class Historico {

    private final List<TVMemento> historico = new ArrayList<>();

    public void adicionarMemento(TVMemento memento) {
        historico.add(memento);
    }

    public TVMemento getMemento(int index) {
        return historico.get(index);
    }
}