package ua.house.book.orderservice.domain.mapper;

import ua.house.book.orderservice.domain.dto.request.PurchaseDTO;
import ua.house.book.orderservice.domain.dto.response.MoneyDTO;
import ua.house.book.orderservice.domain.entity.Money;
import ua.house.book.orderservice.domain.entity.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseMapper {
    private PurchaseMapper() {
    }

    public static List<Purchase> purchaseDTOListIntoPurchaseList(List<PurchaseDTO> purchaseDTOList) {
        List<Purchase> purchaseList = new ArrayList<>();
        purchaseDTOList
                .forEach(purchaseDTO -> purchaseList.add(Purchase.builder()
                        .purchaseName(purchaseDTO.getPurchaseName())
                        .countPurchases(purchaseDTO.getCountPurchases())
                        .money(Money.builder()
                                    .currency(purchaseDTO.getMoneyDTO().getCurrency())
                                    .amount(purchaseDTO.getMoneyDTO().getAmount())
                                    .build())
                        .build()));

        return purchaseList;
    }

    public static List<PurchaseDTO> purchaseListIntoPurchaseDTOList(List<Purchase> purchaseList) {
        List<PurchaseDTO> purchaseDTOList = new ArrayList<>();
        purchaseList
                .forEach(purchase -> purchaseDTOList.add(PurchaseDTO.builder()
                        .purchaseName(purchase.getPurchaseName())
                        .countPurchases(purchase.getCountPurchases())
                        .moneyDTO(MoneyDTO.builder()
                                          .currency(purchase.getMoney().getCurrency())
                                          .amount(purchase.getMoney().getAmount())
                                          .build())
                        .build()));
        return purchaseDTOList;
    }


}
