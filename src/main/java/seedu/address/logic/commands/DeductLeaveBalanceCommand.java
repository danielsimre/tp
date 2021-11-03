package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.LeaveBalance;
import seedu.address.model.person.Person;

/**
 * Removes some number of leaves from an employee in HeRon.
 */
public class DeductLeaveBalanceCommand extends Command {

    public static final String COMMAND_WORD = "deductLeaveBalance";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deducts leaves from the employee identified "
            + "by the index number used in the last person listing. "
            + "Number of leaves removed cannot be greater than the amount of leaves "
            + "the employee currently has. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LEAVE + "NO_OF_LEAVES (must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_LEAVE + "2";
    public static final String MESSAGE_SUCCESS =
            "Leaves successfully removed from person: %1$s";

    private final Index index;
    private final LeaveBalance leaveBalance;

    /**
     * Creates a DeductLeaveBalanceCommand instance.
     * @param index of the person in the filtered employee list to remove leaves from
     * @param leaveBalance that are to be added to the employee
     */
    public DeductLeaveBalanceCommand(Index index, LeaveBalance leaveBalance) {
        requireAllNonNull(index, leaveBalance);

        this.index = index;
        this.leaveBalance = leaveBalance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson;
        try {
            editedPerson = new Person(
                    personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getAddress(),
                    personToEdit.getRole(), personToEdit.getLeaveBalance().removeLeaves(leaveBalance),
                    personToEdit.getLeavesTaken(), personToEdit.getSalary(), personToEdit.getHoursWorked(),
                    personToEdit.getOvertime(), personToEdit.getCalculatedPay(), personToEdit.getTags());
        } catch (IllegalArgumentException iae) {
            throw new CommandException(
                    String.format(Messages.MESSAGE_INVALID_REMOVE_INPUT, leaveBalance, "leaves"));
        }

        model.setPerson(personToEdit, editedPerson);
        model.setViewingPerson(editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson.toString()));
    }

    @Override
    public boolean equals(Object other) {
        // Short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeductLeaveBalanceCommand)) {
            return false;
        }

        // State check
        DeductLeaveBalanceCommand e = (DeductLeaveBalanceCommand) other;
        return index.equals(e.index)
                && leaveBalance.equals(e.leaveBalance);
    }
}