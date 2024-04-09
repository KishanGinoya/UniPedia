-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 20, 2024 at 04:08 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javaproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_login`
--

CREATE TABLE `admin_login` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_login`
--

INSERT INTO `admin_login` (`id`, `username`, `password`) VALUES
(1, 'kishan', 'kishan123'),
(2, 'khushal', 'khushal123');

-- --------------------------------------------------------

--
-- Table structure for table `coursedetails`
--

CREATE TABLE `coursedetails` (
  `id` int(11) NOT NULL,
  `program` varchar(50) NOT NULL,
  `semester` int(11) NOT NULL,
  `c1` varchar(50) NOT NULL,
  `c2` varchar(50) NOT NULL,
  `c3` varchar(50) NOT NULL,
  `c4` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `coursedetails`
--

INSERT INTO `coursedetails` (`id`, `program`, `semester`, `c1`, `c2`, `c3`, `c4`) VALUES
(1, 'BCA', 1, 'C Programming', 'Creative English', 'Computer Fundamentals', 'PC Software'),
(2, 'BBA', 1, 'Financial Accounting', 'Microeconomics', 'Principles of Management', 'India Socio-Political Economics'),
(3, 'BCA', 2, 'Web Programming', 'Communicative English', 'Data structures', 'Web Technology'),
(4, 'BBA', 2, 'Macroeconomics', 'Quantitative Techniques - II', 'Effective Communications', 'Cost Accounting'),
(5, 'BCA', 3, 'C++ Programming', 'Database management system', 'Core PHP', 'Graphics and Animation'),
(6, 'BBA', 3, 'Quantitative techniques - I', 'Essentials of IT', 'Business Analytics', 'Human Resource Management'),
(7, 'BCA', 4, 'OS', 'System Design and Analysis', 'Core JAVA', 'Advance Python'),
(8, 'BCA', 5, 'Advance PHP', 'C#.NET', 'Advance Java', 'SEO'),
(9, 'BBA', 5, 'Banking & Insurance', 'Indian Economics', 'Financial Management', 'Research Methodology');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `id` int(11) NOT NULL,
  `Program` varchar(50) NOT NULL,
  `Course` varchar(50) NOT NULL,
  `FacultyName` varchar(50) NOT NULL,
  `FatherName` varchar(50) NOT NULL,
  `Gender` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `Email` varchar(50) NOT NULL,
  `MobileNo` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`id`, `Program`, `Course`, `FacultyName`, `FatherName`, `Gender`, `DOB`, `Email`, `MobileNo`, `Address`) VALUES
(1, 'BCA', 'C Programming', 'meet', 'jf', 'Male', '1991-01-03', 'meet@gmail.com', '8457475774', 'kalavad'),
(2, 'BBA', 'Financial Accounting', 'virat', 'abc', 'Male', '1999-01-02', 'virat@gmail.com', '8478376767', 'rajkot'),
(3, 'BCA', 'Creative English', 'dhruv', 'abc', 'Male', '1999-01-02', 'dhruv@gmail.com', '8478374767', 'rajkot'),
(4, 'BBA', 'India Socio-Political Economics', 'kirtan', 'bfd', 'Male', '1999-01-02', 'kirtan@gmail.com', '8478374646', 'rajkot'),
(5, 'BCA', 'Communicative English', 'priyanshu', 'bfd', 'Male', '1999-01-02', 'priyanshu@gmail.com', '8478356646', 'rajkot');

-- --------------------------------------------------------

--
-- Table structure for table `fee`
--

CREATE TABLE `fee` (
  `id` int(11) NOT NULL,
  `EnrollmentNo` int(11) NOT NULL,
  `StudentName` varchar(50) NOT NULL,
  `Semester` int(11) NOT NULL,
  `Program` varchar(50) NOT NULL,
  `ReceiptNo` varchar(50) NOT NULL,
  `ReceiptDate` date NOT NULL,
  `PaymentType` varchar(50) NOT NULL,
  `TotalFee` int(11) NOT NULL,
  `Words` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fee`
--

INSERT INTO `fee` (`id`, `EnrollmentNo`, `StudentName`, `Semester`, `Program`, `ReceiptNo`, `ReceiptDate`, `PaymentType`, `TotalFee`, `Words`) VALUES
(1, 210801000, 'ram', 2, 'BCA', '101', '2024-01-05', 'Cash', 20000, 'TWENTY THOUSAND ONLY'),
(2, 210801102, 'daya', 1, 'BBA', '102', '2024-01-05', 'Online', 20000, 'TWENTY THOUSAND ONLY');

-- --------------------------------------------------------

--
-- Table structure for table `marksheet`
--

CREATE TABLE `marksheet` (
  `id` int(11) NOT NULL,
  `enrollmentno` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `grade` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `marksheet`
--

INSERT INTO `marksheet` (`id`, `enrollmentno`, `total`, `grade`) VALUES
(1, 210801000, 332, 'B'),
(2, 210801001, 331, 'B'),
(3, 210801102, 257, 'C'),
(4, 210801003, 295, 'C'),
(5, 210801004, 209, 'D');

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

CREATE TABLE `score` (
  `id` int(11) NOT NULL,
  `program` varchar(50) NOT NULL,
  `semester` int(11) NOT NULL,
  `enrollementno` int(11) NOT NULL,
  `c1m` int(11) NOT NULL,
  `c2m` int(11) NOT NULL,
  `c3m` int(11) NOT NULL,
  `c4m` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `score`
--

INSERT INTO `score` (`id`, `program`, `semester`, `enrollementno`, `c1m`, `c2m`, `c3m`, `c4m`) VALUES
(1, 'BCA', 2, 210801000, 78, 89, 98, 67),
(2, 'BCA', 4, 210801001, 76, 73, 89, 93),
(3, 'BBA', 1, 210801102, 78, 89, 56, 34),
(4, 'BBA', 3, 210801003, 65, 76, 78, 76),
(5, 'BCA', 5, 210801004, 56, 54, 54, 45);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `StudentName` varchar(50) NOT NULL,
  `FatherName` varchar(50) NOT NULL,
  `EnrollmentNo` int(11) NOT NULL,
  `Gender` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `Email` varchar(50) NOT NULL,
  `MobileNo` varchar(50) NOT NULL,
  `Semester` int(11) NOT NULL,
  `Course` varchar(50) NOT NULL,
  `Address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `StudentName`, `FatherName`, `EnrollmentNo`, `Gender`, `DOB`, `Email`, `MobileNo`, `Semester`, `Course`, `Address`) VALUES
(1, 'ram', 'abc', 210801000, 'Male', '2001-01-04', 'raj@gmail.com', '9847567463', 2, 'BCA', 'rajkot'),
(2, 'shyam', 'def', 210801001, 'Male', '2005-01-07', 'shyam@gmail.com', '8746746764', 4, 'BCA', 'surat'),
(3, 'daya', 'rg', 210801102, 'Female', '2004-01-07', 'daya@gmail.com', '9374764764', 1, 'BBA', 'mumbai'),
(4, 'varun', 'rr', 210801003, 'Male', '2005-01-07', 'varun@gmail.com', '8746746734', 3, 'BBA', 'ahmedabad'),
(5, 'avadh', 'rr', 210801004, 'Male', '2005-01-07', 'avadh@gmail.com', '8746746787', 5, 'BCA', 'ahmedabad');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_login`
--
ALTER TABLE `admin_login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `coursedetails`
--
ALTER TABLE `coursedetails`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fee`
--
ALTER TABLE `fee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `marksheet`
--
ALTER TABLE `marksheet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_login`
--
ALTER TABLE `admin_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `coursedetails`
--
ALTER TABLE `coursedetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `fee`
--
ALTER TABLE `fee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `marksheet`
--
ALTER TABLE `marksheet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `score`
--
ALTER TABLE `score`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
