public class Main {

    public static void main(String[] args) {

        TV tv = new TV(5, 10);
        Historico historico = new Historico();

        System.out.println("Estado inicial:");
        System.out.println(tv);

        historico.adicionarMemento(tv.salvar());

        tv.setCanal(12);
        tv.setVolume(20);

        System.out.println("\nApós alteração:");
        System.out.println(tv);

        historico.adicionarMemento(tv.salvar());

        tv.setCanal(30);
        tv.setVolume(5);

        System.out.println("\nNova alteração:");
        System.out.println(tv);

        tv.restaurar(historico.getMemento(1));

        System.out.println("\nEstado restaurado:");
        System.out.println(tv);
    }
}