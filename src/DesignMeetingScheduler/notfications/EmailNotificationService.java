package DesignMeetingScheduler.notfications;

import DesignMeetingScheduler.User;

public class EmailNotificationService implements NotificationService{
    @Override
    public void sendNotification(User user, String message) {
        System.out.println("Email Recipient[" + user.getEmail() + "]: " + message);
    }
}
