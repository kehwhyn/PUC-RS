import java.util.List;

public interface ICarregadorDeTemperatura {
    public boolean isDadosCarregados();
    void carregaDados();    
    List<RegistroDoTempo> getDados();
}
