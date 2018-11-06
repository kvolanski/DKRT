package br.edu.fapi.dkrt.dao.endereco;

import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;

public interface EnderecoDAO {

    EnderecoDTO buscaEndereco(EnderecoDTO enderecoDTO, int ufId);
}
