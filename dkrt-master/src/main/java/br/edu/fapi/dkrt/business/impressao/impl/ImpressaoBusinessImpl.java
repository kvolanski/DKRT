package br.edu.fapi.dkrt.business.impressao.impl;

import br.edu.fapi.dkrt.business.impressao.ImpressaoBusiness;
import br.edu.fapi.dkrt.dao.venda.VendaDAO;
import br.edu.fapi.dkrt.dao.venda.impl.VendaDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.venda.VendaDTO;
import br.edu.fapi.dkrt.services.impressao.GeraPDFService;
import br.edu.fapi.dkrt.services.impressao.impl.GeraPDFServiceImpl;

import java.io.IOException;
import java.util.List;

public class ImpressaoBusinessImpl implements ImpressaoBusiness {
    GeraPDFService geraPDFService;
    String caminho = System.getProperty("user.home");
    VendaDAO vendaDAO;

    public ImpressaoBusinessImpl() {
        geraPDFService = new GeraPDFServiceImpl();
        vendaDAO = new VendaDAOImpl();
    }


    @Override
    public boolean gerarPdfFichaCliente(ClienteDTO clienteDTO) {
        if (clienteDTO != null) {
            try {
                List<VendaDTO> listaVendas = vendaDAO.listarVendasPorCliente(clienteDTO.getId());
                float maiorValor = 0;
                for (VendaDTO venda : listaVendas) {
                    if (venda.getValorTotal() > maiorValor){
                        maiorValor = venda.getValorTotal();
                    }
                }
                if (geraPDFService.gerarPdfFichaCliente(clienteDTO, caminho, maiorValor)) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean gerarPdfFichaProduto(ProdutoDTO produtoDTO) {
        if (produtoDTO != null){
            if (geraPDFService.gerarPdfFichaProduto(produtoDTO, caminho)){
                return true;
            }
        }
        return false;
    }
}
