package br.edu.fapi.dkrt.servlets.cadastro;

import br.edu.fapi.dkrt.business.cadastro.impl.ClienteBusinessImpl;
import br.edu.fapi.dkrt.business.cadastro.impl.ProdutoBusinessImpl;
import br.edu.fapi.dkrt.dao.produto.ProdutoDAO;
import br.edu.fapi.dkrt.dao.produto.impl.ProdutoDAOImpl;
import br.edu.fapi.dkrt.dao.uf.UfDAO;
import br.edu.fapi.dkrt.dao.uf.impl.UfDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;
import br.edu.fapi.dkrt.model.produto.ProdutoDTO;
import br.edu.fapi.dkrt.model.uf.UfDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/cadastro")
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("cliente".equalsIgnoreCase(tipo)) {
            ClienteBusinessImpl clienteBusiness = new ClienteBusinessImpl();
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
            clienteDTO.setAtivo(true);
            String hoje = dateFormat.format(new Date());
            try {
                clienteDTO.setDataCadastroCliente(dateFormat.parse(hoje));
                clienteDTO.setDtNascimento(dateFormat.parse(dtNasc));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            clienteDTO.setEnderecoDTO(enderecoDTO);

            String condicao = clienteBusiness.cadastrarCliente(clienteDTO);

            if ("sucesso".equalsIgnoreCase(condicao)) {
                req.getSession().setAttribute("condicao", condicao);
                req.getRequestDispatcher("WEB-INF/cadastro/clienteCadastro.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("condicao", condicao);
                req.getSession().setAttribute("tipoCadastro", "cliente");
                req.getRequestDispatcher("WEB-INF/cadastro/erroCadastro.jsp").forward(req, resp);
            }
        }

        if ("produto".equalsIgnoreCase(tipo)) {
            ProdutoBusinessImpl produtoBusiness = new ProdutoBusinessImpl();
            String nome = req.getParameter("nomeProduto");
            String descricao = req.getParameter("descricaoProduto");
            String quantidade = req.getParameter("quantidadeProduto");
            String precoVenda = req.getParameter("precoVendaProduto");
            String precoCusto = req.getParameter("precoCustoProduto");

            ProdutoDTO produtoDTO = new ProdutoDTO();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            produtoDTO.setNome(nome);
            produtoDTO.setDescricao(descricao);

            try {
                produtoDTO.setQtdEstoque(Integer.parseInt(quantidade));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                req.getSession().setAttribute("condicao", "qtdInvalida");
                req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
            }

            try {
                produtoDTO.setPrecoVenda(Double.parseDouble(precoVenda));
                produtoDTO.setPrecoCusto(Double.parseDouble(precoCusto));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                req.getSession().setAttribute("condicao", "precoInvalido");
                req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
            }

            String data = dateFormat.format(new Date());
            try {
                produtoDTO.setDataCadastro(dateFormat.parse(data));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            produtoDTO.setAtivo(1);

            String condicao = produtoBusiness.cadastrarProduto(produtoDTO, "cadastro");

            if ("sucesso".equalsIgnoreCase(condicao)) {
                req.getSession().setAttribute("condicao", condicao);
            } else if ("produtoExistente".equalsIgnoreCase(condicao)) {
                req.getSession().setAttribute("condicao", condicao);
                req.getSession().setAttribute("nomeProduto", produtoDTO.getNome());
            } else {
                req.getSession().setAttribute("condicao", condicao);
                req.getSession().setAttribute("tipoCadastro", "produto");
                req.getRequestDispatcher("WEB-INF/cadastro/erroCadastro.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }

        if ("alteraProduto".equalsIgnoreCase(tipo)) {
            ProdutoBusinessImpl produtoBusiness = new ProdutoBusinessImpl();
            String id = req.getParameter("idProduto");
            String nome = req.getParameter("nomeProduto");
            String descricao = req.getParameter("descricaoProduto");
            String quantidade = req.getParameter("quantidadeProduto");
            String precoVenda = req.getParameter("precoVendaProduto");
            String precoCusto = req.getParameter("precoCustoProduto");

            ProdutoDTO produtoDTO = new ProdutoDTO();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            produtoDTO.setId(Integer.parseInt(id));
            produtoDTO.setNome(nome);
            produtoDTO.setDescricao(descricao);

            try {
                produtoDTO.setQtdEstoque(Integer.parseInt(quantidade));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                req.getSession().setAttribute("condicao", "qtdInvalida");
                req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
            }

            try {
                produtoDTO.setPrecoVenda(Double.parseDouble(precoVenda));
                produtoDTO.setPrecoCusto(Double.parseDouble(precoCusto));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                req.getSession().setAttribute("condicao", "precoInvalido");
                req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
            }

            String data = dateFormat.format(new Date());
            try {
                produtoDTO.setDataAlteracao(dateFormat.parse(data));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            produtoDTO.setAtivo(1);

            String condicao = produtoBusiness.cadastrarProduto(produtoDTO, "alteraProduto");

            if ("sucesso".equalsIgnoreCase(condicao)) {
                req.getSession().setAttribute("condicao", condicao);
                req.getSession().setAttribute("alteraForm", "nao");
                req.getSession().setAttribute("produtoBusca", null);
            } else {
                req.getSession().setAttribute("condicao", condicao);
                req.getSession().setAttribute("tipoCadastro", "produto");
                req.getRequestDispatcher("WEB-INF/cadastro/erroCadastro.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("cliente".equalsIgnoreCase(tipo)) {
            UfDAO ufDAO = new UfDAOImpl();
            List<UfDTO> listaUfs = ufDAO.buscarListaUfs();
            req.getSession().setAttribute("condicao", "");
            req.getSession().setAttribute("listaUfs", listaUfs);
            req.getRequestDispatcher("WEB-INF/cadastro/clienteCadastro.jsp").forward(req, resp);
        }

        if ("produto".equalsIgnoreCase(tipo)) {
            req.getSession().setAttribute("condicao", "");
            req.getSession().setAttribute("alteraForm", "nao");
            req.getSession().setAttribute("produtoBusca", null);
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }

        if ("alteraProduto".equalsIgnoreCase(tipo)) {
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            String nomeProduto = req.getParameter("nomeProduto");
            ProdutoDTO produtoBusca = produtoDAO.buscarProdutoPorNome(nomeProduto);
            req.getSession().setAttribute("produtoBusca", produtoBusca);
            req.getSession().setAttribute("condicao", "");
            req.getSession().setAttribute("alteraForm", "sim");
            req.getRequestDispatcher("WEB-INF/cadastro/produtoCadastro.jsp").forward(req, resp);
        }
    }
}
