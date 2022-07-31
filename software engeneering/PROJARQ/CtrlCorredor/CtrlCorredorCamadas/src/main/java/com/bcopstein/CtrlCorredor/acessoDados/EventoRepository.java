package com.bcopstein.CtrlCorredor.acessoDados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        this.jdbcTemplate.execute("DROP TABLE eventos IF EXISTS");
        this.jdbcTemplate.execute("CREATE TABLE eventos("
                + "id int, nome VARCHAR(255), dia int, mes int, ano int, distancia int, horas int, minutos int, segundos int,PRIMARY KEY(id))");
        
        this.jdbcTemplate.batchUpdate(
            "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
            " ('4','Run Day Run', 13, 7, 2019, 5, 0, 32, 40)");
        
        this.jdbcTemplate.batchUpdate(
            "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
            " ('0', 'PUCRS Day Run', 31, 3, 2019, 5, 0, 40, 31)");
        
        this.jdbcTemplate.batchUpdate(
            "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
            " ('1', 'Poa Day Run', 22, 5, 2019, 5, 0, 35, 32)");

        this.jdbcTemplate.batchUpdate(
            "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
            " ('2', 'Poa Day Run 2', 22, 5, 2020, 5, 0, 40, 23)");

        this.jdbcTemplate.batchUpdate(
            "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES" +
            " ('3', 'Poa Day Run 3', 22, 5, 2021, 5, 0, 47, 59)");
    }

    public List<Evento> listaEventos() {
        List<Evento> resp = this.jdbcTemplate.query("SELECT * from eventos",
                (rs, rowNum) -> new Evento(rs.getInt("id"), rs.getString("nome"), rs.getInt("dia"), rs.getInt("mes"),
                        rs.getInt("ano"), rs.getInt("distancia"), rs.getInt("horas"), rs.getInt("minutos"),
                        rs.getInt("segundos")));
        return resp;
    }
    
    public void deletaEventos() {
        this.jdbcTemplate.batchUpdate("DELETE * FROM eventos");
    }
    
    public boolean cadastraEvento(Evento evento) {
        this.jdbcTemplate.update(
                "INSERT INTO eventos(id,nome,dia,mes,ano,distancia,horas,minutos,segundos) VALUES (?,?,?,?,?,?,?,?,?)",
                evento.getId(), evento.getNome(), evento.getDia(), evento.getMes(), evento.getAno(),
                evento.getDistancia(), evento.getHoras(), evento.getMinutos(), evento.getSegundos());
        return true;
    }
}
