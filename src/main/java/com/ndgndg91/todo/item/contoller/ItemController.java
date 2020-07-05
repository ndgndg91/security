package com.ndgndg91.todo.item.contoller;

import com.ndgndg91.todo.item.model.request.CreateItemRequest;
import com.ndgndg91.todo.item.model.request.UpdateItemRequest;
import com.ndgndg91.todo.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/item")
public class ItemController {

    private final ItemService todoService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> items(@PathVariable long userId) {
        return ResponseEntity.ok().build();
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
