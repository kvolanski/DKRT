package br.edu.fapi.dkrt.business.cadastro.impl;

import br.edu.fapi.dkrt.business.cadastro.ClienteBusiness;
import br.edu.fapi.dkrt.validators.cliente.ClienteValidator;
import br.edu.fapi.dkrt.validators.cliente.impl.ClienteValidatorImpl;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.endereco.EnderecoDAO;
import br.edu.fapi.dkrt.dao.endereco.impl.EnderecoDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;

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

}
