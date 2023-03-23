-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Мар 23 2023 г., 16:38
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `deliveries`
--

-- --------------------------------------------------------

--
-- Структура таблицы `logins`
--

CREATE TABLE IF NOT EXISTS `logins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Application` varchar(50) DEFAULT NULL,
  `AppAccountName` varchar(50) DEFAULT NULL,
  `IsActive` tinyint(1) DEFAULT NULL,
  `JobTitle` varchar(50) DEFAULT NULL,
  `Department` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `postings`
--

CREATE TABLE IF NOT EXISTS `postings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `MatDoc` double NOT NULL,
  `Item` int(11) DEFAULT NULL,
  `DocDate` date DEFAULT NULL,
  `PstngDate` date DEFAULT NULL,
  `MaterialDescription` varchar(50) DEFAULT NULL,
  `Quantity` int(11) NOT NULL,
  `BUn` varchar(50) DEFAULT NULL,
  `AmountLC` double DEFAULT NULL,
  `Crcy` varchar(50) DEFAULT NULL,
  `UserName` varchar(50) DEFAULT NULL,
  `authDelivery` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
