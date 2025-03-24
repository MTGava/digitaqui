package br.com.bytesquad.domain;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "accounts")
public class Account {
    public ObjectId id;
    public String name;
    public String email;
    public String password;
}
