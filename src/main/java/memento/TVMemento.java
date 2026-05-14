package memento;

public class TVMemento {

    private final int canal;
    private final int volume;

    public TVMemento(int canal, int volume) {
        this.canal = canal;
        this.volume = volume;
    }

    public int getCanal() {
        return canal;
    }

    public int getVolume() {
        return volume;
    }
}