package br.edu.fapi.dkrt.servlets.cadastro;

import br.edu.fapi.dkrt.dao.uf.UfDAO;
import br.edu.fapi.dkrt.dao.uf.impl.UfDAOImpl;
import br.edu.fapi.dkrt.model.cliente.ClienteDTO;
import br.edu.fapi.dkrt.model.endereco.EnderecoDTO;
import br.edu.fapi.dkrt.model.uf.UfDTO;
import br.edu.fapi.dkrt.services.cadastro.CadastroClienteService;

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
            CadastroClienteService cadastroClienteService = new CadastroClienteService();
            String nome = req.getParameter("nomeCliente");
            String cpf = req.getParameter("cpfCliente");
            String dtNasc = req.getParameter("dtNascCliente");
            String telefone = req.getParameter("telefoneCliente");
            String cepEndereco = req.getParameter("cepEnderecoCliente");
            String ruaEndereco = req.getParameter("ruaEnderecoCliente");
            String complementoEndereco = req.getParameter("complementoEnderecoCliente");
            String numeroEndereco = req.getParameter("numeroEnderecoCliente");
            String bairroEndereco = req.getParameter("bairroEnderecoCliente");
            String cidadeEndereco = req.getParameter("cidadeEnderecoCliente");
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
            clienteDTO.setCpf(cpf);
            clienteDTO.setAtivo(true);
            String hoje = dateFormat.format(new Date());
            try {
                clienteDTO.setDataCadastroCliente(dateFormat.parse(hoje));
                clienteDTO.setDtNascimento(dateFormat.parse(dtNasc));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            clienteDTO.setTelefone(telefone);
            clienteDTO.setEnderecoDTO(enderecoDTO);

            String condicao = cadastroClienteService.cadastrarCliente(clienteDTO);

            if ("sucesso".equalsIgnoreCase(condicao)) {
                req.getRequestDispatcher("WEB-INF/cadastro/clienteCadastro.jsp").forward(req, resp);
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tipo = req.getParameter("tipo");

        if ("cliente".equalsIgnoreCase(tipo)) {
            UfDAO ufDAO = new UfDAOImpl();
            List<UfDTO> listaUfs = ufDAO.buscarListaUfs();
            req.getSession().setAttribute("listaUfs", listaUfs);
            req.getRequestDispatcher("WEB-INF/cadastro/clienteCadastro.jsp").forward(req, resp);
        }
    }
}
