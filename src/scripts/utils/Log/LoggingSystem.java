package src.scripts.utils.Log;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;

import src.scripts.utils.Consts;
import src.scripts.utils.Utils;
import src.scripts.utils.Consts.ConfirmationFlagType;

public class LoggingSystem {
    private JLabel logger = null;
    // Current status
    private ConfirmationFlagType awaitConfirmation = ConfirmationFlagType.NONE;
    // Previous status
    private ConfirmationFlagType previousConfirmation = ConfirmationFlagType.NONE;

    public LoggingSystem() {
    }

    public void setLoggingRef(JLabel logRef) {
        logger = logRef;
    }

    public void confirm(String message) {
        setLog("<font color='" + Consts.log_confirm_pink + "'>Press again to CONFIRM:</font> " + message);
    }

    public void debug(String message) {
        setLog("<font color='" + Consts.log_debug_blue + "'>DEBUG:</font> " + message);
    }

    public void info(String message) {
        setLog("<font color='" + Consts.log_info_green + "'>INFO:</font> " + message);
    }

    public void warning(String message) {
        setLog("<font color='" + Consts.log_warning_yellow + "'>WARNING:</font> " + message);
    }

    public void error(String message) {
        setLog("<font color='" + Consts.log_error_red + "'>ERROR:</font> " + message);
    }

    private void setLog(String message) {
        setLog(message, false);
    }

    private void setLog(String message, boolean confirmed) {
        previousConfirmation = awaitConfirmation;
        // Reset the confirmation flag at each new message
        if (awaitConfirmation != ConfirmationFlagType.NONE)
            awaitConfirmation = ConfirmationFlagType.NONE;

        if (logger != null) {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String currentTimestamp = "[" + currentTime.format(formatter) + "] ";

            String confirmationAddition = "";
            if (previousConfirmation != ConfirmationFlagType.NONE)
                confirmationAddition = "<font color='" + Consts.log_confirm_pink + "'>Previous action "
                        + (confirmed ? "confirmed" : "canceled") + "!</font> ";

            // We use html formatting for the differently colored sections
            logger.setText("<html>" + currentTimestamp + confirmationAddition + message + "</html>");
        } else
            System.out.println("ERROR: LoggingSystem reference unset!");
    }

    public void finishAwaitingConfirmation(ConfirmationFlagType flag) {
        // Can't use a message method as it will show "Previous action canceled"
        setLog(Utils.getFinishedConfirmationMessage(flag), true);
    }

    public void setAwaitingConfirmationFlag(ConfirmationFlagType flag) {
        confirm(Utils.getAwaitingConfirmationMessage(flag));
        // Set the flag AFTER it gets reseted in the setLog method
        previousConfirmation = awaitConfirmation;
        awaitConfirmation = flag;
    }

    public ConfirmationFlagType getAwaitingConfirmationFlag() {
        return awaitConfirmation;
    }
}
