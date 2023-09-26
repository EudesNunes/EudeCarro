package com.example.application.persistencia.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.application.entity.Cliente;
import com.example.application.entity.Locacao;
import com.example.application.entity.Veiculo;
import com.example.application.persistencia.ClienteDAO;
import com.example.application.persistencia.LocacaoDAO;
import com.example.application.persistencia.VeiculoDAO;

@Repository
public class LocacaoDAOImpl extends BaseDAO implements LocacaoDAO {

    @Autowired
    private VeiculoDAO veiculoDAO;

     @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public List<Locacao> listar() {
        String sql = "SELECT * FROM locacao";
        List<Locacao> retorno = new ArrayList<>();
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    Locacao locacao = new Locacao();
                    locacao.setId(rs.getLong("id"));
                    locacao.setValor(rs.getLong("valor"));
                    locacao.setDataDev(rs.getDate("dataDev"));
                    locacao.setDataSaida(rs.getDate("dataSaida"));
                    locacao.setDataPrevDev(rs.getDate("dataPrevDev"));

                    Optional<Veiculo> veiculoOptional = veiculoDAO.obter(rs.getLong("veiculo"));
                    Veiculo veiculo = null;
                    if (veiculoOptional.isPresent()) {
                        veiculo = veiculoOptional.get();
                    }
                    locacao.setVeiculoAlocado(veiculo);

                    
                    Optional<Cliente> clienteOptional = clienteDAO.obter(rs.getLong("cliente"));
                    Cliente cliente = null;
                    if (clienteOptional.isPresent()) {
                        cliente = clienteOptional.get();
                    }
                    locacao.setCliente(cliente);

                    retorno.add(locacao);
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao consultar Locações ");
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
        }
        return retorno;
    }

    @Override
    public Optional<Locacao> obter(Long id) {
        String sql = "SELECT * FROM locacao WHERE id =?";
        Locacao retorno = null;
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setLong(1, id);
                ResultSet rs = p.executeQuery();

                if (rs.next()) {
                    retorno = new Locacao();
                    retorno.setId(rs.getLong("id"));
                    retorno.setValor(rs.getLong("valor"));
                    retorno.setDataDev(rs.getDate("dataDev"));
                    retorno.setDataSaida(rs.getDate("dataSaida"));
                    retorno.setDataPrevDev(rs.getDate("dataPrevDev"));
                
                    Optional<Veiculo> veiculoOptional = veiculoDAO.obter(rs.getLong("veiculo"));
                    Veiculo veiculo = null;
                    if (veiculoOptional.isPresent()) {
                        veiculo = veiculoOptional.get();
                    }
                    retorno.setVeiculoAlocado(veiculo);

                    Optional<Cliente> clienteOptional = clienteDAO.obter(rs.getLong("cliente"));
                    Cliente cliente = null;
                    if (clienteOptional.isPresent()) {
                        cliente = clienteOptional.get();
                    }
                    retorno.setCliente(cliente);
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao consultar Cliente com id " + id);
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
        }
        return Optional.of(retorno);
    }

    @Override
    public boolean inserir(Locacao locacao) {
        String sql = "INSERT INTO locacao(valor, dataPrevDev, dataSaida, veiculo, cliente) VALUES(?,?,?,?,?)";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setDouble(1, locacao.getValor());
                p.setDate(2, new java.sql.Date(locacao.getDataPrevDev().getTime()));
                p.setDate(3, new java.sql.Date(locacao.getDataSaida().getTime()));
                p.setLong(4, locacao.getVeiculoAlocado().getId());
                p.setLong(5, locacao.getCliente().getId());
                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao inserir locação. ");
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
    public boolean alterar(Locacao locacao) {
        String sql = "UPDATE locacao SET valor=?, dataDev=?, dataSaida=?, dataPrevDev=? veiculo=? cliente=? WHERE id=?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setDouble(1, locacao.getValor());
                p.setDate(2, new java.sql.Date(locacao.getDataDev().getTime()));
                p.setDate(3, new java.sql.Date(locacao.getDataSaida().getTime()));
                p.setDate(4, new java.sql.Date(locacao.getDataPrevDev().getTime()));
                p.setLong(5, locacao.getVeiculoAlocado().getId());
                p.setLong(6, locacao.getCliente().getId());
                p.setLong(7, locacao.getId());
                p.executeUpdate();
                c.commit();
                return true;

            } catch (SQLException e) {
                System.out.println("Erro ao atualizar locação.");
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
    public boolean remover(Locacao locacao) {
        String sql = "DELETE from locacao where id = ?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setLong(1, locacao.getId());

                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao deletar locação.");
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
