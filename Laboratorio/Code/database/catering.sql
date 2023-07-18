-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Lug 18, 2023 alle 15:56
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `events`
--

CREATE TABLE `events` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL,
  `organizer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `events`
--

INSERT INTO `events` (`id`, `name`, `date_start`, `date_end`, `expected_participants`, `organizer_id`) VALUES
(1, 'Convegno Agile Community', '2020-09-25', '2020-09-25', 100, 2),
(2, 'Compleanno di Manuela', '2020-08-13', '2020-08-13', 25, 2),
(3, 'Fiera del Sedano Rapa', '2020-10-02', '2020-10-04', 400, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `menufeatures`
--

CREATE TABLE `menufeatures` (
  `menu_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT '',
  `value` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `menufeatures`
--

INSERT INTO `menufeatures` (`menu_id`, `name`, `value`) VALUES
(80, 'Richiede cuoco', 0),
(80, 'Buffet', 0),
(80, 'Richiede cucina', 0),
(80, 'Finger food', 0),
(80, 'Piatti caldi', 0),
(82, 'Richiede cuoco', 0),
(82, 'Buffet', 0),
(82, 'Richiede cucina', 0),
(82, 'Finger food', 0),
(82, 'Piatti caldi', 0),
(86, 'Richiede cuoco', 0),
(86, 'Buffet', 0),
(86, 'Richiede cucina', 0),
(86, 'Finger food', 0),
(86, 'Piatti caldi', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `menuitems`
--

CREATE TABLE `menuitems` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `section_id` int(11) DEFAULT NULL,
  `description` tinytext DEFAULT NULL,
  `recipe_id` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `menuitems`
--

INSERT INTO `menuitems` (`id`, `menu_id`, `section_id`, `description`, `recipe_id`, `position`) VALUES
(96, 80, 0, 'Croissant vuoti', 9, 0),
(97, 80, 0, 'Croissant alla marmellata', 9, 1),
(98, 80, 0, 'Pane al cioccolato mignon', 10, 2),
(99, 80, 0, 'Panini al latte con prosciutto crudo', 12, 4),
(100, 80, 0, 'Panini al latte con prosciutto cotto', 12, 5),
(101, 80, 0, 'Panini al latte con formaggio spalmabile alle erbe', 12, 6),
(102, 80, 0, 'Girelle all\'uvetta mignon', 11, 3),
(103, 82, 0, 'Biscotti', 13, 1),
(104, 82, 0, 'Lingue di gatto', 14, 2),
(105, 82, 0, 'Bigné alla crema', 15, 3),
(106, 82, 0, 'Bigné al caffè', 15, 4),
(107, 82, 0, 'Pizzette', 16, 5),
(108, 82, 0, 'Croissant al prosciutto crudo mignon', 9, 6),
(109, 82, 0, 'Tramezzini tonno e carciofini mignon', 17, 7),
(112, 86, 41, 'Vitello tonnato', 1, 0),
(113, 86, 41, 'Carpaccio di spada', 2, 1),
(114, 86, 41, 'Alici marinate', 3, 2),
(115, 86, 42, 'Penne alla messinese', 5, 0),
(116, 86, 42, 'Risotto alla zucca', 20, 1),
(117, 86, 43, 'Salmone al forno', 8, 0),
(118, 86, 44, 'Sorbetto al limone', 18, 0),
(119, 86, 44, 'Torta Saint Honoré', 19, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `menus`
--

CREATE TABLE `menus` (
  `id` int(11) NOT NULL,
  `title` tinytext DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `published` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `menus`
--

INSERT INTO `menus` (`id`, `title`, `owner_id`, `published`) VALUES
(80, 'Coffee break mattutino', 2, 1),
(82, 'Coffee break pomeridiano', 2, 1),
(86, 'Cena di compleanno pesce', 3, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `menusections`
--

CREATE TABLE `menusections` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `menusections`
--

INSERT INTO `menusections` (`id`, `menu_id`, `name`, `position`) VALUES
(41, 86, 'Antipasti', 0),
(42, 86, 'Primi', 1),
(43, 86, 'Secondi', 2),
(44, 86, 'Dessert', 3),
(45, 87, 'Antipasti', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `recipes`
--

CREATE TABLE `recipes` (
  `id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL,
  `preparation` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `recipes`
--

INSERT INTO `recipes` (`id`, `name`, `preparation`) VALUES
(1, 'Vitello tonnato', 0),
(2, 'Carpaccio di spada', 0),
(3, 'Alici marinate', 0),
(4, 'Insalata di riso', 0),
(5, 'Penne al sugo di baccalà', 0),
(6, 'Pappa al pomodoro', 0),
(7, 'Hamburger con bacon e cipolla caramellata', 0),
(8, 'Salmone al forno', 0),
(9, 'Croissant', 0),
(10, 'Pane al cioccolato', 0),
(11, 'Girelle all\'uvetta', 0),
(12, 'Panini al latte', 0),
(13, 'Biscotti di pasta frolla', 0),
(14, 'Lingue di gatto', 0),
(15, 'Bigné farciti', 0),
(16, 'Pizzette', 0),
(17, 'Tramezzini', 0),
(18, 'Sorbetto al limone', 0),
(19, 'Torta Saint Honoré', 0),
(20, 'Risotto alla zucca', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `roles`
--

CREATE TABLE `roles` (
  `id` char(1) NOT NULL,
  `role` varchar(128) NOT NULL DEFAULT 'servizio'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
('c', 'cuoco'),
('h', 'chef'),
('o', 'organizzatore'),
('s', 'servizio');

-- --------------------------------------------------------

--
-- Struttura della tabella `services`
--

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `proposed_menu_id` int(11) NOT NULL DEFAULT 0,
  `approved_menu_id` int(11) DEFAULT 0,
  `service_date` date DEFAULT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `services`
--

INSERT INTO `services` (`id`, `event_id`, `name`, `proposed_menu_id`, `approved_menu_id`, `service_date`, `time_start`, `time_end`, `expected_participants`) VALUES
(1, 2, 'Cena', 86, 0, '2020-08-13', '20:00:00', '23:30:00', 25),
(2, 1, 'Coffee break mattino', 0, 80, '2020-09-25', '10:30:00', '11:30:00', 100),
(3, 1, 'Colazione di lavoro', 0, 0, '2020-09-25', '13:00:00', '14:00:00', 80),
(4, 1, 'Coffee break pomeriggio', 0, 82, '2020-09-25', '16:00:00', '16:30:00', 100),
(5, 1, 'Cena sociale', 0, 0, '2020-09-25', '20:00:00', '22:30:00', 40),
(6, 3, 'Pranzo giorno 1', 0, 0, '2020-10-02', '12:00:00', '15:00:00', 200),
(7, 3, 'Pranzo giorno 2', 0, 0, '2020-10-03', '12:00:00', '15:00:00', 300),
(8, 3, 'Pranzo giorno 3', 0, 0, '2020-10-04', '12:00:00', '15:00:00', 400);

-- --------------------------------------------------------

--
-- Struttura della tabella `summarysheets`
--

CREATE TABLE `summarysheets` (
  `id` int(11) NOT NULL,
  `chef` int(11) NOT NULL,
  `service` int(11) NOT NULL,
  `task` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `summarysheets`
--

INSERT INTO `summarysheets` (`id`, `chef`, `service`, `task`) VALUES
(1, 2, 2, NULL),
(8, 2, 4, NULL),
(9, 3, 7, 2),
(11, 2, 6, NULL),
(14, 2, 2, NULL),
(15, 2, 2, NULL),
(16, 2, 2, NULL),
(17, 2, 2, NULL),
(18, 2, 2, NULL),
(19, 2, 2, NULL),
(20, 2, 2, NULL),
(21, 2, 2, NULL),
(22, 2, 2, NULL),
(23, 2, 2, NULL),
(24, 2, 2, NULL),
(25, 2, 2, NULL),
(26, 2, 2, NULL),
(27, 2, 2, NULL),
(35, 2, 2, NULL),
(36, 2, 2, NULL),
(37, 2, 2, NULL),
(38, 2, 2, NULL),
(39, 2, 2, NULL),
(40, 2, 2, NULL),
(41, 2, 2, NULL),
(42, 2, 2, NULL),
(44, 2, 6, NULL),
(45, 2, 2, NULL),
(46, 2, 2, NULL),
(47, 2, 2, NULL),
(48, 2, 2, NULL),
(49, 2, 2, NULL),
(50, 2, 2, NULL),
(51, 2, 2, NULL),
(52, 2, 2, NULL),
(53, 2, 2, NULL),
(54, 2, 2, NULL),
(55, 2, 2, NULL),
(56, 2, 2, NULL),
(57, 2, 2, NULL),
(58, 2, 2, NULL),
(59, 2, 2, NULL),
(60, 2, 2, NULL),
(61, 2, 2, NULL),
(62, 2, 2, NULL),
(63, 2, 2, NULL),
(64, 2, 2, NULL),
(65, 2, 2, NULL),
(66, 2, 2, NULL),
(67, 2, 2, NULL),
(68, 2, 2, NULL),
(69, 2, 2, NULL),
(70, 2, 2, NULL),
(71, 2, 2, NULL),
(72, 2, 2, NULL),
(73, 2, 2, NULL),
(74, 2, 2, NULL),
(75, 2, 2, NULL),
(76, 2, 2, NULL),
(77, 2, 2, NULL),
(78, 2, 2, NULL),
(79, 2, 2, NULL),
(80, 2, 2, NULL),
(81, 2, 2, NULL),
(82, 2, 2, NULL),
(83, 2, 2, NULL),
(84, 2, 2, NULL),
(85, 2, 2, NULL),
(86, 2, 2, NULL),
(87, 2, 2, NULL),
(88, 2, 2, NULL),
(89, 2, 2, NULL),
(90, 2, 2, NULL),
(91, 2, 2, NULL),
(92, 2, 2, NULL),
(93, 2, 2, NULL),
(94, 2, 2, NULL),
(95, 2, 2, NULL),
(96, 2, 2, NULL),
(97, 2, 2, NULL),
(98, 2, 2, NULL),
(99, 2, 2, NULL),
(100, 2, 2, NULL),
(101, 2, 2, NULL),
(102, 2, 2, NULL),
(103, 2, 2, NULL),
(104, 2, 2, NULL),
(105, 2, 2, NULL),
(107, 2, 2, NULL),
(108, 2, 2, NULL),
(109, 2, 2, NULL),
(110, 2, 2, NULL),
(111, 2, 2, NULL),
(112, 2, 2, NULL),
(113, 2, 2, NULL),
(114, 2, 2, NULL),
(115, 2, 2, NULL),
(116, 2, 2, NULL),
(117, 2, 2, NULL),
(118, 2, 4, NULL),
(120, 2, 4, NULL),
(122, 2, 2, NULL),
(123, 2, 2, NULL),
(124, 2, 2, NULL),
(125, 2, 2, NULL),
(126, 2, 2, NULL),
(127, 2, 2, NULL),
(128, 2, 2, NULL),
(129, 2, 2, NULL),
(130, 2, 2, NULL),
(131, 2, 2, NULL),
(132, 2, 2, NULL),
(133, 2, 2, NULL),
(134, 2, 2, NULL),
(135, 2, 2, NULL),
(136, 2, 2, NULL),
(137, 2, 2, NULL),
(138, 2, 2, NULL),
(139, 2, 2, NULL),
(140, 2, 2, NULL),
(141, 2, 2, NULL),
(142, 2, 2, NULL),
(143, 2, 2, NULL),
(144, 2, 2, NULL),
(145, 2, 2, NULL),
(146, 2, 2, NULL),
(147, 2, 2, NULL),
(148, 2, 2, NULL),
(149, 2, 2, NULL),
(150, 2, 2, NULL),
(151, 2, 2, NULL),
(152, 2, 2, NULL),
(153, 2, 2, NULL),
(154, 2, 2, NULL),
(155, 2, 2, NULL),
(156, 2, 2, NULL),
(157, 2, 2, NULL),
(158, 2, 2, NULL),
(159, 2, 2, NULL),
(160, 2, 2, NULL),
(161, 2, 2, NULL),
(162, 2, 2, NULL),
(163, 2, 2, NULL),
(164, 2, 2, NULL),
(165, 2, 2, NULL),
(166, 2, 2, NULL),
(167, 2, 2, NULL),
(168, 2, 2, NULL),
(169, 2, 2, NULL),
(170, 2, 2, NULL),
(171, 2, 2, NULL),
(172, 2, 2, NULL),
(173, 2, 2, NULL),
(174, 2, 2, NULL),
(175, 2, 2, NULL),
(176, 2, 2, NULL),
(177, 2, 2, NULL),
(178, 2, 4, NULL),
(180, 2, 2, NULL),
(181, 2, 2, NULL),
(182, 2, 2, NULL),
(183, 2, 2, NULL),
(184, 2, 2, NULL),
(185, 2, 2, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `portion` int(11) DEFAULT NULL,
  `runningTime` double DEFAULT NULL,
  `recipe` int(11) NOT NULL,
  `turn` int(11) DEFAULT NULL,
  `cook` int(11) DEFAULT NULL,
  `sumShId` int(11) NOT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `tasks`
--

INSERT INTO `tasks` (`id`, `quantity`, `portion`, `runningTime`, `recipe`, `turn`, `cook`, `sumShId`, `position`) VALUES
(2, 50, NULL, 15, 3, NULL, 5, 1, 0),
(3, NULL, 100, 10.3, 2, NULL, 6, 9, 0),
(16, NULL, NULL, NULL, 2, NULL, NULL, 39, 0),
(17, NULL, NULL, NULL, 2, NULL, NULL, 40, 0),
(18, NULL, NULL, NULL, 1, NULL, NULL, 40, 1),
(19, NULL, NULL, NULL, 3, NULL, NULL, 40, 2),
(20, NULL, NULL, NULL, 2, NULL, NULL, 41, 0),
(21, NULL, NULL, NULL, 1, NULL, NULL, 41, 1),
(22, NULL, NULL, NULL, 3, NULL, NULL, 41, 2),
(180, NULL, NULL, NULL, 2, 2, NULL, 122, 1),
(182, NULL, NULL, NULL, 3, 2, NULL, 122, 2),
(196, NULL, NULL, NULL, 1, 2, NULL, 127, 0),
(197, NULL, NULL, NULL, 3, 2, NULL, 127, 2),
(260, 69, 0, 3.3, 3, 2, NULL, 150, 2),
(311, NULL, NULL, NULL, 2, 2, 5, 175, 1),
(317, 69, 0, 3.3, 2, 2, 5, 177, 1),
(318, NULL, NULL, NULL, 1, 2, NULL, 177, 0),
(319, NULL, NULL, NULL, 3, 2, NULL, 177, 2),
(320, 0, 18, 15.34, 2, 2, 5, 180, 0),
(322, 69, 0, 3.3, 2, 2, 5, 182, 1),
(323, NULL, NULL, NULL, 1, 2, NULL, 182, 0),
(324, NULL, NULL, NULL, 3, 2, NULL, 182, 2),
(325, 69, 0, 3.3, 2, 2, 5, 184, 1),
(326, NULL, NULL, NULL, 1, 2, NULL, 184, 0),
(327, NULL, NULL, NULL, 3, 2, NULL, 184, 2),
(328, 69, 0, 3.3, 2, 2, 5, 185, 1),
(329, NULL, NULL, NULL, 1, 2, NULL, 185, 0),
(330, NULL, NULL, NULL, 3, 2, NULL, 185, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `turns`
--

CREATE TABLE `turns` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `location` varchar(100) NOT NULL,
  `time` time NOT NULL,
  `complete` tinyint(1) NOT NULL,
  `cook` int(11) NOT NULL,
  `task` int(11) NOT NULL,
  `cookAvailable` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `turns`
--

INSERT INTO `turns` (`id`, `date`, `location`, `time`, `complete`, `cook`, `task`, `cookAvailable`) VALUES
(2, '2023-07-28', 'Torino', '10:30:00', 0, 6, 2, 1),
(3, '2023-08-08', 'Milano navigli', '20:30:00', 1, 7, 180, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `userroles`
--

CREATE TABLE `userroles` (
  `user_id` int(11) NOT NULL,
  `role_id` char(1) NOT NULL DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `userroles`
--

INSERT INTO `userroles` (`user_id`, `role_id`) VALUES
(1, 'o'),
(2, 'o'),
(2, 'h'),
(3, 'h'),
(4, 'h'),
(4, 'c'),
(5, 'c'),
(6, 'c'),
(7, 'c'),
(8, 's'),
(9, 's'),
(10, 's'),
(7, 's');

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(128) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`id`, `username`) VALUES
(1, 'Carlin'),
(2, 'Lidia'),
(3, 'Tony'),
(4, 'Marinella'),
(5, 'Guido'),
(6, 'Antonietta'),
(7, 'Paola'),
(8, 'Silvia'),
(9, 'Marco'),
(10, 'Piergiorgio');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `menuitems`
--
ALTER TABLE `menuitems`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `menus`
--
ALTER TABLE `menus`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `menusections`
--
ALTER TABLE `menusections`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `recipes`
--
ALTER TABLE `recipes`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `summarysheets`
--
ALTER TABLE `summarysheets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sumSh_chef` (`chef`),
  ADD KEY `sumSh_ser` (`service`),
  ADD KEY `sumSh_task` (`task`);

--
-- Indici per le tabelle `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `task_recipe` (`recipe`),
  ADD KEY `task_turn` (`turn`),
  ADD KEY `task_cook` (`cook`),
  ADD KEY `task_sumShId` (`sumShId`);

--
-- Indici per le tabelle `turns`
--
ALTER TABLE `turns`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tur_cook` (`cook`),
  ADD KEY `turn_task` (`task`);

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `events`
--
ALTER TABLE `events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `menuitems`
--
ALTER TABLE `menuitems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT per la tabella `menus`
--
ALTER TABLE `menus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT per la tabella `menusections`
--
ALTER TABLE `menusections`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT per la tabella `recipes`
--
ALTER TABLE `recipes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT per la tabella `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `summarysheets`
--
ALTER TABLE `summarysheets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=186;

--
-- AUTO_INCREMENT per la tabella `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=331;

--
-- AUTO_INCREMENT per la tabella `turns`
--
ALTER TABLE `turns`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `summarysheets`
--
ALTER TABLE `summarysheets`
  ADD CONSTRAINT `sumSh_chef` FOREIGN KEY (`chef`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sumSh_ser` FOREIGN KEY (`service`) REFERENCES `services` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sumSh_task` FOREIGN KEY (`task`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `task_cook` FOREIGN KEY (`cook`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_recipe` FOREIGN KEY (`recipe`) REFERENCES `recipes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_sumShId` FOREIGN KEY (`sumShId`) REFERENCES `summarysheets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `task_turn` FOREIGN KEY (`turn`) REFERENCES `turns` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `turns`
--
ALTER TABLE `turns`
  ADD CONSTRAINT `tur_cook` FOREIGN KEY (`cook`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `turn_task` FOREIGN KEY (`task`) REFERENCES `tasks` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
