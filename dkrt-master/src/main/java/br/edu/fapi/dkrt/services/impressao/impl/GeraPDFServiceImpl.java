package br.edu.fapi.dkrt.services.impressao.impl;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.services.impressao.GeraPDFService;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.IOException;

public class GeraPDFServiceImpl implements GeraPDFService {

    @Override
    public boolean gerarPdfFichaCliente(ClienteDTO clienteDTO) throws IOException {
        if (clienteDTO != null) {
            PDDocument document = new PDDocument();

            PDPage blankPage = new PDPage();
            document.addPage(blankPage);

            try {
                document.save("C:\\Users\\Archibald\\Documents\\DKRT\\dkrt-master\\pdfs\\BlankDocumento.pdf");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (COSVisitorException e) {
                e.printStackTrace();
            }

            document.close();
            return true;
        }
        return false;
    }
}

