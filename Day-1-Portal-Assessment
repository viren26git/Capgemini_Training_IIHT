```java
import java.util.List;

/**
 * Orbit Pricing Rule Engine
 *
 * Goal: apply stacked discount rules to an order subtotal,
 * honoring the Open/Closed Principle.
 *
 * Uses:
 * - sealed interface
 * - records
 * - pattern matching with instanceof
 * - var
 */
public class PricingRuleEngine {

    // TODO 1:
    // Sealed interface restricts which classes/records
    // are allowed to implement DiscountRule.
    sealed interface DiscountRule
            permits PercentageOff, FlatOff, ThresholdOff {
    }

    // TODO 2:
    // Records automatically provide:
    // - constructor
    // - accessor methods
    // - equals()
    // - hashCode()
    // - toString()
    // - immutability

    record PercentageOff(double percent)
            implements DiscountRule {
    }

    record FlatOff(double amount)
            implements DiscountRule {
    }

    record ThresholdOff(double minSubtotal, double amount)
            implements DiscountRule {
    }

    /**
     * Applies each discount rule from left to right.
     *
     * @param subtotal initial order subtotal
     * @param rules ordered list of discount rules
     * @return final payable amount rounded to 2 decimal places
     */
    public double applyDiscounts(
            double subtotal,
            List<DiscountRule> rules) {

        var runningTotal = subtotal;

        for (var rule : rules) {

            // Percentage discount
            if (rule instanceof PercentageOff p) {

                runningTotal =
                        runningTotal - (runningTotal * p.percent() / 100);

            }

            // Flat discount
            else if (rule instanceof FlatOff f) {

                runningTotal =
                        runningTotal - f.amount();

            }

            // Threshold discount
            else if (rule instanceof ThresholdOff t) {

                if (runningTotal >= t.minSubtotal()) {

                    runningTotal =
                            runningTotal - t.amount();
                }
            }

            // Clamp running total to 0
            if (runningTotal < 0) {
                runningTotal = 0;
            }
        }

        // Round to 2 decimal places
        return Math.round(runningTotal * 100.0) / 100.0;
    }

    // --------------------------------------------------
    // Example / Testing
    // --------------------------------------------------

    public static void main(String[] args) {

        var engine = new PricingRuleEngine();

        var rules = List.of(
                new PercentageOff(10),
                new ThresholdOff(150, 20),
                new FlatOff(5)
        );

        var result = engine.applyDiscounts(200.00, rules);

        System.out.println("Final Payable Amount: " + result);
    }
}
```
