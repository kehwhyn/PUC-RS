public interface IMotor {
    int getConsumo();
    TipoCombustivel getTipoMotor();
    int getQuilometragem();
    int combustivelNecessario(int distancia);
    public void percorre(int distancia);
}
