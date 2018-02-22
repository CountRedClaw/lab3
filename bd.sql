-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.7.21-log - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных db_lab3
CREATE DATABASE IF NOT EXISTS `db_lab3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_lab3`;

-- Дамп структуры для таблица db_lab3.groups
CREATE TABLE IF NOT EXISTS `groups` (
  `gr_id` int(11) NOT NULL AUTO_INCREMENT,
  `gr_name` varchar(50) NOT NULL,
  PRIMARY KEY (`gr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_lab3.groups: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` (`gr_id`, `gr_name`) VALUES
	(31, 'PI-1501'),
	(32, 'Mob-1501'),
	(33, 'STHM');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;

-- Дамп структуры для таблица db_lab3.students
CREATE TABLE IF NOT EXISTS `students` (
  `st_id` int(11) NOT NULL AUTO_INCREMENT,
  `st_name` varchar(50) NOT NULL,
  `st_surname` varchar(50) NOT NULL,
  `st_group` int(11) NOT NULL,
  PRIMARY KEY (`st_id`),
  KEY `FK_students_groups` (`st_group`),
  CONSTRAINT `FK_students_groups` FOREIGN KEY (`st_group`) REFERENCES `groups` (`gr_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_lab3.students: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` (`st_id`, `st_name`, `st_surname`, `st_group`) VALUES
	(29, 'Iliya', 'Borsuk', 31),
	(31, 's', 's', 33),
	(32, 'gs', 'gs', 32),
	(35, 'd', 'd', 33),
	(36, 'd', 'd', 31);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
