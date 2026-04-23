CREATE TABLE `Users` (
    `users_id` int primary key not null auto_increment,
    `username` varchar(30) not null,
    `password` varchar(50) not null,
    `role` varchar(10) not null
);