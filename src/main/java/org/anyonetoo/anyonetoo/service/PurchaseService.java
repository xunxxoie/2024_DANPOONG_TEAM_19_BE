package org.anyonetoo.anyonetoo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.anyonetoo.anyonetoo.domain.dto.mypage.PurchaseResponseDTO;
import org.anyonetoo.anyonetoo.domain.entity.Alarm;
import org.anyonetoo.anyonetoo.domain.entity.Consumer;
import org.anyonetoo.anyonetoo.domain.entity.Product;
import org.anyonetoo.anyonetoo.domain.entity.Purchase;
import org.anyonetoo.anyonetoo.domain.enums.Status;
import org.anyonetoo.anyonetoo.exception.RestApiException;
import org.anyonetoo.anyonetoo.exception.code.CustomErrorCode;
import org.anyonetoo.anyonetoo.repository.ConsumerRepository;
import org.anyonetoo.anyonetoo.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final ConsumerRepository consumerRepository;
    private final PurchaseRepository purchaseRepository;

    private final AlarmService alarmService;

    public Long savePurchase(Long userId, Product product){

        Consumer consumer = consumerRepository.findByUserId(userId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.USER_NOT_FOUND));

        Purchase purchase = Purchase.builder()
                .status(Status.ORDER_REQUEST)
                .product(product)
                .consumer(consumer)
                .build();

        purchaseRepository.save(purchase);
        alarmService.createAlarm(consumer, purchase, product.getTitle());

        return purchase.getPurchaseId();
    }

    public List<PurchaseResponseDTO> showAllConsumerPurchases(Long consumerId) {
        List<Purchase> purchases = purchaseRepository.findByConsumerId(consumerId);

//        if (purchases.isEmpty()) {
//            throw new RestApiException(CustomErrorCode.PURCHASE_NOT_FOUND);
//        }
        return purchases.stream()
                .map(PurchaseResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<PurchaseResponseDTO> showAllPurchases(Long productId) {
        List<Purchase> purchases = purchaseRepository.findByProduct_ProductId(productId);
        return purchases.stream()
                .map(PurchaseResponseDTO::from)  // DTO로 변환
                .collect(Collectors.toList());
    }
}
