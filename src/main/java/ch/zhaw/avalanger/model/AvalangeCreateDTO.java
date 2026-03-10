package ch.zhaw.avalanger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
@RequiredArgsConstructor
public class AvalangeCreateDTO {
    private String country;
    private String description;
}
