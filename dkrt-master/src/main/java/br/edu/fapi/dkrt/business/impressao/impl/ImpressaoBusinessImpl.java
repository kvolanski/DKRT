package br.edu.fapi.dkrt.business.impressao.impl;

import br.edu.fapi.dkrt.business.impressao.ImpressaoBusiness;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.services.impressao.GeraPDFService;
import br.edu.fapi.dkrt.services.impressao.impl.GeraPDFServiceImpl;

import java.io.IOException;

public class ImpressaoBusinessImpl implements ImpressaoBusiness {
    GeraPDFService geraPDFService;

    public ImpressaoBusinessImpl(){
        geraPDFService = new GeraPDFServiceImpl();
    }


    @Override
    public boolean gerarPdfFichaCliente(ClienteDTO clienteDTO) {
        if (clienteDTO != null){
            try {
                if (geraPDFService.gerarPdfFichaCliente(clienteDTO)){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
