package com.ndgndg91.todo.item.service;

import com.ndgndg91.todo.item.model.Item;
import com.ndgndg91.todo.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository todoRepository;

    public List<Item> findByUserId(final long userId) {
        return todoRepository.findByUserId(userId);
    }
}
