package com.example.application.persistencia.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.application.entity.Veiculo;
import com.example.application.enums.EnumStatus;
import com.example.application.enums.EnumTipo;
import com.example.application.persistencia.VeiculoDAO;

@Repository
public class VeiculoDAOImpl extends BaseDAO implements VeiculoDAO {

    @Override
    public List<Veiculo> listar() {
     
         String sql = "SELECT * FROM veiculo";
        List<Veiculo> retorno = new ArrayList<>();
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getLong("id"));
                    veiculo.setCombustivel(rs.getString("combustivel"));
                    veiculo.setKm(rs.getLong("km"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setRenavan(rs.getLong("renavan"));
                    veiculo.setStatus(EnumStatus.getEnumStatusByValor(rs.getLong("status")));
                    veiculo.setTipoVeiculo(EnumTipo.getEnumTipoByValor(rs.getLong("tipoVeiculo")));
                    retorno.add(veiculo);
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao consultar Veiculo ");
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public Optional<Veiculo> obter(Long id) {
        String sql = "SELECT * FROM veiculo WHERE id =?";
        Veiculo retorno = null;
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setLong(1, id);
                ResultSet rs = p.executeQuery();

                if (rs.next()) {
                    retorno = new Veiculo();
                    retorno.setId(rs.getLong("id"));
                    retorno.setCombustivel(rs.getString("combustivel"));
                    retorno.setKm(rs.getLong("km"));
                    retorno.setMarca(rs.getString("marca"));
                    retorno.setModelo(rs.getString("modelo"));
                    retorno.setPlaca(rs.getString("placa"));
                    retorno.setRenavan(rs.getLong("renavan"));
                    retorno.setStatus(EnumStatus.getEnumStatusByValor(rs.getLong("status")));
                    retorno.setTipoVeiculo(EnumTipo.getEnumTipoByValor(rs.getLong("tipoVeiculo")));

                }

            } catch (SQLException ex) {
                System.out.println("Erro ao consultar Veiculo com id " + id);
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
        }
        return Optional.of(retorno);
    }

    @Override
    public boolean inserir(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (tipoVeiculo, marca, combustivel, km, status, modelo, renavan,placa) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setLong(1, veiculo.getTipoVeiculo().getValor());
                p.setString(2, veiculo.getMarca());
                p.setString(3, veiculo.getCombustivel());
                p.setLong(4, veiculo.getKm());
                p.setLong(5, veiculo.getStatus().getValor());
                p.setString(6, veiculo.getModelo());
                p.setLong(7, veiculo.getRenavan());
                p.setString(8, veiculo.getPlaca());

                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao inserir Veiculo. ");
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean alterar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET tipoVeiculo=?, marca=?, combustivel=?, km=?, status=?, modelo=?, renavan=?, placa=? WHERE id=?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setLong(1, veiculo.getTipoVeiculo().getValor());
                p.setString(2, veiculo.getMarca());
                p.setString(3, veiculo.getCombustivel());
                p.setLong(4, veiculo.getKm());
                p.setLong(5, veiculo.getStatus().getValor());
                p.setString(6, veiculo.getModelo());
                p.setLong(7, veiculo.getRenavan());
                p.setString(8, veiculo.getPlaca());
                p.setLong(9, veiculo.getId());
                p.executeUpdate();
                c.commit();
                return true;

            } catch (SQLException e) {
                System.out.println("Erro ao atualizar Veiculo. ");
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remover(Veiculo veiculo) {
        String sql = "DELETE from veiculo where id = ?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setLong(1, veiculo.getId());

                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao deletar Veiculo.");
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }

}
