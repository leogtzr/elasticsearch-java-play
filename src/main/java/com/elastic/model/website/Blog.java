package com.elastic.model.website;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(indexName = "website")
public class Blog {
    @Id
    private String id;

    @Field(name = "title", type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss", store = true, name = "@timestamp")
    private Date timestamp;

    @Field(type = FieldType.Text, name = "tags")
    private List<String> tags;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(final List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", timestamp=" + timestamp +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Blog blog = (Blog) o;

        return Objects.equals(id, blog.id) && Objects.equals(title, blog.title) &&
                Objects.equals(timestamp, blog.timestamp) && Objects.equals(tags, blog.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, timestamp, tags);
    }
}
