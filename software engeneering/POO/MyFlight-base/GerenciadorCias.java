package pucrs.myflight.modelo;

import java.util.ArrayList;

public class GerenciadorCias {
	private ArrayList<CiaAerea> ciaAereas;
	
	public GerenciadorCias() {
		ciaAereas = new ArrayList<>();
	}

	public void adicionar(CiaAerea aeronave) {
		ciaAereas.add(aeronave);
	}

	public ArrayList<CiaAerea> listarTodas() {
		return (ArrayList<CiaAerea>) ciaAereas.clone();
	}

	public CiaAerea buscarPorCodigo(String codigo) {
		for (var ciaAerea : ciaAereas)
			if (ciaAerea.getCodigo().equals(codigo))
				return ciaAerea;
		return null;
	}

	public CiaAerea buscarPorNome(String nome) {
		for (var ciaAerea : ciaAereas)
			if (ciaAerea.getNome().equals(nome))
				return ciaAerea;
		return null;
	}
}
