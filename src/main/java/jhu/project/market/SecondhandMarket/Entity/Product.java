package jhu.project.market.SecondhandMarket.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private User seller;

    @Column(name = "category")
    private String category;

    @Column(name = "count")
    private int count;

    public void deleteCount(int count) {
        this.count = Math.max(0, this.count - count);
    }
}