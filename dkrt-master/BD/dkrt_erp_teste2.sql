-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 28-Nov-2018 às 13:41
-- Versão do servidor: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dkrt_erp_teste2`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE `clientes` (
  `cliente_id` int(11) NOT NULL,
  `cliente_nome` varchar(50) NOT NULL,
  `cliente_nomeSocial` varchar(50) DEFAULT NULL,
  `cliente_rg` varchar(15) NOT NULL,
  `cliente_cpf` varchar(15) NOT NULL,
  `cliente_dtNasc` date NOT NULL,
  `cliente_email` varchar(50) NOT NULL,
  `cliente_celular` varchar(15) NOT NULL,
  `cliente_telefone` varchar(15) DEFAULT NULL,
  `cliente_ativo` int(1) NOT NULL,
  `cliente_dataDeCadastro` date NOT NULL,
  `cliente_dataDeAlteracao` date DEFAULT NULL,
  `cliente_observacao` varchar(100) DEFAULT NULL,
  `endereco_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `clientes`
--

INSERT INTO `clientes` (`cliente_id`, `cliente_nome`, `cliente_nomeSocial`, `cliente_rg`, `cliente_cpf`, `cliente_dtNasc`, `cliente_email`, `cliente_celular`, `cliente_telefone`, `cliente_ativo`, `cliente_dataDeCadastro`, `cliente_dataDeAlteracao`, `cliente_observacao`, `endereco_id`) VALUES
(3, 'Tiago Cesar', NULL, '963738261', '84637281539', '1996-11-18', 'tiago.cesar@gmail.com', '98535283', '', 1, '2018-11-23', NULL, '', 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `enderecos`
--

CREATE TABLE `enderecos` (
  `endereco_id` int(11) NOT NULL,
  `endereco_cep` varchar(15) NOT NULL,
  `endereco_rua` varchar(50) NOT NULL,
  `endereco_numero` varchar(10) NOT NULL,
  `endereco_complemento` varchar(50) DEFAULT NULL,
  `endereco_bairro` varchar(50) NOT NULL,
  `endereco_cidade` varchar(50) NOT NULL,
  `uf_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `enderecos`
--

INSERT INTO `enderecos` (`endereco_id`, `endereco_cep`, `endereco_rua`, `endereco_numero`, `endereco_complemento`, `endereco_bairro`, `endereco_cidade`, `uf_id`) VALUES
(6, '73642836', 'Rua das Flores', '289', '', 'Jardim das Flores', 'Curitiba', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamentos`
--

CREATE TABLE `orcamentos` (
  `orcamento_id` int(11) NOT NULL,
  `orcamento_valorTotal` float DEFAULT NULL,
  `orcamento_status` varchar(50) NOT NULL,
  `orcamento_desconto` float DEFAULT NULL,
  `orcamento_dataGeracao` timestamp NULL DEFAULT NULL,
  `orcamento_dataExpiracao` date DEFAULT NULL,
  `cliente_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `orcamentos`
--

INSERT INTO `orcamentos` (`orcamento_id`, `orcamento_valorTotal`, `orcamento_status`, `orcamento_desconto`, `orcamento_dataGeracao`, `orcamento_dataExpiracao`, `cliente_id`) VALUES
(14, 5440, 'Completo', 20, '2018-11-28 11:31:09', '2018-11-30', 3),
(15, 8160, 'Completo', 20, '2018-11-28 11:39:16', '2018-11-30', 3),
(16, 8400, 'Vendido', 20, '2018-11-28 12:02:57', '2018-11-30', 3),
(17, NULL, 'Incompleto', NULL, '2018-11-28 12:29:42', NULL, 3),
(18, NULL, 'Incompleto', NULL, '2018-11-28 12:31:14', NULL, 3),
(19, 600, 'Completo', 0, '2018-11-28 12:33:50', NULL, 3),
(20, NULL, 'Incompleto', NULL, '2018-11-28 12:34:41', NULL, 3),
(21, NULL, 'Incompleto', NULL, '2018-11-28 12:37:57', NULL, 3),
(22, 600, 'Completo', 0, '2018-11-28 12:41:18', NULL, 3);

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `pedido_id` int(11) NOT NULL,
  `produto_id` int(11) NOT NULL,
  `pedido_quantidade` int(11) NOT NULL,
  `pedido_valorUnitario` float NOT NULL,
  `pedido_valorTotal` float NOT NULL,
  `venda_id` int(11) DEFAULT NULL,
  `orcamento_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `pedidos`
--

INSERT INTO `pedidos` (`pedido_id`, `produto_id`, `pedido_quantidade`, `pedido_valorUnitario`, `pedido_valorTotal`, `venda_id`, `orcamento_id`) VALUES
(25, 5, 200, 2, 400, 1, 14),
(26, 7, 200, 32, 6400, 1, 14),
(27, 5, 300, 2, 600, 2, 15),
(28, 7, 300, 32, 9600, 2, 15),
(29, 5, 200, 2, 400, 3, 16),
(30, 6, 250, 2, 500, 3, 16),
(31, 7, 300, 32, 9600, 3, 16),
(32, 5, 200, 2, 400, NULL, 17),
(33, 5, 200, 2, 400, NULL, 18),
(34, 5, 300, 2, 600, NULL, 19),
(35, 5, 300, 2, 600, NULL, 21),
(36, 5, 300, 2, 600, NULL, 22);

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `produto_id` int(11) NOT NULL,
  `produto_nome` varchar(50) NOT NULL,
  `produto_descricao` varchar(100) DEFAULT NULL,
  `produto_precoVenda` float NOT NULL,
  `produto_precoCusto` float NOT NULL,
  `produto_qtdEstoque` int(11) NOT NULL,
  `produto_ativo` int(2) NOT NULL,
  `produto_dataCadastro` date NOT NULL,
  `produto_dataAlteracao` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`produto_id`, `produto_nome`, `produto_descricao`, `produto_precoVenda`, `produto_precoCusto`, `produto_qtdEstoque`, `produto_ativo`, `produto_dataCadastro`, `produto_dataAlteracao`) VALUES
(5, 'Caneta BIC Preta', 'Ponta Fina', 2, 1, 300, 1, '2018-11-23', NULL),
(6, 'Caneta BIC Azul', 'Ponta Fina', 2, 1, 250, 1, '2018-11-23', NULL),
(7, 'Caderno Hot Wheels', '10 Materias', 32, 15, 200, 1, '2018-11-23', NULL),
(8, 'Caderno Barbie', '20 Materias', 45.99, 35.99, 500, 1, '2018-11-25', '2018-11-28');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ufs`
--

CREATE TABLE `ufs` (
  `uf_id` int(11) NOT NULL,
  `uf_sigla` varchar(3) NOT NULL,
  `uf_nome` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `ufs`
--

INSERT INTO `ufs` (`uf_id`, `uf_sigla`, `uf_nome`) VALUES
(1, 'PR', 'Paraná'),
(2, 'SC', 'Santa Catarina'),
(5, 'MS', 'Mato Grosso do Sul'),
(7, 'RJ', 'Rio de janeiro'),
(8, 'AC', 'Acre'),
(9, 'AL', 'Alagoas'),
(10, 'AP', 'Amapa'),
(11, 'AM', 'Amazonas'),
(12, 'BA', 'Bahia'),
(13, 'CE', 'Ceara'),
(14, 'DF', 'Distrito Federal'),
(15, 'ES', 'Espirito Santo'),
(16, 'GO', 'Goias'),
(17, 'MA', 'Maranhao'),
(18, 'MT', 'Mato Grosso'),
(19, 'MG', 'Minas Gerais'),
(20, 'PA', 'Para'),
(21, 'PB', 'Paraiba'),
(22, 'PE', 'Pernambuco'),
(23, 'PI', 'Piaui'),
(24, 'RN', 'Rio Grande do Norte'),
(25, 'RS', 'Rio Grande do Sul'),
(26, 'RO', 'Rondonia'),
(27, 'SP', 'Sao Paulo'),
(28, 'SE', 'Sergipe'),
(29, 'TO', 'Tocantins'),
(30, 'N/A', 'N/A');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `usuario_id` int(11) NOT NULL,
  `usuario_login` varchar(50) NOT NULL,
  `usuario_senha` varchar(50) NOT NULL,
  `usuario_funcao` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`usuario_id`, `usuario_login`, `usuario_senha`, `usuario_funcao`) VALUES
(1, 'admin', 'admin', 'Administrador');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

CREATE TABLE `vendas` (
  `venda_id` int(11) NOT NULL,
  `venda_valorTotal` float DEFAULT NULL,
  `venda_formaDePagamento` varchar(50) DEFAULT NULL,
  `venda_parcelas` int(11) DEFAULT NULL,
  `venda_valorParcela` float DEFAULT NULL,
  `venda_status` varchar(50) NOT NULL,
  `venda_desconto` float DEFAULT NULL,
  `venda_dataDeVenda` timestamp NULL DEFAULT NULL,
  `venda_motivoCancelamento` varchar(500) DEFAULT NULL,
  `venda_vendaOrcamento` varchar(5) DEFAULT NULL,
  `cliente_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `vendas`
--

INSERT INTO `vendas` (`venda_id`, `venda_valorTotal`, `venda_formaDePagamento`, `venda_parcelas`, `venda_valorParcela`, `venda_status`, `venda_desconto`, `venda_dataDeVenda`, `venda_motivoCancelamento`, `venda_vendaOrcamento`, `cliente_id`) VALUES
(1, 5440, 'Debito', 1, 5440, 'Completa', 20, '2018-11-28 11:32:18', 'A venda está incompleta pois foi encerrada de forma inesperada', NULL, 3),
(2, 8160, 'Credito', 5, 1632, 'Completa', 20, '2018-11-28 11:40:07', 'A venda está incompleta pois foi encerrada de forma inesperada', NULL, 3),
(3, 8400, 'Debito', 1, 8400, 'Completa', 20, '2018-11-28 12:04:09', 'A venda está incompleta pois foi encerrada de forma inesperada', 'Sim', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`cliente_id`),
  ADD KEY `endereco_id` (`endereco_id`);

--
-- Indexes for table `enderecos`
--
ALTER TABLE `enderecos`
  ADD PRIMARY KEY (`endereco_id`),
  ADD KEY `uf_id` (`uf_id`);

--
-- Indexes for table `orcamentos`
--
ALTER TABLE `orcamentos`
  ADD PRIMARY KEY (`orcamento_id`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- Indexes for table `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`pedido_id`),
  ADD KEY `venda_id` (`venda_id`),
  ADD KEY `orcamento_id` (`orcamento_id`),
  ADD KEY `produto_id` (`produto_id`);

--
-- Indexes for table `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`produto_id`);

--
-- Indexes for table `ufs`
--
ALTER TABLE `ufs`
  ADD PRIMARY KEY (`uf_id`);

--
-- Indexes for table `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`usuario_id`);

--
-- Indexes for table `vendas`
--
ALTER TABLE `vendas`
  ADD PRIMARY KEY (`venda_id`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `cliente_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `enderecos`
--
ALTER TABLE `enderecos`
  MODIFY `endereco_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `orcamentos`
--
ALTER TABLE `orcamentos`
  MODIFY `orcamento_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `pedido_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `produto_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ufs`
--
ALTER TABLE `ufs`
  MODIFY `uf_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `vendas`
--
ALTER TABLE `vendas`
  MODIFY `venda_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`endereco_id`) REFERENCES `enderecos` (`endereco_id`);

--
-- Limitadores para a tabela `enderecos`
--
ALTER TABLE `enderecos`
  ADD CONSTRAINT `enderecos_ibfk_1` FOREIGN KEY (`uf_id`) REFERENCES `ufs` (`uf_id`);

--
-- Limitadores para a tabela `orcamentos`
--
ALTER TABLE `orcamentos`
  ADD CONSTRAINT `orcamentos_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`);

--
-- Limitadores para a tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`venda_id`) REFERENCES `vendas` (`venda_id`),
  ADD CONSTRAINT `pedidos_ibfk_3` FOREIGN KEY (`orcamento_id`) REFERENCES `orcamentos` (`orcamento_id`),
  ADD CONSTRAINT `pedidos_ibfk_4` FOREIGN KEY (`produto_id`) REFERENCES `produtos` (`produto_id`);

--
-- Limitadores para a tabela `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
