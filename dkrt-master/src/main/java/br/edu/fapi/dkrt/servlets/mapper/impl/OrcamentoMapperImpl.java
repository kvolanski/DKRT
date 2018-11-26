package br.edu.fapi.dkrt.servlets.mapper.impl;

import br.edu.fapi.dkrt.model.orcamento.OrcamentoDTO;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;

import javax.servlet.http.HttpServletRequest;

public class OrcamentoMapperImpl implements BaseMapper <HttpServletRequest, OrcamentoDTO> {

    @Override
    public OrcamentoDTO doMap(HttpServletRequest req) {
        OrcamentoDTO orcamentoDTO = new OrcamentoDTO();

        return orcamentoDTO;
    }
}
