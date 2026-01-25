package DesignMeetingScheduler.notfications;

import DesignMeetingScheduler.User;

public class SMSNotificationService implements NotificationService{
    @Override
    public void sendNotification(User user, String message) {
        System.out.println("SMS Recipient[" + user.getUserId() + "]: " + message);
    }
}
