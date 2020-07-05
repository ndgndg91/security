package com.ndgndg91.todo.item.contoller;

import com.ndgndg91.todo.item.model.request.CreateItemRequest;
import com.ndgndg91.todo.item.model.request.UpdateItemRequest;
import com.ndgndg91.todo.item.model.response.ItemResponse;
import com.ndgndg91.todo.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/item")
public class ItemController {

    private final ItemService todoService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ItemResponse>> items(
            @PathVariable long userId
    ) {
        log.info("{}", userId);
        List<ItemResponse> items = todoService.findByUserId(userId)
                .stream()
                .map(item -> new ItemResponse(
                        item.getId(),
                        item.getTodoName(),
                        item.getDetails(),
                        item.getDue(),
                        item.getCreatedTime(),
                        item.getUserId()
                ))
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{userId}/{itemId}")
    public ResponseEntity<?> item(@PathVariable long userId, @PathVariable long itemId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody final CreateItemRequest request) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).build();
    }

    @PutMapping
    public ResponseEntity<?> updateItem(@RequestBody final UpdateItemRequest request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable long userId, @PathVariable long itemId) {
        return ResponseEntity.ok().build();
    }
}
