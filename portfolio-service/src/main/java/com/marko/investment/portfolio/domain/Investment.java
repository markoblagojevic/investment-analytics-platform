package com.marko.investment.portfolio.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String assetClass;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;


    public Investment() {
    }

    public Investment(Long id, String name, String assetClass, Portfolio portfolio) {
        this.id = id;
        this.name = name;
        this.assetClass = assetClass;
        this.portfolio = portfolio;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}
