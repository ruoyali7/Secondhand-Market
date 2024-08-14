package jhu.project.market.SecondhandMarket.Entity;
//
import jakarta.persistence.*;
import lombok.Data;

//@Entity
//@Table(name = "`cart_itme`")
//@Data
//public class CartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Integer id;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @Column(name = "count")
//    private int count;
//
//    @Column(name = "total_price")
//    private double totalPrice;
//
//    public void addItems(int count) {
//        this.count += count;
//        totalPrice = product.getPrice() * this.count;
//    }
//
//    public void deleteItems(int count) {
//        this.count = Math.max(0, this.count - count);
//        totalPrice = product.getPrice() * this.count;
//    }
//}


@Entity
@Table(name = "`cart_itme`")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // Changed the CASCADE.ALL as it was trying ti delete user and product as well
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private int count;

    @Column(name = "total_price")
    private double totalPrice;

    public void addItems(int count) {
        this.count += count;
        totalPrice = product.getPrice() * this.count;
    }

    public void deleteItems(int count) {
        this.count = Math.max(0, this.count - count);
        totalPrice = product.getPrice() * this.count;
    }
}

