package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.Invoice;
import lombok.NonNull;
import lombok.Value;
import org.joda.money.Money;

@Value
public class InvoiceDto {

    @NonNull Money totalCost;

    static InvoiceDto from(@NonNull final Invoice domainObject) {
        return new InvoiceDto(
                domainObject.getTotalCost()
        );
    }
}
