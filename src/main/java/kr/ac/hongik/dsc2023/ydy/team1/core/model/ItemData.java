package kr.ac.hongik.dsc2023.ydy.team1.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 이 클래스의 인스턴스는 itemName 필드가 같으면 같은 인스턴스로 취급한다.
 */
@AllArgsConstructor
@Getter
public class ItemData {
    @JsonProperty("item_id")
    private long itemID;
    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("access_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accessTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ItemData itemData = (ItemData) o;
        return itemData.itemName.equals(itemName);
    }

    @Override
    public int hashCode() {
        return itemName.hashCode();
    }
}
