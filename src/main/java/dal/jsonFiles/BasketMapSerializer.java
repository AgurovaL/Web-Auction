package dal.jsonFiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.NoArgsConstructor;
import models.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static models.ApplicationConstants.MapAttributes.BID;
import static models.ApplicationConstants.MapAttributes.ITEM;

@NoArgsConstructor
public class BasketMapSerializer extends JsonSerializer<Map<Item, Float>> {
    @Override
    public void serialize(final Map<Item, Float> value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException, JsonProcessingException {
        if (!value.isEmpty()) {
            List<ItemInBasket> list = new ArrayList<>();
            for (Map.Entry<Item, Float> entry : value.entrySet()) {
                list.add(new ItemInBasket(entry.getKey(), entry.getValue()));

            }
            jgen.writeObject(list);
        } else {
            jgen.writeObject(value.entrySet());
        }

    }

    private class ItemInBasket {
        @JsonProperty(ITEM)
        Item item;
        @JsonProperty(BID)
        float bid;

        public ItemInBasket(Item item, Float bid) {
            this.item = item;
            this.bid = bid;
        }
    }
}