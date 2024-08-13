package jhu.project.market.SecondhandMarket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "`order_item`")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private int count;

    @Column(name = "price")
    private double price;
}
