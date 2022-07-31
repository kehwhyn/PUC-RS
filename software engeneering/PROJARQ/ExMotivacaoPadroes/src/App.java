public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("TIPOS:");

        Carro basico = new Carro("Basico", TipoCombustivel.GASOLINA, 10, 55);
        System.out.println(basico);

        Carro esportivo = new Carro("Esportivo", TipoCombustivel.GASOLINA, 6, 45);
        System.out.println(esportivo);

        Carro utilitario = new Carro("Utilitario", TipoCombustivel.DIESEL, 5, 70);
        System.out.println(utilitario);

        var motor = new Motor(TipoCombustivel.FLEX, 8);
        var tanque = new TanqueCombustivel(TipoCombustivel.FLEX, 55);
        Carro suv = new Carro("SUV", motor, tanque);
        System.out.println(suv);

        Carro suvFlex = new SUVFlex("SUVFlex", 55);
        System.out.println(suvFlex);

        var motorEco = new MotorEco(TipoCombustivel.GASOLINA, 20);
        Carro econo = new Carro("Econo", motorEco, tanque);
        System.out.println(econo);
        System.out.println("\n\n----------------");
        
        System.out.println("\nABASTECENDO:");
        
        basico.abastece(TipoCombustivel.GASOLINA, 55);
        System.out.println(basico);

        esportivo.abastece(TipoCombustivel.GASOLINA, 45);
        System.out.println(esportivo);

        utilitario.abastece(TipoCombustivel.DIESEL, 70);
        System.out.println(utilitario);

        suv.abastece(TipoCombustivel.GASOLINA, 55);
        System.out.println(suv);

        suvFlex.abastece(TipoCombustivel.GASOLINA, 55);
        System.out.println(suvFlex);

        econo.abastece(TipoCombustivel.GASOLINA, 55);
        System.out.println(econo);
        System.out.println("\n\n----------------");

        System.out.println("\nVIAJANDO:");
        basico.viaja(250);
        basico.viaja(150);
        System.out.println(basico);

        esportivo.viaja(250);
        esportivo.viaja(150);
        System.out.println(esportivo);

        utilitario.viaja(250);
        utilitario.viaja(150);
        System.out.println(utilitario);

        suv.viaja(250);
        suv.viaja(150);
        System.out.println(suv);

        suvFlex.viaja(250);
        suvFlex.viaja(150);
        System.out.println(suvFlex);

        econo.viaja(250);
        econo.viaja(150);
        System.out.println(econo);
    }
}
