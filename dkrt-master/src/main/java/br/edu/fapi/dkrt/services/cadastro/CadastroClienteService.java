package br.edu.fapi.dkrt.services.cadastro;

import br.edu.fapi.dkrt.business.endereco.EnderecoBusiness;
import br.edu.fapi.dkrt.business.endereco.impl.EnderecoBusinessImpl;
import br.edu.fapi.dkrt.dao.cliente.ClienteDAO;
import br.edu.fapi.dkrt.dao.cliente.impl.ClienteDAOImpl;
import br.edu.fapi.dkrt.dao.endereco.EnderecoDAO;
import br.edu.fapi.dkrt.dao.endereco.impl.EnderecoDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;

public class CadastroClienteService {
    EnderecoDAO enderecoDAO;
    ClienteDAO clienteDAO;
    EnderecoBusiness enderecoBusiness;

    public CadastroClienteService() {
        enderecoDAO = new EnderecoDAOImpl();
        clienteDAO = new ClienteDAOImpl();
        enderecoBusiness = new EnderecoBusinessImpl();
    }

    public String cadastrarCliente(ClienteDTO clienteDTO) {
        String condicao = "usuarioVazio";
        if (clienteDTO != null) {
            condicao = "sucesso";

            if (!enderecoBusiness.verificarNumero(clienteDTO.getEnderecoDTO().getNumero())) {
                condicao = "numEndereco ";
            }

            if (!enderecoBusiness.verificarNumero(clienteDTO.getEnderecoDTO().getCep())) {
                if ("sucesso".equalsIgnoreCase(condicao)) {
                    condicao = "cepEndereco ";
                } else {
                    condicao += "cepEndereco ";
                }
            }

            if ("sucesso".equalsIgnoreCase(condicao)) {
                EnderecoDTO enderecoBusca = enderecoDAO.buscaEndereco(clienteDTO.getEnderecoDTO(), clienteDTO.getEnderecoDTO().getUfDTO().getId());
                clienteDTO.setEnderecoDTO(enderecoBusca);
                clienteDAO.createCliente(clienteDTO);
            }

            System.out.println(condicao);

        }
        return condicao;
    }

}
