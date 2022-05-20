package app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import app.model.Security;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SecurityDTO {
    protected String ticker;
    protected String name;
    protected ExchangeDTO exchange;
    protected String lastUpdated;
    protected BigDecimal price;
    protected BigDecimal ask;
    protected BigDecimal bid;
    protected BigDecimal change;
    protected Long volume;
    protected int contractSize;
    protected Collection<SecurityHistoryDTO> securityHistory;

    protected BigDecimal changePercent;
    protected BigDecimal dollarVolume;
    protected BigDecimal nominalValue;

    protected BigDecimal initialMarginCost;
    protected BigDecimal maintenanceMargin;

    public SecurityDTO(Security stock) {
        this.ticker = stock.getTicker();
        this.name = stock.getName();
        this.exchange = new ExchangeDTO(stock.getExchange());
        this.lastUpdated = stock.getLastUpdated();
        this.price = stock.getPrice();
        this.ask = stock.getAsk();
        this.bid = stock.getBid();
        this.change = stock.getPriceChange();
        this.volume = stock.getVolume();
        this.contractSize = stock.getContractSize();
        this.changePercent = BigDecimal.valueOf(0L);

        try{
            this.dollarVolume = price.multiply(BigDecimal.valueOf(volume));
            this.nominalValue = price.multiply(BigDecimal.valueOf(contractSize));
            this.changePercent = BigDecimal.valueOf(100L).multiply(change.subtract(price)).divide(price, 4, RoundingMode.HALF_EVEN);
        } catch (Exception e){}

//        if (stock.getSecurityHistory() == null || stock.getSecurityHistory().isEmpty())
//            return;
//
//        this.securityHistory = new ArrayList<>();
//        for (SecurityHistory history: stock.getSecurityHistory()){
//            this.securityHistory.add(new SecurityHistoryDTO(history));
//        }
    }

    @Override
    public String toString() {
        return "SecurityDTO{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", exchange=" + exchange +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", price=" + price +
                ", ask=" + ask +
                ", bid=" + bid +
                ", change=" + change +
                ", volume=" + volume +
                ", contractSize=" + contractSize +
                ", securityHistory=" + securityHistory +
                ", changePercent=" + changePercent +
                ", dollarVolume=" + dollarVolume +
                ", nominalValue=" + nominalValue +
                ", initialMarginCost=" + initialMarginCost +
                ", maintenanceMargin=" + maintenanceMargin +
                '}';
    }
}
