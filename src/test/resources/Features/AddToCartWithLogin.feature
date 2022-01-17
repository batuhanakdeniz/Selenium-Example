Feature:
  Scenario:
    Given Kullanıcı Hepsiburada.com sitesini ziyaret eder.
    And Kullanıcı giriş işlemi yapılır.
    And Yönlendirmeden sonra anasayfada kullanıcı giriş işleminin yapıldığı doğrulanır
    When Kullanıcı, burada satın almak istediği ürün için arama yapacaktır.
    And Kullanıcı, Arama sonucunda ekrana gelen ürün listesinden ürün seçer.
    And Seçilen ürün için 2 tane farklı satıcıdan ürün seçilip sepete eklenir.
    Then Seçilen ürünün doğru olarak eklendiği ‘Sepetim’ sayfasında doğrulanmalıdır.