package bookshop.photo.repository;

import bookshop.photo.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}