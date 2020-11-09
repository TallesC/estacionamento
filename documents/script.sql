-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema estacionamento
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema estacionamento
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `estacionamento` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `estacionamento` ;

-- -----------------------------------------------------
-- Table `estacionamento`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(255) NULL DEFAULT NULL,
  `nascimento` DATE NULL DEFAULT NULL,
  `primeiro_nome` VARCHAR(255) NULL DEFAULT NULL,
  `senha` VARCHAR(255) NULL DEFAULT NULL,
  `sobrenome` VARCHAR(255) NULL DEFAULT NULL,
  `status` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Cliente` (`cpf` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`carro` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `apelido` VARCHAR(255) NULL DEFAULT NULL,
  `cor` VARCHAR(255) NULL DEFAULT NULL,
  `modelo` VARCHAR(255) NULL DEFAULT NULL,
  `placa` VARCHAR(255) NULL DEFAULT NULL,
  `tipo` INT NULL DEFAULT NULL,
  `id_cliente_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKsnvx2myid9355ox0tr8ibob5q` (`id_cliente_id` ASC) VISIBLE,
  CONSTRAINT `FKsnvx2myid9355ox0tr8ibob5q`
    FOREIGN KEY (`id_cliente_id`)
    REFERENCES `estacionamento`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`perfil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`perfil` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`cliente_perfis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`cliente_perfis` (
  `cliente_id` BIGINT NOT NULL,
  `perfis_id` BIGINT NOT NULL,
  INDEX `FK2x9a3roc19iso4trosqx9cn4c` (`perfis_id` ASC) VISIBLE,
  INDEX `FKow5p279172nbtvd8m6n0y2ay6` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `FK2x9a3roc19iso4trosqx9cn4c`
    FOREIGN KEY (`perfis_id`)
    REFERENCES `estacionamento`.`perfil` (`id`),
  CONSTRAINT `FKow5p279172nbtvd8m6n0y2ay6`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `estacionamento`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`estacionamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`estacionamento` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cnpj` VARCHAR(255) NULL DEFAULT NULL,
  `endereco` VARCHAR(255) NULL DEFAULT NULL,
  `nome_fantasia` VARCHAR(255) NULL DEFAULT NULL,
  `razao_social` VARCHAR(255) NULL DEFAULT NULL,
  `telefone` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`faixa_preco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`faixa_preco` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `dia` VARCHAR(255) NULL DEFAULT NULL,
  `hora_final` TINYBLOB NULL DEFAULT NULL,
  `hora_inicial` TINYBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`piso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`piso` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `identificao` VARCHAR(255) NULL DEFAULT NULL,
  `num_vagas` INT NULL DEFAULT NULL,
  `id_estacionamento_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK7c535alnukuff5240lsb3uhlx` (`id_estacionamento_id` ASC) VISIBLE,
  CONSTRAINT `FK7c535alnukuff5240lsb3uhlx`
    FOREIGN KEY (`id_estacionamento_id`)
    REFERENCES `estacionamento`.`estacionamento` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`preficacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`preficacao` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `fracao` DOUBLE NOT NULL,
  `tempo_valor_minimo` INT NOT NULL,
  `tolerancia` INT NOT NULL,
  `valor_hora` DOUBLE NOT NULL,
  `valor_minimo` DOUBLE NULL DEFAULT NULL,
  `id_faixa_preco_id` BIGINT NULL DEFAULT NULL,
  `id_piso_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKgm30ktemmpunr0wxlkecbrc37` (`id_faixa_preco_id` ASC) VISIBLE,
  INDEX `FKqi4d8vrrobilhnhy3mvp487e8` (`id_piso_id` ASC) VISIBLE,
  CONSTRAINT `FKgm30ktemmpunr0wxlkecbrc37`
    FOREIGN KEY (`id_faixa_preco_id`)
    REFERENCES `estacionamento`.`faixa_preco` (`id`),
  CONSTRAINT `FKqi4d8vrrobilhnhy3mvp487e8`
    FOREIGN KEY (`id_piso_id`)
    REFERENCES `estacionamento`.`piso` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`ticket` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `codigo_barra` VARCHAR(255) NULL DEFAULT NULL,
  `codigo_confirmacao_pagamento` VARCHAR(255) NULL DEFAULT NULL,
  `hora_entrada` TINYBLOB NULL DEFAULT NULL,
  `hora_saida` TINYBLOB NULL DEFAULT NULL,
  `tempo_permanencia` DOUBLE NOT NULL,
  `valor` DOUBLE NOT NULL,
  `id_carro_id` BIGINT NULL DEFAULT NULL,
  `id_estacionamento_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKqsu07jtmybu2rgmbbtmyq80y0` (`id_carro_id` ASC) VISIBLE,
  INDEX `FK98aejh9pgm465nl018yal5e2b` (`id_estacionamento_id` ASC) VISIBLE,
  CONSTRAINT `FK98aejh9pgm465nl018yal5e2b`
    FOREIGN KEY (`id_estacionamento_id`)
    REFERENCES `estacionamento`.`estacionamento` (`id`),
  CONSTRAINT `FKqsu07jtmybu2rgmbbtmyq80y0`
    FOREIGN KEY (`id_carro_id`)
    REFERENCES `estacionamento`.`carro` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `estacionamento`.`vagas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `estacionamento`.`vagas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NULL DEFAULT NULL,
  `tipo_veiculo` VARCHAR(255) NULL DEFAULT NULL,
  `id_piso_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK8g7dlw5a2kl2u8v0f35kd2xsl` (`id_piso_id` ASC) VISIBLE,
  CONSTRAINT `FK8g7dlw5a2kl2u8v0f35kd2xsl`
    FOREIGN KEY (`id_piso_id`)
    REFERENCES `estacionamento`.`piso` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
