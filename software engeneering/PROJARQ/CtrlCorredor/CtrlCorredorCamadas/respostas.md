# Parte I

## 4

O padrão de injeção de dependências foi usado no main

```java
public static void main(String[] args) {
		SpringApplication.run(CtrlCorredorV1Application.class, args);
}
```

e no construtor da classe *controller*.

```java
public CtrlCorridasControler(JdbcTemplate jdbcTemplate)
```

Sim, há o princípio da inversão de controle, pois ao usar `SpringApplication.run()`, quem passa a ter o controle é o framework.

## 6

Sim, pois os repositórios ainda dependem do `JdbcTemplate`, por mais que o `Controller` não dependa mais.

# Parte II

## 2

Foi necessário o uso do padrão DTO para fazer o retorno dos dados exigidos pelo request usando spring boot.