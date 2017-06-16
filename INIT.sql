CREATE DATABASE IF NOT EXISTS `tim4`;
create user 'EtfSI2016'@'localhost' identified by '2016SIEtf';
grant all privileges on tim4.* to 'EtfSI2016'@'localhost' identified by '2016SIEtf';

INSERT INTO `tim4`.`rola` (`id`, `naziv`) VALUES ('1', 'ROLE_USER');
INSERT INTO `tim4`.`rola` (`id`, `naziv`) VALUES ('2', 'ROLE_HR');
INSERT INTO `tim4`.`rola` (`id`, `naziv`) VALUES ('3', 'ROLE_ADMIN');
