public class App {
    public static void main(String[] args) throws Exception {

        // Criação do modelo básico com a classe Carro usando injeção de dependência
        MotorBasico motorBasico = new MotorBasico(TipoCombustivel.GASOLINA, 10);
        TanqueCombustivel tanqueCombustivel = new TanqueCombustivel(TipoCombustivel.GASOLINA, 55);
        Carro basico = new Carro("Basico", motorBasico, tanqueCombustivel);

        basico.abastece(TipoCombustivel.GASOLINA, 55);
        basico.viaja(200);
        basico.viaja(100);
        System.out.println(basico+"\n");

        // Solução da questão 1
        MotorBasico motorEsportivo = new MotorBasico(TipoCombustivel.GASOLINA, 6);
        TanqueCombustivel tanqueEsportivo = new TanqueCombustivel(TipoCombustivel.GASOLINA, 45);
        Carro esportivo = new Carro("Esportivo", motorEsportivo, tanqueEsportivo);
        esportivo.abastece(TipoCombustivel.GASOLINA, 45);
        esportivo.viaja(200);
        //esportivo.viaja(100);
        System.out.println(esportivo+"\n");


        // Solução da questão 2
        MotorBasico motorUtilitario = new MotorBasico(TipoCombustivel.DIESEL, 5);
        TanqueCombustivel tanqueUtilitario = new TanqueCombustivel(TipoCombustivel.DIESEL, 70);
        Carro utilitario = new Carro("Utilitario", motorUtilitario, tanqueUtilitario);
        utilitario.abastece(TipoCombustivel.DIESEL, 70);
        utilitario.viaja(200);
        utilitario.viaja(100);
        System.out.println(utilitario+"\n");
        
        // Solução da questão 3
        MotorBasico motorSUV = new MotorBasico(TipoCombustivel.GASOLINA, 8);
        TanqueCombustivel tanqueSUV = new TanqueCombustivel(TipoCombustivel.FLEX, 55);
        Carro suv = new Carro("SUV", motorSUV, tanqueSUV);
        suv.abastece(TipoCombustivel.GASOLINA, 55);
        suv.viaja(200);
        suv.viaja(100);
        System.out.println(suv+"\n");

        // Solução da questão 4
        TanqueCombustivelFlex tanqueFlex = new TanqueCombustivelFlex(65);
        MotorBasico motorSUVFlex = new MotorFlex(8,6,tanqueFlex);
        Carro suvFlex = new Carro("SUVFlex", motorSUVFlex, tanqueFlex);
        suvFlex.abastece(TipoCombustivel.GASOLINA, 55);
        suvFlex.viaja(200);
        System.out.println(suvFlex+"\n");
        suvFlex.abastece(TipoCombustivel.GASOLINA,10);
        suvFlex.viaja(100);
        System.out.println(suvFlex+"\n");

        // Solução da questão 5
        MotorBasico motorEcono = new MotorEcono();
        TanqueCombustivel tanqueEcono = new TanqueCombustivel(TipoCombustivel.GASOLINA, 55);
        Carro econo = new Carro("Econo", motorEcono, tanqueEcono);
        econo.abastece(TipoCombustivel.GASOLINA, 55);
        econo.viaja(550);
        System.out.println(econo+"\n");
        econo.viaja(100);
        System.out.println(econo+"\n");
    }
}
