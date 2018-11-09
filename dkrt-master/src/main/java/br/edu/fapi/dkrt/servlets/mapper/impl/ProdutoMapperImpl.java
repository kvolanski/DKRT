package br.edu.fapi.dkrt.servlets.mapper.impl;

import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.servlets.mapper.BaseMapper;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProdutoMapperImpl implements BaseMapper<HttpServletRequest,ProdutoDTO> {

    @Override
    public ProdutoDTO doMap(HttpServletRequest req) throws NumberFormatException{
        String nome = req.getParameter("nomeProduto");
        String descricao = req.getParameter("descricaoProduto");
        String quantidade = req.getParameter("quantidadeProduto");
        String precoVenda = req.getParameter("precoVendaProduto");
        String precoCusto = req.getParameter("precoCustoProduto");

        ProdutoDTO produtoDTO = new ProdutoDTO();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        produtoDTO.setNome(nome);
        produtoDTO.setDescricao(descricao);

        produtoDTO.setQtdEstoque(Integer.parseInt(quantidade));
        produtoDTO.setPrecoVenda(Double.parseDouble(precoVenda));
        produtoDTO.setPrecoCusto(Double.parseDouble(precoCusto));

        String data = dateFormat.format(new Date());
        try {
            produtoDTO.setDataCadastro(dateFormat.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        produtoDTO.setAtivo(1);

        return produtoDTO;
    }
}
