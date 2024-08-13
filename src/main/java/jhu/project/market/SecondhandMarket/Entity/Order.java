package jhu.project.market.SecondhandMarket.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "`orders`")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "item_count")
    private int itemCount;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "order_time")
    private Timestamp orderTime;
}
