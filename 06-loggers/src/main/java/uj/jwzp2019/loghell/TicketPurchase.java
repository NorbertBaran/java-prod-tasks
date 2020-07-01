package uj.jwzp2019.loghell;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import com.internal.DiscountCalculator;
import com.external.PaymentsService;

public class TicketPurchase {

    private static final Logger logger = LoggerFactory.getLogger("com");

    private static Option addNewOption(Options options, String opt, String longOpt, boolean hasArg, String description, boolean required){
        Option option = new Option(opt, longOpt, hasArg, description);
        option.setRequired(required);
        options.addOption(option);
        return option;
    }

    public static void main(String[] args) {
        //TODO implement me!

        Options options = new Options();

        addNewOption(options, "t", "ticketPrice", true, "Ticket Price", true);
        addNewOption(options, "a", "customerAge", true, "Customer Age", true);
        addNewOption(options, "id", "customerId", true, "Customer Id", true);
        addNewOption(options, "coId", "companyId", true, "Company Id", true);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.info(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(0);
        }

        BigDecimal price=new BigDecimal(cmd.getOptionValue("ticketPrice"));
        DiscountCalculator discountCalculator=new DiscountCalculator();
        price=price.subtract(
                discountCalculator.calculateDiscount(price, Integer.parseInt(cmd.getOptionValue("customerAge")))
        );

        PaymentsService paymentsService=new PaymentsService();
        boolean paymentStatus=paymentsService.makePayment(Long.parseLong(cmd.getOptionValue("customerId")), Long.parseLong(cmd.getOptionValue("companyId")), price);

        /*logger.info("Summary:\n"+
                "- transaction for: "+price+"USD\n" +
                "- between: customer "+cmd.getOptionValue("customerId")+" and company "+cmd.getOptionValue("companyId")+"\n" +
                "- status: "+ (paymentStatus ? "successfully" : "failed"));*/
    }
}
