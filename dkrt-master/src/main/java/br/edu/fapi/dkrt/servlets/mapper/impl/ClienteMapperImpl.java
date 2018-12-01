package br.edu.fapi.dkrt.servlets.mapper.impl;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;
import br.edu.fapi.dkrt.model.uf.UfDTO;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteMapperImpl implements BaseMapper<HttpServletRequest, ClienteDTO> {

    @Override
    public ClienteDTO doMap(HttpServletRequest req) {
        String nome = req.getParameter("nomeCliente");
        String rg = req.getParameter("rgCliente");
        String cpf = req.getParameter("cpfCliente");
        String dtNasc = req.getParameter("dtNascCliente");
        String email = req.getParameter("emailCliente");
        String celular = req.getParameter("celularCliente");
        String telefone = req.getParameter("telefoneCliente");
        String cepEndereco = req.getParameter("cepEnderecoCliente");
        String ruaEndereco = req.getParameter("ruaEnderecoCliente");
        String complementoEndereco = req.getParameter("complementoEnderecoCliente");
        String numeroEndereco = req.getParameter("numeroEnderecoCliente");
        String bairroEndereco = req.getParameter("bairroEnderecoCliente");
        String cidadeEndereco = req.getParameter("cidadeEnderecoCliente");
        String observacao = req.getParameter("observacaoCliente");
        String ufId = req.getParameter("clienteUfId");

        UfDTO ufDTO = new UfDTO();
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        ClienteDTO clienteDTO = new ClienteDTO();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        ufDTO.setId(Integer.parseInt(ufId));

        enderecoDTO.setCep(cepEndereco);
        enderecoDTO.setRua(ruaEndereco);
        enderecoDTO.setNumero(numeroEndereco);
        enderecoDTO.setComplemento(complementoEndereco);
        enderecoDTO.setBairro(bairroEndereco);
        enderecoDTO.setCidade(cidadeEndereco);
        enderecoDTO.setUfDTO(ufDTO);

        clienteDTO.setNome(nome);
        clienteDTO.setRg(rg);
        clienteDTO.setCpf(cpf);
        clienteDTO.setEmail(email);
        clienteDTO.setCelular(celular);
        clienteDTO.setTelefone(telefone);
        clienteDTO.setObservacao(observacao);
        clienteDTO.setAtivo(1);
        String hoje = dateFormat.format(new Date());
        try {
            clienteDTO.setDataCadastro(dateFormat.parse(hoje));
            clienteDTO.setDtNascimento(dateFormat.parse(dtNasc));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        clienteDTO.setEnderecoDTO(enderecoDTO);
        return clienteDTO;
    }

}
