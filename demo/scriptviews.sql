CREATE TABLE `db_menus`.`product_ingredient` (
  `idproduct_ingredient` INT NOT NULL,
  `idProduct` INT NULL,
  `idIngredient` INT NULL,
  PRIMARY KEY (`idproduct_ingredient`),
  INDEX `fk_product_ingredient_1_idx` (`idProduct` ASC),
  INDEX `fk_product_ingredient_2_idx` (`idIngredient` ASC),
  CONSTRAINT `fk_product_ingredient_1`
    FOREIGN KEY (`idProduct`)
    REFERENCES `db_menus`.`product` (`idProduct`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_ingredient_2`
    FOREIGN KEY (`idIngredient`)
    REFERENCES `db_menus`.`ingredient` (`idIngredient`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

CREATE TABLE `db_menus`.`product_allergen` (
  `idproduct_allergen` INT NOT NULL,
  `idProduct` INT NULL,
  `idallergen` INT NULL,
  INDEX `fk_product_allergen_1_idx` (`idProduct` ASC),
  INDEX `fk_product_allergen_2_idx` (`idallergen` ASC),
  CONSTRAINT `fk_product_allergen_1`
    FOREIGN KEY (`idProduct`)
    REFERENCES `db_menus`.`product` (`idProduct`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_product_allergen_2`
    FOREIGN KEY (`idallergen`)
    REFERENCES `db_menus`.`allergen` (`idAllergen`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);


create or replace view v_product_allergen as 
select product_allergen.idproduct_allergen,
product_allergen.idProduct,
product_allergen.idAllergen,
allergen.allergenName
from product_allergen join allergen on product_allergen.idAllergen = allergen.idAllergen;


create or replace view v_product_ingredient as 
select product_ingredient.idproduct_ingredient,
product_ingredient.idProduct,
product_ingredient.idIngredient,
ingredient.ingredientName
from product_ingredient join ingredient on product_ingredient.idIngredient = ingredient.idIngredient;

