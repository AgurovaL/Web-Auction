package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dal.jsonFiles.BidderMapSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static models.ApplicationConstants.ItemAttributes.*;


@Data
@ToString(exclude = {"bidders"})
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    @JsonProperty(ITEM_ID)
    private Long itemId;
    @JsonProperty(TITLE)
    private String title;
    @JsonProperty(DESCRIPTION)
    private String description;
    @JsonProperty(START_PRICE)
    private float startPrice;
    @JsonProperty(BID_INC)
    private int bidIncrement;
    @JsonProperty(STOP_DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private Date stopDate;
    @JsonProperty(BEST_OFFER)
    private float bestOffer;
    @JsonProperty(FIRST_BIDDER)
    private Long firstBidder;

    private Long seller;

    @JsonProperty(BIDDERS)
    @JsonSerialize(using = BidderMapSerializer.class)
    private Map<Long, Float> bidders = new HashMap<>();

    public Item(Long itemId, String title, float startPrice, int bidIncrement) {
        this.itemId = itemId;
        this.title = title;
        this.startPrice = startPrice;
        this.bidIncrement = bidIncrement;
    }

    public Item(Long itemId, String title, String description, float startPrice, int bidIncrement, Date stopDate) {
        this.itemId = itemId;
        this.description = description;
        this.title = title;
        this.startPrice = startPrice;
        this.bidIncrement = bidIncrement;
        this.stopDate = stopDate;
    }

    public void addBidder(Long userId, float bid) {
        bidders.put(userId, bid);
    }

    public float calcBestOffer() {
        if (bidders.isEmpty()) {
            bestOffer = startPrice;
            firstBidder = seller;
        } else {
            for (Map.Entry<Long, Float> entry : bidders.entrySet()) {
                if (bestOffer < entry.getValue()) {
                    bestOffer = entry.getValue();
                    firstBidder = entry.getKey();
                }
            }
        }
        return bestOffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return item.getStartPrice() == getStartPrice() &&
                getBidIncrement() == item.getBidIncrement() &&
                getItemId().longValue() == item.getItemId().longValue() &&
                getTitle().equals(item.getTitle()) &&
                getDescription().equals(item.getDescription()) &&
                getStopDate().equals(item.getStopDate()) &&
                getSeller().longValue() == item.getSeller().longValue();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getItemId());
    }
}
