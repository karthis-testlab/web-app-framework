Feature: Sauce Demo is site used to cart sauce lab merchandise product

  Scenario: User able to find the product which they want
    Given User want to purchase the sauce lab merchandise product on the offical cart site
    When Regitered user able to login with valid user credentials "standard_user" and "secret_sauce"
    Then User able to the see "Sauce Labs Bolt T-Shirt" the product they wants
