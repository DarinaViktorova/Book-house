package ua.house.book.creditcardservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ua.house.book.creditcardservice.domain.dto.request.CardDTO;
import ua.house.book.creditcardservice.service.CardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/core/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create-card")
    public ResponseEntity<String> createCard(@RequestBody CardDTO cardDTO) {
        cardService.createCard(cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Card create " + cardDTO + " is successful");
    }

    @GetMapping("/read-card")
    public ResponseEntity<CardDTO> readCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity
                .ok()
                .body(cardService.readCard(cardDTO));
    }

    @GetMapping("/read-card/{accountId}")
    public ResponseEntity<CardDTO> getCardByAccountEmail(@PathVariable(name = "accountId") Long accountId) {
        return ResponseEntity
                .ok()
                .body(cardService.readCardByAccountId(accountId));
    }

    @PutMapping("/update-card")
    public ResponseEntity<String> updateCard(@RequestBody CardDTO cardDTO) {
        cardService.updateCard(cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Update create " + cardDTO + " is successful");
    }
}
