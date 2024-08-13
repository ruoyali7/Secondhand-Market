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

    private String name;
    private String description;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private User seller;

    private String category;
}