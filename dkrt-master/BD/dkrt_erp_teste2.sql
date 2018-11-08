-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 08-Nov-2018 às 05:51
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
(4, '12345678', 'Rua Teste', '123', '', 'Bairro Teste', 'Cidade Teste', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `orcamentos`
--

CREATE TABLE `orcamentos` (
  `orcamento_id` int(11) NOT NULL,
  `orcamento_valorTotal` float NOT NULL,
  `orcamento_status` varchar(50) NOT NULL,
  `orcamento_desconto` float NOT NULL,
  `orcamento_dataGeracao` date NOT NULL,
  `orcamento_dataExpiracao` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `pedidos`
--

CREATE TABLE `pedidos` (
  `pedido_id` int(11) NOT NULL,
  `pedido_nomeProduto` varchar(50) NOT NULL,
  `pedido_quantidade` int(11) NOT NULL,
  `pedido_valorUnitario` float NOT NULL,
  `pedido_valorTotal` float NOT NULL,
  `venda_id` int(11) NOT NULL,
  `orcamento_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(3, 'teste', 'testeteste', 3, 3, 3, 1, '2018-11-08', '2018-11-08');

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
  `venda_valorTotal` float NOT NULL,
  `venda_formaDePagamento` varchar(50) NOT NULL,
  `venda_parcelas` int(11) NOT NULL,
  `venda_status` varchar(50) NOT NULL,
  `venda_desconto` float NOT NULL,
  `venda_dataDeVenda` date NOT NULL,
  `cliente_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD PRIMARY KEY (`orcamento_id`);

--
-- Indexes for table `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`pedido_id`),
  ADD KEY `venda_id` (`venda_id`),
  ADD KEY `orcamento_id` (`orcamento_id`);

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
  MODIFY `cliente_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `enderecos`
--
ALTER TABLE `enderecos`
  MODIFY `endereco_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `orcamentos`
--
ALTER TABLE `orcamentos`
  MODIFY `orcamento_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `pedido_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produtos`
--
ALTER TABLE `produtos`
  MODIFY `produto_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
  MODIFY `venda_id` int(11) NOT NULL AUTO_INCREMENT;

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
-- Limitadores para a tabela `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`venda_id`) REFERENCES `vendas` (`venda_id`),
  ADD CONSTRAINT `pedidos_ibfk_3` FOREIGN KEY (`orcamento_id`) REFERENCES `orcamentos` (`orcamento_id`);

--
-- Limitadores para a tabela `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `vendas_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
