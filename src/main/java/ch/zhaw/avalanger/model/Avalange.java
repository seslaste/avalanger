package ch.zhaw.avalanger.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Document("avalanges")
public class Avalange {
    @Id
    private String id;
    @NonNull
    private String country;
    private AvalangeState state = AvalangeState.NEW;
    @NonNull
    private String description;
  
}
