package kehwhyn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** 
 * Grupo:
 * Adriana Serpa
 * Bruno Andrade
 * Jéssica Freua
 * Kevin Boucinha
*/

public class RankingTest {
    private Ranking ranking;
    private Record[] records = new Record[] { 
        new Record("Kevin", 99), 
        new Record("Ana", 89),
        new Record("Patrícia", 59), 
        new Record("Breno", 55), 
        new Record("Paulo", 47), 
        new Record("Arthur", 17),
        new Record("Ricardo", 31), 
        new Record("Gabriel", 29), 
        new Record("Maria", 27), 
        new Record("Rafaela", 28),
        new Record("Ser ou não ser", 5), 
        new Record("Eis a questão", 24),
        new Record("Enzo", 19),
        new Record("Armínia", 45),
        new Record("Klebson", 70),
        new Record("Alessandra", 35)
    };

    @BeforeEach
    void setUp() {
        ranking = new Ranking();
    }

    // 1ª partição - ranking com menos que 10 jogadores -> novo record é adicionado independente do score
    @Test
    public void testaInsercaoNoRankingComMenosQue10Jogadores() {
        for (int i = 0; i < 7; i++)
            ranking.add(records[i]);

        Assertions.assertEquals(7, ranking.numRecords());
    }

    // 2ª partição - ranking com mais que 10 jogadores -> novo jogador com score menor que o último -> não pode ser adicionado
    @Test
    public void testaScoreMenorQueOMenorAtualNaoEntraNoRanking() {
        for (int i = 0; i < 11; i++)
            ranking.add(records[i]);

        Record record = ranking.worstScore();

        Assertions.assertEquals(17, record.getScore());
    }

    // 3ª partição - ranking com mais que 10 jogadores -> novo jogador com score maior que o último -> é adicionado
    @Test
    public void testaScoreMaiorQueOMenorAtualEntraNoRanking() {
        for (int i = 0; i < 10; i++)
            ranking.add(records[i]);

        // Adiciona jogador com score maior que o último
        ranking.add(records[11]);

        Record record = ranking.worstScore();

        // Verifica se retirou o último da record, e se o novo record entrou
        Assertions.assertEquals(24, record.getScore());
    }

    // 4ª partição - jogadores devem ser ordenados por score - ordem decrescente
    @Test
    public void testaOrdenacaoVerificaPrimeiroNoRanking() {
        for (int i = 0; i < 7; i++)
            ranking.add(records[i]);

        Record best = ranking.bestScore();

        Assertions.assertEquals(99, best.getScore());
    }

    @Test
    public void testaOrdenacaoVerificaUltimoNoRanking() {
        for (int i = 0; i < 7; i++)
            ranking.add(records[i]);

        Record best = ranking.worstScore();

        Assertions.assertEquals(17, best.getScore());
    }
}
