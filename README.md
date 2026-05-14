# Padrão Memento — Exemplo com Controle Remoto (Java)

## Objetivo

O padrão **Memento** é utilizado para salvar e restaurar estados anteriores de um objeto sem violar o encapsulamento.

Neste exemplo, o controle remoto consegue:

* salvar o estado atual da TV;
* restaurar estados anteriores;
* funcionar como um histórico de configurações.

---

# Estrutura do Projeto

```text
src/
 ├── TV.java
 ├── TVMemento.java
 ├── Historico.java
 ├── Main.java
 └── TVTeste.java
```

---

# 1. Classe TV (Originator)

```java
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

    // Salva o estado atual
    public TVMemento salvar() {
        return new TVMemento(canal, volume);
    }

    // Restaura um estado anterior
    public void restaurar(TVMemento memento) {
        this.canal = memento.getCanal();
        this.volume = memento.getVolume();
    }

    @Override
    public String toString() {
        return "TV {Canal=" + canal + ", Volume=" + volume + "}";
    }
}
```

---

# 2. Classe TVMemento (Memento)

```java
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
```

---

# 3. Classe Historico (Caretaker)

```java
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
```

---

# 4. Classe Main

```java
public class Main {

    public static void main(String[] args) {

        TV tv = new TV(5, 10);
        Historico historico = new Historico();

        System.out.println("Estado inicial:");
        System.out.println(tv);

        // Salva estado inicial
        historico.adicionarMemento(tv.salvar());

        // Alterações
        tv.setCanal(12);
        tv.setVolume(20);

        System.out.println("\nApós alteração:");
        System.out.println(tv);

        // Salva novo estado
        historico.adicionarMemento(tv.salvar());

        // Nova alteração
        tv.setCanal(30);
        tv.setVolume(5);

        System.out.println("\nNova alteração:");
        System.out.println(tv);

        // Restaurando estado anterior
        tv.restaurar(historico.getMemento(1));

        System.out.println("\nEstado restaurado:");
        System.out.println(tv);
    }
}
```

---

# 5. Caso de Teste com JUnit

## Dependência Maven

Se estiver usando Maven, adicione no `pom.xml`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
```

---

# Classe de Teste

```java
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TVTeste {

    @Test
    void deveRestaurarEstadoAnteriorDaTV() {

        TV tv = new TV(1, 10);
        Historico historico = new Historico();

        // Salva estado inicial
        historico.adicionarMemento(tv.salvar());

        // Altera estado
        tv.setCanal(20);
        tv.setVolume(50);

        // Restaura estado inicial
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

        // Restaurando segundo estado salvo
        tv.restaurar(historico.getMemento(1));

        assertEquals(8, tv.getCanal());
        assertEquals(25, tv.getVolume());
    }
}
```

---

# Explicação do Padrão

## Participantes

### Originator

A classe `TV`.
Responsável por criar e restaurar estados.

### Memento

A classe `TVMemento`.
Armazena os dados do estado da TV.

### Caretaker

A classe `Historico`.
Guarda os estados sem modificar seu conteúdo.

---

# Resultado Esperado

```text
Estado inicial:
TV {Canal=5, Volume=10}

Após alteração:
TV {Canal=12, Volume=20}

Nova alteração:
TV {Canal=30, Volume=5}

Estado restaurado:
TV {Canal=12, Volume=20}
```

---

# Vantagens do Padrão Memento

* Permite desfazer alterações;
* Mantém encapsulamento;
* Facilita histórico de estados;
* Muito usado em editores, jogos e sistemas de configuração.
