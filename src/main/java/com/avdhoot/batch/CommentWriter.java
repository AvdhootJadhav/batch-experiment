package com.avdhoot.batch;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentWriter implements ItemWriter<CommentData> {

    @Autowired
    DataRepository repository;

    @Override
    public void write(Chunk<? extends CommentData> chunk) throws Exception {
        repository.saveAll(chunk.getItems());
    }
}
