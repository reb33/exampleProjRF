package backData.bd.transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by konstantin on 25.07.2017.
 */
@Data
@AllArgsConstructor
@ToString
public class Transaction {

    String date;
    String description;
    String amount;
    String type;
}
