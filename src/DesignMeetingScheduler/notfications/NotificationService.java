package DesignMeetingScheduler.notfications;

import DesignMeetingScheduler.User;

public interface NotificationService {
    void sendNotification(User user, String message);
}
