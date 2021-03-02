package pucrs.myflight.consoleApp;

import pucrs.myflight.modelo.*;

import java.time.LocalDateTime;
import java.time.Duration;

public class App {

	public static void main(String[] args) {

		System.out.println("MyFlight project...");
		LocalDateTime datahora = LocalDateTime.of(2020, 9, 21, 14, 40);
		Duration duracao = Duration.ofMinutes(120); // 2 horas

		CiaAerea ciaAerea = new CiaAerea("001", "TAM");
		Aeroporto origem = new Aeroporto("100", "Miojo", new Geo(2.5, 2.5));
		Aeroporto destino = new Aeroporto("101", "Camarao", new Geo(5, 5));
		Aeronave aeronave = new Aeronave("200", "Alguma");
		Rota rota = new Rota(ciaAerea, origem, destino, aeronave);
		Voo voo = new Voo(rota, datahora, duracao);

		GerenciadorAeronaves gerenciadorAeronaves = new GerenciadorAeronaves();
		GerenciadorAeroportos gerenciadorAeroportos = new GerenciadorAeroportos();
		GerenciadorCias gerenciadorCias = new GerenciadorCias();
		GerenciadorRotas gerenciadorRotas = new GerenciadorRotas();
		GerenciadorVoos gerenciadorVoos = new GerenciadorVoos();

		gerenciadorCias.adicionar(ciaAerea);
		gerenciadorAeroportos.adicionar(origem);
		gerenciadorAeroportos.adicionar(destino);
		gerenciadorAeronaves.adicionar(aeronave);
		gerenciadorRotas.adicionar(rota);
		gerenciadorVoos.adicionar(voo);

		Voo voo1 = new Voo(rota, duracao);

		Geo portoAlegre = new Geo(-29.9942,-51.1714);
		Geo saoPaulo = new Geo(-23.4322,-46.4692);

		System.out.println("teste medodo estatico: " + Geo.distancia(portoAlegre, saoPaulo));
		System.out.println("teste medodo da classe: " + portoAlegre.distancia(saoPaulo));

		System.out.println("Total de empresas: "+ CiaAerea.getTotalCias());

	}
}
