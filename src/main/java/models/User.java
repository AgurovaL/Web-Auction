package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dal.jsonFiles.BasketMapSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

import static models.ApplicationConstants.UserAttributes.*;

@Data
@ToString(exclude = {"itemsForSale", "itemsInBasket"})
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty(USER_ID)
    private Long userId;
    @JsonProperty(NAME)
    private String name;
    @JsonProperty(ADDRESS)
    private String address;
    @JsonProperty(LOGIN)
    private String login;
    @JsonProperty(PASSWORD)
    private String password;
    @JsonProperty(ITEMS_FOR_SALE)
    private Set<Item> itemsForSale = new HashSet<>();
    @JsonProperty(ITEMS_IN_BASKET)
    @JsonSerialize(using = BasketMapSerializer.class)
    private Map<Item, Float> itemsInBasket = new HashMap<>();

    public void addItemToBasket(Item item, float bid) {
        itemsInBasket.put(item, bid);
    }

    public void addItemForSale(Item item) {
        itemsForSale.add(item);
    }

    public User(Long userId, String name, String address, String login, String password) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId().equals(user.getUserId()) &&
                getName().equals(user.getName()) &&
                Objects.equals(getAddress(), user.getAddress()) &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword()) &&
                Objects.equals(getItemsForSale(), user.getItemsForSale()) &&
                Objects.equals(getItemsInBasket(), user.getItemsInBasket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}

