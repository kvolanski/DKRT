package br.edu.fapi.dkrt.servlets.mapper.impl;

import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VendaMapperImpl implements BaseMapper<HttpServletRequest, VendaDTO> {

    @Override
    public VendaDTO doMap(HttpServletRequest req) {
        VendaDTO vendaDTO = new VendaDTO();
        int id = (int) req.getSession().getAttribute("idVenda");
        String formaDePagamento = req.getParameter("formaDePagamento");
        String numParcelas = req.getParameter("numeroParcelasVenda");
        String desconto = req.getParameter("descontoVenda");
        String statusAberto = req.getParameter("statusAberto");
        vendaDTO.setId(id);
        vendaDTO.setFormaDePagamento(formaDePagamento);
        vendaDTO.setParcelas(Integer.parseInt(numParcelas));
        vendaDTO.setDesconto(Integer.parseInt(desconto));
        if ("emAberto".equalsIgnoreCase(statusAberto)) {
            vendaDTO.setStatus("Em aberto");
        } else {
            vendaDTO.setStatus("Completa");
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String data = dateFormat.format(new Date());
        try {
            vendaDTO.setDataDeVenda(dateFormat.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return vendaDTO;
    }
}
