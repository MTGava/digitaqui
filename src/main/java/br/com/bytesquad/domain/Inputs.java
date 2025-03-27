package br.com.bytesquad.domain;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "inputs")
public class Inputs {
    public ObjectId id;
    public String inputs;
    public ObjectId accountId;
    public String active;
}
