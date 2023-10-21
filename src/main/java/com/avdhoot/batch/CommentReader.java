package com.avdhoot.batch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class CommentReader implements ItemReader<Comment> {

    private static Deque<Comment> data = new ArrayDeque<>();

    public CommentReader() {
        data = null;
    }

    @Override
    public Comment read() {
        if(data == null){
            data = APIUtils.fetchData();
        }
        if(data.isEmpty()){
            data = null;
            return null;
        }
        return data.pollFirst();
    }
}
