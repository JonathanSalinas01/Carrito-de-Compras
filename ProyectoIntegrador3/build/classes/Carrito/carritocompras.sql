-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-04-2023 a las 07:04:56
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `carritocompras`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `IDcarrito` int(11) NOT NULL,
  `numCarro` int(11) NOT NULL,
  `IDcliente` int(11) DEFAULT NULL,
  `montoCarrito` decimal(14,2) DEFAULT NULL,
  `descuento` decimal(14,2) NOT NULL,
  `fechaCompra` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carritoitems`
--

CREATE TABLE `carritoitems` (
  `IDcarrito` int(11) NOT NULL,
  `cliente` varchar(50) NOT NULL,
  `numCarro` int(11) NOT NULL,
  `nombreProd` varchar(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `montoItem` decimal(14,2) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `carritoitems`
--

INSERT INTO `carritoitems` (`IDcarrito`, `cliente`, `numCarro`, `nombreProd`, `cantidad`, `montoItem`, `fecha`) VALUES
(1, 'Ortiz Maria', 30, 'Azúcar', 3, 1167.00, '2023-04-30'),
(2, 'Ortiz Maria', 30, 'Fideos', 1, 308.95, '2023-04-30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `IDcliente` int(11) NOT NULL,
  `nombreComplet` varchar(50) NOT NULL,
  `dni` int(11) NOT NULL,
  `cuil` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`IDcliente`, `nombreComplet`, `dni`, `cuil`) VALUES
(1, 'Jonathan Salinas', 39529469, '20-39529469-6'),
(2, 'Denis Salinas', 40756426, '20-40756426-6'),
(3, 'Marcelo Salinas', 22814062, '10-22814062-3'),
(4, 'Ortiz Maria', 23216076, '15-23216076-8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `IDproducto` int(11) NOT NULL,
  `nombreProducto` varchar(15) NOT NULL,
  `descriProducto` varchar(40) NOT NULL,
  `precio` decimal(14,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`IDproducto`, `nombreProducto`, `descriProducto`, `precio`) VALUES
(1, 'Fideos', 'Fideos Tirabuzón Matarazzo 500gr', 308.95),
(2, 'Arroz', 'Arroz largo fino 1Kg', 258.13),
(3, 'Café', 'Café La Morenita 250gr', 750.41),
(4, 'Queso Crema', 'Queso Crema Casancrem 480gr', 931.69),
(5, 'Mermelada', 'Mermelada de Duraznos 390gr', 427.00),
(6, 'Alimento Perro', 'Alimento Perros Dogui 3 Kg', 1641.01),
(7, 'Azúcar', 'Azúcar 1 Kg', 389.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`IDcarrito`),
  ADD KEY `IDcliente` (`IDcliente`);

--
-- Indices de la tabla `carritoitems`
--
ALTER TABLE `carritoitems`
  ADD PRIMARY KEY (`IDcarrito`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`IDcliente`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`IDproducto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `IDcarrito` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `carritoitems`
--
ALTER TABLE `carritoitems`
  MODIFY `IDcarrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `IDcliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `IDproducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`IDcliente`) REFERENCES `clientes` (`IDcliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
