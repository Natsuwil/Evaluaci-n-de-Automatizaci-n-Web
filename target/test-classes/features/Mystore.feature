Feature: Product - Store
  Como usuario de la tienda en línea,
  Quiero validar el precio de los productos en el carrito,
  Para asegurar que el cálculo y la funcionalidad sean correctos.
  @test
  Scenario: Validacion del precio de un product
    Given  estoy en la pagina de la tienda
    And logeo con el usuario: "chilly_some@hotmail.com" y clave: "Contraseña162020"
    When navego a la categoria "Clothes" y subcategoria "Men"
    And agrego 2 unidades del primer producto al carrito
    Then valio en el popup la confirmacion del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When  finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

  @test-negativo
  Scenario: Intento de login con credenciales inválidas
    Given estoy en la pagina de la tienda
    When logeo con el usuario: "usuario_invalido@test.com" y clave: "claveInvalida123"
    Then valido que se muestre mensaje de error de login

  @test-negativo
  Scenario: Búsqueda de categoría inexistente
    Given estoy en la pagina de la tienda
    And logeo con el usuario: "chilly_some@hotmail.com" y clave: "Contraseña162020"
    When navego a la categoria "Autos" y subcategoria "Deportivos"
    Then valido que se muestre mensaje de categoría no encontrada