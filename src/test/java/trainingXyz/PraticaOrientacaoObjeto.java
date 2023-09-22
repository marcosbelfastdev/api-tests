package trainingXyz;

import model.Product;
import org.junit.jupiter.api.Test;

public class PraticaOrientacaoObjeto {

    @Test
    public void testesKevin() {
        Product produto1 = new Product ("camiseta","Camiseta Gola V Preta",20.00,1);
        Product produto2 = new Product (123,"mouse","Razer",300.00,321);

        System.out.println (produto1.getDescription());
        produto2.setDescription("Logitech");
        System.out.println("Produto 2 descrição -> " + produto2.getDescription());

        double price = produto1.getPrice();
        double newPrice = price - (price*20/100);
        produto1.setPrice(newPrice);
        System.out.println("O novo preço do produto 1 é -> " + produto1.getPrice());

        //produto1.setPrice(produto1.getPrice() - produto1.getPrice()*20/100);

    }
}
