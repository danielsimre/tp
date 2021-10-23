package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_INVALID_INTEGER_INPUT =
            "Please input a valid positive integer when adding/removing leaves or hours worked! \n%1$s";
    public static final String MESSAGE_INVALID_REMOVE_INPUT = "Employee has less than %1$s %2$s!";
    public static final String MESSAGE_INSUFFICIENT_LEAVES = "Employee %1$s does not have any more leaves! \n";
    public static final String MESSAGE_INVALID_COMPARATOR =
            "Invalid comparison operator! Use either '>', '<', '>=', '<=' or '='.";
}
