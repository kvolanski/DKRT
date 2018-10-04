-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 04-Out-2018 às 21:29
-- Versão do servidor: 10.1.36-MariaDB
-- versão do PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dkrt_erp`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE `cliente` (
  `cliente_id` int(11) NOT NULL,
  `cliente_nome` varchar(30) NOT NULL,
  `cliente_nomeSocial` varchar(30) NOT NULL,
  `cliente_cpf` varchar(14) NOT NULL,
  `cliente_telefone` varchar(14) NOT NULL,
  `cliente_ativo` tinyint(1) NOT NULL,
  `cliente_dataCadastroCliente` date NOT NULL,
  `cliente_dataAlteracaoCliente` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE `endereco` (
  `endereco_id` int(11) NOT NULL,
  `endereco_cep` varchar(30) NOT NULL,
  `endereco_rua` varchar(50) NOT NULL,
  `endereco_numero` varchar(20) NOT NULL,
  `endereco_complemento` varchar(30) NOT NULL,
  `endereco_bairro` varchar(30) NOT NULL,
  `endereco_cidade` varchar(30) NOT NULL,
  `endereco_estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `produto_id` int(11) NOT NULL,
  `produto_nome` varchar(30) NOT NULL,
  `produto_descricao` varchar(50) NOT NULL,
  `produto_preco` double NOT NULL,
  `produto_dataCadastroProduto` date NOT NULL,
  `produto_dataAlteracaoProduto` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL,
  `usuario_email` varchar(30) NOT NULL,
  `usuario_senha` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cliente_id`);

--
-- Indexes for table `endereco`
--
ALTER TABLE `endereco`
  ADD PRIMARY KEY (`endereco_id`);

--
-- Indexes for table `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`produto_id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cliente_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `endereco`
--
ALTER TABLE `endereco`
  MODIFY `endereco_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `produto`
--
ALTER TABLE `produto`
  MODIFY `produto_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `usuario_id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
