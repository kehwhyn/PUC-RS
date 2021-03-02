package pucrs.myflight.modelo;

import pucrs.myflight.consoleApp.Imprimivel;

public class Aeronave implements Imprimivel, Contavel, Comparable<Aeronave> {
	private static int totalAeronaves = 0;
	private String codigo;
	private String descricao;
	
	public Aeronave(String codigo, String descricao) {
		totalAeronaves++;
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void imprimir() {
		System.out.println(codigo + " - " + descricao);
	}

	public int objetosJaCriados() {
		return totalAeronaves;
	}

	public int compareTo(Aeronave outra) { return descricao.compareTo(outra.descricao); }
}
