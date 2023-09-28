package com.example.application.persistencia.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.application.entity.Cliente;
import com.example.application.persistencia.ClienteDAO;

@Repository
public class ClienteDAOimpl extends BaseDAO implements ClienteDAO {

    public List<Cliente> listar() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> retorno = new ArrayList<>();
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getLong("id"));
                    cliente.setCpf(rs.getLong("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setNumCnh(rs.getLong("numCnh"));
                    cliente.setTelefone(rs.getString("telefone"));
                    
                    retorno.add(cliente);
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao consultar Clientes ");
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
        }
        return retorno;
    }

    public Optional<Cliente> obter(Long id) {
        String sql = "SELECT * FROM clientes WHERE id =?";
        Cliente retorno = null;
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setLong(1, id);
                ResultSet rs = p.executeQuery();

                if (rs.next()) {
                    retorno = new Cliente();
                    retorno.setId(rs.getLong("id"));
                    retorno.setCpf(rs.getLong("cpf"));
                    retorno.setEmail(rs.getString("email"));
                    retorno.setNome(rs.getString("nome"));
                    retorno.setNumCnh(rs.getLong("numCnh"));
                    retorno.setTelefone(rs.getString("telefone"));
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

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO clientes(cpf, email, nome, numCnh, telefone) VALUES(?,?,?,?,?)";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setLong(1, cliente.getCpf());
                p.setString(2, cliente.getEmail());
                p.setString(3, cliente.getNome());
                p.setLong(4, cliente.getNumCnh());
                p.setString(5, cliente.getTelefone());

                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao inserir Cliente. " + cliente.getCpf());
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Cliente cliente) {
        String sql = "UPDATE clientes SET cpf=?, email=?, nome=?, numCnh=?, telefone=? WHERE id=?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setLong(1, cliente.getCpf());
                p.setString(2, cliente.getEmail());
                p.setString(3, cliente.getNome());
                p.setLong(4, cliente.getNumCnh());
                p.setString(5, cliente.getTelefone());
                p.setLong(6, cliente.getId());
                p.executeUpdate();
                c.commit();
                return true;

            } catch (SQLException e) {
                System.out.println("Erro ao atualizar cliente. " + cliente.getCpf());
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean remover(Cliente cliente) {
        String sql = "DELETE from clientes where id = ?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setLong(1, cliente.getId());

                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao deletar cliente. " + cliente.getCpf());
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
