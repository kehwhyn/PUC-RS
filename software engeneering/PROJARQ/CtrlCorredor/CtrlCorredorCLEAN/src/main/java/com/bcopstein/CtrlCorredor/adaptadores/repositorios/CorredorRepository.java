package com.bcopstein.CtrlCorredor.adaptadores.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcopstein.CtrlCorredor.negocio.entidades.Corredor;

@Repository
public class CorredorRepository {

        private JdbcTemplate jdbcTemplate;

        @Autowired
        public CorredorRepository(JdbcTemplate jdbcTemplate) {
                this.jdbcTemplate = jdbcTemplate;

                this.jdbcTemplate.execute("DROP TABLE corredores IF EXISTS");

                this.jdbcTemplate.execute("CREATE TABLE corredores("
                                + "cpf VARCHAR(255), nome VARCHAR(255), genero VARCHAR(255), "
                                + "diaDn int, mesDn int, anoDn int, PRIMARY KEY(cpf))");

                this.jdbcTemplate.batchUpdate("INSERT INTO corredores(cpf, nome, genero, diaDn, mesDn, anoDn) "
                                + "VALUES ('10001287', 'Luiz', 'masculino', 22, 5, 1987)");

                // Parte I 3
                this.jdbcTemplate.batchUpdate("INSERT INTO corredores(cpf, nome, genero, diaDn, mesDn, anoDn) "
                                + "VALUES ('10001288', 'Tao Ren', 'masculino', 21, 7, 1961)");

                this.jdbcTemplate.batchUpdate("INSERT INTO corredores(cpf, nome, genero, diaDn, mesDn, anoDn) "
                                + "VALUES ('10001289', 'Horo Horo', 'masculino', 15, 4, 1982)");

                this.jdbcTemplate.batchUpdate("INSERT INTO corredores(cpf, nome, genero, diaDn, mesDn, anoDn) "
                                + "VALUES ('10001290', 'Kororo', 'feminino', 30, 9, 1981)");

                this.jdbcTemplate.batchUpdate("INSERT INTO corredores(cpf, nome, genero, diaDn, mesDn, anoDn) "
                                + "VALUES ('10001291', 'Anna', 'feminino', 9, 12, 2000)");
        }

        public List<Corredor> listaCorredores() {
                List<Corredor> resp = this.jdbcTemplate.query("SELECT * from corredores",
                                (rs, rowNum) -> new Corredor(rs.getString("cpf"), rs.getString("nome"),
                                                rs.getInt("diaDn"), rs.getInt("mesDn"), rs.getInt("anoDn"),
                                                rs.getString("genero")));
                return resp;
        }

        public void deletaTodos() {
                // Limpa a base de dados
                this.jdbcTemplate.batchUpdate("DELETE from Corredores");
        }

        public boolean cadastraCorredor(Corredor corredor) {
                // Então cadastra o novo "corredor único"
                this.jdbcTemplate.update(
                                "INSERT INTO corredores(cpf, nome, diaDn, mesDn, anoDn, genero) VALUES (?, ?, ?, ?, ?, ?)",
                                corredor.getCpf(), corredor.getNome(), corredor.getDiaDn(), corredor.getMesDn(),
                                corredor.getAnoDn(), corredor.getGenero());
                return true;
        }
}
