# Impacto das modificações

# 1

Não há impactos. Basta simplesmente criar uma nova classe de carro.

```java
Carro esportivo = new Carro("Esportivo", TipoCombustivel.GASOLINA, 6, 45);
```

# 2

Aqui é apresentado um novo tipo de combustível.

Alterações a serem feitas seriam as seguintes:

* Adicionar mais um enumerador
* Adicionar mais uma validação no método `abastece` da classe `TanqueCombustivel.java`

Adicionar mais um tipo de enumerador é algo simples.

```java
// Adiciona mais um tipo de combustível
public enum TipoCombustivel {
    GASOLINA, ALCOOL, FLEX, DIESEL;
}
```

A complexidade aumenta na função de abastecer. Nos requesitos não diz se **diesel** também pode ser considerado **Flex**. Portanto, tem que adicionar mais uma condição. 

```java
public boolean abastece(TipoCombustivel tipoCombustivel, int quantidade) {
    if (tipoCombustivel != this.tipoCombustivel) {
        if (this.tipoCombustivel == TipoCombustivel.FLEX)  {
            if (!(tipoCombustivel == TipoCombustivel.GASOLINA || tipoCombustivel == TipoCombustivel.ALCOOL)) {
                return false;
            }
        // Adiciona mais uma condicional
        else if (this.tipoCombustivel != TipoCombustivel.DIESEL)
            return false;
        } else {
            return false;
        }
    }

    if (getCombustivelDisponivel() + quantidade > getCapacidade())
        return false;
    
    combustivelDisponivel += quantidade;
    return true;
}
```

Agora conseguimos criar o novo tipo de carro:

```java
Carro utilitario = new Carro("Utilitario", TipoCombustivel.DIESEL, 5, 70);
```

# 3

As complicações vindas deste requisito é que no futuro o carro vira flex, então as alterações a serem feitas são:

* Alterar novamente as condicionais da função `abastece` da classe `TanqueCombustivel.java`

```java
public boolean abastece(TipoCombustivel tipoCombustivel, int quantidade) {
    if (this.tipoCombustivel == TipoCombustivel.FLEX
    && !(tipoCombustivel == TipoCombustivel.ALCOOL 
    ||   tipoCombustivel == TipoCombustivel.GASOLINA)
    )
        return false;

    else if(this.tipoCombustivel != TipoCombustivel.DIESEL)
        return false;
    
    if (getCombustivelDisponivel() + quantidade > getCapacidade())
        return false;

    combustivelDisponivel += quantidade;
    return true;
}
```

* Adicionar um novo construtor a classe `Carro.java` que abstraia a criação do motor e do tanque.

```java
public Carro(String modelo, Motor motor, TanqueCombustivel tanque) {
    this.motor = motor;
    this.modelo = modelo;
    this.tanque = tanque;
}
```

Agora já é possível criar o SUV.

```java
var motor = new Motor(TipoCombustivel.FLEX, 8);
var tanque = new TanqueCombustivel(TipoCombustivel.FLEX, 55);
Carro suv = new Carro("SUV", motor, tanque);
```

# 4

Agora acontece o caso onde, dependendo do tipo de combustivel afeta o consumo do motor.

Então criamos uma subclasse de carro, chamada `SUVFlex`. Assim declaramos um construtor padrão na classe `Carro.java` e alteramos suas variáveis para `protected`, para poder usar na subclasse.

```java
protected Motor motor;
protected String modelo;
protected TanqueCombustivel tanque;

public Carro() {}
```

Depois na subclasse, criamos um novo construtor e sobrescrevemos a função de abastecer para mudar o consumo no último tipo de combustível abastecido.

```java
public class SUVFlex extends Carro {

    public SUVFlex(String modelo, int capacidadeTanque) {
        super();
        this.modelo = modelo;
        this.motor = new Motor(TipoCombustivel.FLEX, 8);
        this.tanque = new TanqueCombustivel(TipoCombustivel.FLEX, capacidadeTanque);
    }

    @Override
    public int abastece(TipoCombustivel tipoCombustivel, int quantidade) {
        var consumoMotor = tipoCombustivel == TipoCombustivel.GASOLINA ? 8 : 6;
        this.motor.setConsumo(consumoMotor);
        return super.abastece(tipoCombustivel, quantidade);
    }
}
```
E agora já é possível criar um SUVFlex.

```java
Carro suvFlex = new Carro("SUVFlex", 55);
```

# 5

Aqui o interessante é criar uma subclasse para o motor econômico, onde dependendo da quilometragem o consumo é ajustado.

```java
public class MotorEco extends Motor {
    
    public MotorEco(TipoCombustivel tipoMotor, int consumo) {
        super(tipoMotor, consumo);
    }

    private void ajustaConsumo() {
        var consumoAtual = this.getConsumo();
        var quilometragemAtual = this.getQuilometragem();
        
        if (quilometragemAtual >= 5000 && consumoAtual > 10) {
            var novoConsumo = consumoAtual - quilometragemAtual / 5000;
            this.consumo = novoConsumo;
        }
    }

    @Override
    public void percorre(int distancia) {
        this.ajustaConsumo();
        super.percorre(distancia);
    }
}
```

Criando o carro econômico.

```java
var motor = new MotorEco(TipoCombustivel.GASOLINA, 20);
var tanque = new TanqueCombustivel(TipoCombustivel.FLEX, 55);
Carro econo = new Carro("Econo", motor, tanque);
```
