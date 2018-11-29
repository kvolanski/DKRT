package br.edu.fapi.dkrt.business.cadastro.impl;

import br.edu.fapi.dkrt.business.cadastro.ClienteBusiness;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.endereco.EnderecoDAO;
import br.edu.fapi.dkrt.dao.endereco.impl.EnderecoDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;
import br.edu.fapi.dkrt.validators.cliente.ClienteValidator;
import br.edu.fapi.dkrt.validators.cliente.impl.ClienteValidatorImpl;

import java.util.List;

public class ClienteBusinessImpl implements ClienteBusiness {
    EnderecoDAO enderecoDAO;
    ClienteDAO clienteDAO;
    ClienteValidator clienteValidator;

    public ClienteBusinessImpl() {
        enderecoDAO = new EnderecoDAOImpl();
        clienteDAO = new ClienteDAOImpl();
        clienteValidator = new ClienteValidatorImpl();
    }

    @Override
    public String cadastrarCliente(ClienteDTO clienteDTO) {
        String condicao = "";
        if (clienteDTO != null) {
            condicao = clienteValidator.verificarCliente(clienteDTO);

            if ("sucesso".equalsIgnoreCase(condicao)) {
                EnderecoDTO enderecoBusca = enderecoDAO.buscaEndereco(clienteDTO.getEnderecoDTO(), clienteDTO.getEnderecoDTO().getUfDTO().getId());
                clienteDTO.setEnderecoDTO(enderecoBusca);
                clienteDAO.createCliente(clienteDTO);
            }
        }
        return condicao;
    }

    @Override
    public List<ClienteDTO> pesquisarClienteLikeNome(String palavra) {
        String sql = "SELECT clientes.cliente_id, clientes.cliente_nome, clientes.cliente_nomeSocial, clientes.cliente_rg, clientes.cliente_cpf, " +
                "clientes.cliente_dtNasc, clientes.cliente_email, clientes.cliente_celular, clientes.cliente_telefone, clientes.cliente_ativo, " +
                "clientes.cliente_dataDeCadastro, clientes.cliente_dataDeAlteracao, clientes.cliente_observacao, clientes.cliente_numeroCompras, enderecos.endereco_id, " +
                "enderecos.endereco_cep, enderecos.endereco_rua, enderecos.endereco_numero, enderecos.endereco_complemento, enderecos.endereco_bairro, " +
                "enderecos.endereco_cidade, ufs.uf_id, ufs.uf_sigla, ufs.uf_nome FROM clientes INNER JOIN enderecos ON clientes.endereco_id = enderecos.endereco_id " +
                "INNER JOIN ufs ON enderecos.uf_id = ufs.uf_id WHERE clientes.cliente_nome LIKE '%" + palavra + "%'";
        List<ClienteDTO> listaClientesLike = clienteDAO.listarClientePesquisa(sql);
        return listaClientesLike;
    }

    @Override
    public ClienteDTO buscarClienteId(int id) {
        ClienteDTO clienteBusca= clienteDAO.buscarCliente(id);
        return clienteBusca;
    }

    @Override
    public boolean adicionarCompraCliente(ClienteDTO clienteDTO) {
        if (clienteDTO != null){
            clienteDTO.setNumeroCompras(clienteDTO.getNumeroCompras()+1);
            if (clienteDAO.adicionaCompraCliente(clienteDTO)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ClienteDTO> listarClienteNumCompras() {
        String sql = "SELECT clientes.cliente_id, clientes.cliente_nome, clientes.cliente_nomeSocial, clientes.cliente_rg, clientes.cliente_cpf, " +
                "clientes.cliente_dtNasc, clientes.cliente_email, clientes.cliente_celular, clientes.cliente_telefone, clientes.cliente_ativo, " +
                "clientes.cliente_dataDeCadastro, clientes.cliente_dataDeAlteracao, clientes.cliente_observacao, clientes.cliente_numeroCompras, enderecos.endereco_id, " +
                "enderecos.endereco_cep, enderecos.endereco_rua, enderecos.endereco_numero, enderecos.endereco_complemento, enderecos.endereco_bairro, " +
                "enderecos.endereco_cidade, ufs.uf_id, ufs.uf_sigla, ufs.uf_nome FROM clientes INNER JOIN enderecos ON clientes.endereco_id = enderecos.endereco_id " +
                "INNER JOIN ufs ON enderecos.uf_id = ufs.uf_id ORDER BY clientes.cliente_numeroCompras DESC";
        List<ClienteDTO> listaClientes = clienteDAO.listarClientePesquisa(sql);
        return listaClientes;
    }

}
