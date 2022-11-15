package utils;

import java.awt.Color;

import javax.swing.JLabel;

public class LoggingService {
    public static class Log {
        public static void Info(String info) {
            JLabel log = getLogger();
            if (log != null) {
                log.setText("SYSTEM_INFO: " + info);
                log.setForeground(Color.WHITE);
                log.repaint();
            }
        }

        public static void Error(String err) {
            JLabel log = getLogger();
            if (log != null) {
                log.setText("ERROR: " + err);
                log.setForeground(Color.RED);
                log.repaint();
            }
        }

        private static JLabel getLogger() {
            return ContentService.logRef;
        }
    }
}
