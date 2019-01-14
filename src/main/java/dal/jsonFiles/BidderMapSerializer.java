package dal.jsonFiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static models.ApplicationConstants.MapAttributes.BID;
import static models.ApplicationConstants.MapAttributes.ID;

@NoArgsConstructor
public class BidderMapSerializer extends JsonSerializer<Map<Long, Float>> {
    @Override
    public void serialize(final Map<Long, Float> value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException, JsonProcessingException {
        if (!value.isEmpty()) {
            List<BidInfo> list = new ArrayList<>();
            for (Map.Entry<Long, Float> entry : value.entrySet()) {
                list.add(new BidInfo(entry.getKey(), entry.getValue()));
            }
            jgen.writeObject(list);
        } else {
            jgen.writeObject(value.entrySet());
        }

    }

    private class BidInfo {
        @JsonProperty(ID)
        Long itemId;
        @JsonProperty(BID)
        float bid;

        public BidInfo(Long itemId, Float bid) {
            this.itemId = itemId;
            this.bid = bid;
        }
    }
}
