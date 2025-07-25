package cutalab.rachael.base.ui.util;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;

public class NotificationUtil {

    public enum Duration {
        FAST(3000),
        NORMAL(5000),
        SLOW(8000);

        private final int millis;

        Duration(int millis) {
            this.millis = millis;
        }

        public int getMillis() {
            return millis;
        }
    }
    
    public enum Type {
        SUCCESS(NotificationVariant.LUMO_SUCCESS),
        WARNING(NotificationVariant.LUMO_CONTRAST),
        ERROR(NotificationVariant.LUMO_ERROR);

        private final NotificationVariant variant;

        Type(NotificationVariant variant) {
            this.variant = variant;
        }

        public NotificationVariant getVariant() {
            return variant;
        }
    }

    public static void show(String message, Duration duration, Type type) {
        Notification note = new Notification(message);
        note.setDuration(duration.getMillis());
        note.addThemeVariants(type.getVariant());
        note.setPosition(Position.TOP_CENTER);
        note.open();
    }
    
}