import 'package:flutter/material.dart';
import 'package:herbs_flutter/pages/base_layout.dart';
import 'package:herbs_flutter/pages/notifications/notification_card.dart';


// Constant table for notifications
final List<Map<String, dynamic>> NOTIFICATIONS = [
  {
    'id': 1,
    'type': 'article',
    'title': 'New article !!',
    'description': 'DIY Chamomile Face Mask for Glowing Skin',
    'imageUrl': 'assets/camomille.jpg',
    'isRead': false,
    'articleId': 1,
  },
  {
    'id': 2,
    'type': 'recommendation',
    'title': 'New article !!',
    'description': 'Rosemary Hair Growth Serum: A Natural Solution',
    'imageUrl': 'assets/rosemary.jpg',
    'isRead': false,
    'articleId': 2,
  },
  // Add more notifications as needed
];

class NotificationsPage extends StatefulWidget {
  const NotificationsPage({super.key});

  @override
  State<NotificationsPage> createState() => _NotificationsPageState();
}

class _NotificationsPageState extends State<NotificationsPage> {
  List<Map<String, dynamic>> notifications = List.from(NOTIFICATIONS);

  void _markAsRead(int notificationId) {
    setState(() {
      final index = notifications.indexWhere((n) => n['id'] == notificationId);
      if (index != -1) {
        notifications[index] = {
          ...notifications[index],
          'isRead': true,
        };
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return BaseLayout(
      currentIndex: 5,
      child: Column(
        children: [
          // Banner
          Container(
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage('assets/pic_accueil.jpg'),
                fit: BoxFit.cover,
                
              ),
              
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                // Logo
                Padding(
                  padding: const EdgeInsets.all(7.0),
                  child: Image.asset(
                    'assets/logo_plante.png',
                    height: 55,
                  ),
                ),
                // 
                Padding(
                  padding: const EdgeInsets.all(18.0),
                  child: Row(
                    children: [
                      
                      const SizedBox(width: 8),
                    ],
                  ),
                ),
              ],
            ),
          ),
          // Notifications List
          Expanded(
            child: ListView.builder(
              padding: const EdgeInsets.all(16),
              itemCount: notifications.length,
              itemBuilder: (context, index) {
                final notification = notifications[index];
                return NotificationCard(
                  notification: notification,
                  onTap: () {
                    _markAsRead(notification['id']);
                    // Navigate to article
                    /*Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => ArticleDetailPage(
                          articleId: notification['articleId'],
                        ),
                      ),
                    );*/
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

