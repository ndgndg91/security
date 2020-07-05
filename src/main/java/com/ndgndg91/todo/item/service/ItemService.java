package com.ndgndg91.todo.item.service;

import com.ndgndg91.todo.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository todoRepository;
}
