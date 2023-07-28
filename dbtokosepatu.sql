-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 28, 2023 at 10:13 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbtokosepatu`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id_brand` int NOT NULL,
  `brand` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id_brand`, `brand`) VALUES
(1, 'aerostreat'),
(3, 'vans'),
(4, 'alkohol'),
(6, 'Nike');

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail_transaksi` int NOT NULL,
  `id_transaksi` int NOT NULL,
  `id_produk_detail` int NOT NULL,
  `harga` float NOT NULL,
  `quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail_transaksi`, `id_transaksi`, `id_produk_detail`, `harga`, `quantity`) VALUES
(2, 2, 3, 4000, 1),
(3, 3, 7, 4000, 1),
(4, 4, 1, 12000, 3),
(5, 5, 2, 4000, 1),
(8, 10, 4, 16000, 4);

-- --------------------------------------------------------

--
-- Table structure for table `produk`
--

CREATE TABLE `produk` (
  `id_produk` int NOT NULL,
  `id_brand` int NOT NULL,
  `nama_product` varchar(255) NOT NULL,
  `harga` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `produk`
--

INSERT INTO `produk` (`id_produk`, `id_brand`, `nama_product`, `harga`) VALUES
(1, 1, 'Massive Low Hitam', 4000),
(2, 1, 'aerostreatmboh', 4000),
(5, 3, 'twizz', 4000);

-- --------------------------------------------------------

--
-- Table structure for table `produk_detail`
--

CREATE TABLE `produk_detail` (
  `id_produk_detail` int NOT NULL,
  `id_produk` int NOT NULL,
  `ukuran` int NOT NULL,
  `warna` varchar(255) NOT NULL,
  `stock` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `produk_detail`
--

INSERT INTO `produk_detail` (`id_produk_detail`, `id_produk`, `ukuran`, `warna`, `stock`) VALUES
(1, 1, 42, 'merah', 9),
(2, 1, 42, 'black', 2),
(3, 1, 42, 'blue', 2),
(4, 1, 37, 'salmon', 8),
(7, 5, 42, 'lali', 0),
(8, 5, 42, 'merah', 10);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int NOT NULL,
  `id_user` int NOT NULL,
  `username_admin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `total_harga` float NOT NULL,
  `tgl_transaksi` timestamp NOT NULL,
  `status` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `username_admin`, `total_harga`, `tgl_transaksi`, `status`) VALUES
(2, 6, '', 8000, '2023-06-23 12:15:11', 2),
(3, 7, '', 4000, '2023-07-20 07:24:22', 2),
(4, 7, 'admin', 12000, '2023-07-24 09:28:44', 2),
(5, 10, '', 4000, '2023-07-24 09:32:30', 0),
(6, 8, 'fahmi', 40000, '2023-07-28 08:24:22', 2),
(10, 7, 'admin', 16000, '2023-07-28 09:03:45', 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` tinyint NOT NULL,
  `active` tinyint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `role`, `active`) VALUES
(1, 'admin', 'adm00n', 1, 1),
(2, 'fahmi', 'sincan', 2, 1),
(6, 'lincon', 'abraham', 0, 1),
(7, 'awan', '123', 0, 1),
(8, 'cls', 'cls', 0, 1),
(9, 'fahim', '456', 0, 1),
(10, 'adib', 'adib', 0, 1),
(11, 'SuckMyDick123', 'dick123', 0, 1),
(12, 'adminbaru', 'adm00n', 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id_brand`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail_transaksi`),
  ADD KEY `fk_detailtransaksi_to_transaksi` (`id_transaksi`),
  ADD KEY `fk_detailtransaksi_to_produkdetail` (`id_produk_detail`);

--
-- Indexes for table `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`),
  ADD KEY `fk_produk_to_brand` (`id_brand`);

--
-- Indexes for table `produk_detail`
--
ALTER TABLE `produk_detail`
  ADD PRIMARY KEY (`id_produk_detail`),
  ADD KEY `fk_produkdetail_to_produk` (`id_produk`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `fk_transaksi_to_users` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id_brand` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail_transaksi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `produk_detail`
--
ALTER TABLE `produk_detail`
  MODIFY `id_produk_detail` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `fk_detailtransaksi_to_produkdetail` FOREIGN KEY (`id_produk_detail`) REFERENCES `produk_detail` (`id_produk_detail`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_detailtransaksi_to_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `produk`
--
ALTER TABLE `produk`
  ADD CONSTRAINT `fk_produk_to_brand` FOREIGN KEY (`id_brand`) REFERENCES `brand` (`id_brand`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `produk_detail`
--
ALTER TABLE `produk_detail`
  ADD CONSTRAINT `fk_produkdetail_to_produk` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_transaksi_to_users` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
