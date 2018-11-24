package br.edu.fapi.dkrt.dao.venda.impl;

import br.edu.fapi.dkrt.dao.conexao.MySqlConnectionProvider;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.model.CancelamentoDTO;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;
import br.edu.fapi.dkrt.model.pedido.PedidoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.uf.UfDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAOImpl implements VendaDAO {

    @Override
    public int abrirVenda(VendaDTO vendaDTO) {
        int id = 0;
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO vendas (venda_status, cliente_id) VALUES " +
                    "(?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, vendaDTO.getStatus());
            preparedStatement.setInt(2, vendaDTO.getClienteDTO().getId());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean finalizarVenda(VendaDTO vendaDTO) {
        String sql = "UPDATE vendas SET venda_valorTotal = ?, venda_formaDePagamento = ?, venda_parcelas = ?, venda_valorParcela = ?, venda_status = ?, venda_desconto = ?, " +
                "venda_dataDeVenda = ? WHERE venda_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setFloat(1, vendaDTO.getValorTotal());
            preparedStatement.setString(2, vendaDTO.getFormaDePagamento());
            preparedStatement.setInt(3, vendaDTO.getParcelas());
            preparedStatement.setFloat(4, vendaDTO.getValorParcelas());
            preparedStatement.setString(5, vendaDTO.getStatus());
            preparedStatement.setInt(6, vendaDTO.getDesconto());
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(vendaDTO.getDataDeVenda().getTime()));
            preparedStatement.setInt(8, vendaDTO.getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean adicionarPedido(PedidoDTO pedidoDTO, String tipo) {
        String sql;
        if ("venda".equalsIgnoreCase(tipo)) {
            sql = "INSERT INTO pedidos (produto_id, pedido_quantidade, pedido_valorUnitario, pedido_valorTotal, venda_id) VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO pedidos (produto_id, pedido_quantidade, pedido_valorUnitario, pedido_valorTotal, orcamento_id) VALUES (?, ?, ?, ?, ?)";
        }
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, pedidoDTO.getProdutoDTO().getId());
            preparedStatement.setInt(2, pedidoDTO.getQuantidade());
            preparedStatement.setFloat(3, pedidoDTO.getValorUnitario());
            preparedStatement.setFloat(4, pedidoDTO.getValorTotal());
            if ("venda".equalsIgnoreCase(tipo)) {
                preparedStatement.setInt(5, pedidoDTO.getVendaDTO().getId());
            } else {
                preparedStatement.setInt(5, pedidoDTO.getOrcamentoDTO().getId());
            }

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PedidoDTO> listarPedidosVenda(int id) {
        List<PedidoDTO> listaPedido = new ArrayList<>();
        String sql = "SELECT pedidos.pedido_id, produtos.produto_id, produtos.produto_nome, produtos.produto_descricao, pedidos.pedido_quantidade, " +
                "pedidos.pedido_valorUnitario, pedidos.pedido_valorTotal FROM pedidos INNER JOIN produtos ON pedidos.produto_id = produtos.produto_id " +
                "WHERE pedidos.venda_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProdutoDTO produtoDTO = new ProdutoDTO();
                produtoDTO.setId(resultSet.getInt("produto_id"));
                produtoDTO.setNome(resultSet.getString("produto_nome"));
                produtoDTO.setDescricao(resultSet.getString("produto_descricao"));
                PedidoDTO pedidoDTO = new PedidoDTO();
                pedidoDTO.setProdutoDTO(produtoDTO);
                pedidoDTO.setId(resultSet.getInt("pedido_id"));
                pedidoDTO.setQuantidade(resultSet.getInt("pedido_quantidade"));
                pedidoDTO.setValorUnitario(resultSet.getFloat("pedido_valorUnitario"));
                pedidoDTO.setValorTotal(resultSet.getFloat("pedido_valorTotal"));
                listaPedido.add(pedidoDTO);
            }

            return listaPedido;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaPedido;
    }

    @Override
    public List<VendaDTO> listarVendas() {
        List<VendaDTO> listaVendas = new ArrayList<>();
        String sql = "SELECT vendas.venda_id, vendas.venda_valorTotal, vendas.venda_formaDePagamento, vendas.venda_parcelas, vendas.venda_valorParcela, " +
                "vendas.venda_status, vendas.venda_desconto, vendas.venda_dataDeVenda, clientes.cliente_id, clientes.cliente_nome, clientes.cliente_nomeSocial, clientes.cliente_rg, " +
                "clientes.cliente_cpf, clientes.cliente_dtNasc, clientes.cliente_email, clientes.cliente_celular, clientes.cliente_telefone, " +
                "clientes.cliente_ativo, clientes.cliente_observacao, enderecos.endereco_id, enderecos.endereco_cep, enderecos.endereco_rua, " +
                "enderecos.endereco_numero, enderecos.endereco_complemento, enderecos.endereco_bairro, enderecos.endereco_cidade, ufs.uf_id, " +
                "ufs.uf_sigla FROM vendas INNER JOIN clientes ON vendas.cliente_id = clientes.cliente_id INNER JOIN enderecos ON clientes.endereco_id = " +
                "enderecos.endereco_id INNER JOIN ufs ON enderecos.uf_id = ufs.uf_id";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                VendaDTO vendaDTO = fillVenda(resultSet);
                listaVendas.add(vendaDTO);
            }

            return listaVendas;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaVendas;
    }

    @Override
    public VendaDTO buscaVenda(int id) {
        VendaDTO vendaBusca = new VendaDTO();
        String sql = "SELECT vendas.venda_id, vendas.venda_valorTotal, vendas.venda_formaDePagamento, vendas.venda_parcelas, vendas.venda_valorParcela, " +
                "vendas.venda_status, vendas.venda_desconto, vendas.venda_dataDeVenda, clientes.cliente_id, clientes.cliente_nome, clientes.cliente_nomeSocial, clientes.cliente_rg, " +
                "clientes.cliente_cpf, clientes.cliente_dtNasc, clientes.cliente_email, clientes.cliente_celular, clientes.cliente_telefone, " +
                "clientes.cliente_ativo, clientes.cliente_observacao, enderecos.endereco_id, enderecos.endereco_cep, enderecos.endereco_rua, " +
                "enderecos.endereco_numero, enderecos.endereco_complemento, enderecos.endereco_bairro, enderecos.endereco_cidade, ufs.uf_id, " +
                "ufs.uf_sigla FROM vendas INNER JOIN clientes ON vendas.cliente_id = clientes.cliente_id INNER JOIN enderecos ON clientes.endereco_id = " +
                "enderecos.endereco_id INNER JOIN ufs ON enderecos.uf_id = ufs.uf_id WHERE vendas.venda_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                vendaBusca = fillVenda(resultSet);
            }
            return vendaBusca;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vendaBusca;
    }

    @Override
    public boolean atualizaStatus(VendaDTO vendaDTO) {
        String sql = "UPDATE vendas SET venda_status = ? WHERE venda_id = ?";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, vendaDTO.getStatus());
            preparedStatement.setInt(2, vendaDTO.getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean atualizaMotivoCancelamento(CancelamentoDTO cancelamentoDTO) {
        String sql = "INSERT INTO cancelamentos (cancelamento_motivo, venda_id) VALUES (?, ?)";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cancelamentoDTO.getMotivo());
            preparedStatement.setInt(2, cancelamentoDTO.getVendaDTO().getId());

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean retirarPedido(int id) {
        String sql = "DELETE FROM pedidos WHERE pedido_id = ? ";
        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            int resultado = preparedStatement.executeUpdate();

            if (resultado != 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PedidoDTO buscaPedido(int id) {
        PedidoDTO pedidoBusca = new PedidoDTO();
        String sql = "SELECT pedidos.pedido_id, pedidos.pedido_quantidade, pedidos.pedido_valorUnitario, pedidos.pedido_valorTotal, " +
                "produtos.produto_id, produtos.produto_nome, produtos.produto_descricao, produtos.produto_precoVenda, produtos.produto_precoCusto, " +
                "produtos.produto_qtdEstoque, produtos.produto_ativo, produtos.produto_dataCadastro, produtos.produto_dataAlteracao, vendas.venda_id, " +
                "vendas.venda_valorTotal, vendas.venda_formaDePagamento, vendas.venda_parcelas, vendas.venda_valorParcela, vendas.venda_status, " +
                "vendas.venda_desconto, vendas.venda_dataDeVenda FROM pedidos INNER JOIN produtos ON pedidos.produto_id = produtos.produto_id " +
                "INNER JOIN vendas ON pedidos.venda_id = vendas.venda_id WHERE pedidos.pedido_id = ?";

        try (Connection connection = MySqlConnectionProvider.abrirConexao()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pedidoBusca = fillPedido(resultSet);
            }

            return pedidoBusca;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pedidoBusca;
    }

    private VendaDTO fillVenda(ResultSet resultSet) throws SQLException {
        UfDTO ufDTO = new UfDTO();
        ufDTO.setId(resultSet.getInt("uf_id"));
        ufDTO.setSigla(resultSet.getString("uf_sigla"));
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setUfDTO(ufDTO);
        enderecoDTO.setId(resultSet.getInt("endereco_id"));
        enderecoDTO.setCep(resultSet.getString("endereco_cep"));
        enderecoDTO.setRua(resultSet.getString("endereco_rua"));
        enderecoDTO.setNumero(resultSet.getString("endereco_numero"));
        enderecoDTO.setComplemento(resultSet.getString("endereco_complemento"));
        enderecoDTO.setBairro(resultSet.getString("endereco_bairro"));
        enderecoDTO.setCidade(resultSet.getString("endereco_cidade"));
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setEnderecoDTO(enderecoDTO);
        clienteDTO.setId(resultSet.getInt("cliente_id"));
        clienteDTO.setNome(resultSet.getString("cliente_nome"));
        clienteDTO.setRg(resultSet.getString("cliente_rg"));
        clienteDTO.setCpf(resultSet.getString("cliente_cpf"));
        clienteDTO.setEmail(resultSet.getString("cliente_email"));
        clienteDTO.setDtNascimento(resultSet.getTimestamp("cliente_dtNasc"));
        clienteDTO.setCelular(resultSet.getString("cliente_celular"));
        clienteDTO.setTelefone(resultSet.getString("cliente_telefone"));
        int ativo = resultSet.getInt("cliente_ativo");
        clienteDTO.setAtivo(ativo == 1);
        clienteDTO.setObservacao(resultSet.getString("cliente_observacao"));
        VendaDTO vendaBusca = new VendaDTO();
        vendaBusca.setClienteDTO(clienteDTO);
        vendaBusca.setId(resultSet.getInt("venda_id"));
        vendaBusca.setValorTotal(resultSet.getFloat("venda_valorTotal"));
        vendaBusca.setFormaDePagamento(resultSet.getString("venda_formaDePagamento"));
        vendaBusca.setParcelas(resultSet.getInt("venda_parcelas"));
        vendaBusca.setValorParcelas(resultSet.getFloat("venda_valorParcela"));
        vendaBusca.setStatus(resultSet.getString("venda_status"));
        vendaBusca.setDesconto(resultSet.getInt("venda_desconto"));
        vendaBusca.setDataDeVenda(resultSet.getTimestamp("venda_dataDeVenda"));
        return vendaBusca;
    }

    private PedidoDTO fillPedido(ResultSet resultSet) throws SQLException {
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setId(resultSet.getInt("venda_id"));
        vendaDTO.setValorTotal(resultSet.getFloat("venda_valorTotal"));
        vendaDTO.setFormaDePagamento(resultSet.getString("venda_formaDePagamento"));
        vendaDTO.setParcelas(resultSet.getInt("venda_parcelas"));
        vendaDTO.setValorParcelas(resultSet.getFloat("venda_valorParcela"));
        vendaDTO.setStatus(resultSet.getString("venda_status"));
        vendaDTO.setDesconto(resultSet.getInt("venda_desconto"));
        vendaDTO.setDataDeVenda(resultSet.getTimestamp("venda_dataDeVenda"));
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(resultSet.getInt("produto_id"));
        produtoDTO.setNome(resultSet.getString("produto_nome"));
        produtoDTO.setDescricao(resultSet.getString("produto_descricao"));
        produtoDTO.setPrecoVenda(resultSet.getFloat("produto_precoVenda"));
        produtoDTO.setPrecoCusto(resultSet.getFloat("produto_precoCusto"));
        produtoDTO.setQtdEstoque(resultSet.getInt("produto_qtdEstoque"));
        produtoDTO.setAtivo(resultSet.getInt("produto_ativo"));
        produtoDTO.setDataCadastro(resultSet.getTimestamp("produto_dataCadastro"));
        produtoDTO.setDataAlteracao(resultSet.getTimestamp("produto_dataAlteracao"));
        PedidoDTO pedidoBusca = new PedidoDTO();
        pedidoBusca.setVendaDTO(vendaDTO);
        pedidoBusca.setProdutoDTO(produtoDTO);
        pedidoBusca.setId(resultSet.getInt("pedido_id"));
        pedidoBusca.setQuantidade(resultSet.getInt("pedido_quantidade"));
        pedidoBusca.setValorUnitario(resultSet.getFloat("pedido_valorUnitario"));
        pedidoBusca.setValorTotal(resultSet.getFloat("pedido_valorTotal"));
        return pedidoBusca;
    }
}
