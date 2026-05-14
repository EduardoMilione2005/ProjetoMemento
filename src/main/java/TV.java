public class TV {

    private int canal;
    private int volume;

    public TV(int canal, int volume) {
        this.canal = canal;
        this.volume = volume;
    }

    public void setCanal(int canal) {
        this.canal = canal;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getCanal() {
        return canal;
    }

    public int getVolume() {
        return volume;
    }

    public TVMemento salvar() {
        return new TVMemento(canal, volume);
    }

    public void restaurar(TVMemento memento) {
        this.canal = memento.getCanal();
        this.volume = memento.getVolume();
    }

    @Override
    public String toString() {
        return "TV {Canal=" + canal + ", Volume=" + volume + "}";
    }
}