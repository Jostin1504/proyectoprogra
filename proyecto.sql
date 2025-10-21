
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

CREATE TABLE IF NOT EXISTS `Usuario` (
                                         `cedula` VARCHAR(20) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `clave` VARCHAR(100) NOT NULL,
    `rol` VARCHAR(3) NOT NULL,
    `especialidad` VARCHAR(100) NULL,
    PRIMARY KEY (`cedula`)
    ) ENGINE = InnoDB;

-- Tabla Paciente
CREATE TABLE IF NOT EXISTS `Paciente` (
                                          `id` VARCHAR(20) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `fechaNacimiento` VARCHAR(20) NULL,
    `telefono` VARCHAR(20) NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB;

-- Tabla Medicamento
CREATE TABLE IF NOT EXISTS `Medicamento` (
                                             `codigo` VARCHAR(20) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `presentacion` VARCHAR(50) NULL,
    `cantidad` INT NULL,
    `duracion` INT NULL,
    `indicaciones` TEXT NULL,
    PRIMARY KEY (`codigo`)
    ) ENGINE = InnoDB;

-- Tabla Receta
CREATE TABLE IF NOT EXISTS `Receta` (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        `fechaRetiro` VARCHAR(20) NULL,
    `fechaCreacion` VARCHAR(20) NULL,
    `idPaciente` VARCHAR(20) NOT NULL,
    `estado` VARCHAR(20) NOT NULL DEFAULT 'Confeccionada',
    PRIMARY KEY (`id`),
    INDEX `fk_Receta_Paciente_idx` (`idPaciente` ASC),
    CONSTRAINT `fk_Receta_Paciente`
    FOREIGN KEY (`idPaciente`)
    REFERENCES `Paciente` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;

-- Tabla intermedia Receta_Medicamento
CREATE TABLE IF NOT EXISTS `Receta_Medicamento` (
                                                    `idReceta` INT NOT NULL,
                                                    `codigoMedicamento` VARCHAR(20) NOT NULL,
    `cantidad` INT NOT NULL,
    `duracion` INT NOT NULL,
    PRIMARY KEY (`idReceta`, `codigoMedicamento`),
    INDEX `fk_RecetaMed_Medicamento_idx` (`codigoMedicamento` ASC),
    CONSTRAINT `fk_RecetaMed_Receta`
    FOREIGN KEY (`idReceta`)
    REFERENCES `Receta` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `fk_RecetaMed_Medicamento`
    FOREIGN KEY (`codigoMedicamento`)
    REFERENCES `Medicamento` (`codigo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    ) ENGINE = InnoDB;
