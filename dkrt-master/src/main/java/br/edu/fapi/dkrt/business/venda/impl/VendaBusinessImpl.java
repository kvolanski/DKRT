package br.edu.fapi.dkrt.business.venda.impl;

import br.edu.fapi.dkrt.business.venda.VendaBusiness;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.dao.venda.impl.VendaDAOImpl;
import br.edu.fapi.dkrt.model.venda.VendaDTO;

public class VendaBusinessImpl implements VendaBusiness {
    VendaDAO vendaDAO;

    public VendaBusinessImpl() {
        vendaDAO = new VendaDAOImpl();
    }

    @Override
    public int abrirVenda(VendaDTO vendaDTO) {
        if (vendaDTO != null) {
            int id = vendaDAO.abrirVenda(vendaDTO);
            if (id != 0) {
                return id;
            }
        }
        return 0;
    }
}
