package com.marko.investment.portfolio.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cash_flows")
public class CashFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private CashFlowType type;

    @ManyToOne
    @JoinColumn(name = "investment_id")
    private Investment investment;

    public CashFlow() {
    }

    public CashFlow(Long id,
                    BigDecimal amount,
                    LocalDate date,
                    CashFlowType type,
                    Investment investment) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.investment = investment;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public CashFlowType getType() {
        return type;
    }

    public Investment getInvestment() {
        return investment;
    }
}
