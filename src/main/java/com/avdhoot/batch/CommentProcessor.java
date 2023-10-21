package com.avdhoot.batch;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.batch.item.ItemProcessor;

public class CommentProcessor implements ItemProcessor<Comment, CommentData> {

    @Override
    public CommentData process(Comment item) throws Exception {
        return new Gson().fromJson(new JSONObject(item).toString(), CommentData.class);
    }
}
