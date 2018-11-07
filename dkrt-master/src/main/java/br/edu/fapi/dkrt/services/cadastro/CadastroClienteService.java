package br.edu.fapi.dkrt.services.cadastro;

import br.edu.fapi.dkrt.business.cliente.ClienteBusiness;
import br.edu.fapi.dkrt.business.cliente.impl.ClienteBusinessImpl;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.endereco.EnderecoDAO;
import br.edu.fapi.dkrt.dao.endereco.impl.EnderecoDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;

public class CadastroClienteService {
    EnderecoDAO enderecoDAO;
    ClienteDAO clienteDAO;
    ClienteBusiness clienteBusiness;

    public CadastroClienteService() {
        enderecoDAO = new EnderecoDAOImpl();
        clienteDAO = new ClienteDAOImpl();
        clienteBusiness = new ClienteBusinessImpl();
    }

    public String cadastrarCliente(ClienteDTO clienteDTO) {
        String condicao = "usuarioVazio";
        if (clienteDTO != null) {
            condicao = "sucesso";

            if (!clienteBusiness.verificarNumero(clienteDTO.getEnderecoDTO().getNumero())) {
                condicao = "numEndereco ";
            }

            if (!clienteBusiness.verificarNumero(clienteDTO.getEnderecoDTO().getCep())) {
                if ("sucesso".equalsIgnoreCase(condicao)) {
                    condicao = "cepEndereco ";
                } else {
                    condicao += "cepEndereco ";
                }
            }

            if (!clienteBusiness.verificarNumero(clienteDTO.getTelefone())) {
                if ("sucesso".equalsIgnoreCase(condicao)) {
                    condicao = "telefoneCliente ";
                } else {
                    condicao += "telefoneCliente ";
                }
            }

            if (!clienteBusiness.verificarNumero(clienteDTO.getCpf())) {
                if ("sucesso".equalsIgnoreCase(condicao)) {
                    condicao = "cpfCliente ";
                } else {
                    condicao += "cpfCliente ";
                }
            }

            if (!clienteBusiness.verificarNumeroPalavra(clienteDTO.getNome())){
                if ("sucesso".equalsIgnoreCase(condicao)){
                    condicao = "nomeCliente ";
                } else {
                    condicao += "nomeCliente ";
                }
            }

            if (clienteDTO.getEnderecoDTO().getUfDTO().getId() == 0){
                if ("sucesso".equalsIgnoreCase(condicao)){
                    condicao = "ufIncorreto ";
                } else {
                    condicao += "ufIncorreto ";
                }
            }

            if ("sucesso".equalsIgnoreCase(condicao)) {
                EnderecoDTO enderecoBusca = enderecoDAO.buscaEndereco(clienteDTO.getEnderecoDTO(), clienteDTO.getEnderecoDTO().getUfDTO().getId());
                clienteDTO.setEnderecoDTO(enderecoBusca);
                clienteDAO.createCliente(clienteDTO);
            }
        }
        return condicao;
    }

}
