package br.edu.fapi.dkrt.services.impressao.impl;

import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.services.impressao.GeraPDFService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeraPDFServiceImpl implements GeraPDFService {

    @Override
    public boolean gerarPdfFichaCliente(ClienteDTO clienteDTO, String caminho, float maiorCompra) throws IOException {
        if (clienteDTO != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            String data = dateFormat.format(new Date());
            String nome = clienteDTO.getNome();
            nome = nome.replaceAll(" ", "_");
            PDDocument document = new PDDocument();

            PDPage page = new PDPage();
            document.addPage(page);

            PDFont pdFont = PDType1Font.COURIER;
            PDFont pdFontTitulo = PDType1Font.COURIER_BOLD;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            int posicaoY = 650;
            int posicaoInicialY = posicaoY;
            int posicaoX1 = 150;
            int posicaoX2 = 350;
            int tamFonte = 8;
            int tamFonteTitulo = 10;

            PDImageXObject pdImage = PDImageXObject.createFromFile(caminho+"\\PdfsDKRT\\Logo\\logo.png", document);

            //Logo
            contentStream.drawXObject(pdImage, 230, 660, 160, 160);

            //Inicio Informações pessoais
            contentStream.beginText();
            contentStream.setFont(pdFontTitulo, tamFonteTitulo);
            contentStream.moveTextPositionByAmount(250, 670);
            contentStream.drawString("Informações Pessoais");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("Nome: " + clienteDTO.getNome());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("RG: " + clienteDTO.getRg());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("CPF: " + clienteDTO.getCpf());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            contentStream.drawString("Data de Nascimento: " + dateFormat.format(clienteDTO.getDtNascimento()));
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("E-mail: " + clienteDTO.getEmail());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("Telefone Principal: " + clienteDTO.getTelefone());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("Telefone Secundario: " + clienteDTO.getTelefone());
            contentStream.endText();
            posicaoY = 625;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX2, posicaoY);
            contentStream.drawString("Data de Cadastro: " + dateFormat.format(clienteDTO.getDataCadastro().getTime()));
            contentStream.endText();
            posicaoY -= 10;

            if (clienteDTO.getDataAlteracao() != null) {
                contentStream.beginText();
                contentStream.setFont(pdFont, tamFonte);
                contentStream.moveTextPositionByAmount(posicaoX2, posicaoY);
                contentStream.drawString("Data de Alteracao: " + dateFormat.format(clienteDTO.getDataAlteracao().getTime()));
                contentStream.endText();
                posicaoY -= 10;
            }

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX2, posicaoY);
            contentStream.drawString("Data de Cadastro: " + clienteDTO.getObservacao());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX2, posicaoY);
            contentStream.drawString("Número de compras realizadas: " + clienteDTO.getNumeroCompras());
            contentStream.endText();

            //Inicio Informações de Endereço
            posicaoY = 540;
            contentStream.beginText();
            contentStream.setFont(pdFontTitulo, tamFonteTitulo);
            contentStream.moveTextPositionByAmount(245, 555);
            contentStream.drawString("Informações de Endereço");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("Nome da Rua: " + clienteDTO.getEnderecoDTO().getRua());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("CEP: " + clienteDTO.getEnderecoDTO().getCep());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
            contentStream.drawString("Número da residência: " + clienteDTO.getEnderecoDTO().getNumero());
            contentStream.endText();
            posicaoY -= 10;

            if (clienteDTO.getEnderecoDTO().getComplemento() != null) {
                contentStream.beginText();
                contentStream.setFont(pdFont, tamFonte);
                contentStream.moveTextPositionByAmount(posicaoX1, posicaoY);
                contentStream.drawString("Complemento: " + clienteDTO.getEnderecoDTO().getComplemento());
                contentStream.endText();
                posicaoY = 535;
            } else {
                posicaoY = 540;
            }

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX2, posicaoY);
            contentStream.drawString("Bairro: " + clienteDTO.getEnderecoDTO().getBairro());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX2, posicaoY);
            contentStream.drawString("Cidade: " + clienteDTO.getEnderecoDTO().getCidade());
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(posicaoX2, posicaoY);
            contentStream.drawString("Estado: " + clienteDTO.getEnderecoDTO().getUfDTO().getSigla());
            contentStream.endText();
            posicaoY -= 10;

            //Inicio Informações Adicionais
            posicaoY = 480;
            contentStream.beginText();
            contentStream.setFont(pdFontTitulo, tamFonteTitulo);
            contentStream.moveTextPositionByAmount(245, 490);
            contentStream.drawString("Informações Adicionais");
            contentStream.endText();
            posicaoY -= 10;

            contentStream.beginText();
            contentStream.setFont(pdFont, tamFonte);
            contentStream.moveTextPositionByAmount(245, posicaoY);
            contentStream.drawString("Valor da maior compra feita: " + maiorCompra);
            contentStream.endText();
            posicaoY -= 10;


            contentStream.close();

            try {
                if (!Files.isDirectory(Paths.get(caminho + "\\PdfsDKRT"))) {
                    Files.createDirectory(Paths.get(caminho + "\\PdfsDKRT"));
                    if (!Files.isDirectory(Paths.get(caminho + "\\PdfsDKRT\\PDFs\\Clientes"))){
                        Files.createDirectory(Paths.get(caminho + "\\PdfsDKRT\\PDFs\\Clientes"));
                    }
                    if (!Files.isDirectory(Paths.get(caminho + "\\PdfsDKRT\\Logo"))){
                        Files.createDirectory(Paths.get(caminho + "\\PdfsDKRT\\Logo"));
                    }
                }
                document.save(caminho + "\\PdfsDKRT\\PDFs\\Clientes\\Relatorio_Cliente_" + nome + "_" + data + ".pdf");
            } catch (IOException e) {
                e.printStackTrace();
            }

            document.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean gerarPdfFichaProduto(ProdutoDTO produtoDTO, String caminho) {
        return false;
    }
}

