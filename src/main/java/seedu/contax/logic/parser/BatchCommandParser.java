package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_END_WITH;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EQUALS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_START_WITH;

import seedu.contax.logic.commands.BatchCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.util.SearchType;


public class BatchCommandParser implements Parser<BatchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the BatchCommandParser
     * and returns an BatchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_SEARCH_TYPE, PREFIX_EQUALS, PREFIX_START_WITH, PREFIX_END_WITH);
        String commandInput = argMultimap.getPreamble();
        String equalsText = "";
        String startText = "";
        String endText = "";
        if (!argMultimap.arePrefixesPresent(PREFIX_SEARCH_TYPE) || argMultimap.getPreamble().isEmpty()
            || (!argMultimap.arePrefixesPresent(PREFIX_EQUALS)
                && !argMultimap.arePrefixesPresent(PREFIX_START_WITH)
                && !argMultimap.arePrefixesPresent(PREFIX_END_WITH))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    BatchCommand.MESSAGE_USAGE));
        }
        if (argMultimap.arePrefixesPresent(PREFIX_EQUALS)) {
            equalsText = argMultimap.getValue(PREFIX_EQUALS).get();
        }
        if (argMultimap.arePrefixesPresent(PREFIX_START_WITH)) {
            startText = argMultimap.getValue(PREFIX_START_WITH).get();
        }
        if (argMultimap.arePrefixesPresent(PREFIX_END_WITH)) {
            endText = argMultimap.getValue(PREFIX_END_WITH).get();
        }
        return new BatchCommand(
                commandInput.trim(),
                new SearchType(argMultimap.getValue(PREFIX_SEARCH_TYPE).get().toLowerCase()),
                equalsText, startText, endText
        );
    }
}
