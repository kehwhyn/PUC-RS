import java.util.Comparator;

public class Consultas {
    ICarregadorDeTemperatura carregadorTemp;

    public Consultas(){
        carregadorTemp = new Temperaturas();
    }

    public String diaQueMaisChoveuNoAno(int ano){
        RegistroDoTempo registro = carregadorTemp
        .getDados()
        .stream()
        .filter(reg->reg.getAno() == ano)
        .max(Comparator.comparing(RegistroDoTempo::getPrecipitacaoMaxima))
        .orElseThrow(IllegalArgumentException::new);
        String resp = registro.getDia()+"/"+registro.getMes()+"/"+registro.getAno()+", "+registro.getPrecipitacaoMaxima();
        return resp;
    }
}
