package br.edu.fapi.dkrt.servlets.controller;

import br.edu.fapi.dkrt.model.usuario.UsuarioDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if ("login".equalsIgnoreCase(acao)) {
            String usuario = req.getParameter("usuario");
            String senha = req.getParameter("senha");

            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setEmail(usuario);
            usuarioDTO.setSenha(senha);
            req.getSession().setAttribute("usuario", usuarioDTO);
            req.getRequestDispatcher("login").forward(req, resp);
        }

        if ("excluir".equalsIgnoreCase(acao)) {
            req.getRequestDispatcher("/excluir").forward(req, resp);
        }

        if ("cadastro".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("cliente".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=cliente").forward(req, resp);
            } else if ("produto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=produto").forward(req, resp);
            } else if ("alteraProduto".equalsIgnoreCase(tipo)) {
                String idProduto = req.getParameter("idProduto");
                req.getRequestDispatcher("cadastro?tipo=alteraProduto&idProduto=" + idProduto).forward(req, resp);
            }
        }

        if ("venda".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("buscaCliente".equalsIgnoreCase(tipo)) {
                String idCliente = req.getParameter("idCliente");
                req.getRequestDispatcher("venda?tipo=buscaCliente&id=" + idCliente).forward(req, resp);
            } else if ("buscaProduto".equalsIgnoreCase(tipo)) {
                String idProduto = req.getParameter("idProduto");
                req.getRequestDispatcher("venda?tipo=buscaProduto&idProduto=" + idProduto).forward(req, resp);
            } else if ("adicionarPedido".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=adicionarPedido").forward(req, resp);
            } else if ("finalizarVenda".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=finalizarVenda").forward(req, resp);
            } else if ("cancelamentoMotivo".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=cancelamentoVenda").forward(req, resp);
            }
        }

        if ("estoque".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("editarProduto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("estoque?tipo=editarProduto").forward(req, resp);
            }
        }

        if ("orcamento".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("adicionarPedidoOrcamento".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=adicionarPedidoOrcamento").forward(req, resp);
            } else if ("finalizarOrcamento".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=finalizarOrcamento").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        if ("cadastro".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("produto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=produto").forward(req, resp);
            } else if ("cliente".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("cadastro?tipo=cliente").forward(req, resp);
            } else if ("alteraProduto".equalsIgnoreCase(tipo)) {
                String nomeProduto = req.getParameter("nomeProduto");
                req.getRequestDispatcher("cadastro?tipo=alteraProduto&nomeProduto=" + nomeProduto).forward(req, resp);
            }
        }

        if ("venda".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("efetuar".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=efetuar").forward(req, resp);
            } else if ("buscaProduto".equalsIgnoreCase(tipo)) {
                String idProduto = req.getParameter("idProduto");
                req.getRequestDispatcher("venda?tipo=buscaProduto&idProduto=" + idProduto).forward(req, resp);
            } else if ("abrirVenda".equalsIgnoreCase(tipo)) {
                String idCliente = req.getParameter("idCliente");
                req.getRequestDispatcher("venda?tipo=abrirVenda&id=" + idCliente).forward(req, resp);
            } else if ("finalizarVenda".equalsIgnoreCase(tipo)) {
                String statusAberto = req.getParameter("statusAberto");
                if ("emAberto".equalsIgnoreCase(statusAberto)) {
                    req.getRequestDispatcher("venda?tipo=vendaEmAberto").forward(req, resp);
                } else {
                    req.getRequestDispatcher("venda?tipo=finalizarVenda").forward(req, resp);
                }
            } else if ("listarVendas".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=listarVendas").forward(req, resp);
            } else if ("buscaVenda".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=buscaVenda").forward(req, resp);
            } else if ("finalizarVendaEmAberto".equalsIgnoreCase(tipo)) {
                String id = req.getParameter("id");
                req.getSession().setAttribute("idVenda", Integer.parseInt(id));
                req.getRequestDispatcher("venda?tipo=finalizarVenda").forward(req, resp);
            } else if ("cancelamentoMotivo".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=cancelamentoVenda").forward(req, resp);
            } else if ("cancelarVenda".equalsIgnoreCase(tipo)) {
                String id = req.getParameter("id");
                req.getSession().setAttribute("idVenda", Integer.parseInt(id));
                req.getRequestDispatcher("venda?tipo=cancelarVenda").forward(req, resp);
            } else if ("adicionarProdutosEmAberto".equalsIgnoreCase(tipo)) {
                String id = req.getParameter("id");
                req.getSession().setAttribute("idVenda", Integer.parseInt(id));
                req.getRequestDispatcher("venda?tipo=adicionarProdutosEmAberto").forward(req, resp);
            } else if ("tirarProdutoLista".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=tirarProdutoLista").forward(req, resp);
            } else if ("listarVendasCanceladas".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("venda?tipo=listarVendasCanceladas").forward(req, resp);
            }
        }

        if ("orcamento".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("iniciarOrcamento".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=iniciarOrcamento").forward(req, resp);
            } else if ("comecarOrcamento".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=comecoOrcamento").forward(req, resp);
            } else if ("finalizarOrcamento".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=finalizarOrcamento").forward(req, resp);
            } else if ("buscaProduto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=buscarProduto").forward(req, resp);
            } else if ("tirarProdutoLista".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=tirarProdutoLista").forward(req, resp);
            } else if ("listarOrcamentos".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=listarOrcamentos").forward(req, resp);
            } else if ("excluirOrcamento".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("orcamento?tipo=excluirOrcamento").forward(req, resp);
            } else if ("comecarVendaOrcamento".equalsIgnoreCase(tipo)){
                req.getRequestDispatcher("orcamento?tipo=comecarVendaOrcamento").forward(req, resp);
            }
        }

        if ("estoque".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("listarEstoque".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("estoque?tipo=listarEstoque").forward(req, resp);
            } else if ("alteraProduto".equalsIgnoreCase(tipo)) {
                String idProduto = req.getParameter("idProduto");
                req.getRequestDispatcher("estoque?tipo=alteraProduto&idProduto=" + idProduto).forward(req, resp);
            }
        }

        if ("relatorio".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("venda".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("relatorio?tipo=venda").forward(req, resp);
            } else if ("estoque".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("relatorio?tipo=estoque").forward(req, resp);
            }
        }

        if ("pesquisa".equalsIgnoreCase(acao)) {
            String tipo = req.getParameter("tipo");
            if ("cliente".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("pesquisa?tipo=cliente").forward(req, resp);
            } else if ("produto".equalsIgnoreCase(tipo)) {
                req.getRequestDispatcher("pesquisa?tipo=produto").forward(req, resp);
            }
        }
    }
}

