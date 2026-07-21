package br.com.alura.FindCar.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "brand_code", nullable = false)
    private String brandCode;

    @Column(name = "model_code", nullable = false)
    private String modelCode;

    @Column(name = "year_code", nullable = false)
    private String yearCode;

    @Column(name = "fipe_code", nullable = false)
    private String fipeCode;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "target_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal targetPrice;

    @Column(name = "current_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal currentPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_vehicle", nullable = false)
    private TipoVeiculo typeVehicle;


    public Alert(){}

    public Alert(TipoVeiculo typeVehicle, User user, String fipeCode, String modelName, BigDecimal targetPrice, BigDecimal currentPrice,String brandCode,String modelCode, String yearCode) {
        this.typeVehicle = typeVehicle;
        this.user = user;
        this.fipeCode = fipeCode;
        this.currentPrice = currentPrice;
        this.modelName = modelName;
        this.targetPrice = targetPrice;
        this.brandCode = brandCode;
        this.modelCode = modelCode;
        this.yearCode = yearCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFipeCode() {
        return fipeCode;
    }

    public void setFipeCode(String fipeCode) {
        this.fipeCode = fipeCode;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(BigDecimal targetPrice) {
        this.targetPrice = targetPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getYearCode() {
        return yearCode;
    }

    public void setYearCode(String yearCode) {
        this.yearCode = yearCode;
    }

    public TipoVeiculo getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(TipoVeiculo typeVehicle) {
        this.typeVehicle = typeVehicle;
    }
}



