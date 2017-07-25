package backData.bd.transactions;

import backData.BackDataCompare;
import backData.bd.Application;
import org.apache.commons.lang3.ArrayUtils;
import utils.MyDateUtils;
import utils.MyStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by konstantin on 25.07.2017.
 */
public class BDTransactions implements BackDataCompare {
//

    private List<Transaction> getTransactions(String... args){
        Application application = new Application();
        String cardNum = args[0];
        cardNum = cardNum.replaceAll("\\s+", "");
        String sql = "select * from Tran t inner join Card c on t.cardId=c.id " +
                "where c.number='"+cardNum+"'";
        application.init();
        return application.guery(sql,
                (rs, rowNum) ->
                        new Transaction(
                                rs.getString("tranDate"),
                                rs.getString("tranDescription"),
                                rs.getString("tranAmount"),
                                rs.getString("tranType")
                        ));
    }


    @Override
    public List<List<String>> listValues(String... args) {
        List<Transaction> list = getTransactions(args);
        return list.stream().map(transaction ->
                        new ArrayList<>(Arrays.asList(
                                MyDateUtils.retDateInPageFormat2(transaction.getDate()),
                                transaction.getDescription().replaceAll("\\t"," "),
                                MyStringUtils.getAmountString(transaction.getAmount(), transaction.getType())
                        )))
                .collect(Collectors.toList());
    }
}
