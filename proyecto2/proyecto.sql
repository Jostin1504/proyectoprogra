CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
USE `mydb`;

-- Tabla Usuario
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

-- Tabla Medicamentos
CREATE TABLE IF NOT EXISTS `Medicamento` (
                                             `codigo` VARCHAR(20) NOT NULL,
    `nombre` VARCHAR(100) NOT NULL,
    `presentacion` VARCHAR(50) NULL,
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

CREATE TABLE IF NOT EXISTS `Receta_Medicamento` (
                                                    `idReceta` INT NOT NULL,
                                                    `codigoMedicamento` VARCHAR(20) NOT NULL,
    `cantidad` INT NOT NULL,
    `duracion` INT NOT NULL,
    `indicaciones` TEXT NULL,
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

INSERT INTO Usuario (cedula, nombre, clave, rol, especialidad)
VALUES
    ('334', 'Administrador 2', '334', 'ADM', NULL),
    ('111', 'Dr. Juan Pérez', '111', 'MED', 'Medicina General'),
    ('222', 'Farm. María López', '222', 'FAR', NULL);

-- Insertar medicamentos
INSERT INTO Medicamento (codigo, nombre, presentacion)
VALUES
    ('PA500', 'Paracetamol', 'Tabletas 500mg'),
    ('IB400', 'Ibuprofeno', 'Tabletas 400mg'),
    ('AM500', 'Amoxicilina', 'Cápsulas 500mg'),
    ('OM20', 'Omeprazol', 'Cápsulas 20mg'),
    ('LO10', 'Loratadina', 'Tabletas 10mg');

-- Insertar un paciente
INSERT INTO Paciente (id, nombre, fechaNacimiento, telefono)
VALUES ('1-2345-6789', 'Carlos Rodríguez', '1985-05-15', '8888-8888');