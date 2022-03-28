package com.swiggy.swiggyClone.dataModel;

import javax.persistence.*;


@Entity
@Table
public class Allergens {
    @Id
    @SequenceGenerator(name = "past_orders_detail", sequenceName = "past_orders_detail",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "past_orders_detail")
    private Long past_order_details_id;
    private boolean peanuts;
    private boolean celery;
    private boolean sesameSeeds;



    @OneToOne
    @PrimaryKeyJoinColumn(name = "past_orders_detail")
    PastOrders past_orders_detail;

    public Allergens(boolean peanuts, boolean celery, boolean sesameSeeds) {
        this.peanuts = peanuts;
        this.celery = celery;
        this.sesameSeeds = sesameSeeds;
    }

    public Allergens(Long id, PastOrders pastOrders, boolean peanuts, boolean celery, boolean sesameSeeds) {
        this.past_order_details_id = id;
        this.past_orders_detail = pastOrders;
        this.peanuts = peanuts;
        this.celery = celery;
        this.sesameSeeds = sesameSeeds;
    }

    public Allergens(PastOrders pastOrders, boolean peanuts, boolean celery, boolean sesameSeeds) {
        this.past_orders_detail = pastOrders;
        this.peanuts = peanuts;
        this.celery = celery;
        this.sesameSeeds = sesameSeeds;
    }

    public Allergens() {
    }

    public Long getPast_order_details_id() {
        return past_order_details_id;
    }

    public void setPast_order_details_id(Long past_order_details_id) {
        this.past_order_details_id = past_order_details_id;
    }

    public PastOrders getPast_orders_detail() {
        return past_orders_detail;
    }

    public void setPast_orders_detail(PastOrders past_orders_detail) {
        this.past_orders_detail = past_orders_detail;
    }

    public boolean isPeanuts() {
        return peanuts;
    }

    public void setPeanuts(boolean peanuts) {
        this.peanuts = peanuts;
    }

    public boolean isCelery() {
        return celery;
    }

    public void setCelery(boolean celery) {
        this.celery = celery;
    }

    public boolean isSesameSeeds() {
        return sesameSeeds;
    }

    public void setSesameSeeds(boolean sesameSeeds) {
        this.sesameSeeds = sesameSeeds;
    }
}
