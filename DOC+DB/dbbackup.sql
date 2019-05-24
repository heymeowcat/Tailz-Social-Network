-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2019 at 06:54 AM
-- Server version: 10.1.33-MariaDB
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
-- Database: `sexuafqh92`
--

-- --------------------------------------------------------

--
-- Table structure for table `ads`
--

CREATE TABLE `ads` (
  `Adid` int(11) NOT NULL,
  `src` text,
  `link` text,
  `adcategory` int(11) NOT NULL DEFAULT '1',
  `users_idusers` int(11) NOT NULL,
  `forhowmanyusers` int(11) DEFAULT NULL,
  `forhowmanyhours` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ads`
--

INSERT INTO `ads` (`Adid`, `src`, `link`, `adcategory`, `users_idusers`, `forhowmanyusers`, `forhowmanyhours`, `status`) VALUES
(1, 'https://i.imgur.com/UoLIpQB.gif', 'http://aliexpress.com', 6, 1, 8, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `adtiming`
--

CREATE TABLE `adtiming` (
  `Ads_Adid` int(11) NOT NULL,
  `adstartedtime` timestamp NULL DEFAULT NULL,
  `adendtime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `adtiming`
--

INSERT INTO `adtiming` (`Ads_Adid`, `adstartedtime`, `adendtime`) VALUES
(1, '2019-05-06 09:50:59', '2019-05-06 10:50:59');

-- --------------------------------------------------------

--
-- Table structure for table `ad_categories`
--

CREATE TABLE `ad_categories` (
  `idAd_category` int(11) NOT NULL,
  `Ad_category_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ad_categories`
--

INSERT INTO `ad_categories` (`idAd_category`, `Ad_category_name`) VALUES
(1, 'All'),
(2, 'Food'),
(3, 'Financial'),
(4, 'Work'),
(5, 'Politics'),
(6, 'Shopping & Fashion'),
(7, 'Life Events');

-- --------------------------------------------------------

--
-- Table structure for table `ad_status`
--

CREATE TABLE `ad_status` (
  `idAd_status` int(11) NOT NULL,
  `status_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ad_status`
--

INSERT INTO `ad_status` (`idAd_status`, `status_name`) VALUES
(1, 'purchased'),
(2, 'incompletead'),
(3, 'complete'),
(4, 'live'),
(5, 'paused'),
(6, 'expired');

-- --------------------------------------------------------

--
-- Table structure for table `apphpi`
--

CREATE TABLE `apphpi` (
  `idAPPHPI` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `apphpi`
--

INSERT INTO `apphpi` (`idAPPHPI`) VALUES
(10);

-- --------------------------------------------------------

--
-- Table structure for table `app_layout`
--

CREATE TABLE `app_layout` (
  `users_idusers` int(11) NOT NULL,
  `layout` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_layout`
--

INSERT INTO `app_layout` (`users_idusers`, `layout`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(8, 1),
(12, 1),
(13, 1),
(15, 1),
(20, 1),
(21, 1),
(22, 1);

-- --------------------------------------------------------

--
-- Table structure for table `app_theme`
--

CREATE TABLE `app_theme` (
  `idapp_theme` int(11) NOT NULL,
  `themename` varchar(45) DEFAULT NULL,
  `users_idusers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `app_theme`
--

INSERT INTO `app_theme` (`idapp_theme`, `themename`, `users_idusers`) VALUES
(1, 'purplelight', 1),
(2, 'pinklight', 2),
(3, 'greendark', 3),
(4, 'yellowdark', 4),
(5, 'bluelight', 5),
(6, 'purplelight', 6),
(7, 'greenlight', 8),
(8, 'purplelight', 12),
(9, 'purplelight', 13),
(10, 'greenlight', 15),
(11, 'purpledark', 20),
(12, 'purplelight', 21),
(13, 'purplelight', 22);

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `idchat` int(11) NOT NULL,
  `chat_text` text CHARACTER SET utf8mb4,
  `src` text,
  `chat_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_sender` int(11) NOT NULL,
  `users_receiver` int(11) NOT NULL,
  `chattype_idchattype` int(11) NOT NULL,
  `chatlinestatus` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`idchat`, `chat_text`, `src`, `chat_datetime`, `user_sender`, `users_receiver`, `chattype_idchattype`, `chatlinestatus`) VALUES
(6, 'jO67DXxqVuxYF0cghs3GLw==', NULL, '2019-05-06 01:00:12', 1, 3, 1, 1),
(7, 'XPFF6Y2azF3SsyorpqWqeA==', NULL, '2019-05-06 01:00:15', 1, 3, 1, 1),
(8, 'BiQK6NJwwJ669CIMVALOSQ==', NULL, '2019-05-06 01:00:22', 3, 1, 1, 1),
(9, 'jO67DXxqVuxYF0cghs3GLw==', NULL, '2019-05-06 01:00:25', 3, 1, 1, 1),
(10, NULL, 'up9FOZDMep6WREyY8wHEWyJyCFqVpDLIhTM7bNIOJrs=', '2019-05-06 01:00:34', 3, 1, 1, 1),
(11, 'laIw+Wzd3doadpNc9jiL9Q==', NULL, '2019-05-06 01:00:50', 1, 3, 1, 1),
(12, 'DppnEl2XMhmI0BL1i7SIcg==', NULL, '2019-05-06 01:01:08', 1, 4, 1, 1),
(13, 'jO67DXxqVuxYF0cghs3GLw==', NULL, '2019-05-06 01:01:14', 1, 4, 1, 1),
(14, 'DKF4yOL3Br8gTyoEP5CUlQ==', NULL, '2019-05-06 01:01:16', 1, 4, 1, 1),
(15, 'HzbaPWi5mPSXJtQZYK35/g==', NULL, '2019-05-06 01:01:33', 4, 1, 1, 1),
(16, 'jZXra5C2tmqkvg3xaNEKAQ==', NULL, '2019-05-06 01:03:33', 1, 3, 1, 1),
(17, '0F7U2uNcHfBWcJeHlD3updILPwCxCpHeZ8PzMDitkWU=', NULL, '2019-05-06 06:56:12', 1, 1, 1, 1),
(18, 'zJBifMUBY0p63mK0q/V+CA==', NULL, '2019-05-06 09:37:05', 8, 1, 1, 1),
(19, 'DppnEl2XMhmI0BL1i7SIcg==', NULL, '2019-05-06 09:37:19', 1, 8, 1, 1),
(20, NULL, 'up9FOZDMep6WREyY8wHEW1Q3PD8bsgte5Q4O5kNVGz8=', '2019-05-06 09:37:36', 1, 8, 1, 1),
(21, '0gs/ALEKkd5nw/MwOK2RZQ==', NULL, '2019-05-06 09:40:00', 8, 8, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `chattype`
--

CREATE TABLE `chattype` (
  `idchattype` int(11) NOT NULL,
  `types` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chattype`
--

INSERT INTO `chattype` (`idchattype`, `types`) VALUES
(1, 'direct');

-- --------------------------------------------------------

--
-- Table structure for table `follow`
--

CREATE TABLE `follow` (
  `idfollow` int(11) NOT NULL,
  `followon` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `follow`
--

INSERT INTO `follow` (`idfollow`, `followon`, `sender`, `receiver`) VALUES
(15, '2019-03-24 03:59:26', 3, 2),
(21, '2019-04-11 10:58:39', 1, 3),
(22, '2019-04-11 15:12:42', 15, 3),
(25, '2019-04-11 23:21:26', 15, 1),
(26, '2019-04-11 23:23:24', 1, 15),
(28, '2019-04-12 00:11:43', 15, 2),
(30, '2019-04-14 13:06:11', 2, 3),
(31, '2019-04-14 13:07:00', 2, 15),
(40, '2019-04-28 03:15:23', 2, 1),
(41, '2019-05-06 06:20:54', 1, 13),
(42, '2019-05-06 06:21:12', 1, 4),
(43, '2019-05-06 06:21:44', 13, 1),
(44, '2019-05-06 06:21:46', 13, 3),
(45, '2019-05-06 06:21:47', 13, 15),
(46, '2019-05-06 06:21:47', 13, 2),
(47, '2019-05-06 06:21:48', 13, 4),
(48, '2019-05-06 06:22:02', 15, 13),
(49, '2019-05-06 06:22:03', 15, 4),
(50, '2019-05-06 06:22:17', 4, 1),
(51, '2019-05-06 06:22:19', 4, 3),
(55, '2019-05-06 06:22:35', 2, 13),
(56, '2019-05-06 06:22:36', 2, 4),
(57, '2019-05-06 06:25:25', 1, 6),
(58, '2019-05-06 06:25:30', 6, 1),
(59, '2019-05-06 06:29:01', 8, 1),
(60, '2019-05-06 06:29:17', 1, 8),
(61, '2019-05-06 06:29:59', 3, 1),
(62, '2019-05-06 06:32:39', 12, 1),
(63, '2019-05-06 06:32:51', 1, 12),
(64, '2019-05-06 06:34:55', 1, 22),
(65, '2019-05-06 06:35:51', 22, 1),
(66, '2019-05-06 06:43:44', 5, 1),
(67, '2019-05-06 06:43:52', 1, 5),
(68, '2019-05-06 15:01:17', 1, 2),
(69, '2019-05-06 15:16:00', 2, 8),
(70, '2019-05-06 15:17:44', 8, 3);

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `group_id` varchar(100) NOT NULL,
  `groupname` text,
  `groupimg` text,
  `groupadmin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groups`
--

INSERT INTO `groups` (`group_id`, `groupname`, `groupimg`, `groupadmin`) VALUES
('6efc7e19-c0cc-45d8-9b21-4b5b2c246b7b', 'SPecial GRoup', 'https://i.imgur.com/Hlq8fDx.jpg', 1),
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'VIVEX GROUP', 'https://i.imgur.com/m7en4h0.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `group_chat`
--

CREATE TABLE `group_chat` (
  `Groups_group_id` varchar(100) NOT NULL,
  `chat_text` text,
  `src` text,
  `chat_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `chatstatus` int(11) DEFAULT NULL,
  `users_idusers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_chat`
--

INSERT INTO `group_chat` (`Groups_group_id`, `chat_text`, `src`, `chat_datetime`, `chatstatus`, `users_idusers`) VALUES
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'HD3qxNTemc7mzWmQb9FZRGtQMRYmdEv9tVHFYU55XNc=', NULL, '2019-05-06 01:14:24', 0, 5),
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'DppnEl2XMhmI0BL1i7SIcg==', NULL, '2019-05-06 01:14:32', 0, 1),
('f2679591-0137-4f6c-b922-1a4231c3fb82', NULL, 'up9FOZDMep6WREyY8wHEW1atQG8zSvT9K7WgkorxFgI=', '2019-05-06 01:18:13', 0, 2),
('f2679591-0137-4f6c-b922-1a4231c3fb82', '05W9Q+hDQMapVGeCnr9igA==', NULL, '2019-05-06 01:18:21', 0, 5),
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'fwokBrPk6YKffAzbYjd2eUp433bnprDE38SLYsB8HUY=', NULL, '2019-05-06 01:18:58', 0, 3),
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'Zyv92QY3QDh3eK/u8yWXiw==', NULL, '2019-05-06 01:19:07', 0, 1),
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'DppnEl2XMhmI0BL1i7SIcg==', NULL, '2019-05-06 09:44:39', 0, 2),
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'DppnEl2XMhmI0BL1i7SIcg==', NULL, '2019-05-06 09:44:44', 0, 3),
('f2679591-0137-4f6c-b922-1a4231c3fb82', 'DppnEl2XMhmI0BL1i7SIcg==', NULL, '2019-05-06 09:44:49', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `group_members`
--

CREATE TABLE `group_members` (
  `idGroup_members` int(11) NOT NULL,
  `Groups_group_id` varchar(100) NOT NULL,
  `members` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `group_members`
--

INSERT INTO `group_members` (`idGroup_members`, `Groups_group_id`, `members`) VALUES
(1, 'f2679591-0137-4f6c-b922-1a4231c3fb82', 1),
(5, '6efc7e19-c0cc-45d8-9b21-4b5b2c246b7b', 1),
(2, 'f2679591-0137-4f6c-b922-1a4231c3fb82', 2),
(3, 'f2679591-0137-4f6c-b922-1a4231c3fb82', 3),
(4, 'f2679591-0137-4f6c-b922-1a4231c3fb82', 5),
(6, 'f2679591-0137-4f6c-b922-1a4231c3fb82', 15);

-- --------------------------------------------------------

--
-- Table structure for table `interfaces`
--

CREATE TABLE `interfaces` (
  `idinterfaces` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `login_sessions`
--

CREATE TABLE `login_sessions` (
  `idlogin_sessions` int(11) NOT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `in_time` timestamp NULL DEFAULT NULL,
  `out_time` timestamp NULL DEFAULT NULL,
  `user_login_iduser_login` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login_sessions`
--

INSERT INTO `login_sessions` (`idlogin_sessions`, `ip_address`, `in_time`, `out_time`, `user_login_iduser_login`) VALUES
(1, '0:0:0:0:0:0:0:1', '2019-03-19 17:12:41', '2019-03-19 18:14:48', 7),
(2, '0:0:0:0:0:0:0:1', '2019-03-19 18:16:25', '2019-03-19 20:34:51', 7),
(3, '0:0:0:0:0:0:0:1', '2019-03-19 20:34:59', NULL, 8),
(4, '0:0:0:0:0:0:0:1', '2019-03-20 04:34:17', NULL, 8),
(5, '0:0:0:0:0:0:0:1', '2019-03-20 05:00:57', '2019-03-20 06:23:06', 8),
(6, '0:0:0:0:0:0:0:1', '2019-03-20 06:23:08', '2019-03-20 06:26:04', 8),
(7, '0:0:0:0:0:0:0:1', '2019-03-20 06:27:05', '2019-03-20 06:27:28', 8),
(8, '0:0:0:0:0:0:0:1', '2019-03-20 06:27:35', '2019-03-20 07:24:28', 8),
(9, '0:0:0:0:0:0:0:1', '2019-03-20 07:24:31', '2019-03-20 07:26:46', 7),
(10, '0:0:0:0:0:0:0:1', '2019-03-20 07:26:49', NULL, 8),
(11, '0:0:0:0:0:0:0:1', '2019-03-20 17:56:40', NULL, 8),
(12, '192.168.1.101', '2019-03-20 22:26:06', NULL, 7),
(13, '192.168.1.101', '2019-03-20 22:40:27', '2019-03-20 22:41:01', 7),
(14, '0:0:0:0:0:0:0:1', '2019-03-20 22:56:40', NULL, 8),
(15, '0:0:0:0:0:0:0:1', '2019-03-20 22:59:32', NULL, 8),
(16, '0:0:0:0:0:0:0:1', '2019-03-20 23:08:02', NULL, 8),
(17, '0:0:0:0:0:0:0:1', '2019-03-20 23:10:14', NULL, 8),
(18, '0:0:0:0:0:0:0:1', '2019-03-21 05:21:23', NULL, 8),
(19, '0:0:0:0:0:0:0:1', '2019-03-21 08:36:51', NULL, 8),
(20, '0:0:0:0:0:0:0:1', '2019-03-21 12:02:59', NULL, 8),
(21, '0:0:0:0:0:0:0:1', '2019-03-21 12:19:08', NULL, 8),
(22, '0:0:0:0:0:0:0:1', '2019-03-21 13:04:41', NULL, 8),
(23, '0:0:0:0:0:0:0:1', '2019-03-21 15:47:58', '2019-03-21 15:48:43', 7),
(24, '0:0:0:0:0:0:0:1', '2019-03-21 16:31:11', NULL, 9),
(25, '0:0:0:0:0:0:0:1', '2019-03-21 16:50:55', NULL, 9),
(26, '0:0:0:0:0:0:0:1', '2019-03-21 19:44:58', NULL, 8),
(27, '0:0:0:0:0:0:0:1', '2019-03-21 22:42:24', NULL, 11),
(28, '0:0:0:0:0:0:0:1', '2019-03-22 06:45:02', NULL, 8),
(29, '0:0:0:0:0:0:0:1', '2019-03-23 14:23:17', NULL, 8),
(30, '0:0:0:0:0:0:0:1', '2019-03-23 19:29:21', NULL, 8),
(31, '0:0:0:0:0:0:0:1', '2019-03-23 19:40:32', NULL, 8),
(32, '0:0:0:0:0:0:0:1', '2019-03-23 20:58:44', '2019-03-23 21:05:42', 8),
(33, '0:0:0:0:0:0:0:1', '2019-03-23 21:05:46', '2019-03-23 21:12:46', 9),
(34, '0:0:0:0:0:0:0:1', '2019-03-23 21:12:52', NULL, 8),
(35, '0:0:0:0:0:0:0:1', '2019-03-23 21:13:55', NULL, 9),
(36, '0:0:0:0:0:0:0:1', '2019-03-23 21:32:25', NULL, 9),
(37, '0:0:0:0:0:0:0:1', '2019-03-23 22:28:43', NULL, 9),
(38, '0:0:0:0:0:0:0:1', '2019-03-24 05:12:36', NULL, 8),
(39, '0:0:0:0:0:0:0:1', '2019-03-24 06:14:38', NULL, 8),
(40, '0:0:0:0:0:0:0:1', '2019-03-24 06:15:03', NULL, 8),
(41, '0:0:0:0:0:0:0:1', '2019-03-24 06:16:58', NULL, 8),
(42, '0:0:0:0:0:0:0:1', '2019-03-24 06:22:15', NULL, 11),
(43, '0:0:0:0:0:0:0:1', '2019-03-24 06:38:39', NULL, 7),
(44, '0:0:0:0:0:0:0:1', '2019-03-24 07:48:23', NULL, 9),
(45, '0:0:0:0:0:0:0:1', '2019-03-24 07:50:46', NULL, 9),
(46, '0:0:0:0:0:0:0:1', '2019-03-24 08:32:36', NULL, 11),
(47, '192.168.43.117', '2019-03-24 09:59:00', NULL, 8),
(48, '0:0:0:0:0:0:0:1', '2019-03-24 10:49:51', NULL, 8),
(49, '0:0:0:0:0:0:0:1', '2019-03-24 10:52:29', NULL, 7),
(50, '0:0:0:0:0:0:0:1', '2019-03-24 18:34:46', NULL, 8),
(51, '0:0:0:0:0:0:0:1', '2019-03-25 04:43:30', NULL, 7),
(52, '0:0:0:0:0:0:0:1', '2019-03-25 05:19:27', NULL, 7),
(53, '0:0:0:0:0:0:0:1', '2019-03-25 05:22:08', NULL, 9),
(54, '0:0:0:0:0:0:0:1', '2019-03-25 05:45:28', NULL, 8),
(55, '0:0:0:0:0:0:0:1', '2019-03-25 06:28:03', NULL, 7),
(56, '0:0:0:0:0:0:0:1', '2019-03-25 08:47:16', NULL, 7),
(57, '0:0:0:0:0:0:0:1', '2019-03-25 14:51:09', '2019-03-25 14:51:39', 7),
(58, '0:0:0:0:0:0:0:1', '2019-03-25 15:45:46', '2019-03-25 15:48:22', 13),
(59, '0:0:0:0:0:0:0:1', '2019-03-25 15:54:28', '2019-03-25 15:55:18', 14),
(60, '0:0:0:0:0:0:0:1', '2019-03-25 15:55:22', '2019-03-25 15:55:42', 13),
(61, '0:0:0:0:0:0:0:1', '2019-03-25 16:09:19', '2019-03-25 16:11:28', 16),
(62, '0:0:0:0:0:0:0:1', '2019-04-11 05:12:31', NULL, 7),
(63, '0:0:0:0:0:0:0:1', '2019-04-11 05:43:06', '2019-04-11 05:47:28', 7),
(64, '0:0:0:0:0:0:0:1', '2019-04-11 05:47:32', '2019-04-11 05:51:05', 8),
(65, '0:0:0:0:0:0:0:1', '2019-04-11 05:51:09', NULL, 7),
(66, '0:0:0:0:0:0:0:1', '2019-04-11 06:04:06', NULL, 7),
(67, '0:0:0:0:0:0:0:1', '2019-04-11 06:27:34', NULL, 7),
(68, '0:0:0:0:0:0:0:1', '2019-04-11 08:45:58', '2019-04-11 08:51:38', 7),
(69, '0:0:0:0:0:0:0:1', '2019-04-11 08:51:42', '2019-04-11 09:03:11', 16),
(70, '0:0:0:0:0:0:0:1', '2019-04-11 09:03:15', '2019-04-11 09:09:08', 7),
(71, '0:0:0:0:0:0:0:1', '2019-04-11 09:32:30', '2019-04-11 12:13:25', 16),
(72, '0:0:0:0:0:0:0:1', '2019-04-11 12:13:32', NULL, 16),
(73, '0:0:0:0:0:0:0:1', '2019-04-11 12:20:59', '2019-04-11 12:23:36', 7),
(74, '0:0:0:0:0:0:0:1', '2019-04-11 12:23:41', NULL, 16),
(75, '0:0:0:0:0:0:0:1', '2019-04-11 12:29:22', NULL, 16),
(76, '0:0:0:0:0:0:0:1', '2019-04-11 12:29:24', NULL, 16),
(77, '0:0:0:0:0:0:0:1', '2019-04-11 12:29:25', NULL, 16),
(78, '0:0:0:0:0:0:0:1', '2019-04-11 12:30:59', '2019-04-11 12:31:08', 16),
(79, '0:0:0:0:0:0:0:1', '2019-04-11 12:31:12', NULL, 7),
(80, '0:0:0:0:0:0:0:1', '2019-04-11 12:42:01', '2019-04-11 12:43:07', 16),
(81, '0:0:0:0:0:0:0:1', '2019-04-11 12:43:13', NULL, 16),
(82, '0:0:0:0:0:0:0:1', '2019-04-11 12:46:20', '2019-04-11 12:50:46', 16),
(83, '0:0:0:0:0:0:0:1', '2019-04-11 12:51:45', NULL, 16),
(84, '0:0:0:0:0:0:0:1', '2019-04-11 12:52:20', NULL, 16),
(85, '0:0:0:0:0:0:0:1', '2019-04-11 12:58:08', NULL, 16),
(86, '0:0:0:0:0:0:0:1', '2019-04-11 12:59:47', '2019-04-11 13:00:15', 16),
(87, '0:0:0:0:0:0:0:1', '2019-04-11 13:00:18', '2019-04-11 13:23:02', 16),
(88, '0:0:0:0:0:0:0:1', '2019-04-11 13:23:05', '2019-04-11 13:23:11', 8),
(89, '0:0:0:0:0:0:0:1', '2019-04-11 13:37:58', '2019-04-11 13:38:24', 8),
(90, '0:0:0:0:0:0:0:1', '2019-04-11 13:58:14', NULL, 16),
(91, '0:0:0:0:0:0:0:1', '2019-04-11 14:23:25', NULL, 16),
(92, '0:0:0:0:0:0:0:1', '2019-04-11 17:48:26', '2019-04-12 07:24:18', 16),
(93, '0:0:0:0:0:0:0:1', '2019-04-11 17:52:53', '2019-04-11 17:54:00', 7),
(94, '0:0:0:0:0:0:0:1', '2019-04-11 17:54:07', NULL, 8),
(95, '0:0:0:0:0:0:0:1', '2019-04-12 07:24:21', NULL, 8),
(96, '0:0:0:0:0:0:0:1', '2019-04-12 08:09:44', NULL, 8),
(97, '0:0:0:0:0:0:0:1', '2019-04-12 10:49:19', NULL, 16),
(98, '0:0:0:0:0:0:0:1', '2019-04-14 07:35:37', '2019-04-14 07:36:40', 8),
(99, '0:0:0:0:0:0:0:1', '2019-04-14 07:36:43', NULL, 16),
(100, '0:0:0:0:0:0:0:1', '2019-04-14 17:09:51', '2019-04-14 17:47:36', 7),
(101, '0:0:0:0:0:0:0:1', '2019-04-14 17:11:17', '2019-04-15 15:33:06', 8),
(102, '0:0:0:0:0:0:0:1', '2019-04-14 17:11:43', NULL, 16),
(103, '0:0:0:0:0:0:0:1', '2019-04-14 17:12:27', NULL, 9),
(104, '0:0:0:0:0:0:0:1', '2019-04-15 15:33:47', '2019-04-15 15:33:56', 16),
(105, '0:0:0:0:0:0:0:1', '2019-04-15 15:34:43', '2019-04-15 15:34:58', 16),
(106, '0:0:0:0:0:0:0:1', '2019-04-15 15:35:22', NULL, 16),
(107, '0:0:0:0:0:0:0:1', '2019-04-16 07:23:49', '2019-04-16 07:27:12', 16),
(108, '0:0:0:0:0:0:0:1', '2019-04-16 07:27:15', NULL, 8),
(109, '0:0:0:0:0:0:0:1', '2019-04-16 08:46:10', NULL, 8),
(110, '0:0:0:0:0:0:0:1', '2019-04-16 08:59:20', NULL, 8),
(111, '0:0:0:0:0:0:0:1', '2019-04-16 09:04:52', NULL, 8),
(112, '0:0:0:0:0:0:0:1', '2019-04-17 06:09:41', NULL, 8),
(113, '0:0:0:0:0:0:0:1', '2019-04-17 09:16:14', '2019-04-17 15:35:44', 8),
(114, '0:0:0:0:0:0:0:1', '2019-04-17 15:35:48', '2019-04-17 15:36:20', 7),
(115, '0:0:0:0:0:0:0:1', '2019-04-17 15:36:25', NULL, 8),
(116, '0:0:0:0:0:0:0:1', '2019-04-17 15:37:51', NULL, 9),
(117, '0:0:0:0:0:0:0:1', '2019-04-17 15:42:53', NULL, 16),
(118, '0:0:0:0:0:0:0:1', '2019-04-17 15:43:36', NULL, 8),
(119, '0:0:0:0:0:0:0:1', '2019-04-17 15:53:55', NULL, 8),
(120, '0:0:0:0:0:0:0:1', '2019-04-17 17:30:24', NULL, 8),
(121, '0:0:0:0:0:0:0:1', '2019-04-17 17:34:11', NULL, 9),
(122, '0:0:0:0:0:0:0:1', '2019-04-17 17:36:49', NULL, 16),
(123, '0:0:0:0:0:0:0:1', '2019-04-18 05:44:18', NULL, 16),
(124, '0:0:0:0:0:0:0:1', '2019-04-18 07:11:36', NULL, 16),
(125, '0:0:0:0:0:0:0:1', '2019-04-18 09:03:58', NULL, 16),
(126, '0:0:0:0:0:0:0:1', '2019-04-19 03:17:14', NULL, 16),
(127, '0:0:0:0:0:0:0:1', '2019-04-19 03:30:13', NULL, 7),
(128, '0:0:0:0:0:0:0:1', '2019-04-19 08:17:24', NULL, 7),
(129, '0:0:0:0:0:0:0:1', '2019-04-19 08:50:41', NULL, 16),
(130, '0:0:0:0:0:0:0:1', '2019-04-19 09:00:54', NULL, 8),
(131, '0:0:0:0:0:0:0:1', '2019-04-19 17:09:33', NULL, 8),
(132, '0:0:0:0:0:0:0:1', '2019-04-19 18:58:33', NULL, 8),
(133, '0:0:0:0:0:0:0:1', '2019-04-19 19:22:36', NULL, 8),
(134, '0:0:0:0:0:0:0:1', '2019-04-21 15:33:43', NULL, 7),
(135, '0:0:0:0:0:0:0:1', '2019-04-21 18:30:35', NULL, 8),
(136, '0:0:0:0:0:0:0:1', '2019-04-21 18:31:07', NULL, 9),
(137, '0:0:0:0:0:0:0:1', '2019-04-21 18:48:44', NULL, 9),
(138, '0:0:0:0:0:0:0:1', '2019-04-22 07:47:37', NULL, 7),
(139, '0:0:0:0:0:0:0:1', '2019-04-22 07:55:41', NULL, 8),
(140, '0:0:0:0:0:0:0:1', '2019-04-22 08:17:46', NULL, 8),
(141, '0:0:0:0:0:0:0:1', '2019-04-22 08:29:20', NULL, 8),
(142, '0:0:0:0:0:0:0:1', '2019-04-22 09:01:29', NULL, 7),
(143, '0:0:0:0:0:0:0:1', '2019-04-22 10:30:36', NULL, 7),
(144, '0:0:0:0:0:0:0:1', '2019-04-22 10:42:05', NULL, 8),
(145, '0:0:0:0:0:0:0:1', '2019-04-22 10:42:29', NULL, 16),
(146, '0:0:0:0:0:0:0:1', '2019-04-22 10:46:02', NULL, 16),
(147, '0:0:0:0:0:0:0:1', '2019-04-22 10:48:38', NULL, 16),
(148, '0:0:0:0:0:0:0:1', '2019-04-22 10:53:54', '2019-04-22 10:54:02', 16),
(149, '0:0:0:0:0:0:0:1', '2019-04-22 10:54:07', NULL, 7),
(150, '0:0:0:0:0:0:0:1', '2019-04-22 12:20:25', NULL, 7),
(151, '192.168.1.101', '2019-04-22 12:24:16', NULL, 7),
(152, '0:0:0:0:0:0:0:1', '2019-04-22 14:15:57', NULL, 7),
(153, '0:0:0:0:0:0:0:1', '2019-04-22 14:47:58', NULL, 7),
(154, '0:0:0:0:0:0:0:1', '2019-04-22 18:27:07', NULL, 7),
(155, '0:0:0:0:0:0:0:1', '2019-04-22 18:30:09', NULL, 7),
(156, '0:0:0:0:0:0:0:1', '2019-04-22 18:42:31', NULL, 7),
(157, '0:0:0:0:0:0:0:1', '2019-04-23 06:28:54', '2019-04-23 07:17:58', 7),
(158, '0:0:0:0:0:0:0:1', '2019-04-23 07:18:01', NULL, 8),
(159, '0:0:0:0:0:0:0:1', '2019-04-23 09:13:35', '2019-04-23 09:13:54', 8),
(160, '0:0:0:0:0:0:0:1', '2019-04-23 09:13:56', '2019-04-23 09:52:39', 7),
(161, '0:0:0:0:0:0:0:1', '2019-04-23 09:52:42', '2019-04-23 10:25:25', 8),
(162, '0:0:0:0:0:0:0:1', '2019-04-23 10:25:28', NULL, 7),
(163, '0:0:0:0:0:0:0:1', '2019-04-23 11:13:03', NULL, 8),
(164, '0:0:0:0:0:0:0:1', '2019-04-23 12:59:57', '2019-04-23 13:00:14', 8),
(165, '0:0:0:0:0:0:0:1', '2019-04-23 13:00:19', '2019-04-23 14:08:25', 7),
(166, '0:0:0:0:0:0:0:1', '2019-04-23 14:08:28', '2019-04-23 14:08:38', 8),
(167, '0:0:0:0:0:0:0:1', '2019-04-23 14:08:41', NULL, 7),
(168, '0:0:0:0:0:0:0:1', '2019-04-23 15:07:52', '2019-04-23 15:08:49', 16),
(169, '0:0:0:0:0:0:0:1', '2019-04-23 15:08:51', NULL, 8),
(170, '0:0:0:0:0:0:0:1', '2019-04-23 15:17:13', '2019-04-23 15:17:19', 8),
(171, '0:0:0:0:0:0:0:1', '2019-04-23 15:17:22', NULL, 16),
(172, '0:0:0:0:0:0:0:1', '2019-04-23 15:21:18', NULL, 8),
(173, '0:0:0:0:0:0:0:1', '2019-04-23 15:31:38', '2019-04-23 15:45:49', 8),
(174, '0:0:0:0:0:0:0:1', '2019-04-23 15:31:48', NULL, 16),
(175, '0:0:0:0:0:0:0:1', '2019-04-23 15:45:53', '2019-04-23 16:27:17', 7),
(176, '0:0:0:0:0:0:0:1', '2019-04-23 16:27:21', '2019-04-23 16:34:34', 8),
(177, '0:0:0:0:0:0:0:1', '2019-04-23 16:34:38', NULL, 7),
(178, '0:0:0:0:0:0:0:1', '2019-04-23 17:13:27', NULL, 7),
(179, '0:0:0:0:0:0:0:1', '2019-04-23 17:46:06', NULL, 16),
(180, '0:0:0:0:0:0:0:1', '2019-04-23 18:35:55', NULL, 16),
(181, '0:0:0:0:0:0:0:1', '2019-04-27 13:11:21', NULL, 7),
(182, '0:0:0:0:0:0:0:1', '2019-04-27 13:15:55', NULL, 8),
(183, '0:0:0:0:0:0:0:1', '2019-04-27 14:12:47', NULL, 7),
(184, '0:0:0:0:0:0:0:1', '2019-04-27 15:33:10', NULL, 7),
(185, '0:0:0:0:0:0:0:1', '2019-04-27 16:06:55', NULL, 7),
(186, '0:0:0:0:0:0:0:1', '2019-04-27 16:26:12', NULL, 7),
(187, '0:0:0:0:0:0:0:1', '2019-04-27 16:36:38', NULL, 7),
(188, '0:0:0:0:0:0:0:1', '2019-04-27 16:43:20', NULL, 7),
(189, '0:0:0:0:0:0:0:1', '2019-04-27 17:08:58', NULL, 7),
(190, '0:0:0:0:0:0:0:1', '2019-04-27 17:11:56', NULL, 7),
(191, '0:0:0:0:0:0:0:1', '2019-04-27 17:24:04', NULL, 7),
(192, '0:0:0:0:0:0:0:1', '2019-04-27 17:31:13', NULL, 7),
(193, '0:0:0:0:0:0:0:1', '2019-04-27 17:33:39', NULL, 7),
(194, '0:0:0:0:0:0:0:1', '2019-04-27 17:34:46', NULL, 7),
(195, '0:0:0:0:0:0:0:1', '2019-04-27 18:06:20', NULL, 7),
(196, '0:0:0:0:0:0:0:1', '2019-04-27 18:09:35', NULL, 8),
(197, '0:0:0:0:0:0:0:1', '2019-04-27 18:10:46', NULL, 7),
(198, '0:0:0:0:0:0:0:1', '2019-04-27 18:11:09', NULL, 8),
(199, '0:0:0:0:0:0:0:1', '2019-04-27 18:11:28', NULL, 7),
(200, '0:0:0:0:0:0:0:1', '2019-04-27 18:17:41', NULL, 7),
(201, '0:0:0:0:0:0:0:1', '2019-04-27 18:22:30', NULL, 7),
(202, '0:0:0:0:0:0:0:1', '2019-04-27 18:30:35', NULL, 7),
(203, '0:0:0:0:0:0:0:1', '2019-04-27 18:32:40', NULL, 7),
(204, '0:0:0:0:0:0:0:1', '2019-04-27 18:38:03', NULL, 7),
(205, '0:0:0:0:0:0:0:1', '2019-04-27 18:39:39', NULL, 7),
(206, '0:0:0:0:0:0:0:1', '2019-04-27 18:45:26', NULL, 7),
(207, '0:0:0:0:0:0:0:1', '2019-04-27 19:17:46', NULL, 7),
(208, '0:0:0:0:0:0:0:1', '2019-04-27 19:19:12', NULL, 7),
(209, '0:0:0:0:0:0:0:1', '2019-04-27 19:21:00', NULL, 7),
(210, '0:0:0:0:0:0:0:1', '2019-04-27 19:22:54', NULL, 7),
(211, '0:0:0:0:0:0:0:1', '2019-04-27 19:25:39', NULL, 7),
(212, '0:0:0:0:0:0:0:1', '2019-04-27 21:20:20', NULL, 7),
(213, '0:0:0:0:0:0:0:1', '2019-04-27 21:22:59', NULL, 8),
(214, '0:0:0:0:0:0:0:1', '2019-04-27 21:45:01', NULL, 7),
(215, '0:0:0:0:0:0:0:1', '2019-04-27 21:45:18', NULL, 8),
(216, '0:0:0:0:0:0:0:1', '2019-04-27 22:08:33', NULL, 8),
(217, '0:0:0:0:0:0:0:1', '2019-04-27 22:10:05', NULL, 7),
(218, '0:0:0:0:0:0:0:1', '2019-05-03 15:14:09', NULL, 7),
(219, '0:0:0:0:0:0:0:1', '2019-05-03 16:03:40', NULL, 7),
(220, '0:0:0:0:0:0:0:1', '2019-05-03 16:17:12', NULL, 7),
(221, '0:0:0:0:0:0:0:1', '2019-05-04 12:38:13', NULL, 7),
(222, '0:0:0:0:0:0:0:1', '2019-05-04 15:03:59', NULL, 8),
(223, '0:0:0:0:0:0:0:1', '2019-05-04 16:01:17', '2019-05-04 16:04:13', 8),
(224, '0:0:0:0:0:0:0:1', '2019-05-04 16:01:25', NULL, 7),
(225, '0:0:0:0:0:0:0:1', '2019-05-04 16:01:51', NULL, 16),
(226, '0:0:0:0:0:0:0:1', '2019-05-04 16:04:15', '2019-05-04 16:58:09', 7),
(227, '0:0:0:0:0:0:0:1', '2019-05-04 16:58:11', '2019-05-04 17:26:03', 7),
(228, '0:0:0:0:0:0:0:1', '2019-05-04 17:14:47', NULL, 8),
(229, '0:0:0:0:0:0:0:1', '2019-05-04 17:26:06', NULL, 8),
(230, '0:0:0:0:0:0:0:1', '2019-05-05 05:19:41', NULL, 7),
(231, '0:0:0:0:0:0:0:1', '2019-05-05 09:54:48', NULL, 7),
(232, '0:0:0:0:0:0:0:1', '2019-05-05 11:56:01', NULL, 7),
(233, '0:0:0:0:0:0:0:1', '2019-05-05 17:37:00', NULL, 7),
(234, '0:0:0:0:0:0:0:1', '2019-05-05 17:45:53', NULL, 7),
(235, '0:0:0:0:0:0:0:1', '2019-05-06 00:25:06', NULL, 7),
(236, '0:0:0:0:0:0:0:1', '2019-05-06 00:50:38', NULL, 7),
(237, '0:0:0:0:0:0:0:1', '2019-05-06 00:51:32', NULL, 15),
(238, '0:0:0:0:0:0:0:1', '2019-05-06 00:52:00', NULL, 16),
(239, '0:0:0:0:0:0:0:1', '2019-05-06 00:52:10', NULL, 10),
(240, '0:0:0:0:0:0:0:1', '2019-05-06 00:52:33', NULL, 8),
(241, '0:0:0:0:0:0:0:1', '2019-05-06 00:55:05', NULL, 12),
(242, '0:0:0:0:0:0:0:1', '2019-05-06 00:58:32', NULL, 13),
(243, '0:0:0:0:0:0:0:1', '2019-05-06 00:59:56', NULL, 9),
(244, '0:0:0:0:0:0:0:1', '2019-05-06 01:01:26', NULL, 10),
(245, '0:0:0:0:0:0:0:1', '2019-05-06 01:02:18', NULL, 14),
(246, '0:0:0:0:0:0:0:1', '2019-05-06 01:05:08', NULL, 7),
(247, '0:0:0:0:0:0:0:1', '2019-05-06 01:05:45', NULL, 17),
(248, '0:0:0:0:0:0:0:1', '2019-05-06 01:13:35', NULL, 11),
(249, '0:0:0:0:0:0:0:1', '2019-05-06 01:17:56', NULL, 8),
(250, '0:0:0:0:0:0:0:1', '2019-05-06 01:18:33', NULL, 9),
(251, '0:0:0:0:0:0:0:1', '2019-05-06 01:20:05', NULL, 9),
(252, '0:0:0:0:0:0:0:1', '2019-05-06 01:23:38', NULL, 11),
(253, '0:0:0:0:0:0:0:1', '2019-05-06 01:26:11', NULL, 8),
(254, '0:0:0:0:0:0:0:1', '2019-05-06 01:27:34', NULL, 13),
(255, '0:0:0:0:0:0:0:1', '2019-05-06 03:53:15', NULL, 7),
(256, '0:0:0:0:0:0:0:1', '2019-05-06 04:13:32', '2019-05-06 04:22:04', 7),
(257, '0:0:0:0:0:0:0:1', '2019-05-06 04:59:37', NULL, 7),
(258, '0:0:0:0:0:0:0:1', '2019-05-06 06:38:01', '2019-05-06 06:59:55', 7),
(259, '0:0:0:0:0:0:0:1', '2019-05-06 06:39:01', NULL, 9),
(260, '0:0:0:0:0:0:0:1', '2019-05-06 07:26:20', '2019-05-06 07:26:28', 7),
(261, '0:0:0:0:0:0:0:1', '2019-05-06 09:30:06', '2019-05-06 09:57:00', 7),
(262, '0:0:0:0:0:0:0:1', '2019-05-06 09:32:59', '2019-05-06 09:42:05', 13),
(263, '0:0:0:0:0:0:0:1', '2019-05-06 09:42:13', NULL, 9),
(264, '0:0:0:0:0:0:0:1', '2019-05-06 09:44:28', NULL, 8),
(265, '0:0:0:0:0:0:0:1', '2019-05-06 09:45:46', '2019-05-06 09:46:54', 8),
(266, '0:0:0:0:0:0:0:1', '2019-05-06 09:46:58', NULL, 13),
(267, '0:0:0:0:0:0:0:1', '2019-05-06 16:58:09', '2019-05-06 17:03:16', 7);

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `idnotification` int(11) NOT NULL,
  `notificationfor` int(11) NOT NULL,
  `notificationfrom` int(11) NOT NULL,
  `notification-type` int(11) NOT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT '0',
  `target` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`idnotification`, `notificationfor`, `notificationfrom`, `notification-type`, `time`, `status`, `target`) VALUES
(8, 1, 2, 2, '2019-04-28 02:53:04', 1, 1),
(9, 1, 2, 2, '2019-04-28 02:54:26', 1, 1),
(10, 1, 2, 1, '2019-04-28 03:15:23', 1, 0),
(11, 13, 1, 1, '2019-05-06 06:20:55', 1, 0),
(12, 4, 1, 1, '2019-05-06 06:21:12', 1, 0),
(13, 1, 13, 1, '2019-05-06 06:21:44', 1, 0),
(14, 1, 4, 1, '2019-05-06 06:22:17', 1, 0),
(15, 6, 1, 1, '2019-05-06 06:25:25', 1, 0),
(16, 1, 6, 1, '2019-05-06 06:25:30', 1, 0),
(17, 1, 8, 1, '2019-05-06 06:29:01', 1, 0),
(18, 8, 1, 1, '2019-05-06 06:29:17', 1, 0),
(19, 1, 12, 1, '2019-05-06 06:32:39', 1, 0),
(20, 12, 1, 1, '2019-05-06 06:32:51', 0, 0),
(21, 22, 1, 1, '2019-05-06 06:34:55', 1, 0),
(22, 1, 22, 1, '2019-05-06 06:35:51', 1, 0),
(23, 1, 5, 1, '2019-05-06 06:43:44', 1, 0),
(24, 5, 1, 1, '2019-05-06 06:43:52', 1, 0),
(25, 3, 1, 2, '2019-05-06 12:25:34', 1, 7),
(26, 8, 1, 2, '2019-05-06 15:03:49', 1, 11);

-- --------------------------------------------------------

--
-- Table structure for table `notification_type`
--

CREATE TABLE `notification_type` (
  `idnotification-type` int(11) NOT NULL,
  `typename` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `notification_type`
--

INSERT INTO `notification_type` (`idnotification-type`, `typename`) VALUES
(1, 'follow'),
(2, 'comment');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `idpost` int(11) NOT NULL,
  `post_heading` text NOT NULL,
  `post_img_area` text,
  `post_detial` text,
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `users_idusers` int(11) NOT NULL,
  `post_type_idpost_type` int(11) NOT NULL,
  `Post_Privacy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`idpost`, `post_heading`, `post_img_area`, `post_detial`, `post_time`, `users_idusers`, `post_type_idpost_type`, `Post_Privacy`) VALUES
(1, 'moO4oWpcjf1Th+0yQ8QlSg==', 'up9FOZDMep6WREyY8wHEW9ZGjGOD7pnk74fGaNt+Dl4=', 'DppnEl2XMhmI0BL1i7SIcg==', '2019-04-27 17:38:02', 1, 1, 1),
(2, 'vESwnz37R3h8yjIAl2cNTA==', 'up9FOZDMep6WREyY8wHEW5kPfVoHWcqYpdgQM3Nqy6o=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:20:27', 3, 1, 1),
(3, 'vESwnz37R3h8yjIAl2cNTA==', 'up9FOZDMep6WREyY8wHEW6V5t/F2p5SwTXm/1JJQdBA=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:24:00', 5, 1, 1),
(4, 'vESwnz37R3h8yjIAl2cNTA==', 'up9FOZDMep6WREyY8wHEW9WNMCoNETIMt4ng8Mk4CYM=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:24:24', 3, 1, 1),
(5, 'vESwnz37R3h8yjIAl2cNTA==', 'up9FOZDMep6WREyY8wHEWwwMt0JEnBK15Ob3fvnzLU4=', 'vESwnz37R3h8yjIAl2cNTA==', '2019-05-06 01:24:54', 3, 1, 1),
(6, 'WRzSi8MgAxkaO9/YHypr5Q==', 'up9FOZDMep6WREyY8wHEWw1hQi5bxK3ocuoftCVxF+g=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:25:28', 5, 1, 1),
(7, 'WRzSi8MgAxkaO9/YHypr5Q==', 'up9FOZDMep6WREyY8wHEWxOP6fWnF+WxHRiBl3Uoi5Q=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:25:57', 3, 1, 1),
(8, 'WRzSi8MgAxkaO9/YHypr5Q==', 'up9FOZDMep6WREyY8wHEW/csElnGLgqqehFEvOTFH9E=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:26:24', 2, 1, 1),
(9, 'WRzSi8MgAxkaO9/YHypr5Q==', 'up9FOZDMep6WREyY8wHEW/oidpuJgm/k8o1I+ZRVgPY=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:26:54', 2, 1, 1),
(10, 'WRzSi8MgAxkaO9/YHypr5Q==', 'up9FOZDMep6WREyY8wHEWxLCCNXu4USZ/wmHUnivpJo=', 'WRzSi8MgAxkaO9/YHypr5Q==', '2019-05-06 01:27:18', 2, 1, 1),
(11, 'CW9rDwO3skqYrm+bAK56mA==', 'up9FOZDMep6WREyY8wHEW7Ga0XPuKlg0eOSAfDsmfrw=', 'O5t1x7Gg6IJGrFEVQIETtg==', '2019-05-06 01:28:29', 8, 1, 1),
(12, 'N723CKBC4fPckDbnEKDTzQ==', 'r9jXFpjnnLPqaUk48ECukzFIUvWK82jasL+CQ2KMz6Q+gxSYy/o5FcfzNP4BedwKeRydQ3rw4HiWj0yVM1acryWcVTKE/iO1DXDd9+JoP2SMLvtz9N6g+upVjirEuN4bwro2ME0Y/yghiwf2YZvRoaaJrX2rPWqIGkIY31jWKGuNBVE5r3cTYqVbFyhTyOy5OJp2fPBaYlat8qQSs1sXbU8uM94JxgWFoWy7utuWWtR0RX/Mh1TudmHz/vVRbBLy0FoCqMFQ0j1uawOjN24lZZqk9ZlGhHAPbIH7a5Bd+HS9l0+dZfY3Zz8loa2BvVgZ4qTKpe9qPznPgHUu+mE+cEI0IfAu2Ot+484ucOG8YbbpkTp3fFjM0o+0M49Sfeg3WZQBNN1bXT+bdb7XDccD3+gjFZF21A+IdsBHv3TBSfc=', 'B1cRktrb8fNjw+iVjM1Gag==', '2019-05-06 01:31:11', 1, 2, 1),
(13, 'CW9rDwO3skqYrm+bAK56mA==', 'up9FOZDMep6WREyY8wHEWydS9vy9KYo9bcuYPAMCRTU=', 'O5t1x7Gg6IJGrFEVQIETtg==', '2019-05-06 09:54:20', 1, 1, 1),
(14, 'DnTreeLrJIhRsWlHPLjvqA==', 'YFaVnpCV5wvVMmHqEwDzLmJV+prqKGiLLKeJ6dYY0Q8XpMYiAKq7Njj9bW+TM8hxK9GQTkr6h4aiPKts5n49zNrdaKnhCbuSNv/Jumuw90Dmv4h1FtlAUfgl1R3mrrQc4SGF2etT8fnzzEseMFsXwJyh46gopPoNNn1zFvWgSqKkl7u688m3epiqWnpFesy3EI/s63l7tGExcdTS0F0OsHhYqGM5HRHI05JjRJMHnz2zipmahVsm7CiHQkq+XtkF', 'osk0jt2c6DJgQvCpFs+TPg==', '2019-05-06 09:55:38', 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `post_comment`
--

CREATE TABLE `post_comment` (
  `idpost_comment` int(11) NOT NULL,
  `comment` text NOT NULL,
  `users_idusers` int(11) NOT NULL,
  `post_idpost` int(11) NOT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_comment`
--

INSERT INTO `post_comment` (`idpost_comment`, `comment`, `users_idusers`, `post_idpost`, `datetime`) VALUES
(1, 'yum', 2, 1, '2019-04-27 23:39:43'),
(2, 'yum', 2, 1, '2019-04-27 23:41:48'),
(3, 'segw', 2, 1, '2019-04-28 02:53:04'),
(4, 'cwaf', 2, 1, '2019-04-28 02:54:26'),
(5, 'nice', 1, 7, '2019-05-06 12:25:34'),
(6, 'nice post', 1, 11, '2019-05-06 15:03:49'),
(7, 'meow', 8, 11, '2019-05-06 15:04:12');

-- --------------------------------------------------------

--
-- Table structure for table `post_privacy`
--

CREATE TABLE `post_privacy` (
  `idPost_Privacy` int(11) NOT NULL,
  `Privacy Name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_privacy`
--

INSERT INTO `post_privacy` (`idPost_Privacy`, `Privacy Name`) VALUES
(1, 'public'),
(2, 'private');

-- --------------------------------------------------------

--
-- Table structure for table `post_rank`
--

CREATE TABLE `post_rank` (
  `idPost_rank` int(11) NOT NULL,
  `likes` int(11) NOT NULL DEFAULT '0',
  `post_idpost` int(11) NOT NULL,
  `likedby` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_rank`
--

INSERT INTO `post_rank` (`idPost_rank`, `likes`, `post_idpost`, `likedby`) VALUES
(1, 1, 12, 1),
(2, 1, 11, 1),
(3, 1, 11, 2),
(4, 1, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `post_status`
--

CREATE TABLE `post_status` (
  `idpost_status` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `post_idpost` int(11) NOT NULL,
  `users_idusers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `post_type`
--

CREATE TABLE `post_type` (
  `idpost_type` int(11) NOT NULL,
  `type_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `post_type`
--

INSERT INTO `post_type` (`idpost_type`, `type_name`) VALUES
(1, 'imagepost'),
(2, 'videopost');

-- --------------------------------------------------------

--
-- Table structure for table `privilages`
--

CREATE TABLE `privilages` (
  `idPrivilages` int(11) NOT NULL,
  `user_type_iduser_type` int(11) NOT NULL,
  `interfaces_idinterfaces` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `purchase_history`
--

CREATE TABLE `purchase_history` (
  `idpurchase_history` int(11) NOT NULL,
  `users_idusers` int(11) NOT NULL,
  `rate` double DEFAULT NULL,
  `total` double NOT NULL,
  `status` int(11) NOT NULL,
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hours` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_history`
--

INSERT INTO `purchase_history` (`idpurchase_history`, `users_idusers`, `rate`, `total`, `status`, `datetime`, `hours`) VALUES
(1, 1, 10, 10, 1, '2019-05-06 09:48:45', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tailzconfig`
--

CREATE TABLE `tailzconfig` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `state` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tailzconfig`
--

INSERT INTO `tailzconfig` (`id`, `name`, `state`) VALUES
(1, 'login', 1),
(2, 'google login', 0),
(3, 'user', 0);

-- --------------------------------------------------------

--
-- Table structure for table `uap`
--

CREATE TABLE `uap` (
  `idUAP` int(11) NOT NULL,
  `Preference` int(11) DEFAULT '1',
  `users_idusers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `uap`
--

INSERT INTO `uap` (`idUAP`, `Preference`, `users_idusers`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 1, 4),
(5, 1, 5),
(6, 1, 6),
(7, 1, 8),
(8, 1, 12),
(9, 1, 13),
(10, 1, 15),
(11, 1, 20),
(12, 1, 21),
(13, 1, 22);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `idusers` int(11) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` text,
  `status` int(11) DEFAULT '0',
  `hash` text,
  `user_type_iduser_type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`idusers`, `firstname`, `lastname`, `email`, `status`, `hash`, `user_type_iduser_type`) VALUES
(1, 'Vidura', 'Dulanjan', 'heyvidurda@gmail.com', 1, 'f999c67e376ba3d7a083b606b0f4190b', 3),
(2, 'Dulaj', 'Myuranga', 'xunacaxay@for4mail.com', 1, '44f9fb22223b3fb4bfde46f65b68b4c7', 3),
(3, 'Maduranjana', 'Janakantha', 'camar@kingyslmail.top', 1, 'f603e1f55a0ff037bbe74268fe01cebb', 1),
(4, 'Navindu', 'Heshan', 'ondsinne@yuirz.com', 1, '87490067a2f1f4c76964d3b196012407', 2),
(5, 'Lahiru', 'Ruwantha', 'camar@zexeet9i5l49ocke.ml', 1, '4eba722ddbf0735fae4895b2f4daf077', 3),
(6, 'Kalana', 'Rajapaksha', 'camar@zexeet9i5l49ocke.ml', 1, '4eba722ddbf0735fae4895b2f4daf077', 2),
(8, 'Chamod', 'Diyamantha', 'convecino@whiteseoromania.tk', 1, 'b1ba51617f67fab29b74e18e03c50421', 2),
(12, 'Bhanuka', 'Dilhara', 'convecino@zgu5la23tngr2molii.cf', 1, '9f7ad315f9c00f167a68f62ea2654f93', 2),
(13, 'Pundima', 'Lakshan', 'docti@vgamers.win', 1, '5228619a0566daf789c1ddab563bc8f7', 2),
(15, 'Bat', 'Man', 'docti@zinmail.tk', 1, '87c7412c7e2372fc661c4c554b3cc0ee', 3),
(20, 'Vidura', 'Fernando', 'heyvidura@gmail.com', 1, '30db3431e81f1795ea26041d23717621', 2),
(21, 'tailz', 'project', 'tailz.network@gmail.com', 1, 'cb35688fd0b924d6d9c14f61b677e8bf', 2),
(22, 'Dor', 'mammu', 'vidurabot@gmail.com', 1, '9645260bdf6cbe5b4397b5d78c14bf8b', 2),
(23, NULL, NULL, 'datis@kudaponiea.cf', 0, '9edb0469ec1e97bc2bda0281d5f3a2ff', 2),
(24, NULL, NULL, 'dunde@branden1121.club', 0, 'cf8b2ab626621ceaeb54ced8dcd47e07', 2),
(25, NULL, NULL, 'warmde@system-2123.com', 1, '603b4046e251404fa7ea207d29991caf', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user_bookmarks`
--

CREATE TABLE `user_bookmarks` (
  `iduser_bookmarks` int(11) NOT NULL,
  `notice_time` varchar(45) DEFAULT NULL,
  `users_idusers` int(11) NOT NULL,
  `post_idpost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_bookmarks`
--

INSERT INTO `user_bookmarks` (`iduser_bookmarks`, `notice_time`, `users_idusers`, `post_idpost`) VALUES
(2, '2019-05-06 15:04:39', 1, 12),
(3, '2019-05-06 15:04:42', 1, 11),
(4, '2019-05-06 15:04:53', 1, 9);

-- --------------------------------------------------------

--
-- Table structure for table `user_followed_ad_catergories`
--

CREATE TABLE `user_followed_ad_catergories` (
  `hid` int(11) NOT NULL,
  `users_idusers` int(11) NOT NULL,
  `adcategory` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_followed_ad_catergories`
--

INSERT INTO `user_followed_ad_catergories` (`hid`, `users_idusers`, `adcategory`) VALUES
(1, 1, 6),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_login`
--

CREATE TABLE `user_login` (
  `iduser_login` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `users_idusers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_login`
--

INSERT INTO `user_login` (`iduser_login`, `username`, `password`, `users_idusers`) VALUES
(7, 'vidura', '1e3946ddafec8ef5296c5f7925e9c8f1', 1),
(8, 'dulaj', '1e3946ddafec8ef5296c5f7925e9c8f1', 2),
(9, 'maduranjana', '1e3946ddafec8ef5296c5f7925e9c8f1', 3),
(10, 'navindu', '1e3946ddafec8ef5296c5f7925e9c8f1', 4),
(11, 'lahiru', '1e3946ddafec8ef5296c5f7925e9c8f1', 5),
(12, 'kalana', '1e3946ddafec8ef5296c5f7925e9c8f1', 6),
(13, 'Chamod', '1e3946ddafec8ef5296c5f7925e9c8f1', 8),
(14, 'Bhanuka', '1e3946ddafec8ef5296c5f7925e9c8f1', 12),
(15, 'pundima', '1e3946ddafec8ef5296c5f7925e9c8f1', 13),
(16, 'Batman', '1e3946ddafec8ef5296c5f7925e9c8f1', 15),
(17, 'Dor', '1e3946ddafec8ef5296c5f7925e9c8f1', 22);

-- --------------------------------------------------------

--
-- Table structure for table `user_privacy`
--

CREATE TABLE `user_privacy` (
  `iduser_privacy` int(11) NOT NULL,
  `privacy_name` varchar(50) DEFAULT NULL,
  `users_idusers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_privacy`
--

INSERT INTO `user_privacy` (`iduser_privacy`, `privacy_name`, `users_idusers`) VALUES
(1, 'public', 1),
(2, 'public', 2),
(3, 'public', 3),
(4, 'public', 4),
(5, 'public', 5),
(6, 'public', 6),
(7, 'public', 8),
(8, 'public', 12),
(9, 'public', 13),
(10, 'public', 15),
(11, 'public', 20),
(12, 'public', 21),
(13, 'public', 22);

-- --------------------------------------------------------

--
-- Table structure for table `user_profile_pic`
--

CREATE TABLE `user_profile_pic` (
  `iduser_profile_pic` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `users_idusers` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_profile_pic`
--

INSERT INTO `user_profile_pic` (`iduser_profile_pic`, `image`, `users_idusers`) VALUES
(1, 'https://i.imgur.com/PfLXIaW.jpg', 1),
(2, 'https://i.imgur.com/XwTldcL.jpg', 2),
(3, 'https://i.imgur.com/rB7aokz.jpg', 3),
(4, 'https://i.imgur.com/FPmBA16.jpg', 4),
(5, 'img/Profile_avatar_placeholder_large.png', 5),
(6, 'https://i.imgur.com/HJmJ21b.jpg', 6),
(7, 'https://i.imgur.com/YaTKNA1.jpg', 8),
(8, 'https://i.imgur.com/NywVUdU.jpg', 12),
(9, 'https://i.imgur.com/FVJq6Av.jpg', 13),
(10, 'https://i.imgur.com/vL2CCRi.jpg', 15),
(11, 'https://lh6.googleusercontent.com/-49xCsO4UTbM/AAAAAAAAAAI/AAAAAAAAANk/vJakUlOo-kM/s96-c/photo.jpg', 20),
(12, 'https://lh6.googleusercontent.com/-OpBBWVTDgRE/AAAAAAAAAAI/AAAAAAAAAA8/Yyap1sthqKM/s96-c/photo.jpg', 21),
(13, 'https://i.imgur.com/nOixqR0.png', 22);

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `iduser_type` int(11) NOT NULL,
  `type_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`iduser_type`, `type_name`) VALUES
(1, 'Admin'),
(3, 'Customer'),
(2, 'User');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ads`
--
ALTER TABLE `ads`
  ADD PRIMARY KEY (`Adid`,`users_idusers`),
  ADD KEY `fk_Ads_users1_idx` (`users_idusers`),
  ADD KEY `fk_Ads_Ad_status1_idx` (`status`),
  ADD KEY `fk_Ads_Ad_categories1_idx` (`adcategory`);

--
-- Indexes for table `adtiming`
--
ALTER TABLE `adtiming`
  ADD PRIMARY KEY (`Ads_Adid`);

--
-- Indexes for table `ad_categories`
--
ALTER TABLE `ad_categories`
  ADD PRIMARY KEY (`idAd_category`);

--
-- Indexes for table `ad_status`
--
ALTER TABLE `ad_status`
  ADD PRIMARY KEY (`idAd_status`),
  ADD UNIQUE KEY `idAd_status_UNIQUE` (`idAd_status`);

--
-- Indexes for table `apphpi`
--
ALTER TABLE `apphpi`
  ADD PRIMARY KEY (`idAPPHPI`);

--
-- Indexes for table `app_layout`
--
ALTER TABLE `app_layout`
  ADD PRIMARY KEY (`users_idusers`),
  ADD KEY `fk_app_layout_users1_idx` (`users_idusers`);

--
-- Indexes for table `app_theme`
--
ALTER TABLE `app_theme`
  ADD PRIMARY KEY (`idapp_theme`),
  ADD KEY `fk_app_theme_users1_idx` (`users_idusers`);

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`idchat`),
  ADD KEY `fk_chat_users1_idx` (`user_sender`),
  ADD KEY `fk_chat_users2_idx` (`users_receiver`),
  ADD KEY `fk_chat_chattype1_idx` (`chattype_idchattype`);

--
-- Indexes for table `chattype`
--
ALTER TABLE `chattype`
  ADD PRIMARY KEY (`idchattype`);

--
-- Indexes for table `follow`
--
ALTER TABLE `follow`
  ADD PRIMARY KEY (`idfollow`),
  ADD KEY `fk_follow_users1_idx` (`sender`),
  ADD KEY `fk_follow_users2_idx` (`receiver`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`group_id`),
  ADD UNIQUE KEY `group id_UNIQUE` (`group_id`),
  ADD KEY `fk_Groups_users1_idx` (`groupadmin`);

--
-- Indexes for table `group_chat`
--
ALTER TABLE `group_chat`
  ADD KEY `fk_Group_chat_Groups1_idx` (`Groups_group_id`),
  ADD KEY `fk_Group_chat_users1_idx` (`users_idusers`);

--
-- Indexes for table `group_members`
--
ALTER TABLE `group_members`
  ADD PRIMARY KEY (`idGroup_members`,`Groups_group_id`),
  ADD KEY `fk_Group_members_Groups1_idx` (`Groups_group_id`),
  ADD KEY `fk_Group_members_users1_idx` (`members`);

--
-- Indexes for table `interfaces`
--
ALTER TABLE `interfaces`
  ADD PRIMARY KEY (`idinterfaces`),
  ADD UNIQUE KEY `url_UNIQUE` (`url`);

--
-- Indexes for table `login_sessions`
--
ALTER TABLE `login_sessions`
  ADD PRIMARY KEY (`idlogin_sessions`),
  ADD KEY `fk_login_sessions_user_login1_idx` (`user_login_iduser_login`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`idnotification`),
  ADD KEY `fk_notification_users1_idx` (`notificationfor`),
  ADD KEY `fk_notification_users2_idx` (`notificationfrom`),
  ADD KEY `fk_notification_notification-type1_idx` (`notification-type`);

--
-- Indexes for table `notification_type`
--
ALTER TABLE `notification_type`
  ADD PRIMARY KEY (`idnotification-type`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`idpost`),
  ADD KEY `fk_post_users1_idx` (`users_idusers`),
  ADD KEY `fk_post_post_type1_idx` (`post_type_idpost_type`),
  ADD KEY `fk_post_Post_Privacy1_idx` (`Post_Privacy`);

--
-- Indexes for table `post_comment`
--
ALTER TABLE `post_comment`
  ADD PRIMARY KEY (`idpost_comment`),
  ADD KEY `fk_post_comment_users1_idx` (`users_idusers`),
  ADD KEY `fk_post_comment_post1_idx` (`post_idpost`);

--
-- Indexes for table `post_privacy`
--
ALTER TABLE `post_privacy`
  ADD PRIMARY KEY (`idPost_Privacy`);

--
-- Indexes for table `post_rank`
--
ALTER TABLE `post_rank`
  ADD PRIMARY KEY (`idPost_rank`),
  ADD KEY `fk_Post_rank_post1_idx` (`post_idpost`),
  ADD KEY `fk_Post_rank_users1_idx` (`likedby`);

--
-- Indexes for table `post_status`
--
ALTER TABLE `post_status`
  ADD PRIMARY KEY (`idpost_status`),
  ADD KEY `fk_post_status_post1_idx` (`post_idpost`),
  ADD KEY `fk_post_status_users1_idx` (`users_idusers`);

--
-- Indexes for table `post_type`
--
ALTER TABLE `post_type`
  ADD PRIMARY KEY (`idpost_type`),
  ADD UNIQUE KEY `type_name_UNIQUE` (`type_name`);

--
-- Indexes for table `privilages`
--
ALTER TABLE `privilages`
  ADD PRIMARY KEY (`idPrivilages`),
  ADD KEY `fk_Privilages_user_type1_idx` (`user_type_iduser_type`),
  ADD KEY `fk_Privilages_interfaces1_idx` (`interfaces_idinterfaces`);

--
-- Indexes for table `purchase_history`
--
ALTER TABLE `purchase_history`
  ADD PRIMARY KEY (`idpurchase_history`,`users_idusers`),
  ADD KEY `fk_purchase_history_users1_idx` (`users_idusers`),
  ADD KEY `fk_purchase_history_Ad_status1_idx` (`status`);

--
-- Indexes for table `tailzconfig`
--
ALTER TABLE `tailzconfig`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `uap`
--
ALTER TABLE `uap`
  ADD PRIMARY KEY (`idUAP`),
  ADD KEY `fk_User_location_users1_idx` (`users_idusers`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`idusers`),
  ADD KEY `fk_users_user_type_idx` (`user_type_iduser_type`);

--
-- Indexes for table `user_bookmarks`
--
ALTER TABLE `user_bookmarks`
  ADD PRIMARY KEY (`iduser_bookmarks`),
  ADD KEY `fk_user_notice_users1_idx` (`users_idusers`),
  ADD KEY `fk_user_bookmarks_post1_idx` (`post_idpost`);

--
-- Indexes for table `user_followed_ad_catergories`
--
ALTER TABLE `user_followed_ad_catergories`
  ADD PRIMARY KEY (`hid`),
  ADD KEY `fk_user_warning_users1_idx` (`users_idusers`),
  ADD KEY `fk_user_followed_ad_catergories_Ad_categories1_idx` (`adcategory`);

--
-- Indexes for table `user_login`
--
ALTER TABLE `user_login`
  ADD PRIMARY KEY (`iduser_login`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`),
  ADD KEY `fk_user_login_users1_idx` (`users_idusers`);

--
-- Indexes for table `user_privacy`
--
ALTER TABLE `user_privacy`
  ADD PRIMARY KEY (`iduser_privacy`),
  ADD KEY `fk_user_status_users1_idx` (`users_idusers`);

--
-- Indexes for table `user_profile_pic`
--
ALTER TABLE `user_profile_pic`
  ADD PRIMARY KEY (`iduser_profile_pic`),
  ADD KEY `fk_user_profile_pic_users1_idx` (`users_idusers`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`iduser_type`),
  ADD UNIQUE KEY `type_name_UNIQUE` (`type_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ads`
--
ALTER TABLE `ads`
  MODIFY `Adid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ad_status`
--
ALTER TABLE `ad_status`
  MODIFY `idAd_status` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `app_theme`
--
ALTER TABLE `app_theme`
  MODIFY `idapp_theme` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `chat`
  MODIFY `idchat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `chattype`
--
ALTER TABLE `chattype`
  MODIFY `idchattype` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `follow`
--
ALTER TABLE `follow`
  MODIFY `idfollow` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `group_members`
--
ALTER TABLE `group_members`
  MODIFY `idGroup_members` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `interfaces`
--
ALTER TABLE `interfaces`
  MODIFY `idinterfaces` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `login_sessions`
--
ALTER TABLE `login_sessions`
  MODIFY `idlogin_sessions` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=268;

--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `idnotification` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `notification_type`
--
ALTER TABLE `notification_type`
  MODIFY `idnotification-type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `idpost` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `post_comment`
--
ALTER TABLE `post_comment`
  MODIFY `idpost_comment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `post_privacy`
--
ALTER TABLE `post_privacy`
  MODIFY `idPost_Privacy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `post_rank`
--
ALTER TABLE `post_rank`
  MODIFY `idPost_rank` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `post_status`
--
ALTER TABLE `post_status`
  MODIFY `idpost_status` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post_type`
--
ALTER TABLE `post_type`
  MODIFY `idpost_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `privilages`
--
ALTER TABLE `privilages`
  MODIFY `idPrivilages` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `purchase_history`
--
ALTER TABLE `purchase_history`
  MODIFY `idpurchase_history` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tailzconfig`
--
ALTER TABLE `tailzconfig`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `uap`
--
ALTER TABLE `uap`
  MODIFY `idUAP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `idusers` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `user_bookmarks`
--
ALTER TABLE `user_bookmarks`
  MODIFY `iduser_bookmarks` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_followed_ad_catergories`
--
ALTER TABLE `user_followed_ad_catergories`
  MODIFY `hid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_login`
--
ALTER TABLE `user_login`
  MODIFY `iduser_login` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user_privacy`
--
ALTER TABLE `user_privacy`
  MODIFY `iduser_privacy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `user_profile_pic`
--
ALTER TABLE `user_profile_pic`
  MODIFY `iduser_profile_pic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `user_type`
--
ALTER TABLE `user_type`
  MODIFY `iduser_type` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ads`
--
ALTER TABLE `ads`
  ADD CONSTRAINT `fk_Ads_Ad_categories1` FOREIGN KEY (`adcategory`) REFERENCES `ad_categories` (`idAd_category`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Ads_Ad_status1` FOREIGN KEY (`status`) REFERENCES `ad_status` (`idAd_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Ads_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `adtiming`
--
ALTER TABLE `adtiming`
  ADD CONSTRAINT `fk_adtiming_Ads1` FOREIGN KEY (`Ads_Adid`) REFERENCES `ads` (`Adid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `app_layout`
--
ALTER TABLE `app_layout`
  ADD CONSTRAINT `fk_app_layout_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `app_theme`
--
ALTER TABLE `app_theme`
  ADD CONSTRAINT `fk_app_theme_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `fk_chat_chattype1` FOREIGN KEY (`chattype_idchattype`) REFERENCES `chattype` (`idchattype`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_chat_users1` FOREIGN KEY (`user_sender`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_chat_users2` FOREIGN KEY (`users_receiver`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `follow`
--
ALTER TABLE `follow`
  ADD CONSTRAINT `fk_follow_users1` FOREIGN KEY (`sender`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_follow_users2` FOREIGN KEY (`receiver`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `fk_Groups_users1` FOREIGN KEY (`groupadmin`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `group_chat`
--
ALTER TABLE `group_chat`
  ADD CONSTRAINT `fk_Group_chat_Groups1` FOREIGN KEY (`Groups_group_id`) REFERENCES `groups` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Group_chat_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `group_members`
--
ALTER TABLE `group_members`
  ADD CONSTRAINT `fk_Group_members_Groups1` FOREIGN KEY (`Groups_group_id`) REFERENCES `groups` (`group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Group_members_users1` FOREIGN KEY (`members`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `login_sessions`
--
ALTER TABLE `login_sessions`
  ADD CONSTRAINT `fk_login_sessions_user_login1` FOREIGN KEY (`user_login_iduser_login`) REFERENCES `user_login` (`iduser_login`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `fk_notification_notification-type1` FOREIGN KEY (`notification-type`) REFERENCES `notification_type` (`idnotification-type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_notification_users1` FOREIGN KEY (`notificationfor`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_notification_users2` FOREIGN KEY (`notificationfrom`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `fk_post_Post_Privacy1` FOREIGN KEY (`Post_Privacy`) REFERENCES `post_privacy` (`idPost_Privacy`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_post_post_type1` FOREIGN KEY (`post_type_idpost_type`) REFERENCES `post_type` (`idpost_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_post_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `post_comment`
--
ALTER TABLE `post_comment`
  ADD CONSTRAINT `fk_post_comment_post1` FOREIGN KEY (`post_idpost`) REFERENCES `post` (`idpost`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_post_comment_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `post_rank`
--
ALTER TABLE `post_rank`
  ADD CONSTRAINT `fk_Post_rank_post1` FOREIGN KEY (`post_idpost`) REFERENCES `post` (`idpost`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Post_rank_users1` FOREIGN KEY (`likedby`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `post_status`
--
ALTER TABLE `post_status`
  ADD CONSTRAINT `fk_post_status_post1` FOREIGN KEY (`post_idpost`) REFERENCES `post` (`idpost`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_post_status_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `privilages`
--
ALTER TABLE `privilages`
  ADD CONSTRAINT `fk_Privilages_interfaces1` FOREIGN KEY (`interfaces_idinterfaces`) REFERENCES `interfaces` (`idinterfaces`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Privilages_user_type1` FOREIGN KEY (`user_type_iduser_type`) REFERENCES `user_type` (`iduser_type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `purchase_history`
--
ALTER TABLE `purchase_history`
  ADD CONSTRAINT `fk_purchase_history_Ad_status1` FOREIGN KEY (`status`) REFERENCES `ad_status` (`idAd_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_purchase_history_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `uap`
--
ALTER TABLE `uap`
  ADD CONSTRAINT `fk_User_location_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_users_user_type` FOREIGN KEY (`user_type_iduser_type`) REFERENCES `user_type` (`iduser_type`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_bookmarks`
--
ALTER TABLE `user_bookmarks`
  ADD CONSTRAINT `fk_user_bookmarks_post1` FOREIGN KEY (`post_idpost`) REFERENCES `post` (`idpost`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_notice_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_followed_ad_catergories`
--
ALTER TABLE `user_followed_ad_catergories`
  ADD CONSTRAINT `fk_user_followed_ad_catergories_Ad_categories1` FOREIGN KEY (`adcategory`) REFERENCES `ad_categories` (`idAd_category`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_user_warning_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_login`
--
ALTER TABLE `user_login`
  ADD CONSTRAINT `fk_user_login_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_privacy`
--
ALTER TABLE `user_privacy`
  ADD CONSTRAINT `fk_user_status_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user_profile_pic`
--
ALTER TABLE `user_profile_pic`
  ADD CONSTRAINT `fk_user_profile_pic_users1` FOREIGN KEY (`users_idusers`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
